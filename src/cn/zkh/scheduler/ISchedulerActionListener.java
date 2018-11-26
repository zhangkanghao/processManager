package cn.zkh.scheduler;

/**
 * 给界面使用的监听器
 * @author likole
 * @date 11/25/18
 */
public interface ISchedulerActionListener {

    /**
     * 有进程被换下cpu
     * @param pid 进程编号
     */
    void downCpu(int pid);

    /**
     * 有进程被换上cpu
     * @param pid 进程编号
     */
    void upCpu(int pid);


    /**
     * 无进程可调度
     */
    void finish();
}
