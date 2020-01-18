package top.parkzepto.locker.mcs;

import top.parkzepto.locker.ILoopLock;
import top.parkzepto.locker.RunThread;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-18 13:47
 * @Version 1.0
 */
public class Task {
    public static void main(String[] args) throws InterruptedException {
        ILoopLock locker = new MCSLock();
        new RunThread(locker).run();
    }
}
