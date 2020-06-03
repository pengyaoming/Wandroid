package com.example.wananzhuo.ui.series;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.FlowLayoutManager;
import com.example.wananzhuo.ui.series.article.ArticleActivity;
import com.example.wananzhuo.ui.web.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/1
 * Time: 11:43
 * Name: 作用类：
 */
public class SeriesMessageAdapter extends BaseQuickAdapter<SeriesEntity, BaseViewHolder> {
    Context context;

    public SeriesMessageAdapter(Context context) {
        super(R.layout.view_series_item);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SeriesEntity seriesEntity) {
        SeriesItemAdapter itemAdapter = new SeriesItemAdapter();
        RecyclerView recyclerView = baseViewHolder.findView(R.id.RecyclerView);
        recyclerView.setLayoutManager(new FlowLayoutManager());
        recyclerView.setAdapter(itemAdapter);
        List<ChildrenBean> list = new ArrayList<>();
        list.addAll(seriesEntity.getChildren());
        itemAdapter.addData(list);

        itemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(context, ArticleActivity.class);
                intent.putExtra("title", itemAdapter.getItem(position).getName());
                intent.putExtra("id", itemAdapter.getItem(position).getId());
                context.startActivity(intent);
            }
        });
    }
}
