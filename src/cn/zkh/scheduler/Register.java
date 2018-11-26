package cn.zkh.scheduler;

/**
 * 寄存器
 * @author likole
 * @date 11/26/18
 */
public class Register {
    private int ax;
    private int bx;
    private int pc;

    public int getAx() {
        return ax;
    }

    public void setAx(int ax) {
        this.ax = ax;
    }

    public int getBx() {
        return bx;
    }

    public void setBx(int bx) {
        this.bx = bx;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }
}
