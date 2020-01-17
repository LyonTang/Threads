package top.parkzepto.thread;

import top.parkzepto.thread.domain.TaskResults;

import java.util.concurrent.Callable;

/**
 * @Description 一些示例任务
 * @Author 唐亮
 * @Date 2020-01-10 09:30
 * @Version 1.0
 */
public class Tasks {
    public static int WASH = 3 * 1000;
    public static int WATER = 3 * 1000;
    /**
     * 洗衣服进程
     */
    public static class WashClothesTask implements Callable<Boolean>{

        public Boolean call() throws Exception {
            System.out.println("Wash:"+Thread.currentThread().getName());
            Thread.sleep(WASH);
            System.out.println("Clothes were washed!");
            return true;
        }
    }

    /**
     * 烧水进程
     */
    public static class HotWaterTask implements Callable<Boolean> {

        public Boolean call() throws Exception {
            System.out.println("Water:"+Thread.currentThread().getName());
            Thread.sleep(WATER);
            System.out.println("Water is ready!");
            return true;
        }
    }

    /**
     * 读书进程
     */
    public static class ReadTask implements Runnable{
        private TaskResults results;

        public ReadTask(TaskResults results){
            this.results = results;
        }

        public void run() {
            System.out.println("Read:" + Thread.currentThread().getName());
            try {
                while (!Thread.interrupted()){
                    System.out.println("Reading!");
                    Thread.sleep(2000);
                    if (results.watered) {
                        System.out.println("Can make tea!");
                    }
                    if(results.washed) {
                        System.out.println("Can hang clothes out!");
                    }
                    if(results.washed && results.watered){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
