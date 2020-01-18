package top.parkzepto.locker.clh;

import top.parkzepto.locker.ILoopLock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Description 基于链表
 * @Author 唐亮
 * @Date 2020-01-17 23:22
 * @Version 1.0
 */
public class CLHLock implements ILoopLock {
    public static class CLHNode {
        public volatile boolean locked = true;// 主存
    }

    public volatile CLHNode tail;
    private ThreadLocal<CLHNode> nodeCache = new ThreadLocal<CLHNode>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER
            = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock(){
        // 总是生成一个新节点
        CLHNode node = new CLHNode();
        nodeCache.set(node);
        // 前驱节点一定是当前的tail节点，并将当前节点设置为tail节点
        CLHNode preNode = UPDATER.getAndSet(this, node);
        // 有前驱节点，自旋等待前驱节点释放
        if(preNode != null) {
            while (preNode.locked){
                // loop
            }
        }
    }

    public void unlock(){
        CLHNode node = nodeCache.get();
        // 尾节点如果与当前节点一致，把尾节点置空，否则释放锁定
        UPDATER.compareAndSet(this, node, null);
        node.locked = false;
    }

}
