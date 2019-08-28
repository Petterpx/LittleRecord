package com.petterp.latte_ec.main.add;

import android.os.Bundle;
import android.util.Log;

import com.petterp.latte_core.util.litepal.ClassifyConsume;
import com.petterp.latte_core.util.litepal.ClassifyIncome;
import com.petterp.latte_ec.main.home.IHomeRvFields;
import com.petterp.latte_ec.main.home.IHomeStateType;
import com.petterp.latte_ec.main.home.IHomeTitleRvItems;
import com.petterp.latte_ec.main.add.BootomCompile.CompileListItemType;
import com.petterp.latte_ec.main.add.topViewVp.RecordListItemType;
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
    private List<MultipleItemEntity> itemConsumes;
    private List<MultipleItemEntity> itemIncome;

    @Override
    public void queryInfo() {
        itemConsumes = new ArrayList<>();
        List<ClassifyConsume> consumes = LitePal.findAll(ClassifyConsume.class);
        int size = consumes.size();
        for (int i = 0; i < size; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, consumes.get(i).getIcon())
                    .setField(IHomeRvFields.KIND, consumes.get(i).getKind())
                    .setField(IHomeRvFields.CATEGORY,consumes.get(i).getMode())
                    .setField(MultipleFidls.ID, i)
                    .setField(MultipleFidls.TAG, false)
                    .build();
            itemConsumes.add(itemEntity);
        }
        conSumekind = new String[]{itemConsumes.get(0).getField(MultipleFidls.NAME), itemConsumes.get(0).getField(IHomeRvFields.KIND)};

        itemIncome = new ArrayList<>();
        List<ClassifyIncome> incomes = LitePal.findAll(ClassifyIncome.class);
        int size2 = incomes.size();
        for (int i = 0; i < size2; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, incomes.get(i).getIcon())
                    .setField(IHomeRvFields.KIND, incomes.get(i).getKind())
                    .setField(IHomeRvFields.CATEGORY,incomes.get(i).getMode())
                    .setField(MultipleFidls.ID, i)
                    .setField(MultipleFidls.TAG, false)
                    .build();
            itemIncome.add(itemEntity);
        }
        inComekind = new String[]{itemIncome.get(0).getField(MultipleFidls.NAME), itemIncome.get(0).getField(IHomeRvFields.KIND)};
    }

    @Override
    public List<MultipleItemEntity> getConsumeRvList() {
        return itemConsumes;
    }

    @Override
    public List<MultipleItemEntity> getIncomeRvList() {
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
    public String[] getTitleRvKind(String mode) {
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
        Log.e("demo",""+state);
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

    @Override
    public void addRvItem(String kind, String category) {
        String icon = kind.substring(0, 1);
        MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                .setField(MultipleFidls.NAME, icon)
                .setField(IHomeRvFields.KIND, kind)
                .setField(IHomeRvFields.CATEGORY,category)
                .build();
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            itemConsumes.add(itemEntity);
        } else {
            itemIncome.add(itemEntity);
        }
    }

    @Override
    public void updateRvItem(String kind, String kindNew, String category) {
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            int size = itemConsumes.size();
            for (int i = 0; i < size; i++) {
                if (itemConsumes.get(i).getField(IHomeRvFields.KIND).equals(kind)) {
                    itemConsumes.get(i).setFild(IHomeRvFields.KIND, kindNew);
                    return;
                }
            }
        } else {
            int size = itemIncome.size();
            for (int i = 0; i < size; i++) {
                if (itemIncome.get(i).getField(IHomeRvFields.KIND).equals(kind)) {
                    itemIncome.get(i).setFild(IHomeRvFields.KIND, kindNew);
                    return;
                }
            }
        }
    }

    @Override
    public void delegateRvItem(String kind, String category) {
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            int size = itemConsumes.size();
            for (int i = 0; i < size; i++) {
                if (itemConsumes.get(i).getField(IHomeRvFields.KIND).equals(kind)) {
                    itemConsumes.remove(i);
                    return;
                }
            }
        } else {
            int size = itemIncome.size();
            for (int i = 0; i < size; i++) {
                if (itemIncome.get(i).getField(IHomeRvFields.KIND).equals(kind)) {
                    itemIncome.remove(i);
                    return;
                }
            }
        }
    }

}
