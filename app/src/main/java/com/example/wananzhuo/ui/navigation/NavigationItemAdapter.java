package com.example.wananzhuo.ui.navigation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.R;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/4
 * Time: 10:10
 * Name: 作用类：
 */
public class NavigationItemAdapter extends BaseQuickAdapter<ArticlesBean, BaseViewHolder> {
    public NavigationItemAdapter() {
        super(R.layout.view_series_title);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ArticlesBean articlesBean) {
        baseViewHolder.setText(R.id.tvTitle, articlesBean.getTitle());

    }
}
