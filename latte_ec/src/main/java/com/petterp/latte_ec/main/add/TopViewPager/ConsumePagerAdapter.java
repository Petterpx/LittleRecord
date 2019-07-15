package com.petterp.latte_ec.main.add.TopViewPager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Add Tab_ViewPager_Adapter
 */
public class ConsumePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private String[] sums;

    public ConsumePagerAdapter(FragmentManager fm, List<Fragment> list, String[] sums) {
        super(fm);
        this.list = list;
        this.sums = sums;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return sums[position];

    }
}
