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
 * Created by yash on 22/11/17.
 */

public class CabAdapter extends RecyclerView.Adapter<CabAdapter.cabViewHolder>{

    Context context;
    ArrayList<Message> cabMsgList;

    public CabAdapter(Context context, ArrayList<Message> cabMsgList) {
        this.context = context;
        this.cabMsgList = cabMsgList;
    }

    @Override
    public cabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new cabViewHolder(itemView);
    }

    public void updateFoodList(ArrayList<Message> foodMsgList)
    {
        this.cabMsgList = foodMsgList;
       // Log.d(TAG, "updateFoodList: "+foodMsgList.size());
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(cabViewHolder holder, int position) {
        Message thisMsg = cabMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());
    }

    @Override
    public int getItemCount() {
        return cabMsgList.size();
    }

    public class cabViewHolder extends RecyclerView.ViewHolder{


        TextView tvNumber;
        TextView tvBody;
        public cabViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
    }
}
