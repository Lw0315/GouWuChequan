package bean;

/**
 * Created by asus on 2017/10/25.
 */

public class Shangpin {
    public boolean spSelect;
    public String sp;
    public double jiage;
    private int num;

    public Shangpin(boolean spSelect, String sp, double jiage,int num) {
        this.spSelect = spSelect;
        this.sp = sp;
        this.jiage = jiage;
        this.num=num;
    }

    public Shangpin() {
    }

    public boolean isSpSelect() {
        return spSelect;
    }

    public void setSpSelect(boolean spSelect) {
        this.spSelect = spSelect;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public double getJiage() {
        return jiage;
    }

    public void setJiage(double jiage) {
        this.jiage = jiage;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
