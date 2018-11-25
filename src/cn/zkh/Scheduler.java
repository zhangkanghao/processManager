package cn.zkh;

import cn.zkh.Structures.PCB;
import sun.java2d.cmm.PCMM;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * @author likole
 */
public class Scheduler {

    /**
     * the counter to generate next pid
     */
    private int processCount=0;

    /**
     * 给界面使用的回调接口
     */
    private ISchedulerActionListener callback;

    void setActionListener(ISchedulerActionListener actionListener){
        callback=actionListener;
    }

    private Queue<PCB> waitingQueue=new ConcurrentLinkedDeque<>();
    Queue<PCB> blockingQueue=new ConcurrentLinkedDeque<>();
    PCB running;

    void start(IProcess process){
        PCB pcb=new PCB();
        pcb.setId(++processCount);
        pcb.setProcess(process);
        pcb.setStatu(Status.READY);
        waitingQueue.add(pcb);
    }



    /**
     * find next available process in waiting queue
     * @return next available process
     */
    private PCB nextProcess(){
        return waitingQueue.peek();
    }

    void schedule(){
        PCB next=nextProcess();
        //if there is no available process,return
        if(next==null&&running==null){
            callback.finish();
            return;
        }
        //having process in waiting queue
        if(next!=null){
            //put running process into waiting queue
            if(running!=null){
                callback.downCpu(running.getId());
                running.setStatu(Status.READY);
                waitingQueue.add(running);
            }
            waitingQueue.remove();
            next.setStatu(Status.EXCUTION);
            running=next;
            callback.upCpu(running.getId());
        }
    }




}
