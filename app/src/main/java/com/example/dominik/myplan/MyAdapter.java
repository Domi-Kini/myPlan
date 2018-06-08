package com.example.dominik.myplan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
    private ArrayList<ItemData> mDataset;
    private LayoutInflater mInflater;

    MyAdapter(Context context, ArrayList<ItemData> mDataset) {
        this.mDataset = mDataset;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = mInflater.inflate(R.layout.layout_recycler_view, null);
        return new CustomViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtViewTitle.setText(mDataset.get(position).getTitle());
        holder.imgViewIcon.setImageResource(mDataset.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle;
        ImageView imgViewIcon;

        CustomViewHolder(View v) {
            super(v);
            txtViewTitle = (TextView) v.findViewById(R.id.item_text);
            imgViewIcon = (ImageView) v.findViewById(R.id.item_icon);
        }
    }
}
