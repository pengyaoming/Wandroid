package com.example.wananzhuo.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.wananzhuo.R;
import com.example.wananzhuo.base.BaseFragment;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:39
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.IView {

    public static Fragment getInstance() {
        MineFragment fragment = new MineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("home", "我的");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, null, false);
    }

    @Override
    protected void initData() {

    }
}
