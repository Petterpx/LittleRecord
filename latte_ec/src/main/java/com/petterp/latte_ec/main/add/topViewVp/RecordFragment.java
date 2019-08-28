package com.petterp.latte_ec.main.add.topViewVp;

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
import com.petterp.latte_ec.main.add.IAddTitleItems;
import com.petterp.latte_ec.main.add.AddPresenter;
import com.petterp.latte_ec.main.add.AddDelegateDirections;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.List;

public class RecordFragment extends Fragment implements IRvItemKind {
    private AddPresenter addPresenter;
    private String mode;
    private List<MultipleItemEntity> list;
    private RecordListAdapter adapter;
    private View view;

    public RecordFragment(AddPresenter addPresenter, String mode) {
        this.addPresenter = addPresenter;
        this.mode = mode;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.arrow_add_vp_consume, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.rv_add_vp_consume);
            if (mode.equals(IAddTitleItems.CONSUME_ITEMS)) {
                list = addPresenter.getConsumeRvList();
            } else {
                list = addPresenter.getIncomeRvList();
            }
            final String name=addPresenter.getTitleRvKind(mode)[1];
            adapter = new RecordListAdapter(list, name);
            View flooter = inflater.inflate(R.layout.item_vp_flooter_list, container, false);
            adapter.addFooterView(flooter);
            flooter.setOnClickListener(view1 -> addPresenter.getView().fragmentStart(AddDelegateDirections.actionAddDelegateToAddTopRvItemDelegate(addPresenter.getTitleMode())));
            GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(manager);
            RecordItemClickListener listener = new RecordItemClickListener(this);
            recyclerView.addOnItemTouchListener(listener);
        }
        return view;
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
     * 用于跳转后的View销毁
     */
    public void removeView() {
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    public void addRvItem(){
        adapter.notifyItemChanged(list.size());
    }

    public void updateRvItem(int position){
        adapter.notifyItemChanged(position);
    }

    public void delegateRvItem(){
        adapter.notifyDataSetChanged();
    }
}
