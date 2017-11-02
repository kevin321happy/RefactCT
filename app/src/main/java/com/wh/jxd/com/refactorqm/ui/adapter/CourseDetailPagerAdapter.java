package com.wh.jxd.com.refactorqm.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by kevin321vip on 2017/9/28.
 */

public class CourseDetailPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> list;
    private String[] titles = new String[]{"详情介绍", "目录", "课程推荐","全部"};
    public CourseDetailPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        fm.beginTransaction();
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list==null?null:list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        if (position<list.size()){
//            return titles[position];
//        }else {
//            return null;
//        }
        return titles[position];

    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }
}
