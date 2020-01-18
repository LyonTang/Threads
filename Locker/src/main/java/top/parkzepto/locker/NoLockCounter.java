package top.parkzepto.locker;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-18 23:32
 * @Version 1.0
 */
public class NoLockCounter {
    int count = 0;
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void add(){
        count += 1;
    }

    public void dec(){
        count -= 1;
    }
}
