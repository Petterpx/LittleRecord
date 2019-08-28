package com.petterp.latte_ec.main.add.topViewVp.rvItems;

import android.util.Log;

import com.petterp.latte_core.util.litepal.ClassifyConsume;
import com.petterp.latte_core.util.litepal.ClassifyIncome;
import com.petterp.latte_ec.main.home.IHomeRvFields;
import com.petterp.latte_ec.main.home.IHomeTitleRvItems;
import com.petterp.latte_ec.main.add.topViewVp.RecordListItemType;
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
    private String category;

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
                    .setField(IHomeRvFields.CATEGORY, consumes.get(i).getMode())
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
                    .setField(IHomeRvFields.CATEGORY, incomes.get(i).getMode())
                    .setField(MultipleFidls.ID, "" + i)
                    .build();
            itemIncome.add(itemEntity);
        }
    }

    @Override
    public void update(String kindNew, String kind, String category) {
        Log.e("Demo", "kindNew" + kindNew + "      kind" + kind + "     cate" + category);
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            Log.e("Demo", "修改");
            ClassifyConsume consume = new ClassifyConsume();
            consume.setKind(kindNew);
            consume.updateAll("kind=?", kind);
            List<ClassifyConsume> consumes = LitePal.where("kind=?", kindNew).find(ClassifyConsume.class);
            Log.e("Demo", consumes.size() + "");
        } else {
            ClassifyIncome income = new ClassifyIncome();
            income.setKind(kindNew);
            income.updateAll("kind=?", kind);
        }
    }

    @Override
    public void setTitleMode(String mode) {
        this.category = mode;
    }

    @Override
    public boolean add(String kindNew, String category) {
        String icon = kindNew.substring(0, 1);
        List list;
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            list = LitePal.where("kind=?", kindNew).find(ClassifyConsume.class);

        } else {
            list = LitePal.where("kind=?", kindNew).find(ClassifyIncome.class);
        }
        if (list == null || list.size() <= 0) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(RecordListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, icon)
                    .setField(IHomeRvFields.KIND, kindNew)
                    .setField(IHomeRvFields.CATEGORY, category)
                    .setField(MultipleFidls.TAG, false)
                    .build();
            if (category.equals(IHomeTitleRvItems.CONSUME)) {
                ClassifyConsume consume = new ClassifyConsume(icon, kindNew, category);
                consume.save();
                itemConsumes.add(itemEntity);
            } else {
                ClassifyIncome income = new ClassifyIncome(icon, kindNew, category);
                income.save();
                itemIncome.add(itemEntity);
            }
            return true;
        }
        return false;
    }

    @Override
    public void delegate(String kind, String category) {
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            LitePal.deleteAll(ClassifyConsume.class, "kind=?", kind);
        } else {
            LitePal.deleteAll(ClassifyIncome.class, "kind=?", kind);
        }
    }


}
