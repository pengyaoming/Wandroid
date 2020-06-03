package com.example.wananzhuo.ui.home;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wananzhuo.Entity.DataBean;
import com.example.wananzhuo.R;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/19
 * Time: 15:48
 */
public class ImageAdapter extends BannerAdapter<DataBean, ImageAdapter.ImageHolder> {
    private Context context;

    public ImageAdapter(List<DataBean> datas, Context context) {
        super(datas);
        this.context = context;
    }


    @Override
    public void setDatas(List<DataBean> datas) {
        super.setDatas(datas);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = BannerUtils.getView(parent, R.layout.item_banner_view);
        ImageView imageView = view.findViewById(R.id.imageView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView, 20);
        }
        return new ImageHolder(view);
    }

    @Override
    public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
        Glide.with(context).load(data.getImagePath()).error(R.drawable.nex_back).into(holder.imageView);
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_title, tv_message;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_message = itemView.findViewById(R.id.tv_message);
        }
    }
}
