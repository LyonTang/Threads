package top.parkzepto.locker.ticket;

import top.parkzepto.locker.ILoopLock;
import top.parkzepto.locker.RunThread;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-17 09:50
 * @Version 1.0
 */



public class Task {

    public static void main(String[] args) throws InterruptedException {

        ILoopLock locker = new TicketLock();
        new RunThread(locker).run();
    }
}