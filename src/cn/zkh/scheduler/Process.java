package cn.zkh.scheduler;

/**
 * 一个进程
 * @author likole
 * @date 11/25/18
 */
public abstract class Process {

    private Scheduler scheduler;

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 进程运行完毕
     */
    protected void finish(){
        scheduler.finish();
    }

    /**
     * 停止运行
     */
    abstract public void stop();

    /**
     * 程序运行代码
     */
    abstract public void run();
}
