package cn.zkh.scheduler;

/**
 * 一个进程
 * @author likole
 * @date 11/25/18
 */
public abstract class Process {

    /**
     * 保存了调度程序的引用，可以告知调度程序当前进程已经运行完毕
     */
    private Scheduler scheduler;

    /**
     * 停止信号
     */
    protected boolean stop;

    /**
     * 已经收到停止信号
     */
    private boolean stopped;


    void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 进程运行完毕，加入结束队列并调度
     */
    protected void finish(){
        scheduler.finish();
    }

    /**
     * 停止运行
     */
    protected void stop(){
        stop=true;
    }

    /**
     * 结束运行
     */
    protected void stopped(){
        stopped=true;
    }

    /**
     * 是否已经停止运行
     * @return
     */
    public boolean isStopped() {
        return stopped;
    }

    /**
     * 程序运行代码
     */
    abstract public void run();

    /**
     * 运行前指定的函数，清空标志
     */
    void preRun(){
        stopped=false;
        stop=false;
    }
}
