package cn.zkh.scheduler;

import java.util.TreeMap;

import static cn.zkh.Main.Pprint;
import static cn.zkh.Main.Vprint;

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
    @Deprecated
    protected boolean stop;

    /**
     * 已经收到停止信号
     */
    @Deprecated
    boolean stopped;


    /**
     * 由调度程序设置
     */
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
    @Deprecated
    void stop(){
        stop=true;
    }

    /**
     * 结束运行
     */
    protected boolean stopped(){
//        if(stop){
//            stopped=true;
//            return true;
//        }
//        return false;
        return scheduler.reSchedule;
    }

    /**
     * 程序运行代码
     */
    abstract public void run();

    /**
     * 运行前指定的函数，清空标志
     */
    @Deprecated
    void preRun(){
//        stopped=false;
//        stop=false;
        scheduler.reSchedule=false;
    }

    protected boolean P(Semaphore semaphore){
        Pprint(semaphore.getName());
        System.out.println("==========================P");
        semaphore.setValue(semaphore.getValue()-1);
        if(semaphore.getValue()>=0){
            return false;
        }
        scheduler.block(semaphore.getBlockingQueue(),semaphore.getName());
        return true;
    }

    protected void V(Semaphore semaphore){
        Vprint(semaphore.getName());
        System.out.println("=========================V");
        semaphore.setValue(semaphore.getValue()+1);
        if(semaphore.getValue()<=0){
            scheduler.wakeup(semaphore.getBlockingQueue(),semaphore.getName());
        }
    }
}
