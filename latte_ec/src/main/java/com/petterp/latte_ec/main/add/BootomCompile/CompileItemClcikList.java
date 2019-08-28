package com.petterp.latte_ec.main.add.BootomCompile;

import android.view.View;

import androidx.navigation.Navigation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_core.util.time.SystemClock;
import com.petterp.latte_ec.main.home.MessageItems;
import com.petterp.latte_ec.main.home.IHomeRvFields;
import com.petterp.latte_ec.main.home.IHomeStateType;
import com.petterp.latte_ec.main.home.IHomeTitleRvItems;
import com.petterp.latte_ec.main.add.AddPresenter;
import com.petterp.latte_ec.main.home.HomeItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;

/**
 * 输入账单的点击事件
 *
 * @author by Petterp
 * @date 2019-07-16
 */
public class CompileItemClcikList extends SimpleClickListener {
    private final StringBuilder TEXT_BUILDER;
    private final DecimalFormat DECI_FORMAT;
    private final int SUM_LENGTH = 20;
    private AddPresenter mPresenter;
    public CompileItemClcikList(AddPresenter mPresenter) {
        this.mPresenter = mPresenter;
        TEXT_BUILDER = new StringBuilder();
        if (mPresenter.getStateMode() == IHomeStateType.UPDATE) {
            TEXT_BUILDER.append(mPresenter.getUpdateRvItem().getMoney());
        }
        DECI_FORMAT = new DecimalFormat("###################.##");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final String NAME = entity.getField(MultipleFidls.NAME);
        switch (NAME) {
            case "0":
                setMoneyzery();
                break;
            case "1":
                setMoney(1);
                break;
            case "2":
                setMoney(2);
                break;
            case "3":
                setMoney(3);
                break;
            case "4":
                setMoney(4);
                break;
            case "5":
                setMoney(5);
                break;
            case "6":
                setMoney(6);
                break;
            case "7":
                setMoney(7);
                break;
            case "8":
                setMoney(8);
                break;
            case "9":
                setMoney(9);
                break;
            case "+":
                if (TEXT_BUILDER.length() < SUM_LENGTH) {
                    String res = TEXT_BUILDER.toString();
                    if ((res.length() - res.replace("+", "").length()) == 1) {
                        int id = res.indexOf("+");
                        String[] values = {res.substring(0, id), res.substring(id + 1)};
                        if (isNumber(values[0]) && isNumber(values[1])) {
                            res = DECI_FORMAT.format(Double.parseDouble(values[0]) + Double.parseDouble(values[1]));
                            TEXT_BUILDER.setLength(0);
                            TEXT_BUILDER.append(res).append("+");
                        }
                        mPresenter.setBootomKey(TEXT_BUILDER.toString());

                    } else if (!res.contains("+") && res.length() > 0) {
                        TEXT_BUILDER.append("+");
                        mPresenter.setBootomKey(TEXT_BUILDER.toString());
                    }
                }
                break;
            case ".":
                if (TEXT_BUILDER.length() < SUM_LENGTH) {
                    String res = TEXT_BUILDER.toString();
                    //是否包含
                    if (res.contains("+")) {
                        //计算位置
                        int p = res.indexOf("+");
                        //计算有无 "." ->这里是为了相加时左右都为小数情况下
                        res = res.substring(p + 1);
                    }
                    if (!res.contains(".")) {
                        TEXT_BUILDER.append(".");
                    }
                    mPresenter.setBootomKey(TEXT_BUILDER.toString());
                }
                break;
            case "X":
                if (TEXT_BUILDER.length() > 0) {
                    TEXT_BUILDER.delete(TEXT_BUILDER.length() - 1, TEXT_BUILDER.length());
                    mPresenter.setBootomKey(TEXT_BUILDER.toString());
                }
                break;
            case "清零":
                mPresenter.setBootomKey("0");
                TEXT_BUILDER.setLength(0);
                break;
            case "再记":
                mPresenter.setBootomKey("0");
                addSave();
                break;
            case "保存":
                addSave();
                //弹栈
                Navigation.findNavController(view).navigateUp();
                break;
        }
    }

    private void setMoney(int id) {
        if (TEXT_BUILDER.length() < SUM_LENGTH) {
            String res = TEXT_BUILDER.toString();
            if (res.contains("+")) {
                int p = res.indexOf("+");
                res = res.substring(p + 1);
            }
            //判断小数点后位置
            int position = res.indexOf(".");
            //如果不包含 .
            if (position == -1 || res.length() - position <= 2) {
                TEXT_BUILDER.append(id);
                res = TEXT_BUILDER.toString();
                mPresenter.setBootomKey(res);
            }
        }
    }

    private void setMoneyzery() {
        int length = TEXT_BUILDER.length();
        if (length < SUM_LENGTH && length > 0) {
            String res = TEXT_BUILDER.toString();
            //判断是否带+号
            if (res.contains("+")) {
                int p = res.indexOf("+");
                res = res.substring(p + 1);
            }
            //判断小数点后位置
            int position = res.indexOf(".");
            //如果不包含 .
            if (position == -1 || res.length() - position <= 2) {
                TEXT_BUILDER.append(0);
                res = TEXT_BUILDER.toString();
                mPresenter.setBootomKey(res);
            }
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }


    /**
     * 普通添加
     */
    private void addSave() {
        String res = TEXT_BUILDER.toString();
        if (!res.equals("")) {
            //如果包含"+"
            if (res.contains("+")) {
                if (res.endsWith("+") || res.startsWith("+")) {
                    res = res.replace("+", "");
                } else {
                    int id = res.indexOf("+");
                    String[] values = {res.substring(0, id), res.substring(id + 1)};
                    res = DECI_FORMAT.format(Double.parseDouble(values[0]) + Double.parseDouble(values[1]));
                }
            }
            float money = Float.parseFloat(res);
            if (mPresenter.getTitleMode().equals(IHomeTitleRvItems.CONSUME)) {
                money = -1 * money;
            }
            String[] kindRes = mPresenter.getTitleRvKind();
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(HomeItemType.HOME_DETAIL_LIST)
                    .setField(IHomeRvFields.CATEGORY, mPresenter.getTitleMode())
                    .setField(IHomeRvFields.CONSUME_I, money)
                    .setField(MultipleFidls.NAME, kindRes[0])
                    .setField(IHomeRvFields.KIND, kindRes[1])
                    .setField(IHomeRvFields.REMARK, mPresenter.getRemarkInfo())
                    .setField(IHomeRvFields.LONG_TIME, SystemClock.now())
                    .setField(IHomeRvFields.KEY, null)
                    .build();
            //发送消息
            EventBus.getDefault().post(new MessageItems(itemEntity));
            TEXT_BUILDER.setLength(0);
        }
    }



    /**
     * 判断是否可转为Double
     *
     * @param obj
     * @return
     */
    private boolean isNumber(Object obj) {
        if (obj instanceof Number) {
            return true;
        } else if (obj instanceof String) {
            try {
                Double.parseDouble((String) obj);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
