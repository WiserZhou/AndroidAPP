package com.example.app3;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImagezAdapter extends RecyclerView.Adapter<ImagezAdapter.ViewHolder> {
    private List<Imagechange> mimageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View newsView;
        ImageView newsImage;


        public ViewHolder(View view) {
            super(view);
            newsView = view;
            newsImage = (ImageView) view.findViewById(R.id.imagez);
        }
    }

    public ImagezAdapter(List<Imagechange> imageList) {
        mimageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                imagez, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.newsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap  = ((BitmapDrawable)holder.newsImage.getDrawable()).getBitmap();
                final Dialog dialog = new Dialog(view.getContext());
                ImageView image = new ImageView(view.getContext());
                image.setImageBitmap(bitmap);
                dialog.setContentView(image);
                WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
                attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
                attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes(attributes);
                dialog.getWindow().setBackgroundDrawableResource(R.color.black);
                //显示
                dialog.show();
                //点击图片取消
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
        return holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Imagechange imagechange = mimageList.get(position);
        holder.newsImage.setImageResource(imagechange.getImageID());

    }

    @Override
    public int getItemCount() {
        return mimageList.size();
    }

    static class Imagechange {
        private int ImageID;

        public void setImageID(int ImageID) {
            this.ImageID = ImageID;
        }

        Imagechange(int ImageID) {
            this.ImageID = ImageID;
        }

        public int getImageID() {
            return ImageID;
        }
    }
}