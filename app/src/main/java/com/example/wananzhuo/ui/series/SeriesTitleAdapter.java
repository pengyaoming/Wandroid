package com.example.wananzhuo.ui.series;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.R;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/1
 * Time: 11:26
 * Name: 作用类：
 */
public class SeriesTitleAdapter extends BaseQuickAdapter<SeriesEntity, BaseViewHolder> {
    public SeriesTitleAdapter() {
        super(R.layout.view_series);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SeriesEntity seriesEntity) {
        baseViewHolder.setText(R.id.tv_title, seriesEntity.getName());
    }
}
