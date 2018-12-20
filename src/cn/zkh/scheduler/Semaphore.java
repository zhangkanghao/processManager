package cn.zkh.scheduler;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author likole
 */
public class Semaphore {

    /**
     * 信号量的值
     */
    private int value;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 阻塞队列
     */
    private Queue<PCB> blockingQueue=new ConcurrentLinkedDeque<>();

    public Semaphore(int value) {
        this.value=value;
    }

    public Queue<PCB> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(Queue<PCB> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
