package cn.zkh.scheduler;

import java.util.Date;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * 调度程序
 *
 * @author likole
 */
public class Scheduler {

    /**
     * 调度间隔
     */
    private static final int DELAY_TIME = 1;

    /**
     * the counter to generate next pid
     */
    private int processCount = 0;

    /**
     * 给界面使用的回调接口
     */
    private ISchedulerActionListener callback;

    /**
     * 充当时间中断
     */
    private Timer timer = new Timer();

    long lastScheduleTime=0;

    boolean reSchedule=false;

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            schedule();
        }
    };

    private Queue<PCB> waitingQueue = new ConcurrentLinkedDeque<>();
    private Queue<PCB> blockingQueue = new ConcurrentLinkedDeque<>();
    private PCB running;
    private Queue<PCB> finishedQueue = new ConcurrentLinkedDeque<>();

    void setActionListener(ISchedulerActionListener actionListener) {
        callback = actionListener;
    }

    /**
     * add a process
     *
     * @param process 进程
     */
    public void addProcess(Process process) {
        PCB pcb = new PCB();
        pcb.setPid(++processCount);
        pcb.setRegister(new Register());
        process.setScheduler(this);
        pcb.setProcess(process);
        pcb.setStatus(Status.READY);
        waitingQueue.add(pcb);
    }

    /**
     * find next available process in waiting queue
     *
     * @return next available process
     */
    private PCB nextProcess() {
        return waitingQueue.peek();
    }

    private void downCpu(PCB pcb) {
        //换下cpu
//        pcb.getProcess().stop();
        //等待程序换下cpu,不懂为啥暂时用不了
//        while (!pcb.getProcess().stopped);
        //add to waiting queue;
        pcb.setStatus(Status.READY);
        waitingQueue.add(pcb);
        //保存运行现场
        Register register = pcb.getRegister();
        register.setAx(CPU.i);
        register.setPc(CPU.address);
        //输出信息
        System.out.println("已将进程" + pcb.getPid() + "换下cpu");
        if (callback != null) {
            callback.downCpu(pcb.getPid());
        }
    }

    private void upCpu(PCB pcb) {
        //remove from waiting queue
        waitingQueue.poll();
        pcb.setStatus(Status.EXECUTION);
        running = pcb;
        //恢复运行线程
        Register register = pcb.getRegister();
        CPU.i = register.getAx();
        CPU.address = register.getPc();
        //输出信息
        System.out.println("已将进程" + pcb.getPid() + "换上cpu");
        if (callback != null) {
            callback.upCpu(pcb.getPid());
        }
        //准备运行
//        pcb.getProcess().preRun();
        lastScheduleTime=new Date().getTime();
        reSchedule=false;
        //运行
        //暂时不开线程运行，以避免线程同步问题
//        new Thread(() -> pcb.getProcess().run()).start();
        pcb.getProcess().run();
        //单线程时要在此处再次调度
        schedule();
    }

    private void schedule() {
        PCB next = nextProcess();
        //if there is no available process,return
        if (next == null && running == null) {
            System.out.println("没有进程可以运行了");
//            timer.cancel();
            lastScheduleTime=Long.MAX_VALUE;
            if (callback != null) {
                callback.finish();
            }
        }
        //having process in waiting queue
        if (next != null) {
            //put running process into waiting queue
            if (running != null) {
                System.out.println("目前有进程运行");
                downCpu(running);
            }
            upCpu(next);
        }
    }

    /**
     * 结束运行当期进程
     */
     void finish() {
        System.out.println("进程" + running.getPid() + "已经运行完毕,加入结束队列");
        //移至结束队列
        running.setStatus(Status.FINISH);
        finishedQueue.add(running);
        running = null;
//        //单线程
//         schedule();
        //todo:线程同步有点问题，暂不重新调度
//        try {
//            timer.cancel();
//        } catch (Exception e) {
//
//        }
//        timer.scheduleAtFixedRate(timerTask, 0, DELAY_TIME);
    }

    /**
     * 阻塞
     *
     */
    void block(Queue<PCB> queue) {
        if(queue==null){
            queue=blockingQueue;
        }
        System.out.println("进程" + running.getPid() + "被阻塞，进入阻塞队列");
        //保存运行现场
        Register register = running.getRegister();
        register.setAx(CPU.i);
        register.setPc(CPU.address);
        running.setStatus(Status.WAITING);
        queue.add(running);
        running = null;
        //todo:重新调度
//        //单线程
//        schedule();
    }

    /**
     * 唤醒
     *
     */
    void wakeup(Queue<PCB> queue) {
        if(queue==null){
            queue=blockingQueue;
        }
        PCB pcb = queue.poll();
        if (pcb == null) {
            System.out.println("无进程可唤醒");
        } else {
            pcb.setStatus(Status.READY);
            waitingQueue.add(pcb);
            System.out.println("进程" + pcb.getPid() + "被唤醒");
        }

    }

    public void start() {
        new Thread(() -> {
            while (true){
                if (new Date().getTime()-lastScheduleTime>=DELAY_TIME&& !reSchedule){
                    reSchedule=true;
                }
            }
        }).start();
        schedule();
//        timer.scheduleAtFixedRate(timerTask, 0, DELAY_TIME);
    }
}
