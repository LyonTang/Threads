package top.parkzepto.thread.domain;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-01-10 10:02
 * @Version 1.0
 */
public class TaskResults {
    public Boolean washed;
    public Boolean watered;

    public TaskResults(Boolean washed, Boolean watered){
        this.washed = washed;
        this.watered = watered;
    }
}
