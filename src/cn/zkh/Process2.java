package cn.zkh;

import cn.zkh.scheduler.CPU;
import cn.zkh.scheduler.Process;

import static cn.zkh.Main.semaphores;


/**
 * Created by likole on 11/26/18.
 */
public class Process2 extends Process {
    @Override
    public void run(){
        while (CPU.i<6){
            switch (CPU.address){
                case 0:
                    System.out.println("process3 calls P on semaphore2\n");

                    CPU.address=1;
                    if(P(semaphores[1]))return;
                case 1:
                    System.out.println("process3 is executing on the cretical section \n");
                    CPU.address=2;
                    if(stopped()) return;

                case 2:
                    Main.res2++;
                    System.out.println("s1="+Main.res2+"\n");
                    System.out.println("process3 calls V on semaphore2 and quit cretical section.\n");
                    CPU.address=3;
                    V(semaphores[1]);
                    if(stopped()) return;
                case 3:
                    System.out.println("process3 cycle epoch="+CPU.i+"\n");
                    CPU.address=0;
                    if(stopped())return;
            }
            CPU.i++;
        }
        finish();
    }


}
