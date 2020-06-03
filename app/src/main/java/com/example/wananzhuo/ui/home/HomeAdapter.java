package com.example.wananzhuo.ui.home;


import android.text.Html;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.Entity.HomeEntity;
import com.example.wananzhuo.R;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/19
 * Time: 9:58
 */
public class HomeAdapter extends BaseQuickAdapter<HomeEntity, BaseViewHolder> implements LoadMoreModule {
    public HomeAdapter() {
        super(R.layout.view_recyclerview);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeEntity homeEntity) {
        holder.setText(R.id.tv_title, Html.fromHtml(homeEntity.getTitle()));
        holder.setText(R.id.username, homeEntity.getChapterName());
        holder.setText(R.id.tv_time, homeEntity.getNiceDate());
        holder.setText(R.id.tv_class, homeEntity.getSuperChapterName());
        switch (homeEntity.getType()) {
            case 0:
                holder.setGone(R.id.tv_top, true);
                break;
            case 1:
                holder.setVisible(R.id.tv_top, true);
                break;
        }
    }
}
