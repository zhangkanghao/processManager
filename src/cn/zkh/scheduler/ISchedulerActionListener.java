package cn.zkh;

/**
 *
 * @author likole
 * @date 11/25/18
 */
public interface ISchedulerActionListener {

    void downCpu(int pid);
    void upCpu(int pid);
    void finish();
}
