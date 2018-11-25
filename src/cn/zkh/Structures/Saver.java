package cn.zkh.Structures;

/**
 * @author kanghao
 * @date 18-11-25 下午2:10
 */
public class Saver {

    //进程号
    private int id;

    //断点地址
    private String cutAddr;

    //寄存器内容
    private String regContent;

    //...


    public Saver(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCutAddr() {
        return cutAddr;
    }

    public void setCutAddr(String cutAddr) {
        this.cutAddr = cutAddr;
    }

    public String getRegContent() {
        return regContent;
    }

    public void setRegContent(String regContent) {
        this.regContent = regContent;
    }
}