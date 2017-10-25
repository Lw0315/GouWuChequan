package presenter;

import model.Model;
import view.View;

/**
 * Created by asus on 2017/10/25.
 */

public class Presenter implements Model.MM {
    private Model model;
    private View view;
    public Presenter(View view) {
        this.view = view;
        model=new Model();
        model.setMm(this);
    }
     public void news(String url){
         model.news(url);
     }

    @Override
    public void Success(String result) {
        view.Success(result);
    }

    @Override
    public void Fail(String code, String msg) {

    }

    @Override
    public void Error(String code, String msg) {

    }
}
