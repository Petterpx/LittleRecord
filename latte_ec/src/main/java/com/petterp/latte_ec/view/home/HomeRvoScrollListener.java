package com.petterp.latte_ec.view.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petterp.latte_ec.presenter.HomePresenter;

/**
 * 监听Rv滑动改变flootButton
 * @author by Petterp
 * @date 2019-07-24
 */
public class HomeRvoScrollListener extends RecyclerView.OnScrollListener {
    private HomePresenter homePresenter;

    HomeRvoScrollListener(HomePresenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            homePresenter.showFlootButton();
        }
        if (dy < 0) {
            homePresenter.hideFlootButton();
        }
    }
}
