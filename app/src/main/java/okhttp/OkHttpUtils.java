package okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

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
 * Created by J on 2017/10/16.
 */

public class OkHttpUtils {
    private Context context;
    private static OkHttpUtils okHttpUtils;
    private final Gson gson;
    private final OkHttpClient client;
    private final Handler handler;

    private OkHttpUtils() {
        client = new OkHttpClient();
        gson = new Gson();
        //构造一个handler，不管是从哪个哪个线程中发出消息，
        // 消息都会发送到主线程的messagequeue中
        handler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpUtils getInstace() {

        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public void doGet(String url,MyHttpCallback callback){
        Request request= new Request.Builder().url(url).get().build();
        doRequest(request,callback);
    }
    //post方法. username  password
    public void doPost(String url, Map<String ,String> params, MyHttpCallback  callback){
        FormBody.Builder formBuilder=new FormBody.Builder();
        if(params!=null){
            for (Map.Entry<String,String> entry:params.entrySet()) {
                formBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        RequestBody body=formBuilder.build();
        Request.Builder builder =new Request.Builder().url(url).post(body).addHeader("User-Agent", "OkHttp Example");
        Request request = builder.build();
        doRequest(request,callback);
    }



    private void doRequest(Request request, final MyHttpCallback callback) {
        callback.onBeforeRequest(request);
        client.newCall(request).enqueue(new Callback() {
            //这些okhttp的回调方法执行在子线程里面，返回的数据为了给主线程使用，
            // 必须想办法把数据供给主线程使用，所以引用了自定义的回调接口
            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(call,e);
                    }
                });
            }
            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                if(response.isSuccessful()){
                    //返回数据成功的话就解析json串
                    String json=response.body().string();
                    final Object o=gson.fromJson(json,callback.type);//将json解析成对应的bean
                    //handler 除了可以发送Message 也可以发送 runnable对象
                    //这个runnable对象的run方法就执行在 handler对应的线程中 当前的案例中 会执行在主线程中
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //将response返回给主线程
                            try {
                                callback.onSuccess(response,o);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }else{
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callback.onError(response,"服务器返回错误");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });


    }


}
