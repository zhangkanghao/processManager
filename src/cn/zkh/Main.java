package cn.zkh;

import cn.zkh.Structures.PCB;
import cn.zkh.Structures.Saver;
import cn.zkh.Structures.Semaphore;

public class Main {

    /**
     * 全局变量
     */
    public static int res1=50;

    public static int res2=100;

    public static Scheduler scheduler;

    public static Semaphore[] semaphores=new Semaphore[2];

    public static Saver[] savers=new Saver[3];

    public char addr;




    //初始化函数
    private static void initial(){
        //调度程序
        scheduler=new Scheduler();

        //设置互斥信号量
        for(int i=0;i<2;i++){
            semaphores[i]=new Semaphore();
        }
    }

    public static void main(String[] args) {
        int processChoosed;

        /**
         * 初始化
         */
        initial();
        System.out.println("\tprocess management starting\t");
        System.out.println("resource1="+res1+"\t resource2="+res2);
        System.out.println("process1,2,3 are all in ready.");
        while(true){
            if((processChoosed=scheduler.schedule())!=-1){
                switch (processChoosed){
                    case 0:break;
                    case 1:break;
                    case 2:break;
                    default:
                        System.out.println("something errors happen in schdule()");
                        break;
                }
            }else break;
        }
        System.out.println("resource1="+res1+"\t resource2="+res2);
        System.out.println("\tprocess management finish\t");
    }
}
