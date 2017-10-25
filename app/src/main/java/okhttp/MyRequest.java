package okhttp;

import android.content.Context;

import java.io.IOException;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by asus on 2017/10/24.
 */

public class MyRequest {
     private Context context;
     private static MyRequest intsal;
     private   MyRequest (Context context){
          this.context=context;

     }
     public MyRequest getIntsal(Context context) {
          if (intsal == null) {
               synchronized (MyRequest.class) {
                    if (intsal == null) {
                         intsal = new MyRequest(context);
                    }
               }
          }
          return  intsal;
     }
     public static void call(String url, Map<String,String> parmaps, final Callback callback){
          OkHttpClient.Builder ob=new OkHttpClient.Builder();
          OkHttpClient client=ob.build();
          FormBody.Builder fb=new FormBody.Builder();
          for (Map.Entry<String, String> stringStringEntry : parmaps.entrySet()) {
//            builder.add("mobile",mobile);
//            builder.add("password",pwd);
               fb.add(stringStringEntry.getKey(), stringStringEntry.getValue());
          }
          RequestBody body=fb.build();
          Request request=new Request.Builder().url(url).post(body).build();
          client.newCall(request).enqueue(new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {
                   callback.onFailure(call,e);
               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {
                   callback.onResponse(call,response);
               }
          });

     }
     public static void ca(String url, final Callback callback){
          OkHttpClient.Builder ob=new OkHttpClient.Builder();
          OkHttpClient client=ob.build();
          Request request=new Request.Builder().url(url).get().build();
          client.newCall(request).enqueue(new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {
                    callback.onFailure(call,e);
               }
               @Override
               public void onResponse(Call call, Response response) throws IOException {
                    callback.onResponse(call,response);
               }
          });
     }

}
