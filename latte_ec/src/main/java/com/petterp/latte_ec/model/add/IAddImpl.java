package com.petterp.latte_ec.model.add;

import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.view.add.BootomCompile.CompileListItemType;
import com.petterp.latte_ec.view.add.topViewVp.RecordListItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Petterp
 * @date 2019-07-24
 */
public class IAddImpl implements IAddModel {
    private String mode = IAddTitleItems.CONSUME_ITEMS;
    private List<MultipleItemEntity> list;
    private String[] kind = {"{icon-kind}", "三餐"};

    @Override
    public List<MultipleItemEntity> getConsumeRvList() {
        List<MultipleItemEntity> itemEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, "{icon-award}")
                    .setField(IHomeRvFields.KIND, "三餐")
                    .setField(MultipleFidls.ID, "" + i)
                    .build();
            itemEntities.add(itemEntity);
        }
        return itemEntities;
    }

    @Override
    public List<MultipleItemEntity> getIncomeRvList() {
        List<MultipleItemEntity> itemEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, "{icon-award}")
                    .setField(IHomeRvFields.KIND, "三餐")
                    .setField(MultipleFidls.ID, "" + i)
                    .build();
            itemEntities.add(itemEntity);
        }
        return itemEntities;
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
    public void queryRvInfo() {

    }

    @Override
    public String keySave() {
        return null;
    }

    @Override
    public void setRemark(String remark) {

    }

    @Override
    public String getRemark() {
        return null;
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
        this.kind = kind;
    }

    @Override
    public String[] getTitleRvKind() {
        return kind;
    }

    @Override
    public void setKeyRvSaveColor(boolean mode) {
        list.get(15).setFild(MultipleFidls.TAG, mode);
    }

}
