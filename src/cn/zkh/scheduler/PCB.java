package cn.zkh.scheduler;

/**
 * 进程控制块
 * @author likole
 */
public class PCB {

    /**
     * 进程号
     */
    private int pid;

    /**
     * 进程目前的运行状态
     */
    private Status status;

    /**
     * 处理机现场
     */
    private Register register;

    /**
     * 程序
     */
    private IProcess process;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public IProcess getProcess() {
        return process;
    }

    public void setProcess(IProcess process) {
        this.process = process;
    }
}
