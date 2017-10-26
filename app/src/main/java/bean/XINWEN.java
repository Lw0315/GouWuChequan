package bean;

import java.util.List;

/**
 * Created by asus on 2017/10/25.
 */

public class XINWEN {
    public boolean select;
    public String shangjia;
    public List<Shangpin> List;

    public XINWEN(boolean select, String shangjia, java.util.List<Shangpin> list) {
        this.select = select;
        this.shangjia = shangjia;
        List = list;
    }

    public XINWEN() {
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getShangjia() {
        return shangjia;
    }

    public void setShangjia(String shangjia) {
        this.shangjia = shangjia;
    }

    public java.util.List<Shangpin> getList() {
        return List;
    }

    public void setList(java.util.List<Shangpin> list) {
        List = list;
    }
}
