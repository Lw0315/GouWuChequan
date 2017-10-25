package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.Bean;
import okhttp.MyRequest;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import presenter.Presenter;

/**
 * Created by asus on 2017/10/24.
 */

public class Model {
    private MM mm;
    public interface  MM{
      void Success(String result);
        void Fail(String code,String msg);
        void Error(String code,String msg);
    }
    public void setMm(MM mm) {
        this.mm = mm;
    }
    public void news(String url){
//        Map<String,String> map=new HashMap<>();
//        map.put("url",url);
        MyRequest.ca(url,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String result=response.body().string();
                try{
//                    Gson gson=new Gson();
//                    Bean bean = gson.fromJson(result, Bean.class);
//                    String date = bean.getDate();
                    mm.Success(result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
