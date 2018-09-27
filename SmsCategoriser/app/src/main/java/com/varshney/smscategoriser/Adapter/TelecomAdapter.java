package com.varshney.smscategoriser.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class TelecomAdapter extends RecyclerView.Adapter<TelecomAdapter.telecomViewHolder>{

    Context context;
    ArrayList<Message> telecomMsgList;

    public TelecomAdapter(Context context, ArrayList<Message> telecomMsgList) {
        this.context = context;
        this.telecomMsgList = telecomMsgList;
    }

    @Override
    public telecomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new telecomViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(telecomViewHolder holder, int position) {
        Message thisMsg = telecomMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());

    }

    @Override
    public int getItemCount() {
        return telecomMsgList.size();
    }

    public class telecomViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumber;
        TextView tvBody;
        public telecomViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
    }
}
