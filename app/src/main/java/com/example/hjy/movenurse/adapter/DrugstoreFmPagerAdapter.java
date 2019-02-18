package com.example.hjy.movenurse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 用户注册 fragment + viewpager 适配器
 * Created by fangs on 2017/7/14.
 */
public class DrugstoreFmPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;

    public DrugstoreFmPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mTitles[position];
//    }
}
