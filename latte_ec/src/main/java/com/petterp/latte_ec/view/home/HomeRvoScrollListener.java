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

    private IHomeRvListener iHomeRvListener;

    public HomeRvoScrollListener(IHomeRvListener iHomeRvListener) {
        this.iHomeRvListener = iHomeRvListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            iHomeRvListener.hideFlootButton();
        }
        if (dy < 0) {
            iHomeRvListener.showFlootButton();
        }
    }
}
