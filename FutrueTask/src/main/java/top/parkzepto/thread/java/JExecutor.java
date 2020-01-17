package top.parkzepto.thread.java;

import top.parkzepto.thread.Tasks;
import top.parkzepto.thread.domain.TaskResults;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description java的FutureTask
 * @Author 唐亮
 * @Date 2020-01-10 10:08
 * @Version 1.0
 */
public class JExecutor {
    public static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        TaskResults results = new TaskResults(false, false);
        Tasks.ReadTask readTask = new Tasks.ReadTask(results);
        Tasks.WashClothesTask washClothesTask = new Tasks.WashClothesTask();
        Tasks.HotWaterTask hotWaterTask = new Tasks.HotWaterTask();

        // java FutureTask实现了Future方法，有get方法阻塞获取Callable任务的返回值
        // 他实际上并未达到真正意义上的回调
        FutureTask<Boolean> futureTask1 = new FutureTask<Boolean>(washClothesTask);
        FutureTask<Boolean> futureTask2 = new FutureTask<Boolean>(hotWaterTask);
        pool.execute(readTask);
        pool.execute(futureTask1);
        pool.execute(futureTask2);

        try {
            // 阻塞
            results.watered = futureTask2.get();
            results.washed = futureTask1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
