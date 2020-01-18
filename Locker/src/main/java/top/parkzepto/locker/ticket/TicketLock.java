package top.parkzepto.locker.ticket;

import top.parkzepto.locker.ILoopLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 基于队列
 * @Author 唐亮
 * @Date 2020-01-17 09:48
 * @Version 1.0
 */
public class TicketLock implements ILoopLock {
    // 取号
    AtomicInteger takeQueue = new AtomicInteger();
    // 叫号
    AtomicInteger callQueue = new AtomicInteger(1);
    // 每个线程持有一个自己的号
    ThreadLocal<Integer> ticketCache = new ThreadLocal<Integer>();

    public void lock(){
        // 在取号器拿到当前的号，原子操作
        Integer ticket = takeQueue.incrementAndGet();
        // 线程缓存自己的票据
        ticketCache.set(ticket);
        // 当前票据是否为叫号器所叫号
        while (ticket != callQueue.get()){
            // loop
        }
    }

    public void unlock(){
        // 当前线程解锁，获取其票据，认为事件完成
        int done = ticketCache.get();
        // 叫号器呼叫下一个
        callQueue.compareAndSet(done, done + 1);
    }
}