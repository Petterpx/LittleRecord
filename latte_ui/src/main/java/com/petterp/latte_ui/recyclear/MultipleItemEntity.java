package com.petterp.latte_ui.recyclear;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * @author Petterp on 2019/4/23
 * Summary:Item的样式->与MulitpleItemEntity建造者
 * 邮箱：1509492795@qq.com
 */
public class MultipleItemEntity implements MultiItemEntity {

    /**
     * //内存紧张时被回收
     */
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);


    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    /**
     * 控制Recyclear每一个item的样式
     *
     * @return
     */
    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFidls.ITEM_TYPE);
    }


    /**
     * 获取其他具体数据
     *
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FIELDS_REFERENCE.get();
    }

    /**
     * 插入数据
     *
     * @param key
     * @param value
     * @return
     */
    public final MultiItemEntity setFild(Object key, Object value) {
        FIELDS_REFERENCE.get().put(key, value);
        return this;
    }

    public static MultipleEntityBuilder builder() {
        return new MultipleEntityBuilder();
    }
}
