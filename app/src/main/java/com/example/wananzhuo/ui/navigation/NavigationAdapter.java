package com.example.wananzhuo.ui.navigation;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/4
 * Time: 9:42
 * Name: 作用类：
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationEntity, BaseViewHolder> {
    Context context;

    public NavigationAdapter(Context context) {
        super(R.layout.view_navigation);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NavigationEntity navigationEntity) {
        NavigationItemAdapter itemAdapter = new NavigationItemAdapter();
        baseViewHolder.setText(R.id.tv_title, navigationEntity.getName());
        RecyclerView recyclerView = baseViewHolder.findView(R.id.recyclerView);
        recyclerView.setLayoutManager(new FlowLayoutManager());
        recyclerView.setAdapter(itemAdapter);
        List<ArticlesBean> list = new ArrayList<>();
        list.addAll(navigationEntity.getArticles());
        itemAdapter.addData(list);
        itemAdapter.setOnItemClickListener((adapter, view, position) -> onItemIcklist.onClickList(itemAdapter.getItem(position).getLink(), itemAdapter.getItem(position).getTitle()));


    }


    public interface OnItemIcklist {
        void onClickList(String link, String title);
    }

    private OnItemIcklist onItemIcklist;

    public void setOnItemIcklist(OnItemIcklist onItemIcklist) {
        this.onItemIcklist = onItemIcklist;
    }
}
