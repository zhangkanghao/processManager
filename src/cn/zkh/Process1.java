package cn.zkh;

import cn.zkh.scheduler.CPU;
import cn.zkh.scheduler.Process;

import static cn.zkh.Main.addr;

/**
 * Created by likole on 11/26/18.
 */
public class Process1 extends Process {

    @Override
    public void run() {
        while(CPU.i<6){
            System.out.println("process1  calls P on the semaphore 1\n");
//            if(P(0,'a'))  return;
            switch (addr){
                case 0:System.out.println("process1 is executing in the cretical section 1\n");
                CPU.address=1;
                    if(stop) return;
                case 1:
                    Main.res1++;
                    System.out.println("s1="+Main.res1+"\n");
                    System.out.println("process1 calls V on semaphore1 and quit cretical section 1.\n");
//                    if(V(0,'c'))return;
                    CPU.address=2;
                    if(stop) return;
                case 2:
                    for (int i = 0; i <100 ; i++) {
                        Main.res1++;
                        Main.res1--;
                    }
                    System.out.println("process1 calls P on semaphore 2.\n");
//                    if(P(1,'d'))return;
                    CPU.address=3;
                    if(stop) return;
                case 3:
                    System.out.println("process1 is executing cretical section 2.\n");
                    CPU.address=4;
                    if(stop) return;
                case 4:
                    Main.res2++;
                    System.out.println("s2="+Main.res2+"\n");
                    System.out.println("process1 calls V on semaphore2 and quit cretical section 2.\n");
//                    if(V(1,'f'))return;
                    CPU.address=5;
                    if(stop) return;
                case 5:
                    System.out.println("process1 cycle epoch="+CPU.i+"\n");
            }
            CPU.i++;
        }
        finish();
    }
}
