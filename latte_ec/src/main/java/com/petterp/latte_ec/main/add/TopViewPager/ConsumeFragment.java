package com.petterp.latte_ec.main.add.TopViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ConsumeFragment extends Fragment {

    @BindView(R2.id.rv_add_vp_consume)
    RecyclerView mRecyclerView = null;
    private  ConsumeListAdapter  adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arrow_add_vp_consume, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.rv_add_vp_consume);
        List<MultipleItemEntity> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(ConsumeListItemType.ITEM_CONSUME_LIST)
                    .setField(MultipleFidls.NAME, "{icon-award}")
                    .setField(MultipleFidls.TITLE, "三餐")
                    .setField(MultipleFidls.ID, ""+i)
                    .build();
            list.add(itemEntity);
        }
        adapter = new ConsumeListAdapter(list);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new ConsumeItemClickListener(getContext()));
        return view;
    }


}
