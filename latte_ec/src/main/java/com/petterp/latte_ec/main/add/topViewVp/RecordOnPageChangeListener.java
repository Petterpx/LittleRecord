package com.petterp.latte_ec.main.add.topViewVp;

import androidx.viewpager.widget.ViewPager;

import com.petterp.latte_ec.main.add.IAddTitleItems;
import com.petterp.latte_ec.main.add.AddPresenter;

/**
 * 监听Top ViewPager滑动
 * @author by Petterp
 * @date 2019-07-24
 */
public class RecordOnPageChangeListener implements ViewPager.OnPageChangeListener {

    private AddPresenter mPresenter;

    public RecordOnPageChangeListener(AddPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mPresenter.setTitleMode(IAddTitleItems.CONSUME_ITEMS);
            mPresenter.setKeyRvSaveColor(true);
        } else {
            mPresenter.setTitleMode(IAddTitleItems.INCOME_ITEMS);
            mPresenter.setKeyRvSaveColor(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
