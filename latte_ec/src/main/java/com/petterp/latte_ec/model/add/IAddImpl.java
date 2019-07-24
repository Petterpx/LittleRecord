package com.petterp.latte_ec.model.add;

import com.petterp.latte_ec.main.index.add.BootomCompile.CompileListItemType;
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
    private String titleRvKind="三餐";
    @Override
    public List<MultipleItemEntity> getConsumeRvList() {
        return null;
    }

    @Override
    public List<MultipleItemEntity> getIncomeRvList() {
        return null;
    }

    @Override
    public List<MultipleItemEntity> getKeyRvList() {
        String[] values = {
                "1", "2", "3", "X",
                "4", "5", "6", "清零",
                "7", "8", "9", "再记",
                "+", "0", ".", "保存"
        };
        List<MultipleItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(CompileListItemType.ITEM_COMPILE)
                    .setField(MultipleFidls.NAME, values[i])
                    .setField(MultipleFidls.ID, i)
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
        this.mode=mode;
    }

    @Override
    public String getTitleMode() {
        return mode;
    }

    @Override
    public void setTitleRvKind(String kind) {
        this.titleRvKind=kind;
    }

    @Override
    public String getTitleRvKind() {
        return titleRvKind;
    }

}
