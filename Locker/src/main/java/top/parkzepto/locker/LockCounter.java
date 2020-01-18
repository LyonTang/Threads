package top.parkzepto.locker;

import top.parkzepto.locker.ticket.TicketLock;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-17 09:52
 * @Version 1.0
 */
public class LockCounter {

    int count = 0;
    ILoopLock lock;

    public LockCounter(ILoopLock lock){
        this.lock = lock;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        lock.lock();
        this.count = count;
        lock.unlock();
    }

    public void add(){
        lock.lock();
        count += 1;
        lock.unlock();
    }

    public void dec(){
        lock.lock();
        count -= 1;
        lock.unlock();
    }
}