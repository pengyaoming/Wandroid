package com.example.wananzhuo.ui.mine.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.wananzhuo.Entity.LoginEntity;
import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.BitmapMessage;
import com.example.wananzhuo.Uilt.OnClickUtils;
import com.example.wananzhuo.base.BaseActivity;
import com.hjq.toast.ToastUtils;

import java.io.FileNotFoundException;

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
    @BindView(R.id.relative)
    RecyclerView relative;

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void initView() {
        String uri = SPUtils.getInstance("img").getString("image");
        try {
            BitmapMessage bitmapMessage = new BitmapMessage();
            Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(Uri.parse(uri)));
            Bitmap bitmap1 = bitmapMessage.blurBitmap(bitmap);
            Glide.with(this).asBitmap().load(bitmap1).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    Drawable drawable = new BitmapDrawable(resource);
                    relative.setBackground(drawable);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
        SPUtils.getInstance("user").put("user", data.getUsername());
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
