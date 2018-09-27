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

public class FashionAdapter extends RecyclerView.Adapter<FashionAdapter.fashionViewHolder> {

    Context context;
    ArrayList<Message> fashionMsgList;

    public FashionAdapter(Context context, ArrayList<Message> fashionMsgList) {
        this.context = context;
        this.fashionMsgList = fashionMsgList;
    }

    @Override
    public fashionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new fashionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(fashionViewHolder holder, int position) {
        Message thisMsg = fashionMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());
    }

    @Override
    public int getItemCount() {
        return fashionMsgList.size();
    }

    public class fashionViewHolder extends RecyclerView.ViewHolder{
        TextView tvNumber;
        TextView tvBody;
        public fashionViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
    }
}
