package com.example.wananzhuo.ui.mine;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.BitmapMessage;
import com.example.wananzhuo.Uilt.getPhotoFromPhotoAlbum;
import com.example.wananzhuo.base.BaseFragment;
import com.youth.banner.util.BannerUtils;


import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.example.wananzhuo.Uilt.Constants.*;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/15
 * Time: 13:39
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.IView, EasyPermissions.PermissionCallbacks {
    @BindView(R.id.img_title)
    ImageView img_title;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.liner)
    LinearLayout liner;
    @BindView(R.id.jif)
    TextView jif;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.relative)
    RelativeLayout relative;
    String path;
    String fileName;
    String mFilePath;

    @OnClick({R.id.img_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_title:
                if (EasyPermissions.hasPermissions(getContext(), PESS)) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 2);
                } else {
                    EasyPermissions.requestPermissions(this, "需要获取权限", PASS_INDEX, PESS);
                }
        }
    }

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
        mPresenter = new MinePresenter(this, getContext());
        setImage();
        if (SPUtils.getInstance("user").getString("user") == null) {
            tv_name.setText("未登录");
        } else {
            tv_name.setText(SPUtils.getInstance("user").getString("user"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String photoPath;
        if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = getPhotoFromPhotoAlbum.getRealPathFromUri(getContext(), data.getData());
            int sdkVersion = Build.VERSION.SDK_INT;
            if (sdkVersion == 29) {
                Uri uri = getImageContentUri(getContext(), photoPath);
                Glide.with(getContext()).load(uri).into(img_title);
                //高斯模糊
                getBack(uri);
            } else {
                Glide.with(getContext()).load(photoPath).into(img_title);
                getBack(Uri.parse(photoPath));
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void getBack(Uri uri) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri));
            BitmapMessage bitmapMessage = new BitmapMessage();
            Bitmap bitmap1 = bitmapMessage.blurBitmap(bitmap);
            Glide.with(getContext()).load(bitmap1).into(img_back);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtils.showShort("你需要给予权限，才能选择图片");
    }

    public static Uri getImageContentUri(Context context, String path) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            // 如果图片不在手机的共享图片数据库，就先把它插入。
            if (new File(path).exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, path);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    private void setImage() {
// 通过uri加载图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(img_title, 20);
        }
    }
}
