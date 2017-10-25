package fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.gouwuche2.R;
import com.google.gson.Gson;

import java.util.List;

import adapter.NewswwAdapter;
import bean.News;
import common.Api;
import presenter.Presenter;

/**
 * Created by asus on 2017/10/24.
 */

public class Fragment4 extends Fragment implements view.View {
    private View view;
    private RecyclerView rv;
    private Presenter pp;
    private NewswwAdapter na;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fp_item,null);
        pp=new Presenter(this);
        pp.news(Api.URLL);
        initview();
        return view;
    }

    private void initview() {
        rv=view.findViewById(R.id.rv);
    }

    @Override
    public void Success(String result) {
         try{
             Gson gson=new Gson();
             News news = gson.fromJson(result, News.class);
             final List<News.OthersBean> others = news.getOthers();
             getActivity().runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     na=new NewswwAdapter(others,getActivity());
                     rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
                     rv.setAdapter(na);
                 }
             });
         }catch (Exception e){
             e.printStackTrace();
         }
    }

    @Override
    public void Fail(String code, String msg) {

    }

    @Override
    public void Error(String code, String msg) {

    }
}
