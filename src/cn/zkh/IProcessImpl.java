package cn.zkh;

/**
 * @author kanghao
 * @date 18-11-25 下午8:42
 */

import cn.zkh.scheduler.IProcess;

public class IProcessImpl  {

    public void process1(int epoch, char addr) {
//        for(int i=6-epoch;i<6;i++){
//            System.out.println("process1  calls P on the semaphore 1\n");
//            if(P(0,'a'))  return;
//            switch (addr){
//                case 'a':System.out.println("process1 is executing in the cretical section 1\n");
//                    if(timeint('b'))return;
//                case 'b':
//                    Main.res1++;
//                    System.out.println("s1="+Main.res1+"\n");
//                    System.out.println("process1 calls V on semaphore1 and quit cretical section 1.\n");
//                    if(V(0,'c'))return;
//                case 'c':
//                    System.out.println("process1 calls P on semaphore 2.\n");
//                    if(P(1,'d'))return;
//                case 'd':
//                    System.out.println("process1 is executing cretical section 2.\n");
//                    if(timeint('e'))return;
//                case 'e':
//                    Main.res2++;
//                    System.out.println("s2="+Main.res2+"\n");
//                    System.out.println("process1 calls V on semaphore2 and quit cretical section 2.\n");
//                    if(V(1,'f'))return;
//                case 'f':
//                    System.out.println("process1 cycle epoch="+i+"\n");
//            }
//            addr='a';
//        }
//        exit(0);
    }

    public void process2(int epoch, char addr) {
//        for(int i=6-epoch;i<6;i++){
//            System.out.println("process2 calls Pon semephore2\n");
//            if(P(1,'a'))  return;
//            switch (addr){
//                case 'a':System.out.println("process2 is executing on the cretical section2\n");
//                    if(timeint('b'))return;
//                case 'b':
//                    Main.res2++;
//                    System.out.println("s2="+Main.res2+"\n");
//                    System.out.println("process2 calls V on semephore2 and quit cretical section2.\n");
//                    if(V(1,'c'))return;
//                case 'c':
//                    System.out.println("process2 calls P on semaphore1.\n");
//                    if(P(0,'d'))return;
//                case 'd':
//                    System.out.println("process2 is executing cretical section1.\n");
//                    if(timeint('e'))return;
//                case 'e':
//                    Main.res1++;
//                    System.out.println("s1="+Main.res1+"\n");
//                    System.out.println("process2 calls V on semephore1 and quit cretical section1.\n");
//                    if(V(0,'f'))return;
//                case 'f':
//                    System.out.println("process2 cycle epoch="+i+"\n");
//            }
//            addr='a';
//        }
//        exit(1);
    }

    public void process3(int epoch, char addr) {
//        for(int i=6-epoch;i<6;i++){
//            System.out.println("process3 calls P on semaphore2\n");
//            if(P(1,'a'))  return;
//            switch (addr){
//                case 'a':System.out.println("process3 is executing on the cretical section \n");
//                    if(timeint('b'))return;
//                case 'b':
//                    Main.res2++;
//                    System.out.println("s1="+Main.res2+"\n");
//                    System.out.println("process3 calls V on semaphore2 and quit cretical section.\n");
//                    if(V(1,'c'))return;
//                case 'c':
//                    System.out.println("process3 cycle epoch="+i+"\n");
//            }
//        }
//        exit(2);
    }
}
