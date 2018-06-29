package com.example.dominik.myplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private static final String TAG = "MyAdapter";

    private Context mContext;
    private ArrayList<ItemData> mDataset;
    private LayoutInflater mInflater;
    private MySingleton singleton = MySingleton.getInstance();

    public MyAdapter(Context context, ArrayList<ItemData> mDataset) {
        mContext = context;
        this.mDataset = mDataset;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = mInflater.inflate(R.layout.layout_recycler_view, parent, false);
        Log.e(TAG, "Creating ViewHolder");
        return new CustomViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.txtViewTitle.setText(mDataset.get(position).getTitle());
        holder.imgViewIcon.setImageResource(mDataset.get(position).getImageUrl());
        final int typeFlag = mDataset.get(position).getType();
        if ((typeFlag & ItemData.PLAN) == ItemData.PLAN) {
            holder.dltIcon.setVisibility(View.VISIBLE);
            holder.dltIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((typeFlag & ItemData.UEBUNG) == ItemData.UEBUNG)
                        singleton.getRightPlan().deleteUebung(position);
                    else
                        singleton.getPlans().remove(position);
                    mDataset.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mDataset.size());
                }
            });
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: clicked on" + mDataset.get(position).getTitle());
                int typeFlag = mDataset.get(position).getType();
                if ((typeFlag & ItemData.GROUP) == ItemData.GROUP) {
                    Intent intent_group = new Intent(mContext, UebungenActivity.class);
                    intent_group.putExtra("musclegroup", position);
                    ((Activity) mContext).startActivityForResult(intent_group, MuscleGroupActivity.REQUEST_FINISH);
                } else if ((typeFlag & ItemData.UEBUNG) == ItemData.UEBUNG) {
                    Log.e(TAG, "opends next Activity");
                    Intent intent_uebung;
                    if (mDataset.get(position).isExistingUebung())
                        intent_uebung = new Intent(mContext, ShowUebungActivity.class);
                    else {
                        intent_uebung = new Intent(mContext, ConfigureUebungActivity.class);
                        intent_uebung.putExtra("imageUrl", mDataset.get(position).getImageUrl());
                    }
                    intent_uebung.putExtra("uebung_name", mDataset.get(position).getTitle());
                    intent_uebung.putExtra("position", position);
                    intent_uebung.putExtra("group", mDataset.get(position).getGroup());
                    Log.e(TAG, "starting Show Activity");
                    mContext.startActivity(intent_uebung);
                } else if ((typeFlag & ItemData.PLAN) == ItemData.PLAN) {
                    singleton.setIndex(position);
                    Intent intent_plan = new Intent(mContext, PlanActivity.class);
                    mContext.startActivity(intent_plan);
                } else {
                    Log.e("MyAdapter", "onClick: wrong type");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewTitle;
        ImageView imgViewIcon;
        ImageView dltIcon;
        ConstraintLayout parentLayout;

        CustomViewHolder(View v) {
            super(v);
            txtViewTitle = (TextView) v.findViewById(R.id.item_text);
            imgViewIcon = (ImageView) v.findViewById(R.id.item_icon);
            dltIcon = (ImageView) v.findViewById(R.id.item_delete_icon);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }
}
