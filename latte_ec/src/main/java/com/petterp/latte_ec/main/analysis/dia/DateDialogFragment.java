package com.petterp.latte_ec.main.analysis.dia;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.petterp.latte_core.util.dpsityUtil.DensityUtil;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.main.analysis.AnalyDiaFields;
import com.petterp.latte_ec.main.analysis.AnalyMessages;
import com.petterp.latte_ui.dialog.AnimStyle;
import com.petterp.latte_ui.recyclear.ItemType;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;


import java.util.ArrayList;
import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-09
 */
public class DateDialogFragment extends DialogFragment implements View.OnClickListener, TabLayout.BaseOnTabSelectedListener {
    private AnalyMessages messages;
    private TabLayout tabLayout;
    private RecyclerView rvDate;
    private int year;
    private int month;
    private List<MultipleItemEntity> itemEntitiesYear;
    private List<MultipleItemEntity> itemEntitiesMonth;
    private List<MultipleItemEntity> itemEntities;
    private DateRvAdapter adapter;
    private AppCompatTextView tvMonth;
    private AppCompatTextView tvYear;

    public DateDialogFragment() {
        //获取当前时间
        String[] times = TimeUtils.build().getYearMonthDays();
        year = Integer.parseInt(times[0]);
        month = Integer.parseInt(times[1].replaceAll("^(0+)", ""));
        messages = new AnalyMessages();
        messages.setYear(String.valueOf(year));
//        messages.setMode(AnalyDiaFields.DIA_SELECT_MONTH);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dia_anlysis_date, container, false);
        tvMonth = view.findViewById(R.id.tv_select_month);
        tvYear = view.findViewById(R.id.tv_select_year);
        tabLayout = view.findViewById(R.id.tl_dia_date);
        rvDate = view.findViewById(R.id.rv_dia_date);
        tvMonth.setOnClickListener(this);
        tvYear.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(this);
        setData();
        return view;
    }

    private void setData() {
        itemEntitiesYear = new ArrayList<>();
        itemEntities = new ArrayList<>();

        setItemMonth(String.valueOf(year));

        for (int i = 0; i < 12; i++) {
            String mode = String.valueOf(year--);
            itemEntitiesYear.add(MultipleItemEntity.builder().setField(MultipleFidls.ID, AnalyDiaFields.DIA_SELECT_YEAR).setItemType(ItemType.TEXT).setField(MultipleFidls.TEXT, mode).build());
            tabLayout.addTab(tabLayout.newTab().setText(mode + "年"));
        }
        adapter = new DateRvAdapter(itemEntities);
        rvDate.setAdapter(adapter);
        rvDate.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvDate.addOnItemTouchListener(new DateItemClcikListener(getDialog(), messages));
    }

    private void setItemMonth(String year) {
        itemEntities.clear();
        itemEntitiesMonth = new ArrayList<>();
        String[] months = getResources().getStringArray(R.array.draw_analysis_dia_date);
        if (String.valueOf(year).equals(TimeUtils.build().getYear())) {
            for (int i = 0; i < 12; i++) {
                if (i < month) {
                    itemEntitiesMonth.add(MultipleItemEntity.builder().setField(MultipleFidls.ID, AnalyDiaFields.DIA_SELECT_MONTH).setItemType(ItemType.TEXT).setField(MultipleFidls.TEXT, months[i]).setField(MultipleFidls.TAG, true).build());
                } else {
                    itemEntitiesMonth.add(MultipleItemEntity.builder().setField(MultipleFidls.ID, AnalyDiaFields.DIA_SELECT_MONTH).setItemType(ItemType.TEXT).setField(MultipleFidls.TEXT, months[i]).setField(MultipleFidls.TAG, false).build());
                }
            }
        } else {
            for (int i = 0; i < 12; i++) {
                itemEntitiesMonth.add(MultipleItemEntity.builder().setField(MultipleFidls.ID, AnalyDiaFields.DIA_SELECT_MONTH).setItemType(ItemType.TEXT).setField(MultipleFidls.TEXT, months[i]).setField(MultipleFidls.TAG, true).build());
            }
        }
        itemEntities.addAll(itemEntitiesMonth);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_select_month) {
//            selectData(itemEntitiesMonth, View.VISIBLE, AnalyDiaFields.DIA_SELECT_MONTH);
        } else {
//            selectData(itemEntitiesYear, View.GONE, AnalyDiaFields.DIA_SELECT_YEAR);
            Toast.makeText(getContext(), "正在开发", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 更新Rv数据
     *
     * @param itemEntitiesMode
     * @param visible
     * @param diaSelectMonth
     */
    private void selectData(List<MultipleItemEntity> itemEntitiesMode, int visible, int diaSelectMonth) {
        //判断，避免多次无效刷新
        if (diaSelectMonth != messages.getMode()) {
            itemEntities.clear();
            itemEntities.addAll(itemEntitiesMode);
            adapter.notifyDataSetChanged();
            tabLayout.setVisibility(visible);
            messages.setMode(diaSelectMonth);
            if (diaSelectMonth == AnalyDiaFields.DIA_SELECT_MONTH) {
                tvMonth.setBackgroundResource(R.drawable.analysis_dia_title_left);
                tvYear.setBackgroundColor(Color.TRANSPARENT);
            } else {
                tvMonth.setBackgroundColor(Color.TRANSPARENT);
                tvYear.setBackgroundResource(R.drawable.analysis_dia_title_right);
            }
        }
    }


    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getClass().getName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setWindowAnimations(AnimStyle.DEFAULT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = (int) (DensityUtil.getScreenWidth(getActivity()) * 0.9);
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        String year = tab.getText().toString().replace("年", "");
        if (adapter != null) {
            setItemMonth(year);
            adapter.notifyDataSetChanged();
        }
        messages.setYear(year);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
