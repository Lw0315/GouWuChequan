package presenter;

import bean.Shop;
import model.Model_A;
import view.View_A;

/**
 * Created by asus on 2017/10/25.
 */

public class Presenter_A implements Model_A.SS {
    private Model_A model_a;
    private View_A view_a;

    public Presenter_A(View_A view_a) {
        this.view_a = view_a;
        model_a=new Model_A();
        model_a.setSs(this);
    }
    public void shop(String url){
        model_a.news(url);
    }
    @Override
    public void Success(Shop shop) {
        view_a.Success(shop);
    }
}
