package cn.zkh;

import cn.zkh.scheduler.ISchedulerActionListener;
import cn.zkh.scheduler.Semaphore;
import cn.zkh.scheduler.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    /**
     * 全局变量
     */
    public static int res1=1;
    public static int res2=1;
    public static Scheduler scheduler;
    public static Semaphore[] semaphores=new Semaphore[2];
    public static int register;
    public static char addr;
    public static String res;
    public static String prolist;
    public static int count=3;
    public static boolean flag=true;
    //初始化函数
    public static void Pprint(String name){
        res+="对"+name+"进行了P操作\n";
    }
    public static void Vprint(String name){
        res+="对"+name+"进行了V操作\n";

    }
    public static void initial(){
        prolist="进程1\n进程2\n进程3";
        //调度程序
        scheduler=new Scheduler();
        res="";
        //设置互斥信号量
        for(int i=0;i<2;i++){
            semaphores[i]=new Semaphore(1);
        }
        semaphores[0].setName("S1");
        semaphores[1].setName("S2");
    }

    public static void main(String[] args) {
        int processChoosed;
        /**
         * 初始化
         */
        initial();
        JFrame jf = new JFrame("测试窗口");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        JTextArea textArea=new JTextArea(8,50);
        JButton btn = new JButton("开始");
        JLabel list=new JLabel("进程列表");
        JLabel name=new JLabel("进程名");
        JTextArea plist=new JTextArea(8,50);
        JButton addbtn= new JButton("增加进程");
        JButton pause=new JButton("暂停");
        panel1.add(list);
        panel1.add(plist);
        panel2.add(btn);
        panel2.add(addbtn);
        //panel2.add(pause);

        panel3.add(new JScrollPane(textArea));
        BorderLayout b = new BorderLayout();
        b.setHgap(200);
        plist.setText(prolist);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //textArea.setFont(new Font("黑体",Font.BOLD,15));
//        Vprint();
//        Pprint();
//        textArea.setText(res);
        scheduler.setActionListener(new ISchedulerActionListener() {
            @Override
            public void downCpu(int pid) {
                res+="时间片到，已将进程"+pid+"换下CPU\n";
                textArea.setText(res);
            }

            @Override
            public void finish() {
                if(flag==true){
                res+="没有进程可以运行了\n";
                textArea.setText(res);
                flag=false;
                }
            }

            @Override
            public void upCpu(int pid) {
                res+="已将进程"+pid+"换上CPU\n";
                textArea.setText(res);
            }

            @Override
            public void finishprocess(int pid) {
                res+="进程"+pid+"运行结束已经加入结束队列\n";
            }
            @Override
            public void block(int pid,String des) {
                res+="进程"+pid+"因为缺少"+des+"被阻塞,进入阻塞队列\n";
            }

            @Override
            public void wakeup(int pid,String des) {
                res+="进程"+pid+"从"+des+"的队列上唤醒\n";
            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process1 process1=new Process1();
                Process2 process2=new Process2();
                Process3 process3=new Process3();
                scheduler.addProcess(process1);
                scheduler.addProcess(process2);
                scheduler.addProcess(process3);
                scheduler.start();
            }
        });
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process1 p=new Process1();
                scheduler.addProcess(p);
                count++;
                prolist+="\n"+"进程"+count;
                plist.setText(prolist);
            }
        });
       /* pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
        jf.add(panel1, b.NORTH);
        jf.add(panel3, b.CENTER);
        jf.add(panel2,b.SOUTH);
        jf.setSize(600,400);
        jf.setVisible(true);
//        System.out.println("\tprocess management starting\t");
//        System.out.println("resource1="+res1+"\t resource2="+res2);
//        System.out.println("process1,2,3 are all in ready.");
//        while(true){
//            if((processChoosed=scheduler.schedule())!=-1){
//                switch (processChoosed){
//                    case 0:break;
//                    case 1:break;
//                    case 2:break;
//                    default:
//                        System.out.println("something errors happen in schdule()");
//                        break;
//                }
//            }else break;
//        }
//        System.out.println("resource1="+res1+"\t resource2="+res2);
//        System.out.println("\tprocess management finish\t");

    }


}
