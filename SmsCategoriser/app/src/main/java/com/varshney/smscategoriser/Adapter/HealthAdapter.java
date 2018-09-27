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

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.healthViewHolder> {

    Context context;
    ArrayList<Message> healthMsgList;


    public HealthAdapter(Context context, ArrayList<Message> healthMsgList) {
        this.context = context;
        this.healthMsgList = healthMsgList;
    }

    @Override
    public healthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new healthViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(healthViewHolder holder, int position) {
        Message thisMsg = healthMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());
    }

    @Override
    public int getItemCount() {
        return healthMsgList.size();
    }

    public class healthViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber;
        TextView tvBody;
        public healthViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
    }
}
