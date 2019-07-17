package com.petterp.latte_ec.main.add.BootomCompile;

import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.petterp.latte_core.delegates.LatteDelegate;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.text.DecimalFormat;

/**
 * 输入账单的点击事件
 *
 * @author by Petterp
 * @date 2019-07-16
 */
public class CompileItemClcikList extends SimpleClickListener {
    private final LatteDelegate DELEGATE;
    private final IEditListener IMONEY;
    private final StringBuilder TEXT_BUILDER;
    private final DecimalFormat DECI_FORMAT;
    private Double mode = 0.0;

    public CompileItemClcikList(LatteDelegate DELEGATE, IEditListener editListener) {
        this.DELEGATE = DELEGATE;
        this.IMONEY = editListener;
        TEXT_BUILDER = new StringBuilder();
        DECI_FORMAT = new DecimalFormat("#0.00");
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final String NAME = entity.getField(MultipleFidls.NAME);
        Toast.makeText(DELEGATE.getContext(), NAME, Toast.LENGTH_SHORT).show();
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
                if (TEXT_BUILDER.length() < 14) {
                    String res = TEXT_BUILDER.toString();
                    if (res.contains("+")) {
                        int id=res.indexOf("+");
                        String[] values = {res.substring(0,id),res.substring(id+1)};
                        TEXT_BUILDER.delete(0, TEXT_BUILDER.length());
                        res = DECI_FORMAT.format(Double.parseDouble(values[0]) + Double.parseDouble(values[1]));
                        TEXT_BUILDER.delete(0, TEXT_BUILDER.length()).append(res).append("+");
                    } else {
                        TEXT_BUILDER.append("+");
                    }
                    IMONEY.setMoney(TEXT_BUILDER.toString());
                }
                break;
            case ".":
                if (TEXT_BUILDER.length() < 14) {
                    String res = TEXT_BUILDER.toString();
                    if (res.contains("+")){
                        int p=res.indexOf("+");
                        res=res.substring(p+1);
                    }
                    if (!res.contains(".")) {
                        TEXT_BUILDER.append(".");
                    }
                    IMONEY.setMoney(TEXT_BUILDER.toString());
                }
                break;
            case "X":
                if (TEXT_BUILDER.length()>0){
                    TEXT_BUILDER.delete(TEXT_BUILDER.length()-1,TEXT_BUILDER.length());
                    IMONEY.setMoney(TEXT_BUILDER.toString());
                }
                break;
            case "清零":
                IMONEY.setMoney("");
                mode = 0.0;
                break;
            case "再记":
                IMONEY.setMoney("");
                mode = 0.0;
                break;
            case "保存":

                break;
        }
    }

    private void setMoney(int id) {
        if (TEXT_BUILDER.length() < 14) {
            String res = TEXT_BUILDER.toString();
            if (res.contains("+")){
                int p=res.indexOf("+");
                res=res.substring(p+1);
            }
            //判断小数点后位置
            int position = res.indexOf(".");
            //如果不包含 .
            if (position == -1||res.length() - position <= 2) {
                TEXT_BUILDER.append(id);
                res = TEXT_BUILDER.toString();
                IMONEY.setMoney(res);
                //避免转型失败，相加
                if (!TEXT_BUILDER.toString().contains("+")) {
                    mode = Double.parseDouble(res);
                }
            }
        }
    }

    private void setMoneyzery() {
        if (mode != 0 && TEXT_BUILDER.length() < 14) {
            String res = TEXT_BUILDER.toString();
            //判断是否带+号
            if (res.contains("+")){
                int p=res.indexOf("+");
                res=res.substring(p+1);
            }
            //判断小数点后位置
            int position = res.indexOf(".");
            //如果不包含 .
            if (position == -1||res.length() - position <= 2) {
                TEXT_BUILDER.append(0);
                res = TEXT_BUILDER.toString();
                IMONEY.setMoney(res);
                //避免转型失败，相加
                if (!res.contains("+")) {
                    mode = Double.parseDouble(res);
                }
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

}
