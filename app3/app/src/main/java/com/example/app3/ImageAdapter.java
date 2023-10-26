package com.example.app3;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<Imagechange> mimageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View newsView;
        ImageView newsImage;

        public ViewHolder(View view) {
            super(view);
            newsView = view;
            newsImage = (ImageView) view.findViewById(R.id.imagex);
        }
    }

    public ImageAdapter(List<Imagechange> imageList) {
        mimageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                image, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.newsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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