package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.gouwuche2.R;

import java.util.List;

import bean.Bean;

/**
 * Created by asus on 2017/10/25.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<Bean.StoriesBean> list;
    private Context context;

    public NewsAdapter(List<Bean.StoriesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.fl_item,null);
        MyViewHolder mh=new MyViewHolder(view);
        return mh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         holder.tv.setText(list.get(position).getTitle());
        for (int i = 0; i <list.size() ; i++) {
            List<String> images = list.get(i).getImages();
            for (int j = 0; j <images.size() ; j++) {
                String s = images.get(j);
                Glide.with(context).load(s).into(holder.img);
            }
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
            img=itemView.findViewById(R.id.img);
        }
    }
}
