package com.petterp.latte_ec.view.add.topViewVp;

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
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

public class RecordFragment extends Fragment {
    @BindView(R2.id.rv_add_vp_consume)
    RecyclerView mRecyclerView = null;
    private List<MultipleItemEntity> list;
    private String name;

    public RecordFragment(List<MultipleItemEntity> list, String name) {
        this.list = list;
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arrow_add_vp_consume, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_add_vp_consume);
        RecordListAdapter adapter = new RecordListAdapter(list, name);
//        adapter.addFooterView()
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new RecordItemClickListener());
        return view;
    }
}
