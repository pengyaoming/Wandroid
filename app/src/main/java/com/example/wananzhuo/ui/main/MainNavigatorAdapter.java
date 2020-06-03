package com.example.wananzhuo.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.Constants;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;


/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 11:41
 */
public class MainNavigatorAdapter extends CommonNavigatorAdapter {
    @Override
    public int getCount() {
        return Constants.MAIN.length;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_view, null, false);
        TextView textView = view.findViewById(R.id.tv_text);
        textView.setText(Constants.MAIN[index]);
        commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
            @Override
            public void onSelected(int index, int totalCount) {
                textView.setTextColor(ContextCompat.getColor(context, R.color.home_text));
            }

            @Override
            public void onDeselected(int index, int totalCount) {
                textView.setTextColor(ContextCompat.getColor(context, R.color.main_end_color));
            }

            @Override
            public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

            }

            @Override
            public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

            }
        });
        commonPagerTitleView.setContentView(view);
        commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(index);
            }
        });
        return commonPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        return null;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
