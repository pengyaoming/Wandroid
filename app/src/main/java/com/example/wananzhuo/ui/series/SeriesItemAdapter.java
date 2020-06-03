package com.example.wananzhuo.ui.series;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.R;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/1
 * Time: 14:14
 * Name: 作用类：
 */
public class SeriesItemAdapter extends BaseQuickAdapter<ChildrenBean, BaseViewHolder> {
    public SeriesItemAdapter() {
        super(R.layout.view_series_title);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ChildrenBean childrenBean) {
        baseViewHolder.setText(R.id.tvTitle, childrenBean.getName());
    }
}
