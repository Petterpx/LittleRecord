package com.petterp.latte_ec.main.home;

import android.annotation.SuppressLint;

import com.petterp.latte_core.util.litepal.BillInfo;
import com.petterp.latte_core.util.litepal.EveryBillCollect;
import com.petterp.latte_core.util.litepal.UserInfo;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_core.util.time.SystemClock;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ui.recyclear.MultipleFidls;
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
    private int addPosition = 0;
    //月支出
    private float consumeMoney;
    //月收入
    private float incomeMoney;
    //月结余
    private float surplusMoney;
    //title详细
    private HashMap<IHomeRvFields, String> titleHashMap;
    //数字格式化
    private DecimalFormat decimalFormat;
    //日期key，默认当天
    private String timeKey = TimeUtils.build().getLongTimekey();
    //header位置
    private int headerPosition = 0;
    //手指按下位置
    private int onDownPosition = 1;
    //header头添加时的位置
    private int updateAddPosition = 0;

    //当前状态
    private int stateMode = IHomeStateType.ADD;

    private boolean addMode = false;
    //天数
    private int collectSize = 0;


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
        decimalFormat = new DecimalFormat("###################.##");
    }

    @Override
    public void queryInfo() {
        consumeMoney = 0;
        incomeMoney = 0;
        surplusMoney = 0;
        itemEntities.clear();
        //降序获取数据
        List<EveryBillCollect> collects = LitePal
                .order("longDate desc")
                .find(EveryBillCollect.class);
        collectSize = collects.size();
        String yearMonth = TimeUtils.build().getYearMonth();
        for (int i = 0; i < collectSize; i++) {
            //如果是当天改为今天
            long longTime = collects.get(i).getLongDate();
            String time = TimeUtils.build().getYearMonthDay(longTime);
            String day = TimeUtils.build().getday(longTime);
            if (time.equals(TimeUtils.build().getYearMonthDay())) {
                day = "今天";
            }

            String key = collects.get(i).getKey();
            //获取相应时间的具体item
            List<BillInfo> billInfos = LitePal.
                    where("key=?", key)
                    .find(BillInfo.class);
            int billInfoSize = billInfos.size();
            if (i == 0) {
                addPosition = billInfoSize + 1;
            }

            MultipleItemEntity itemHeader = MultipleItemEntity.builder()
                    .setItemType(HomeItemType.HOME_DETAIL_HEADER)
                    .setField(IHomeRvFields.YEAR_MONTH_DAY, time)
                    .setField(IHomeRvFields.HEADER_SUM, billInfoSize)
                    .setField(IHomeRvFields.DAY, day)
                    .setField(IHomeRvFields.CONSUME, collects.get(i).getConsume())
                    .setField(IHomeRvFields.KEY, key)
                    .setField(IHomeRvFields.LONG_TIME, longTime)
                    .build();

            //添加头,记得判断
            if (billInfoSize > 0) {
                itemEntities.add(itemHeader);
            }

            for (int j = 0; j < billInfoSize; j++) {
                MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setItemType(HomeItemType.HOME_DETAIL_LIST)
                        .setField(IHomeRvFields.CATEGORY, billInfos.get(j).getCategory())
                        .setField(IHomeRvFields.KIND, billInfos.get(j).getKind())
                        .setField(IHomeRvFields.CONSUME_I, billInfos.get(j).getMoney())
                        .setField(IHomeRvFields.LONG_TIME, billInfos.get(j).getLongDate())
                        .setField(IHomeRvFields.KEY, billInfos.get(j).getKey())
                        .setField(MultipleFidls.NAME, billInfos.get(j).getKindIcon())
                        .setField(IHomeRvFields.REMARK, billInfos.get(j).getRemark())
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
    public void update(MultipleItemEntity itemEntity) {
        //先拿到以前数据
        MultipleItemEntity multipleItemEntity = itemEntities.get(onDownPosition);
        //状态
        String state = multipleItemEntity.getField(IHomeRvFields.CATEGORY);
        String stateNew = itemEntity.getField(IHomeRvFields.CATEGORY);
        long datetime = multipleItemEntity.getField(IHomeRvFields.LONG_TIME);
        float money = multipleItemEntity.getField(IHomeRvFields.CONSUME_I);
        float moneyNew = itemEntity.getField(IHomeRvFields.CONSUME_I);
        List<EveryBillCollect> collects = LitePal.where("key = ?", timeKey).find(EveryBillCollect.class);
        float consume = collects.get(0).getConsume();
        float income = collects.get(0).getIncome();
        //判断是否是本月，再决定更改title数据
        boolean mode = TimeUtils.build().isMonth(itemEntities.get(headerPosition).getField(IHomeRvFields.LONG_TIME));

        //从外层开始判断，先判以前是收入还是支出
        if (state.equals(IHomeTitleRvItems.CONSUME)) {
            //内层决定如何改变
            if (stateNew.equals(IHomeTitleRvItems.CONSUME)) {
                consume = consume - money + moneyNew;
                if (mode) {
                    consumeMoney = consumeMoney - money + moneyNew;
                }
            } else {
                consume -= money;
                income += moneyNew;
                if (mode) {
                    consumeMoney -= money;
                    incomeMoney += moneyNew;
                }
            }
        } else {
            if (stateNew.equals(IHomeTitleRvItems.INCOME)) {
                income = income - money + moneyNew;
                if (mode) {
                    incomeMoney = incomeMoney - money + moneyNew;
                }
            } else {
                income -= money;
                consume += moneyNew;
                if (mode) {
                    incomeMoney -= money;
                    consumeMoney += moneyNew;
                }
            }
        }
        surplusMoney = consumeMoney + incomeMoney;
        EveryBillCollect collect = new EveryBillCollect();
        collect.setIncome(income);
        collect.setConsume(consume);
        //更新Header数据
        collect.updateAllAsync("key=?", timeKey).listen(rowsAffected -> {
        });
        //更新RvHeader数值
        itemEntities.get(headerPosition).setFild(IHomeRvFields.CONSUME, consume);
        //重新计算结余
        //更新子项
        BillInfo info = new BillInfo();
        info.setCategory(itemEntity.getField(IHomeRvFields.CATEGORY));
        info.setKind(itemEntity.getField(IHomeRvFields.KIND));
        info.setLongDate(itemEntity.getField(IHomeRvFields.LONG_TIME));
        info.setMoney(moneyNew);
        info.setLongDate(SystemClock.now());
        info.setKindIcon(itemEntity.getField(MultipleFidls.NAME));
        info.setRemark(itemEntity.getField(IHomeRvFields.REMARK));
        info.updateAllAsync("key=? and longDate=?", timeKey, "" + datetime).listen(rowsAffected -> {
        });
        //更新Rv
        itemEntities.set(onDownPosition, itemEntity);
    }

    @Override
    public void delete(MultipleItemEntity itemEntity) {
        String key = itemEntity.getField(IHomeRvFields.KEY);
        float money = itemEntity.getField(IHomeRvFields.CONSUME_I);
        long time = itemEntity.getField(IHomeRvFields.LONG_TIME);
        //判断是否是本月，再决定更改title数据
        boolean mode = TimeUtils.build().isMonth(itemEntities.get(headerPosition).getField(IHomeRvFields.LONG_TIME));
        List<EveryBillCollect> collect = LitePal.where("key=?", key).find(EveryBillCollect.class);
        EveryBillCollect billCollect = new EveryBillCollect();
        if (itemEntity.getField(IHomeRvFields.CATEGORY).equals(IHomeTitleRvItems.CONSUME)) {
            float consume = collect.get(0).getConsume() - money;
            billCollect.setConsume(consume);
            itemEntities.get(headerPosition).setFild(IHomeRvFields.CONSUME, consume);
            if (mode) {
                consumeMoney -= money;
            }
        } else {
            float income = collect.get(0).getIncome() - money;
            billCollect.setIncome(income);
//            itemEntities.get(headerPosition).setFild(IHomeRvFields.CONSUME,income);
            if (mode) {
                incomeMoney -= money;
            }
        }
        billCollect.setSum(collect.get(0).getSum() - 1);
        if (billCollect.getSum() == 0) {
            LitePal.deleteAllAsync(EveryBillCollect.class, "key=?", timeKey).listen(rowsAffected -> {
            });
        } else {
            billCollect.updateAllAsync("key=?", timeKey).listen(rowsAffected -> {
            });
        }
        surplusMoney = incomeMoney + consumeMoney;
        itemEntities.remove(itemEntity);

        LitePal.deleteAllAsync(BillInfo.class, "longDate=?", "" + time).listen(rowsAffected -> {
        });
        removeHeaderSum(headerPosition);
    }

    @Override
    public void add(MultipleItemEntity itemEntity) {
        //操作数据库
        sqlAdd(itemEntity);
        if (itemEntities.size() == 0 || addMode) {
            queryInfo();
        } else {
            itemEntities.add(addPosition, itemEntity);
            addPosition += 1;
            float money = itemEntity.getField(IHomeRvFields.CONSUME_I);
            if (itemEntity.getField(IHomeRvFields.CATEGORY).equals(IHomeTitleRvItems.INCOME)) {
                incomeMoney = incomeMoney + money;
            } else {
                consumeMoney = consumeMoney + money;
                float consume = itemEntities.get(0).getField(IHomeRvFields.CONSUME);
                itemEntities.get(0).setFild(IHomeRvFields.CONSUME, Float.parseFloat(decimalFormat.format(Math.abs(consume) + Math.abs(money))));
            }
            surplusMoney = incomeMoney + consumeMoney;
            addHeaderSum(0);
        }
    }

    /**
     * 用于每次更新完后，header头的自动相加，避免数据库操作
     */
    private void addHeaderSum(int i) {
        int sum = itemEntities.get(i).getField(IHomeRvFields.HEADER_SUM);
        itemEntities.get(i).setFild(IHomeRvFields.HEADER_SUM, sum + 1);
    }

    /**
     * 用于每次更新完后，header头的自动相减，避免数据库操作
     *
     * @param i
     */
    private void removeHeaderSum(int i) {
        int sum = itemEntities.get(i).getField(IHomeRvFields.HEADER_SUM);
        sum -= 1;
        if (sum == 0) {
            itemEntities.remove(headerPosition);
        } else {
            itemEntities.get(i).setFild(IHomeRvFields.HEADER_SUM, sum);
        }
    }

    @Override
    public HashMap<IHomeRvFields, String> getTitleInfo() {
        titleHashMap.clear();
        titleHashMap.put(IHomeRvFields.CONSUME, "支出：" + decimalFormat.format(consumeMoney));
        titleHashMap.put(IHomeRvFields.INCOME, "收入：" + decimalFormat.format(incomeMoney));
        titleHashMap.put(IHomeRvFields.SUR_PLUS, decimalFormat.format(surplusMoney));
        return titleHashMap;
    }

    @Override
    public void addHeader(MultipleItemEntity itemEntity) {
        sqlAdd(itemEntity);
        addHeaderSum(headerPosition);
        itemEntities.add(updateAddPosition, itemEntity);
        //更改header的高度
        String month = TimeUtils.build().getday(itemEntity.getField(IHomeRvFields.LONG_TIME));
        if (month.equals(TimeUtils.build().getday())) {
            float money = itemEntity.getField(IHomeRvFields.CONSUME_I);
            if (itemEntity.getField(IHomeRvFields.CATEGORY).equals(IHomeTitleRvItems.INCOME)) {
                incomeMoney += Math.abs(money);
            } else {
                consumeMoney -= Math.abs(money);
                float consume = itemEntities.get(headerPosition).getField(IHomeRvFields.CONSUME);
                itemEntities.get(headerPosition).setFild(IHomeRvFields.CONSUME, Float.parseFloat(decimalFormat.format(Math.abs(consume) + Math.abs(money))));
            }
            surplusMoney = incomeMoney + consumeMoney;
        }
        if (headerPosition == 0) {
            addPosition += 1;
        }
    }


    @Override
    public void setKey(String key) {
        this.timeKey = key;
    }

    @Override
    public String getKey() {
        return timeKey;
    }

    @Override
    public void setHeaderPosition(String key) {
        headerPosition = 0;
        this.timeKey = key;
        List<EveryBillCollect> collects = LitePal
                .order("longDate desc")
                .select("sum", "key").find(EveryBillCollect.class);
        int size = collects.size();
        if (size <= 1) {
            headerPosition = 0;
        } else {
            for (int i = 0; i < size; i++) {
                if (key.equals(collects.get(i).getKey())) {
                    if (i == 0) {
                        break;
                    }
                    headerPosition += 1;
                    break;
                }
                headerPosition += collects.get(i).getSum();
            }
        }
    }

    @Override
    public void setHeaderPosition(int headerPosition) {
        this.headerPosition = headerPosition;
    }


    @Override
    public void setOndownPosition(int position) {
        this.onDownPosition = position;
    }

    @Override
    public void setStateMode(int state) {
        this.stateMode = state;
    }

    @Override
    public int getStateMode() {
        return stateMode;
    }

    @Override
    public void setAddPosition(int position) {
        this.updateAddPosition = position;
    }

    @Override
    public String getDrawUserIcon() {
        List<UserInfo> userId = LitePal.where("account=?", LatterPreference.getUserId()).limit(1).find(UserInfo.class);
        return userId.get(0).getIconUrl();
    }

    @Override
    public String getDrawRecord() {
        if (collectSize == 0) {
            return "还没有记录数据呢，快去记录吧!";
        } else {
            return "已经记录 " + collectSize + " 天啦,继续加油!";
        }
    }

    private void sqlAdd(MultipleItemEntity itemEntity) {
        //判断是否已经存入当天数据
        List<EveryBillCollect> collects
                = LitePal.where("key=?", timeKey)
                .select("consume", "income", "sum", "id")
                .find(EveryBillCollect.class);

        EveryBillCollect collect = new EveryBillCollect();

        String kind = itemEntity.getField(IHomeRvFields.KIND);
        float money = itemEntity.getField(IHomeRvFields.CONSUME_I);
        String category = itemEntity.getField(IHomeRvFields.CATEGORY);
        long time = itemEntity.getField(IHomeRvFields.LONG_TIME);
        String kindIcon = itemEntity.getField(MultipleFidls.NAME);

        //如果是支出
        if (category.equals(IHomeTitleRvItems.CONSUME)) {
            if (collects.size() > 0) {
                addMode = false;
                //修改
                collect.setConsume((collects.get(0).getConsume() + money));
                collect.setSum(collects.get(0).getSum() + 1);
            } else {
                addHeaderSql(money, 0, time);
            }
        } else {
            if (collects.size() > 0) {
                addMode = false;
                collect.setIncome((collects.get(0).getIncome() + money));
                collect.setSum(collects.get(0).getSum() + 1);
            } else {
                addHeaderSql(0, money, time);
            }

        }
        collect.updateAll("key=?", timeKey);
        //新增到日账单详细
        BillInfo info = new BillInfo(timeKey, time, money, itemEntity.getField(IHomeRvFields.REMARK), kind, category, kindIcon);
        info.save();
    }

    private void addHeaderSql(float consume, float income, long time) {
        addMode = true;
        new EveryBillCollect(timeKey, time, "petterp", consume, income, 1).save();
    }

    @Override
    public void updateItem(String kind, String kindNew, String category) {
        BillInfo billInfo = new BillInfo();
        billInfo.setKind(kindNew);
        billInfo.updateAll("kind=?", kind);
        int size = itemEntities.size();
        for (int i = 0; i < size; i++) {
            MultipleItemEntity itemEntity = itemEntities.get(i);
            if (itemEntity.getItemType() == HomeItemType.HOME_DETAIL_LIST) {
                if (itemEntity.getField(IHomeRvFields.KIND).equals(kind) && itemEntity.getField(IHomeRvFields.CATEGORY).equals(category)) {
                    itemEntities.get(i).setFild(IHomeRvFields.KIND, kindNew);
                }
            }
        }
    }

    @Override
    public void delegateItem(String kind, String category) {
        BillInfo billInfo = new BillInfo();
        billInfo.setKind("其他");
        billInfo.updateAll("kind=?", kind);
        int size = itemEntities.size();
        for (int i = 0; i < size; i++) {
            MultipleItemEntity itemEntity = itemEntities.get(i);
            if (itemEntity.getItemType() == HomeItemType.HOME_DETAIL_LIST) {
                if (itemEntity.getField(IHomeRvFields.KIND).equals(kind) && itemEntity.getField(IHomeRvFields.CATEGORY).equals(category)) {
                    itemEntities.get(i).setFild(IHomeRvFields.KIND, "其他");
                }
            }
        }
    }
}
