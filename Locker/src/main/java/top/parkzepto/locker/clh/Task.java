package top.parkzepto.locker.clh;

import top.parkzepto.locker.ILoopLock;
import top.parkzepto.locker.RunThread;
import top.parkzepto.locker.ticket.TicketLock;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-18 12:02
 * @Version 1.0
 */
public class Task {

    public static void main(String[] args) throws InterruptedException {

        ILoopLock locker = new CLHLock();
        new RunThread(locker).run();
    }
}
