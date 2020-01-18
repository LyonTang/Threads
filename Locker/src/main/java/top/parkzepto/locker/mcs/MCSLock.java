package top.parkzepto.locker.mcs;

import top.parkzepto.locker.ILoopLock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Description 基于链表
 * @Author 唐亮
 * @Date 2020-01-18 13:40
 * @Version 1.0
 */
public class MCSLock implements ILoopLock {
    public static class MCSNode {
        public volatile MCSNode next;
        public volatile boolean locked = true;
    }

    private volatile MCSNode queue; // 这个依旧代表着尾节点
    private static final ThreadLocal<MCSNode> NODE_CACHE = new ThreadLocal<MCSNode>();
    private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER
            = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class, MCSNode.class, "queue");

    public void lock() {
        MCSNode node = new MCSNode();
        NODE_CACHE.set(node);
        MCSNode preNode = UPDATER.getAndSet(this, node);
        if(preNode != null) {
            preNode.next = node;
            // 自旋等待前驱节点通知锁的释放
            while (node.locked) {
                // loop
            }
        }
    }

    public void unlock() {
        MCSNode node = NODE_CACHE.get();
        MCSNode nextNode = node.next;
        if(nextNode != null) {
            // 通知下一节点锁被释放
            nextNode.locked = false;
            // 保证node被回收掉？
            node.next = null;
        }else {
            // 在更新链表尾节点时，如果其他线程正好开始获取锁，尾节点已被更新，那么就等待node.next被赋值
            if(!UPDATER.compareAndSet(this, node, null)){
                // 会不会因为下一执行线程意外退出导致卡死呢？
                while (node.next == null){
                    // loop
                }
                node.next.locked = false;
                node.next = null;
            }

        }
    }
}
