package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.gouwuche2.R;

import java.util.List;

/**
 * Created by asus on 2017/10/24.
 */

public class Fragment_viewpager extends FragmentPagerAdapter{
    private Context context;
    private List<Fragment> list;
    private String[] name={"日报","专栏","热门","主题日报"};
    public Fragment_viewpager(FragmentManager fm) {
        super(fm);
    }

    public Fragment_viewpager(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public CharSequence getPageTitle(int position){
        return name[position];
    }
}
