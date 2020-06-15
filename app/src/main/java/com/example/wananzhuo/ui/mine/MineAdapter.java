package com.example.wananzhuo.ui.mine;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.wananzhuo.R;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/6/15
 * Time: 10:24
 * Name: 作用类：
 */
public class MineAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MineAdapter() {
        super(R.layout.item_mine);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.tv_title, s);
    }
}
