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

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.financeViewHolder> {

    Context context;
    ArrayList<Message> financeMsgList;

    public FinanceAdapter(Context context, ArrayList<Message> financeMsgList) {
        this.context = context;
        this.financeMsgList = financeMsgList;
    }

    @Override
    public financeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new financeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(financeViewHolder holder, int position) {
        Message thisMsg = financeMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());
    }

    @Override
    public int getItemCount() {
        return financeMsgList.size();
    }

    public class financeViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumber;
        TextView tvBody;
        public financeViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
   }
}
