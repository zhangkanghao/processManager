package cn.zkh;

import cn.zkh.scheduler.CPU;
import cn.zkh.scheduler.Process;

import static cn.zkh.Main.addr;
import static cn.zkh.Main.semaphores;

/**
 * Created by likole on 11/26/18.
 */
public class Process1 extends Process {

    @Override
    public void run() {
        while(CPU.i<6){
            switch (CPU.address){
                case 0:
                    System.out.println("process1  calls P on the semaphore 1\n");
                    CPU.address=1;
                    if(P(semaphores[0]))  return;
                case 1:System.out.println("process1 is executing in the cretical section 1\n");
                CPU.address=2;
                    if(stopped()) return;
                case 2:
                    Main.res1++;
                    System.out.println("s1="+Main.res1+"\n");
                    System.out.println("process1 calls V on semaphore1 and quit cretical section 1.\n");
//                    if(V(0,'c'))return;
                    CPU.address=3;
                    if(stopped()) return;
                case 3:
                    for (int i = 0; i <100 ; i++) {
                        Main.res1++;
                        Main.res1--;
                    }
                    System.out.println("process1 calls P on semaphore 2.\n");
//                    if(P(1,'d'))return;
                    CPU.address=4;
                    if(stopped()) return;
                case 4:
                    System.out.println("process1 is executing cretical section 2.\n");
                    CPU.address=5;
                    if(stopped()) return;
                case 5:
                    Main.res2++;
                    System.out.println("s2="+Main.res2+"\n");
                    System.out.println("process1 calls V on semaphore2 and quit cretical section 2.\n");
                    CPU.address=6;
                    V(semaphores[0]);
                    if(stopped()) return;
                case 6:
                    System.out.println("process1 cycle epoch="+CPU.i+"\n");
            }
            CPU.i++;
        }
        finish();
    }
}
