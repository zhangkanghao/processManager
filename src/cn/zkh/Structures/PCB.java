package cn.zkh.Structures;

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

    public PCB(int id) {
        this.id = id;
        statu=Status.READY;
        nextwp=-1;
    }
}
