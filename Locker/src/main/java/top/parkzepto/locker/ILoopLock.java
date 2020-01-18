package top.parkzepto.locker;

public interface ILoopLock {
    /**
     * 加锁
     */
    void lock();

    /**
     * 解锁
     */
    void unlock();
}
