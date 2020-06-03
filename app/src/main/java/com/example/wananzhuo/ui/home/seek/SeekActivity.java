package com.example.wananzhuo.ui.home.seek;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wananzhuo.R;
import com.example.wananzhuo.base.BaseActivity;
import com.example.wananzhuo.ui.home.HomeAdapter;
import com.hjq.toast.ToastUtils;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;


import butterknife.BindView;
import butterknife.OnClick;

import static com.blankj.utilcode.util.ConvertUtils.dp2px;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/25
 * Time: 13:50
 */
public class SeekActivity extends BaseActivity<SeekPresenter> implements SeekContract.IView {
    @BindView(R.id.flowRecyclerView)
    RecyclerView flowRecyclerView;
    @BindView(R.id.edt_edit)
    EditText edt_edit;
    @BindView(R.id.tv_seek)
    TextView tv_seek;
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.tv_title)
    TextView tv_title;
    boolean b = false;
    boolean a = false;


    @OnClick({R.id.tv_seek, R.id.img_left})

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_seek:
                String name = edt_edit.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.show("搜索选项为空，请输入数据");
                    return;
                }
                mPresenter.getSeek(name);
                break;
            case R.id.img_left:
                finish();
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_seek;
    }

    @Override
    public void initView() {
        tv_title.setText("搜索");
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        flowRecyclerView.addItemDecoration(new SpaceItemDecoration(dp2px(5)));
        flowRecyclerView.setLayoutManager(flowLayoutManager);
        mPresenter.initFlow();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.initSeek();
    }

    @Override
    public void initData() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                /**
                 *判断是否滑倒顶部或者底部的方法
                 *1代表底部
                 *-1代表顶部
                 */
                b = recyclerView.canScrollVertically(1);
                a = recyclerView.canScrollVertically(-1);
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter = new SeekPresenter(this, this);
    }

    @Override
    public void setFlow(HotAdapter mHotAdapter) {
        flowRecyclerView.setAdapter(mHotAdapter);
        mPresenter.getHotKey();
    }

    @Override
    public void setHomeAdapter(HomeAdapter mHomeAdapter) {
        recyclerView.setAdapter(mHomeAdapter);
    }

    @Override
    public void setEdit(String name) {
        edt_edit.setText(name);
    }
}
