package com.example.app3;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImagedaoAdapter extends RecyclerView.Adapter<ImagedaoAdapter.ViewHolder> {
    private List<Imagechange> mimageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View newsView;
        ImageView newsImage;

        public ViewHolder(View view) {
            super(view);
            newsView = view;
            newsImage = (ImageView) view.findViewById(R.id.imagedao);
        }
    }

    public ImagedaoAdapter(List<Imagechange> imageList) {
        mimageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                imagedao, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.newsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LiulangActivity.class);
                v.getContext().startActivity(intent);
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