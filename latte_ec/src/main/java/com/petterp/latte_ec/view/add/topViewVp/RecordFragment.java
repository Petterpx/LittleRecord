package com.petterp.latte_ec.view.add.topViewVp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.model.add.IAddTitleItems;
import com.petterp.latte_ec.presenter.AddPresenter;
import com.petterp.latte_ec.view.add.AddDelegateDirections;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

public class RecordFragment extends Fragment implements IRvItemKind {
    private AddPresenter addPresenter;
    private String mode;
    private List<MultipleItemEntity> list;
    private RecordListAdapter adapter;
    private RecordItemClickListener listener;

    public RecordFragment(AddPresenter addPresenter, String mode) {
        this.addPresenter = addPresenter;
        this.mode = mode;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.arrow_add_vp_consume, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_add_vp_consume);
        if (mode.equals(IAddTitleItems.CONSUME_ITEMS)) {
            list = addPresenter.getConsumeRvList();
        } else {
            list = addPresenter.getIncomeRvList();
        }
        final String name = addPresenter.getTitleRvKind()[1];
        adapter = new RecordListAdapter(list, name);
        View flooter = inflater.inflate(R.layout.item_vp_flooter_list, container, false);
        adapter.addFooterView(flooter);
        flooter.setOnClickListener(view1 -> addPresenter.getView().fragmentStart(AddDelegateDirections.actionAddDelegateToAddTopRvItemDelegate(addPresenter.getTitleMode())));
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        listener = new RecordItemClickListener(this);
        recyclerView.addOnItemTouchListener(listener);
        return view;
    }

    public void updateView() {
        List<MultipleItemEntity> listbf;
        if (mode.equals(IAddTitleItems.CONSUME_ITEMS)) {
            listbf = addPresenter.getConsumeRvList();
        } else {
            listbf = addPresenter.getIncomeRvList();
        }
        list.clear();
        list.addAll(listbf);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPosition(int position) {
        addPresenter.setRvItemPosition(position);
    }

    @Override
    public void setKinds(String[] kinds) {
        addPresenter.setTitleRvKind(kinds);
    }

    /**
     * 更新Item
     */
    public void updateItem(){
        listener.updateItem();
    }
}
