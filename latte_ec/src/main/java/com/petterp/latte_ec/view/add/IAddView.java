package com.petterp.latte_ec.view.add;

/**
 * @author by Petterp
 * @date 2019-07-24
 */
public interface IAddView {

    /**
     * 初始化top
     */
    void topViewPagerInfo();


    /**
     * 初始化自定义键盘
     */
    void bottomKeyInfo();


    /**
     * 键盘数据的改变
     */
    void setKeyRes(String res);

    /**
     * 获得备注
     */
    String getEditRemark();

    /**
     *动态刷新Key-颜色
     */
    void updateKeyColor(boolean mode);

    /**
     * 更新数据
     */
    void UpdateRv();

}
