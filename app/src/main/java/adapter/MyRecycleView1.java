package adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.asus.gouwuche2.R;

import java.util.List;

import bean.Shangpin;
import bean.XINWEN;

/**
 * Created by asus on 2017/10/25.
 */

public class MyRecycleView1 extends RecyclerView.Adapter<MyRecycleView1.MyViewHolder> {
    private List<XINWEN> list;
    private Context context;

    public MyRecycleView1(List<XINWEN> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.fragment_2_item,null);
        MyViewHolder my=new MyViewHolder(view);
        return my;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position).shangjia);
        final List<Shangpin> splist=list.get(position).getList();
        final MyRecycleView2 m2=new MyRecycleView2(context,splist);
        holder.rvv.setLayoutManager(new LinearLayoutManager(context));
        holder.rvv.setAdapter(m2);

        boolean flag=true;
        for (Shangpin shangpin : splist) {
            if(shangpin.isSpSelect()==false)
            {
                flag=false;
            }
        }
        holder.ck.setChecked(flag);

        m2.setGeshu(new MyRecycleView2.Geshu() {
            @Override
            public void gs() {
                boolean flag=true;
                for (Shangpin shangpin : splist) {
                    if(shangpin.isSpSelect()==false)
                    {
                        flag=false;
                    }
                }
                holder.ck.setChecked(flag);
                zong.shangpin();
            }
        });
        holder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Shangpin shangpin : splist) {
                    if(holder.ck.isChecked())
                    {
                        shangpin.setSpSelect(true);
                    }
                    else
                    {
                        shangpin.setSpSelect(false);
                    }
                }
                m2.notifyDataSetChanged();
                zong.shangjia();
                //商家价格接口
                sjjiage.sjjg();
                //商家数量接口
                sjshuliang.sjsl();
            }
        });
        //计算价钱
        m2.setSpjiage(new MyRecycleView2.Spjiage() {
            @Override
            public void spjg() {
                sjjiage.sjjg();
            }
        });
        //计算数量
        m2.setShuliang(new MyRecycleView2.Shuliang() {
            @Override
            public void spsl() {
                sjshuliang.sjsl();
            }
        });
    }

    //总的复选框
    private Zong zong;
    public void setZong(Zong zong) {
        this.zong = zong;
    }
    public interface Zong
    {
        void shangjia();
        void shangpin();
    }


    //商家复选框
    private Sjjiage sjjiage;
    public void setSjjiage(Sjjiage sjjiage) {
        this.sjjiage = sjjiage;
    }
    public interface Sjjiage
    {
        void sjjg();
    }

    //商家复选框数量
    private Sjshuliang sjshuliang;
    public void setSjshuliang(Sjshuliang sjshuliang) {
        this.sjshuliang = sjshuliang;
    }
    public interface Sjshuliang
    {
        void sjsl();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private CheckBox ck;
        private TextView tv;
        private RecyclerView rvv;
        public MyViewHolder(View itemView) {
            super(itemView);
            ck=itemView.findViewById(R.id.ck);
            tv=itemView.findViewById(R.id.tv);
            rvv=itemView.findViewById(R.id.rvv);
        }
    }
}
