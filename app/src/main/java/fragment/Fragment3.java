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
import java.util.ArrayList;
import java.util.List;

import adapter.MyRecycleView1;
import bean.Shangpin;
import bean.XINWEN;

/**
 * Created by asus on 2017/10/24.
 */

public class Fragment3 extends Fragment {
    private View view;
    private RecyclerView rv;
    private CheckBox all_check;
    private TextView price;
    private Button btn;
    private ArrayList<XINWEN> list;
    private List<Shangpin> splist;
    private double money;
    private int num;
    private MyRecycleView1 m1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_3,null);
        initview();
        initdata();
        return view;
    }

    private void initdata() {
        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            splist=new ArrayList<>();
            for (int j = 0; j <3 ; j++) {
                splist.add(new Shangpin(false,"商品"+(j+1),10.0+(j+1),1));
                
                }
                list.add(new XINWEN(false,"商家"+(i+1),splist));

            }
            setData();
    }

    private void setData() {
        m1 = new MyRecycleView1(list,getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(m1);
        m1.setZong(new MyRecycleView1.Zong() {
            @Override
            public void shangjia() {
                boolean flag=true;
                for (XINWEN shop : list) {
                    for (Shangpin shangpin : shop.getList()) {
                        if(shangpin.isSpSelect()==false)
                        {
                            flag=false;
                        }
                    }
                }
                all_check.setChecked(flag);
            }

            @Override
            public void shangpin() {
                boolean flag=true;
                for (XINWEN shop : list) {
                    for (Shangpin shangpin : shop.getList()) {
                        if(shangpin.isSpSelect()==false)
                        {
                            flag=false;
                        }
                    }
                }
                all_check.setChecked(flag);
            }
        });

        m1.setSjjiage(new MyRecycleView1.Sjjiage() {
            @Override
            public void sjjg() {
                money=0;
                for (XINWEN shop : list) {
                    for (Shangpin shangpin : shop.getList()) {
                        if(shangpin.isSpSelect()==true)
                        {
                            money=money+(shangpin.getJiage()*shangpin.getNum());
                        }
                    }
                }
                DecimalFormat decimalFormat=new DecimalFormat("######0.00");
                price.setText("¥"+decimalFormat.format(money));

            }
        });

        m1.setSjshuliang(new MyRecycleView1.Sjshuliang() {
            @Override
            public void sjsl() {
                num=0;
                for (XINWEN shop : list) {
                    for (Shangpin shangpin : shop.getList()) {
                        if(shangpin.isSpSelect()==true)
                        {
                            num++;
                        }
                    }
                }
                btn.setText("结钱("+num+")");

            }
        });
    }


    private void initview() {
        rv=view.findViewById(R.id.rv);
        all_check=view.findViewById(R.id.all_check);
        price=view.findViewById(R.id.price);
        btn=view.findViewById(R.id.btn);
        all_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money=0;
                num=0;
                for (XINWEN shop : list) {
                    for (Shangpin shangpin : shop.getList()) {
                        if(all_check.isChecked())
                        {
                            shangpin.setSpSelect(true);
                            money=money+(shangpin.getJiage()*shangpin.getNum());
                            num=(shop.getList().size())*10;
                        }
                        else
                        {
                            shangpin.setSpSelect(false);
                        }
                    }
                }
                m1.notifyDataSetChanged();
                DecimalFormat decimalFormat=new DecimalFormat("######0.00");
                price.setText("¥"+decimalFormat.format(money));
                btn.setText("结钱("+num+")");
            }
        });
    }

}
