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
 * Created by yash on 21/11/17.
 */

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.OtherViewHolder>{

    Context context;
    ArrayList<Message> otherMsgList;

    public OtherAdapter(Context context, ArrayList<Message> otherMsgList) {
        this.context = context;
        this.otherMsgList = otherMsgList;
    }

    public void updateOtherList(ArrayList<Message> msgList)
    {
        this.otherMsgList = msgList;
        notifyDataSetChanged();
    }

    @Override
    public OtherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new OtherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OtherViewHolder holder, int position) {
        Message thisMsg = otherMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());
    }

    @Override
    public int getItemCount() {
        return otherMsgList.size();
    }

    public class OtherViewHolder extends RecyclerView.ViewHolder{

        TextView tvNumber;
        TextView tvBody;
        public OtherViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
    }
}
