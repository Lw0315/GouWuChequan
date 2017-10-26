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

import bean.Shop;

/**
 * Created by asus on 2017/10/25.
 */

public class ShopOneAdapter  extends RecyclerView.Adapter<ShopOneAdapter.ShopView>{
    private Context context;
    private List<Shop.DataBean> list;
    public ShopOneAdapter(Context context, List<Shop.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ShopView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.fragment_2_item,null);
        ShopView sv=new ShopView(view);
        return sv;
    }

    @Override
    public void onBindViewHolder(final ShopView holder, int position) {
        holder.tv.setText(list.get(position).getSellerName());

        final List<Shop.DataBean.ListBean> data = this.list.get(position).getList();
        final ShopTwoAdapter sp=new ShopTwoAdapter(context,data);
        holder.rvv.setLayoutManager(new LinearLayoutManager(context));
        holder.rvv.setAdapter(sp);
        boolean flag=true;
        for (Shop.DataBean.ListBean listBean : data) {
            if(listBean.getSelected()==0){
                flag=false;
            }
        }
        holder.ck.setChecked(flag);
        sp.setGeshu(new ShopTwoAdapter.Geshu() {
            @Override
            public void gs() {
                boolean flag=true;
                for (Shop.DataBean.ListBean listBean : data) {
                    if(listBean.getSelected()==0)
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
                for (Shop.DataBean.ListBean listBean : data) {
                    if(holder.ck.isChecked())
                    {
                        listBean.setSelected(1);
                    }else{
                        listBean.setSelected(0);
                    }
                }
                sp.notifyDataSetChanged();
                zong.shangjia();
                sjjiage.sjjg();
                sjshuliang.sjsl();
            }
        });
        //计算价钱
        sp.setSpjiage(new ShopTwoAdapter.Spjiage() {
            @Override
            public void spjg() {
                sjjiage.sjjg();
            }
        });
        //计算数量
        sp.setShuliang(new ShopTwoAdapter.Shuliang() {
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

    public class ShopView extends RecyclerView.ViewHolder{
        private CheckBox ck;
        private TextView tv;
        private RecyclerView rvv;
        public ShopView(View itemView) {
            super(itemView);
            ck=itemView.findViewById(R.id.ck);
            tv=itemView.findViewById(R.id.tv);
            rvv=itemView.findViewById(R.id.rvv);
        }
    }
}
