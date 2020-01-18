package top.parkzepto.locker;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-18 12:05
 * @Version 1.0
 */
public class RunThread {
    private LockCounter counter;

    public RunThread(ILoopLock locker){
        counter = new LockCounter(locker);
    }

    public void run() throws InterruptedException {
        long startTime=System.nanoTime();

        Producer producer = new Producer(counter);
        Consumer consumer = new Consumer(counter);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println(counter.getCount());

        long endTime=System.nanoTime();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
    }
}
