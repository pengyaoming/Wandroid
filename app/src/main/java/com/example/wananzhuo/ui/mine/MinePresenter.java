package com.example.wananzhuo.ui.mine;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.wananzhuo.Uilt.Constants;
import com.example.wananzhuo.Uilt.OnClickUtils;
import com.example.wananzhuo.base.BaseModel;
import com.example.wananzhuo.base.BasePresenter;
import com.example.wananzhuo.ui.home.HomeContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Android Studio.
 * User: pengyam
 * Date: 2020/5/15
 * Time: 13:40
 */
public class MinePresenter extends BasePresenter<BaseModel, MineContract.IView> implements MineContract.IPresenter {
    Context context;
    MineAdapter mAdapter;

    public MinePresenter(MineContract.IView rootView, Context context) {
        super(rootView);
        this.context = context;
    }

    public void initAdapter() {
        List<String> list = new ArrayList<>();
        mAdapter = new MineAdapter();
        list.addAll(Arrays.asList(Constants.Mine));
        mAdapter.addData(list);
        mRootView.setAdapter(mAdapter);
        String[] name = Constants.Mine;
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (mAdapter.getItem(position)) {
                    case "广场":
                        if (OnClickUtils.isFastClick()){

                        }
                        break;
                    case "项目":
                        if (OnClickUtils.isFastClick()){

                        }
                        break;
                    case "收藏":
                        if (OnClickUtils.isFastClick()){

                        }
                        break;
                    case "问答":
                        if (OnClickUtils.isFastClick()){

                        }
                        break;
                    case "设置":
                        if (OnClickUtils.isFastClick()){

                        }
                        break;
                }
            }
        });
    }

}
