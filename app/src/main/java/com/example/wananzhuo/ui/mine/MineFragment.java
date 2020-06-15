package com.example.wananzhuo.ui.mine;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.example.wananzhuo.R;
import com.example.wananzhuo.Uilt.BitmapMessage;
import com.example.wananzhuo.Uilt.getPhotoFromPhotoAlbum;
import com.example.wananzhuo.base.BaseFragment;
import com.example.wananzhuo.ui.mine.login.LoginActivity;
import com.hjq.toast.ToastUtils;
import com.youth.banner.util.BannerUtils;


import java.io.File;
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
    ContentValues values = new ContentValues();

    @OnClick({R.id.img_title, R.id.tv_name})
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
                break;
            case R.id.tv_name:
                switch (TYPE) {
                    case 0:
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivityForResult(intent, 3);
                        break;
                    case 1:
                        ToastUtils.show("已登录~");
                        break;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void initData() {
        mPresenter = new MinePresenter(this, getContext());
        String uri = SPUtils.getInstance("img").getString("image");
        if (uri != null) {
            getBack(Uri.parse(uri));
        }
        if ((SPUtils.getInstance("user").getString("user") == null)) {
            tv_name.setText("未登录");
        } else {
            tv_name.setText(SPUtils.getInstance("user").getString("user"));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.initAdapter();
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
        switch (resultCode) {
            case 1:
                String name = data.getStringExtra("name");
                tv_name.setText(name);
                break;
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = getPhotoFromPhotoAlbum.getRealPathFromUri(getContext(), data.getData());
            int sdkVersion = Build.VERSION.SDK_INT;
            if (sdkVersion == 29) {
                Uri uri = getMediaUriFromPath(getContext(), photoPath);
                getBack(uri);
                //高斯模糊
                SPUtils.getInstance("img").put("image", uri + "");
            } else {
                SPUtils.getInstance("img").put("image", photoPath);
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void getBack(Uri uri) {
        Glide.with(getContext()).load(uri).into(img_title);
        try {
            BitmapMessage bitmapMessage = new BitmapMessage();
            Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(uri));
            Bitmap bitmap1 = bitmapMessage.blurBitmap(bitmap);
            Glide.with(getContext()).load(bitmap1).into(img_back);
            setImage();
        } catch (Exception e) {
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
        ToastUtils.show("你需要给予权限，才能选择图片");
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

    public static Uri getMediaUriFromPath(Context context, String path) {
        Uri mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = context.getContentResolver().query(mediaUri,
                null,
                MediaStore.Images.Media.DISPLAY_NAME + "= ?",
                new String[]{path.substring(path.lastIndexOf("/") + 1)},
                null);
        Uri uri = null;
        assert cursor != null;
        if (cursor.moveToFirst()) {
            uri = ContentUris.withAppendedId(mediaUri,
                    cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID)));
        }
        cursor.close();
        return uri;
    }

    @Override
    public void setAdapter(MineAdapter mAdapter) {
        recyclerView.setAdapter(mAdapter);
    }
}
