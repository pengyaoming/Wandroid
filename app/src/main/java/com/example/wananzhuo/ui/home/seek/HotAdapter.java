package com.example.wananzhuo.ui.home.seek;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.Entity.HotEntity;
import com.example.wananzhuo.R;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/25
 * Time: 15:28
 */
public class HotAdapter extends BaseQuickAdapter<HotEntity, BaseViewHolder> {
    public HotAdapter() {
        super(R.layout.item_seek_hot);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HotEntity hotEntity) {
        baseViewHolder.setText(R.id.tv_text, hotEntity.getName());

    }
}
