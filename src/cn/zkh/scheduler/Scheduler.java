package cn.zkh.scheduler;

import java.sql.Time;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * 调度程序
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

    /**
     * 充当时间中断
     */
    private Timer timer=new Timer();

    /**
     * 定时器执行的任务
     */
    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            schedule();
        }
    };

    private Queue<PCB> waitingQueue=new ConcurrentLinkedDeque<>();
    private Queue<PCB> blockingQueue=new ConcurrentLinkedDeque<>();
    private PCB running;
    private Queue<PCB> finishedQueue=new ConcurrentLinkedDeque<>();

    void setActionListener(ISchedulerActionListener actionListener){
        callback=actionListener;
    }

    /**
     * add a process
     * @param process 进程
     */
    public void addProcess(Process process){
        PCB pcb =new PCB();
        pcb.setPid(++processCount);
        pcb.setRegister(new Register());
        process.setScheduler(this);
        pcb.setProcess(process);
        pcb.setStatus(Status.READY);
        waitingQueue.add(pcb);
    }

    /**
     * find next available process in waiting queue
     * @return next available process
     */
    private PCB nextProcess(){
        return waitingQueue.peek();
    }

    private void downCpu(PCB pcb){
        //换下cpu
        pcb.getProcess().stop();
        //等待程序换下cpu
        while (!pcb.getProcess().isStopped());
        //add to waiting queue;
        running.setStatus(Status.READY);
        waitingQueue.add(running);
        //保存运行现场
        Register register=pcb.getRegister();
        register.setAx(CPU.i);
        register.setPc(CPU.address);
    }

    private void upCpu(PCB pcb){
        //remove from waiting queue
        waitingQueue.poll();
        pcb.setStatus(Status.EXCUTION);
        running=pcb;
        //恢复运行线程
        Register register=pcb.getRegister();
        CPU.i=register.getAx();
        CPU.address=register.getPc();
        //运行
        pcb.getProcess().preRun();
        new Thread(() -> pcb.getProcess().run()).start();
    }

    private void schedule(){
        PCB next=nextProcess();
        //if there is no available process,return
        if(next==null&&running==null){
            System.out.println("没有进程可以运行了");
            if(callback!=null){
                callback.finish();
            }
        }
        //having process in waiting queue
        if(next!=null){
            //put running process into waiting queue
            if(running!=null){
                System.out.println("目前有进程运行");
                if(callback!=null){
                    callback.downCpu(running.getPid());
                }
                downCpu(running);
                System.out.println("已将进程"+running.getPid()+"换下cpu");
            }
            if(callback!=null){
                callback.upCpu(next.getPid());
            }
            upCpu(next);
            System.out.println("已将进程"+next.getPid()+"换上cpu");
        }
    }

    /**
     * 结束运行当期进程
     */
    public void finish(){
        System.out.println("进程"+running.getPid()+"已经运行完毕,加入结束队列,并进行调度");
        //移至结束队列
        running.setStatus(Status.FINISH);
        finishedQueue.add(running);
        running=null;
        //重新调度
        try {
            timer.cancel();
        }catch (Exception e){
            //
        }
        schedule();
        try {
            timer.scheduleAtFixedRate(timerTask, 0, 2);
        }catch (Exception e){
            //
        }
    }

    public void start() {
        timer.scheduleAtFixedRate(timerTask, 0, 1);
    }
}
