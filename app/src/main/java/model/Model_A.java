package model;

import java.io.IOException;

import bean.News;
import bean.Shop;
import okhttp.MyHttpCallback;
import okhttp.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class Model_A {
    private SS ss;
    public interface  SS{
        void Success(Shop shop);
    }
    public void setSs(SS ss) {
        this.ss = ss;
    }
    public void news(String url) {
        OkHttpUtils.getInstace().doGet(url, new MyHttpCallback<Shop>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onSuccess(Response response, Shop shop) throws IOException {
                 ss.Success(shop);
            }

            @Override
            public void onError(Response response, String errorMsg) throws IOException {

            }
        });
    }
}