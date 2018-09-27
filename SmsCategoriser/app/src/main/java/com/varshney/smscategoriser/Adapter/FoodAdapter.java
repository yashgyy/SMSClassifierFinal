package com.varshney.smscategoriser.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varshney.smscategoriser.Message;
import com.varshney.smscategoriser.R;

import java.util.ArrayList;

/**
 * Created by yash on 21/11/17.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

    Context context;
    ArrayList<Message> foodMsgList;
    public static final String TAG = "FoodAdapter";

    public FoodAdapter(Context context, ArrayList<Message> foodMsgList) {
        this.context = context;
        this.foodMsgList = foodMsgList;
    }


    public void updateFoodList(ArrayList<Message> foodMsgList)
    {
        this.foodMsgList = foodMsgList;
        Log.d(TAG, "updateFoodList: "+foodMsgList.size());
        notifyDataSetChanged();
    }
    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        Message thisMsg = foodMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());
    }

    @Override
    public int getItemCount() {
        return foodMsgList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{


        TextView tvNumber;
        TextView tvBody;
        public FoodViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
    }
}
