package com.petterp.latte_ui.recyclear;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.petterp.latte_ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petterp on 2019/4/23
 * Summary:需要传入两个泛型，一个是holder，一个是数据结构
 * 邮箱：1509492795@qq.com
 */
public class MultipleRecyclearAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity,
                MultipleViewHolder> implements BaseQuickAdapter.SpanSizeLookup{


    /**
     * 确保初始化一次Banner，防止重复加载
     */
    private boolean mIsInitBanner = false;

    /**
     * 设置图片加载策略
     */
    public static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     * 在这里加载一些布局
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleRecyclearAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }


    /**
     * //传入数据
     *
     * @param data
     * @return
     */
    public static MultipleRecyclearAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclearAdapter(data);
    }

    public static MultipleRecyclearAdapter create(BaseDataConverter converter) {
        return new MultipleRecyclearAdapter(converter.convert());
    }

    /**
     * 进行数据转换,针对不同的布局做出不同选择
     */
    @Override
    public void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = entity.getField(MultipleFidls.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = entity.getField(MultipleFidls.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_single));
              /*
              也可以链式调用
              GlideApp.with()
                        .load()
                        .centerCrop()*/
                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFidls.TEXT);
                imageUrl = entity.getField(MultipleFidls.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_multiple));
                holder.setText(R.id.tv_multiple, text);
                break;
            default:
                break;
        }
    }

    private void init() {
        //设置不同的item布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multipe_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multipe_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multipe_banner);

        //设置宽度监听
        setSpanSizeLookup(this);
        //加载时打开动画
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);

    }

    /**
     * 传入我们现有的ViewHolder
     *
     * @param view
     * @return
     */
    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFidls.SPAN_SIZE);
    }


}
