package com.example.wananzhuo.ui.wx;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.wananzhuo.Entity.WxTitleEntity;
import com.example.wananzhuo.R;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/26
 * Time: 8:57
 */
public class WxTitleNavigatorAdapter extends CommonNavigatorAdapter {
    private List<WxTitleEntity> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    public void setData(List<WxTitleEntity> list) {
        this.list = list;
        //刷新UI
        notifyDataSetChanged();
    }

    public List<WxTitleEntity> getAll() {
        return list;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
        commonPagerTitleView.setContentView(R.layout.item_main_view);
        TextView tv_text = commonPagerTitleView.findViewById(R.id.tv_text);
        tv_text.setText(list.get(index).getName());
        commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
            @Override
            public void onSelected(int index, int totalCount) {
                tv_text.setTextColor(ContextCompat.getColor(context, R.color.home_text));

            }

            @Override
            public void onDeselected(int index, int totalCount) {
                tv_text.setTextColor(ContextCompat.getColor(context, R.color.main_end_color));
            }

            @Override
            public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

            }

            @Override
            public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

            }
        });
        commonPagerTitleView.setOnClickListener(v -> mOnItemClickListener.OnItemClick(index));
        return commonPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        linePagerIndicator.setStartInterpolator(new AccelerateInterpolator());
        linePagerIndicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
        linePagerIndicator.setYOffset(UIUtil.dip2px(context, 4));
        linePagerIndicator.setLineHeight(UIUtil.dip2px(context, 1.8));
        linePagerIndicator.setRoundRadius(UIUtil.dip2px(context, 2));
        linePagerIndicator.setColors(ContextCompat.getColor(context, R.color.white));
        return linePagerIndicator;
    }

    public interface OnItemClickListener {
        void OnItemClick(int index);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}

