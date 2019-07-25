package com.petterp.latte_ec.model.home;

import android.annotation.SuppressLint;
import android.util.Log;

import com.petterp.latte_core.util.litepal.BillInfo;
import com.petterp.latte_core.util.litepal.EveryBillCollect;
import com.petterp.latte_core.util.time.SystemClock;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.view.home.HomeItemType;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据层-> 具体实现
 *
 * @author by Petterp
 * @date 2019-07-23
 */
public class IHomeImpl implements IHomeModel {
    //Rv数据集
    private List<MultipleItemEntity> itemEntities;
    //新增数据时的位置
    private int position = 0;
    //月支出
    private double consumeMoney;
    //月收入
    private double incomeMoney;
    //月结余
    private double surplusMoney;
    //title详细
    private HashMap<IHomeRvFields, String> titleHashMap;
    //数字格式化
    private DecimalFormat decimalFormat;

    @Override
    public List<MultipleItemEntity> getInfo() {
        if (itemEntities == null) {
            throw new NullPointerException("List<MultipleItemEntity>  ==  NULL!!!");
        }
        return itemEntities;
    }

    @SuppressLint("SimpleDateFormat")
    public IHomeImpl() {
        itemEntities = new ArrayList<>();
        titleHashMap = new HashMap<>();
        decimalFormat = new DecimalFormat("0.00");
    }

    @Override
    public void queryInfo() {
        //降序获取数据
        List<EveryBillCollect> collects = LitePal
                .order("longDate desc")
                .find(EveryBillCollect.class);
        int collectSize = collects.size();
        String yearMonth = TimeUtils.build().getYearMonth();
        for (int i = 0; i < collectSize; i++) {
            //如果是当天改为今天
            long longTime = collects.get(i).getLongDate();
            String time = collects.get(i).getDateRes();
            String day = TimeUtils.build().getday(longTime);
            if (time.equals(TimeUtils.build().getDate())) {
                day = "今天";
            }
            MultipleItemEntity itemHeader = MultipleItemEntity.builder()
                    .setItemType(HomeItemType.HOME_DETAIL_HEADER)
                    .setField(IHomeRvFields.YEAR_MONTH_DAY, time)
                    .setField(IHomeRvFields.DAY, day)
                    .setField(IHomeRvFields.CONSUME, collects.get(i).getConsume())
                    .build();
            //添加头
            itemEntities.add(itemHeader);
            //获取相应时间的具体item
            List<BillInfo> billInfos = LitePal.
                    where("dateRes=?", collects.get(i).getDateRes())
                    .find(BillInfo.class);
            int billInfoSize = billInfos.size();
            if (i == 0) {
                position = billInfoSize + 1;
            }
            for (int j = 0; j < billInfoSize; j++) {
                MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setItemType(HomeItemType.HOME_DETAIL_LIST)
                        .setField(IHomeRvFields.CATEGORY, billInfos.get(j).getCategory())
                        .setField(IHomeRvFields.KIND, billInfos.get(j).getKind())
                        .setField(IHomeRvFields.CONSUME_I, billInfos.get(j).getMoney())
                        .build();
                //添加具体item
                itemEntities.add(itemEntity);
            }
            if (yearMonth.equals(TimeUtils.build().getYearMonth(collects.get(i).getLongDate()))) {
                consumeMoney += collects.get(i).getConsume();
                incomeMoney += collects.get(i).getIncome();
            }
        }
        surplusMoney = consumeMoney + incomeMoney;
    }


    @Override
    public void update(MultipleItemEntity itemEntity, int position) {
        itemEntities.set(position, itemEntity);
    }

    @Override
    public void delegate(int p) {
        itemEntities.remove(p);
        this.position -= 1;
    }

    @Override
    public void add(MultipleItemEntity itemEntity) {
        addNo(itemEntity);
        if (itemEntities.size() == 0) {
            queryInfo();
        } else {
            itemEntities.add(position, itemEntity);
            position += 1;
            double money = itemEntity.getField(IHomeRvFields.CONSUME_I);
            if (itemEntity.getField(IHomeRvFields.CATEGORY).equals(IHomeTitleRvItems.INCOME)) {
                incomeMoney += money;
            } else {
                consumeMoney -= money;
                double consume = itemEntities.get(0).getField(IHomeRvFields.CONSUME);
                itemEntities.get(0).setFild(IHomeRvFields.CONSUME, Double.parseDouble(decimalFormat.format(Math.abs(consume) + Math.abs(money))));
            }
            surplusMoney=incomeMoney+consumeMoney;
        }
    }

    @Override
    public HashMap<IHomeRvFields, String> getTitleInfo() {
        titleHashMap.clear();
        titleHashMap.put(IHomeRvFields.CONSUME, "支出：" + decimalFormat.format(consumeMoney));
        titleHashMap.put(IHomeRvFields.INCOME, "收入：" + decimalFormat.format(incomeMoney));
        titleHashMap.put(IHomeRvFields.SUR_PLUS, decimalFormat.format(surplusMoney));
        Log.e("demo", consumeMoney + "------" + incomeMoney + "------" + surplusMoney);
        return titleHashMap;
    }

    private void addNo(MultipleItemEntity itemEntity) {
        //当前时间 年-月-日
        String timeday = TimeUtils.build().getDate();
        //判断是否已经存入当天数据
        List<EveryBillCollect> collects
                = LitePal.where("dateRes=?", timeday)
                .select("consume", "income", "sum")
                .find(EveryBillCollect.class);
        EveryBillCollect collect = new EveryBillCollect();
        String kind = itemEntity.getField(IHomeRvFields.KIND);
        double money = itemEntity.getField(IHomeRvFields.CONSUME_I);
        String category = itemEntity.getField(IHomeRvFields.CATEGORY);
        long time = itemEntity.getField(IHomeRvFields.LONG_TIME);
        String dateRes = TimeUtils.build().getDate(time);
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            money = -1 * money;
            if (collects.size() > 0) {
                collect.setConsume(collects.get(0).getConsume() + money);
                collect.setSum(collects.get(0).getSum() + 1);
            } else {
                new EveryBillCollect(dateRes, time, "petterp", money, 0.0, 1).save();
            }
        } else {
            if (collects.size() > 0) {
                collect.setIncome(collects.get(0).getIncome() + money);
                collect.setSum(collects.get(0).getSum() + 1);
            } else {
                new EveryBillCollect(dateRes, time, "petterp", 0.0, money, 1).save();
            }

        }
        //修改数据
        collect.updateAll("dateRes=?", timeday);
        BillInfo info = new BillInfo(time, dateRes, money, TimeUtils.build().getday(time), "petterp", kind, category);
        info.save();
    }


}
