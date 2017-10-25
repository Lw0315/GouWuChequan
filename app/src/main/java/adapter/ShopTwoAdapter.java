package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.gouwuche2.R;

import java.util.List;

import bean.Shop;
import common.AmmountView;

/**
 * Created by asus on 2017/10/25.
 */

public class ShopTwoAdapter extends RecyclerView.Adapter<ShopTwoAdapter.ShopTwoView>{
    private Context context;
    private List<Shop.DataBean.ListBean> list;

    public ShopTwoAdapter(Context context, List<Shop.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ShopTwoView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.itemm,null);
        ShopTwoView sv=new ShopTwoView(view);
        return sv;
    }

    @Override
    public void onBindViewHolder(final ShopTwoView holder, final int position) {
        holder.tv.setText(list.get(position).getTitle());
        holder.price.setText("￥"+list.get(position).getBargainPrice());
        holder.av.setGoods_storage(50);
        final TextView etAmount=holder.av.findViewById(R.id.etAmount);
        etAmount.setText(list.get(position).getNum()+"");
        holder.av.setOnAmountChangeListener(new AmmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                String s = etAmount.getText().toString();
                list.get(position).setNum(Integer.parseInt(s));
                spjiage.spjg();
                shuliang.spsl();
            }
        });
        holder.zi_check.setOnCheckedChangeListener(null);
        if(list.get(position).getSelected()==1){
            holder.zi_check.setChecked(true);
            geshu.gs();
        }else{
            holder.zi_check.setChecked(false);
        }
        holder.zi_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 if(holder.zi_check.isChecked()){
                     list.get(position).setSelected(1);

                 }else{
                     list.get(position).setSelected(0);
                 }
                geshu.gs();
                shuliang.spsl();
                spjiage.spjg();
            }
        });
    }
    //构选复选框计算商品的价钱
    private Spjiage spjiage;
    public void setSpjiage(Spjiage spjiage) {
        this.spjiage = spjiage;
    }
    public interface Spjiage
    {
        void spjg();
    }
    private Geshu geshu;
    public void setGeshu(Geshu geshu) {
        this.geshu = geshu;
    }
    public interface Geshu
    {
        void gs();
    }
    //构选复选框计算数量
    private Shuliang shuliang;
    public void setShuliang(Shuliang shuliang) {
        this.shuliang = shuliang;
    }
    public interface Shuliang
    {
        void spsl();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShopTwoView extends RecyclerView.ViewHolder{
         private TextView tv;
        private TextView price;
        private CheckBox zi_check;
        private AmmountView av;
        public ShopTwoView(View itemView) {
            super(itemView);
          tv=itemView.findViewById(R.id.tv);
            price=itemView.findViewById(R.id.price);
            zi_check=itemView.findViewById(R.id.zi_check);
            av=itemView.findViewById(R.id.av);
        }
    }
}
