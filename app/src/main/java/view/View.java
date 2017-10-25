package view;

/**
 * Created by asus on 2017/10/25.
 */

public interface View {
    void Success(String result);
    void Fail(String code,String msg);
    void Error(String code,String msg);
}
