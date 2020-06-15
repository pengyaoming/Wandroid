package com.example.wananzhuo.ui.mine.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wananzhuo.Entity.LoginEntity;
import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.OnClickUtils;
import com.example.wananzhuo.base.BaseActivity;
import com.hjq.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Android Studio.
 * User:
 * Date: 2020/6/10
 * Time: 11:17
 * Name: 作用类：
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.IVew {
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.edt_password_2)
    EditText edt_password_2;
    @BindView(R.id.tv_click)
    TextView tv_click;
    @BindView(R.id.edt_2_view)
    View edt_2_view;
    int index = 0;

    @OnClick({R.id.tv_click, R.id.tv_register})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_click:
                if (OnClickUtils.isFastClick()) {
                    String username = edt_phone.getText().toString();
                    String password = edt_password.getText().toString();
                    switch (index) {
                        case 0:
                            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                                mPresenter.getLogin(username, password);
                            } else {
                                ToastUtils.show("账号密码不能为空!");
                            }
                            break;
                        case 1:
                            String password_1 = edt_password_2.getText().toString();
                            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(password_1)) {
                                mPresenter.getRegister(username, password, password_1);
                            } else {
                                ToastUtils.show("账号密码不能为空!");
                            }
                            break;
                    }
                }
                break;
            case R.id.tv_register:
                if (OnClickUtils.isFastClick()) {
                    initRegister();
                }
                break;
        }
    }

    private void initRegister() {
        switch (index) {
            case 0:
                //注册
                edt_password_2.setVisibility(View.VISIBLE);
                edt_2_view.setVisibility(View.VISIBLE);
                tv_click.setText("注册");
                index = 1;
                break;
            case 1:
                //登录
                edt_password_2.setVisibility(View.GONE);
                edt_2_view.setVisibility(View.GONE);
                tv_click.setText("登录");
                index = 0;
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new LoginPresenter(this, this);
    }

    @Override
    public void setIntent(LoginEntity data) {
        Intent intent = new Intent();
        intent.putExtra("name", data.getPublicName());
        setResult(1, intent);
        finish();
    }

    @Override
    public void setRegister(LoginEntity data) {
        index = 1;
        initRegister();
        edt_phone.setText(data.getUsername());
    }
}
