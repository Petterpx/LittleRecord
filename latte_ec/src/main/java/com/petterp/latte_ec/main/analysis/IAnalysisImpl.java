package com.petterp.latte_ec.main.analysis;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.petterp.latte_core.util.litepal.BillInfo;
import com.petterp.latte_core.util.litepal.EveryBillCollect;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ui.recyclear.ItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import org.litepal.LitePal;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author by petterp
 * @date 2019-08-10
 */
public class IAnalysisImpl implements IAnalysisModel {
    private DecimalFormat decimalFormat = new DecimalFormat("###################.##");
    private String year;
    private String month;
    //收支总览
    private HashMap<AnalyInConsumeFields, String> inConsumeMap = new HashMap<>();

    //每天收支波动图
    private List<List<Entry>> charList = new ArrayList<>();
    //每天收支波动图->时间
    private List<String> charTimes = new ArrayList<>();

    //分类账单-外围Rv
    private List<MultipleItemEntity> pieRvList = new ArrayList<>();
    //分类账单-内围Rv->dialog的
    private HashMap<String, List<MultipleItemEntity>> pieRvItemMap = new HashMap<>();
    //分类账单->中心piechart图
    private List<PieEntry> pieChartList = new ArrayList<>();
    private HashMap<String, Float> pieMap;

    //每日账单-外围List
    private List<MultipleItemEntity> dayBillList = new ArrayList<>();
    //内围
    private HashMap<String, List<MultipleItemEntity>> dayBillItemMap = new HashMap<>();
    //平均每日支出
    private float dayConsume;
    //是否有数据
    private boolean mode = true;

    public IAnalysisImpl() {
        //获取当前时间
        String[] times = TimeUtils.build().getYearMonthDays();
        year = times[0];
        month = times[1];
        for (int i = 0; i < 3; i++) {
            charList.add(new ArrayList<>());
        }
    }

    @Override
    public String getTitleRes() {
        return year + "年" + month.replaceAll("^(0+)", "") + "月";
    }

    @Override
    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int getMonth() {
        return Integer.parseInt(month);
    }

    @Override
    public int getYear() {
        return Integer.parseInt(year);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void queryInfo() {
        mode = true;
        //清空分类账单数据
        pieRvList.clear();
        pieChartList.clear();
        pieRvItemMap.clear();

        //清空每天收支波动图数据
        charTimes.clear();
        for (int i = 0; i < 3; i++) {
            charList.get(i).clear();
        }

        //清空每日账单统计
        dayBillList.clear();
        dayBillItemMap.clear();

        //kind
        List<String> kindList = new ArrayList<>();
        //存储数据库item
        List<MultipleItemEntity> pieClassify = new ArrayList<>();

        float income = 0, consum = 0, balance, avg;
        List<EveryBillCollect> all = LitePal
                .where("year=? and month=?", year, month)
                .find(EveryBillCollect.class);
        int size = all.size();
        if (size<=0){
            mode=false;
            return;
        }

        charTimes.add("");
        float charSum = 1;
        for (int i = 0; i < size; i++) {
            float incomeMoney = all.get(i).getIncome();
            float consumeMoney = all.get(i).getConsume();
            income += incomeMoney;
            consum += consumeMoney;
            charList.get(0).add(new Entry(charSum, Math.abs(consumeMoney)));
            charList.get(1).add(new Entry(charSum, incomeMoney));
            ++charSum;
            charTimes.add(TimeUtils.build().getMonthDay(all.get(i).getLongDate()));

            List<MultipleItemEntity> daylist = new ArrayList<>();
            //分类账单
            //根据key查询天数数据
            List<BillInfo> billInfos = LitePal.where("key=?", all.get(i).getKey()).find(BillInfo.class);
            int sizej = billInfos.size();
            for (int j = 0; j < sizej; j++) {
                kindList.add(billInfos.get(j).getKind());
                String category = billInfos.get(j).getCategory();
                String kind = billInfos.get(j).getKind();
                float money = Math.abs(billInfos.get(j).getMoney());
                long date = billInfos.get(j).getLongDate();
                //储存category
                pieClassify.add(MultipleItemEntity
                        .builder()
                        .setItemType(DataAnalysisItemType.DATA_ANALYSIS_PIE_ITEM_LIST)
                        .setField(AnalysisFields.KIND, kind)
                        .setField(AnalysisFields.MONEY, money)
                        .setField(AnalysisFields.CATEGORY, category)
                        .setField(MultipleFidls.NAME, billInfos.get(j).getKindIcon())
                        .setField(AnalysisFields.MONTH_DAY, TimeUtils.build().getMonthDay(date))
                        .build());
                daylist.add(MultipleItemEntity
                        .builder()
                        .setItemType(DataAnalysisItemType.DATA_BILL_RV_ITEM_LIST)
                        .setField(AnalysisFields.KIND, kind)
                        .setField(AnalysisFields.MONEY, money)
                        .setField(AnalysisFields.CATEGORY, category)
                        .build());
            }
            int billItemSize = daylist.size();

            String times = TimeUtils.build().getYearMonthDay(all.get(i).getLongDate());
            StringBuilder stringBuilder = new StringBuilder();
            daylist.add(0, MultipleItemEntity
                    .builder()
                    .setItemType(ItemType.TEXT)
                    .setField(MultipleFidls.TEXT, stringBuilder.append(times).append("(").append(billItemSize).append(")").toString())
                    .build());
            dayBillItemMap.put(times, daylist);
            dayBillList.add(MultipleItemEntity
                    .builder()
                    .setItemType(DataAnalysisItemType.DATA_BILL_RV_LIST)
                    .setField(AnalysisFields.YEAR_MONTH_DAY, times)
                    .setField(AnalysisFields.SUM, billItemSize)
                    .setField(AnalysisFields.INCOME_MONEY, incomeMoney)
                    .setField(AnalysisFields.CONSUME_MONEY, consumeMoney)
                    .build());
        }

        charTimes.add("");
        balance = income + consum;
        String[] times = TimeUtils.build().getYearMonthDays();
        int day;
        if ((year + month).equals(times[0] + times[1])) {
            day = Integer.parseInt(times[2].replaceAll("^(0+)", ""));
        } else {
            int months = Integer.parseInt(month);
            if (months == 1 || months == 3 || months == 5 || months == 7 || months == 8 || months == 10 || months == 12) {
                day = 31;
            } else if (months == 2) {
                int years = Integer.parseInt(year);
                if (years % 4 == 0) {
                    day = 29;
                } else {
                    day = 28;
                }
            } else {
                day = 30;
            }
        }
        avg = balance / day;
        dayConsume = consum / day;
        //每天收支结束


        inConsumeMap.put(AnalyInConsumeFields.INCOME, decimalFormat.format(income));
        inConsumeMap.put(AnalyInConsumeFields.CONSUME, decimalFormat.format(consum));
        inConsumeMap.put(AnalyInConsumeFields.AVERAGE, decimalFormat.format(avg));
        inConsumeMap.put(AnalyInConsumeFields.BALANCE, decimalFormat.format(balance));
        //收支总览结束


        //准备进行排序
        pieMap = new HashMap<>();
        //计算种类数
        Set<String> pieSet = new HashSet<>(kindList);
        int pieSize = pieClassify.size();
        float moneySum = 0;
        //将相应数据储存进即将排序的map，从而计算出哪个种类的money最高
        for (String res : pieSet) {
            pieMap.put(res, 0f);
            for (int i = 0; i < pieSize; i++) {
                if (pieClassify.get(i).getField(AnalysisFields.KIND).equals(res)) {
                    float money = pieClassify.get(i).getField(AnalysisFields.MONEY);
                    pieMap.put(res, pieMap.get(res) + money);
                    moneySum += money;
                }
            }
        }
        //转List便于排序
        List<Map.Entry<String, Float>> entryList = new ArrayList<>(pieMap.entrySet());
        //降序
        entryList.sort((t1, t2) -> t2.getValue().compareTo(t1.getValue()));

        //外围RvItem长
        int RvPiesize = entryList.size();
        //数据清洗
        for (int i = 0; i < RvPiesize; i++) {
            //拿到kind
            String kind = entryList.get(i).getKey();
            //kind_icon
            String kind_icon = "";
            //类别。支出还是收入
            String category = "";

            //子item的个数及数量
            List<MultipleItemEntity> list = new ArrayList<>();
            for (int j = 0; j < pieSize; j++) {
                if (kind.equals(pieClassify.get(j).getField(AnalysisFields.KIND))) {
                    list.add(pieClassify.get(j));
                    kind_icon = pieClassify.get(j).getField(MultipleFidls.NAME);
                    category = pieClassify.get(j).getField(AnalysisFields.CATEGORY);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            int itemSize = list.size();
            list.add(0, MultipleItemEntity
                    .builder()
                    .setItemType(ItemType.TEXT)
                    .setField(MultipleFidls.TEXT, stringBuilder.append(kind).append("(").append(itemSize).append(")").toString())
                    .build());
            pieRvItemMap.put(kind, list);

            float data = entryList.get(i).getValue();
            //储存pie图
            pieChartList.add(new PieEntry(data / moneySum, kind, kind));
            //父item
            pieRvList.add(MultipleItemEntity.builder()
                    .setItemType(DataAnalysisItemType.DATA_ANALYSIS_PIE_LIST)
                    .setField(AnalysisFields.KIND, kind)
                    .setField(AnalysisFields.MONEY, entryList.get(i).getValue())
                    .setField(AnalysisFields.SUM, itemSize)
                    .setField(MultipleFidls.NAME, kind_icon)
                    .setField(AnalysisFields.CATEGORY, category)
                    .setField(AnalysisFields.SCALE, data / moneySum)
                    .build());
        }
        //分类账单结束
    }


    @Override
    public HashMap<AnalyInConsumeFields, String> getInConsume() {
        return inConsumeMap;
    }

    @Override
    public List<List<Entry>> getDaysInConsume() {
        return charList;
    }

    @Override
    public List<String> getTimes() {
        return charTimes;
    }

    @Override
    public List<PieEntry> classifyPieChart() {
        return pieChartList;
    }

    @Override
    public List<MultipleItemEntity> classifyRvList() {
        return pieRvList;
    }

    @Override
    public List<MultipleItemEntity> classifyRvItemList(String kind) {
        return pieRvItemMap.get(kind);
    }

    @Override
    public String classifyPieKindMoney(String kind) {
        return decimalFormat.format(pieMap.get(kind));
    }

    @Override
    public List<MultipleItemEntity> billRvList() {
        return dayBillList;
    }

    @Override
    public float billScaleMoney() {
        return dayConsume;
    }

    @Override
    public List<MultipleItemEntity> billRvItemList(String date) {
        return dayBillItemMap.get(date);
    }

    @Override
    public boolean getDataMode() {
        return mode;
    }


}
