package com.petterp.latte_ec.model.analysis;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.petterp.latte_core.util.litepal.BillInfo;
import com.petterp.latte_core.util.litepal.EveryBillCollect;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.model.home.IHomeRvFields;
import com.petterp.latte_ec.view.analysis.DataAnalysisItemType;
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
        //清空分类账单数据
        pieRvList.clear();
        pieChartList.clear();
        pieRvItemMap.clear();

        //清空每天收支波动图数据
        charTimes.clear();
        for (int i = 0; i < 3; i++) {
            charList.get(i).clear();
        }

        //kind
        List<String> kindList = new ArrayList<>();
        //存储数据库item
        List<MultipleItemEntity> pieClassify = new ArrayList<>();

        float income = 0, consum = 0, balance, avg;
        List<EveryBillCollect> all = LitePal.findAll(EveryBillCollect.class);
        charTimes.add("");
        float charSum = 1;
        int size = all.size();
        for (int i = 0; i < size; i++) {
            if (TimeUtils.build().isMonth(all.get(i).getLongDate(), year + "-" + month)) {
                float incomeMoney = all.get(i).getIncome();
                float consumeMoney = all.get(i).getConsume();
                income += incomeMoney;
                consum += consumeMoney;
                charList.get(0).add(new Entry(charSum, Math.abs(consumeMoney)));
                charList.get(1).add(new Entry(charSum, incomeMoney));
                ++charSum;
                charTimes.add(TimeUtils.build().getMonthDay(all.get(i).getLongDate()));

                //分类账单
                //根据key查询天数数据
                List<BillInfo> billInfos = LitePal.where("key=?", all.get(i).getKey()).find(BillInfo.class);
                int sizej = billInfos.size();
                for (int j = 0; j < sizej; j++) {
                    kindList.add(billInfos.get(j).getKind());
                    String category = billInfos.get(j).getCategory();
                    String kind = billInfos.get(j).getKind();
                    //储存category
                    pieClassify.add(MultipleItemEntity
                            .builder()
                            .setItemType(DataAnalysisItemType.DATA_ANALYSIS_PIE_ITEM_LIST)
                            .setField(AnalysisFields.KIND, kind)
                            .setField(AnalysisFields.MONEY, Math.abs(billInfos.get(j).getMoney()))
                            .setField(AnalysisFields.CATEGORY, category)
                            .setField(MultipleFidls.NAME, billInfos.get(j).getKindIcon())
                            .setField(AnalysisFields.MONTH_DAY, TimeUtils.build().getMonthDay(billInfos.get(j).getLongDate()))
                            .build());
                }
            }
        }
        charTimes.add("");
        balance = income + consum;
        String[] times = TimeUtils.build().getYearMonthDays();
        if ((year + month).equals(times[0] + times[1])) {
            avg = balance / Integer.parseInt(times[2].replaceAll("^(0+)", ""));
        } else {
            avg = balance / 30;
        }
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
//        Log.e("demo", "父" + pieRvMap.size() + "---子-" + pieRvItemMap.get("三餐").size());
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

}
