package top.parkzepto.locker;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-17 09:52
 * @Version 1.0
 */

public class Consumer extends Thread{

    LockCounter counter;

    public Consumer(LockCounter counter){
        this.counter = counter;
    }


    @Override
    public void run() {
        for(int j = 0; j < Consts.LOOP; j++){
            counter.dec();
        }
    }
}
