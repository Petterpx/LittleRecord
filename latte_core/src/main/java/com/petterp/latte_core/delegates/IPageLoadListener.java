package com.petterp.latte_core.delegates;

/**
 * 监听外部加载事件
 */

public interface IPageLoadListener {

    void onLoadStart();

    void onLoadEnd();
}
