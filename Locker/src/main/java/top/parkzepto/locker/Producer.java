package top.parkzepto.locker;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-17 09:53
 * @Version 1.0
 */
public class Producer extends Thread{

    LockCounter counter;

    public Producer(LockCounter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 0; i < Consts.LOOP; ++i){
            counter.add();
        }
    }
}
