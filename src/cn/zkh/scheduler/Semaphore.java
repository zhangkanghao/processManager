package cn.zkh.scheduler;

/**
 * @author kanghao
 * @date 18-11-25 下午2:01
 */
public class Semaphore {

    //信号量值
    private int value;

    //等待链指针：该信号量上下一个等待进程的id
    private int nextwp;

    public Semaphore() {
        value=1;

        //没有wait进程
        nextwp=-1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNextwp() {
        return nextwp;
    }

    public void setNextwp(int nextwp) {
        this.nextwp = nextwp;
    }
}
