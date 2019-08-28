package com.petterp.latte_ec.main.add.topViewVp.rvItems;

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
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-24
 */
public class AddTopRvItemFragment extends Fragment {
    private List<MultipleItemEntity> list;
    private AddTopRvItemAdapter adapter;

    public AddTopRvItemFragment(List<MultipleItemEntity> list) {
        this.list = list;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arrow_add_vp_consume, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_add_vp_consume);
        adapter = new AddTopRvItemAdapter(list);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnItemTouchListener(new AddTopRvItemClickListener(getFragmentManager()));
        return view;
    }

    void addView(){
        adapter.notifyItemChanged(list.size());
    }

    void delegateView(){
        adapter.notifyDataSetChanged();
    }
}
