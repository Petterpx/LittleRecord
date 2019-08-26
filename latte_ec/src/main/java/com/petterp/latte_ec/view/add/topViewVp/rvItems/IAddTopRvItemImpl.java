package com.petterp.latte_ec.view.add.topViewVp.rvItems;

import com.petterp.latte_core.util.litepal.ClassifyConsume;
import com.petterp.latte_core.util.litepal.ClassifyIncome;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.model.home.IHomeStateType;
import com.petterp.latte_ec.view.add.topViewVp.RecordListItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petterp on 2019/8/24
 * Summary:
 * 邮箱：1509492795@qq.com
 */
public class IAddTopRvItemImpl implements IAddTopRvItemModel {

    private List<MultipleItemEntity> itemConsumes;
    private List<MultipleItemEntity> itemIncome;

    @Override
    public List<MultipleItemEntity> consumeList() {

        return itemConsumes;
    }

    @Override
    public List<MultipleItemEntity> incomeList() {

        return itemIncome;
    }

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
                    .setField(MultipleFidls.ID, "" + i)
                    .setField(MultipleFidls.TAG, false)
                    .build();
            itemConsumes.add(itemEntity);
        }

        itemIncome = new ArrayList<>();
        List<ClassifyIncome> incomes = LitePal.findAll(ClassifyIncome.class);
        int size2 = incomes.size();
        for (int i = 0; i < size2; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, incomes.get(i).getIcon())
                    .setField(IHomeRvFields.KIND, incomes.get(i).getKind())
                    .setField(MultipleFidls.ID, "" + i)
                    .build();
            itemIncome.add(itemEntity);
        }
    }


}
