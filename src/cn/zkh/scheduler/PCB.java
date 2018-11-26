package cn.zkh.scheduler;

import cn.zkh.IProcess;
import cn.zkh.Status;

/**
 * @author likole
 */
public class ProcessControlBlock {

    /**
     * 进程号
     */
    private int pid;

    /**
     * 进程目前的运行状态
     */
    private Status status;

    private Register register;

    private IProcess process;

    public IProcess getProcess() {
        return process;
    }

    public void setProcess(IProcess process) {
        this.process = process;
    }

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

    public int getNextwp() {
        return nextwp;
    }

    public void setNextwp(int nextwp) {
        this.nextwp = nextwp;
    }

    public int getEpoch() {
        return epoch;
    }

    public void setEpoch(int epoch) {
        this.epoch = epoch;
    }

    public char getAddr() {
        return addr;
    }

    public void setAddr(char addr) {
        this.addr = addr;
    }

    public ProcessControlBlock() {

    }

    public ProcessControlBlock(int id) {
        this.pid = id;
        status =Status.READY;
        nextwp=-1;
    }
}
