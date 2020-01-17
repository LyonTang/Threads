package top.parkzepto.thread.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import top.parkzepto.thread.Tasks;
import top.parkzepto.thread.domain.TaskResults;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description Guava的异步回调
 * @Author 唐亮
 * @Date 2020-01-10 10:30
 * @Version 1.0
 */
public class GExecutor {
    public static ExecutorService jPool = Executors.newFixedThreadPool(10);
    public static ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);

    public static void main(String[] args) {
        final TaskResults results = new TaskResults(false, false);
        Tasks.ReadTask readTask = new Tasks.ReadTask(results);
        Tasks.WashClothesTask washClothesTask = new Tasks.WashClothesTask();
        Tasks.HotWaterTask hotWaterTask = new Tasks.HotWaterTask();

        jPool.execute(readTask);
        ListenableFuture<Boolean> washFuture =  gPool.submit(washClothesTask);
        ListenableFuture<Boolean> waterFuture =  gPool.submit(hotWaterTask);

        System.out.println(Thread.activeCount());

        // 传一个Executor的目的是什么
        // 通过静态方法添加异步回调，成功返回进入onSuccess，异常进入onFailure
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            public void onSuccess(@Nullable Boolean aBoolean) {
                results.washed = aBoolean;
                System.out.println(Thread.activeCount());
            }

            public void onFailure(Throwable throwable) {
                System.out.println("Error when washing!");
            }
        }, jPool);

        Futures.addCallback(waterFuture, new FutureCallback<Boolean>() {
            public void onSuccess(@Nullable Boolean aBoolean) {
                results.watered = aBoolean;
            }

            public void onFailure(Throwable throwable) {
                System.out.println("Error when watering!");
            }
        }, jPool);
    }
}
