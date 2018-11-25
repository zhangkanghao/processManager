package cn.zkh.Structures;

import cn.zkh.IProcess;
import cn.zkh.Status;

/**
 * @author kanghao
 * @date 18-11-25 下午1:50
 */
public class PCB {

    //进程号
    private int id;

    //进程状态
    private Status statu;

    //等待链指针：在同一信号量是wait的下一个进程id
    private int nextwp;

    private int epoch;

    private char addr;

    private IProcess process;

    public IProcess getProcess() {
        return process;
    }

    public void setProcess(IProcess process) {
        this.process = process;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatu() {
        return statu;
    }

    public void setStatu(Status statu) {
        this.statu = statu;
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

    public PCB() {

    }

    public PCB(int id) {
        this.id = id;
        statu=Status.READY;
        nextwp=-1;
    }
}
