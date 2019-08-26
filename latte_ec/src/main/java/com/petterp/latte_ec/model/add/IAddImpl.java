package com.petterp.latte_ec.model.add;

import android.os.Bundle;
import android.util.Log;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.petterp.latte_core.util.litepal.ClassifyConsume;
import com.petterp.latte_core.util.litepal.ClassifyIncome;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.model.home.IHomeTitleRvItems;
import com.petterp.latte_ec.view.add.BootomCompile.CompileListItemType;
import com.petterp.latte_ec.view.add.topViewVp.RecordListItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Petterp
 * @date 2019-07-24
 */
public class IAddImpl implements IAddModel {
    private String mode = IAddTitleItems.CONSUME_ITEMS;
    private List<MultipleItemEntity> list;
    private String[] conSumekind;
    private String[] inComekind;
    //当前Rvitem的手指按下位置
    private int itemPosition;
    //默认状态添加
    private int state = IHomeStateType.ADD;
    private IAddBundleFields iAddBundleFields = null;

    @Override
    public List<MultipleItemEntity> getConsumeRvList() {
        List<MultipleItemEntity> itemConsumes = new ArrayList<>();
        List<ClassifyConsume> consumes = LitePal.findAll(ClassifyConsume.class);
        int size = consumes.size();
        for (int i = 0; i < size; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, consumes.get(i).getIcon())
                    .setField(IHomeRvFields.KIND, consumes.get(i).getKind())
                    .setField(MultipleFidls.ID,i)
                    .setField(MultipleFidls.TAG, false)
//                    .setField()
                    .build();
            itemConsumes.add(itemEntity);
        }
        state = IHomeStateType.ADD;
        conSumekind = new String[]{itemConsumes.get(0).getField(MultipleFidls.NAME), itemConsumes.get(0).getField(IHomeRvFields.KIND)};
        return itemConsumes;
    }

    @Override
    public List<MultipleItemEntity> getIncomeRvList() {
        List<MultipleItemEntity> itemIncome = new ArrayList<>();
        List<ClassifyIncome> incomes = LitePal.findAll(ClassifyIncome.class);
        int size = incomes.size();
        for (int i = 0; i < size; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, incomes.get(i).getIcon())
                    .setField(IHomeRvFields.KIND, incomes.get(i).getKind())
                    .setField(MultipleFidls.ID,i)
                    .build();
            itemIncome.add(itemEntity);
        }
        return itemIncome;
    }

    @Override
    public List<MultipleItemEntity> getKeyRvList() {
        String[] values = {
                "1", "2", "3", "X",
                "4", "5", "6", "清零",
                "7", "8", "9", "再记",
                "+", "0", ".", "保存"
        };
        list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(CompileListItemType.ITEM_COMPILE)
                    .setField(MultipleFidls.NAME, values[i])
                    .setField(MultipleFidls.ID, i)
                    .setField(MultipleFidls.TAG, true)
                    .build();
            list.add(itemEntity);
        }
        return list;
    }


    @Override
    public void setTitleMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String getTitleMode() {
        return mode;
    }

    @Override
    public void setTitleRvKind(String[] kind) {
        if (mode.equals(IHomeTitleRvItems.CONSUME)) {
            this.conSumekind = kind;
        } else {
            this.inComekind = kind;
        }
    }

    @Override
    public String[] getTitleRvKind() {
        if (mode.equals(IHomeTitleRvItems.CONSUME)) {
            return conSumekind;
        }
        return inComekind;
    }

    @Override
    public void setKeyRvSaveColor(boolean mode) {
        list.get(15).setFild(MultipleFidls.TAG, mode);
    }


    @Override
    public void setBundle(Bundle bundle) {
        if (bundle != null) {
            iAddBundleFields = bundle.getParcelable(IAddBundleType.KEY_UPDATE_LIST);
            if (iAddBundleFields != null) {
                state = IHomeStateType.UPDATE;
                mode = iAddBundleFields.getCargoy();
                String[] kind = new String[]{iAddBundleFields.getName(), iAddBundleFields.getKind()};
                if (mode.equals(IHomeTitleRvItems.CONSUME)) {
                    this.conSumekind = kind;
                } else {
                    this.inComekind = kind;
                }
            }
        }
    }

    @Override
    public int getStateMode() {
        return state;
    }


    @Override
    public IAddBundleFields getStateUpdate() {
        return iAddBundleFields;
    }

    @Override
    public void setItemPosition(int position) {
//        itemPosition
    }

}
