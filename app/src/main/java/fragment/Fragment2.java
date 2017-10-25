package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.asus.gouwuche2.R;

import java.text.DecimalFormat;
import java.util.List;

import adapter.ShopOneAdapter;
import bean.Shop;
import common.Api;
import presenter.Presenter_A;
import view.View_A;

/**
 * Created by asus on 2017/10/24.
 */

public class Fragment2 extends Fragment implements View_A{
    private View view;
    private RecyclerView rv;
    private Presenter_A pa;
    private ShopOneAdapter sa;
    private CheckBox all_check;
    private TextView price;
    private Button btn;
    private double money;
    private int num=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=View.inflate(getActivity(), R.layout.fragment_2,null);
        pa=new Presenter_A(this);
        pa.shop(Api.GOUWU);
        initview();
        return view;
    }

    private void initview() {
        rv=view.findViewById(R.id.rv);
        all_check=view.findViewById(R.id.all_check);
        price=view.findViewById(R.id.price);
        btn=view.findViewById(R.id.btn);

    }

    @Override
    public void Success(Shop shop) {
        try{
            final List<Shop.DataBean> data = shop.getData();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    sa=new ShopOneAdapter(getActivity(),data);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(sa);
                }
            });
            sa.setZong(new ShopOneAdapter.Zong() {
                @Override
                public void shangjia() {
                    boolean flag=true;
                    for (Shop.DataBean dataBean : data) {
                        List<Shop.DataBean.ListBean> list = dataBean.getList();
                        for (Shop.DataBean.ListBean listBean : list) {
                            if(listBean.getSelected()==0){
                                flag=false;
                            }
                        }
                    }
                    all_check.setChecked(flag);
                }

                @Override
                public void shangpin() {
                    boolean flag=true;
                    for (Shop.DataBean dataBean : data) {
                        List<Shop.DataBean.ListBean> list = dataBean.getList();
                        for (Shop.DataBean.ListBean listBean : list) {
                            if(listBean.getSelected()==0){
                                flag=false;
                            }
                        }
                    }
                    all_check.setChecked(flag);
                }
            });
            sa.setSjjiage(new ShopOneAdapter.Sjjiage() {
                @Override
                public void sjjg() {
                    money=0;
                    for (Shop.DataBean dataBean : data) {
                        List<Shop.DataBean.ListBean> list = dataBean.getList();
                        for (Shop.DataBean.ListBean listBean : list) {
                            if(listBean.getSelected()==1){
                                money=money+(listBean.getNum()*(listBean.getBargainPrice()));
                            }
                        }
                    }
                    DecimalFormat decimalFormat=new DecimalFormat("######0.00");
                    price.setText("¥"+decimalFormat.format(money));
                }
            });
            sa.setSjshuliang(new ShopOneAdapter.Sjshuliang() {
                @Override
                public void sjsl() {
                    num=0;
                    for (Shop.DataBean dataBean : data) {
                        List<Shop.DataBean.ListBean> list = dataBean.getList();
                        for (Shop.DataBean.ListBean listBean : list) {
                            if(listBean.getSelected()==1){
                               num++;
                            }
                        }
                    }
                    btn.setText("结钱("+num+")");
                }
            });
            all_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    money=0;
                    num=0;
                    for (Shop.DataBean dataBean : data) {
                        List<Shop.DataBean.ListBean> list = dataBean.getList();
                        for (Shop.DataBean.ListBean listBean : list) {
                            if(all_check.isChecked()){
                                listBean.setSelected(1);
                                money=money+(listBean.getNum()*(listBean.getBargainPrice()));
                                num++;
                            }else{
                                listBean.setSelected(0);
                            }
                        }
                    }
                    sa.notifyDataSetChanged();
                    DecimalFormat decimalFormat=new DecimalFormat("######0.00");
                    price.setText("¥"+decimalFormat.format(money));
                    btn.setText("结钱("+num+")");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
