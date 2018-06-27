package com.example.dominik.myplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        return new CustomViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.txtViewTitle.setText(mDataset.get(position).getTitle());
        holder.imgViewIcon.setImageResource(mDataset.get(position).getImageUrl());
        if (mDataset.get(position).getType() == ItemData.PLAN) {
            holder.dltIcon.setVisibility(View.VISIBLE);
            holder.dltIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                switch(mDataset.get(position).getType()) {
                    case ItemData.GROUP:
                        Intent intent_group = new Intent(mContext, UebungenActivity.class);
                        intent_group.putExtra("muclegroup_name", mDataset.get(position).getTitle());
                        ((Activity) mContext).startActivityForResult(intent_group, MuscleGroupActivity.REQUEST_FINISH);
                        break;
                    case ItemData.UEBUNG:
                        Log.e(TAG, "opends next Activity");
                        Intent intent_uebung = new Intent(mContext, ConfigureUebungActivity.class);
                        intent_uebung.putExtra("uebung_name", mDataset.get(position).getTitle());
                        mContext.startActivity(intent_uebung);
                        break;
                    case ItemData.PLAN:
                        singleton.setIndex(position);
                        Intent intent_plan = new Intent(mContext, PlanActivity.class);
                        mContext.startActivity(intent_plan);
                        break;
                    default:
                        //should never happen:
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
