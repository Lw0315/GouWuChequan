package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.gouwuche2.GlideImage;
import com.example.asus.gouwuche2.R;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import adapter.NewsAdapter;
import bean.Bean;
import common.Api;
import presenter.Presenter;

/**
 * Created by asus on 2017/10/24.
 */

public class Fragment1 extends Fragment implements view.View{
    private View view;
    private Banner banner;
    private RecyclerView rv;
    private List<String> list;
    private Presenter pp;
    private NewsAdapter na;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment,null);
        pp=new Presenter(this);
        pp.news(Api.URL);
        initview();
        initdata();
        return view;
    }

    private void initdata() {
        list=new ArrayList<>();
    }

    private void initview() {
        banner=view.findViewById(R.id.banner);
        rv=view.findViewById(R.id.rv);
        banner.setImageLoader(new GlideImage());

    }

    @Override
    public void Success(String result) {
        try{
            Gson gson=new Gson();
            list.clear();
            Bean bean = gson.fromJson(result, Bean.class);
            final List<Bean.StoriesBean> stories = bean.getStories();
            List<Bean.TopStoriesBean> top_stories = bean.getTop_stories();
            for (Bean.TopStoriesBean top_story : top_stories) {
                final String image = top_story.getImage();
                list.add(image);
                System.out.println("list"+list.toString());
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    banner.setImages(list);
                    banner.start();
                    na=new NewsAdapter(stories,getActivity());
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
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
