package cn.zkh;

import cn.zkh.Structures.PCB;
import cn.zkh.Structures.Saver;
import cn.zkh.Structures.Semaphore;

import java.util.Arrays;

public class Main {

    /**
     * 全局变量
     */
    private static int excuting=-1;

    private static int res1=50;

    private static int res2=100;


    //初始化函数
    private void initial(){
        //初始化进程
        PCB[] process=new PCB[3];
        Saver[] processSaver=new Saver[3];
        for (int i=0;i<3;i++){
            process[i]=new PCB(i);
            processSaver[i]=new Saver(i);
        }

        //设置互斥信号量
        Semaphore semaphore1=new Semaphore();
        Semaphore semaphore2=new Semaphore();
    }


    public static void main(String[] args) {
        
    }
}
