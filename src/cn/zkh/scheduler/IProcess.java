package cn.zkh.scheduler;

/**
 * 一个进程
 * @author likole
 * @date 11/25/18
 */
public interface IProcess {

    /**
     * 停止运行
     */
    void stop();

    /**
     * 程序运行代码
     */
    void run();
}
