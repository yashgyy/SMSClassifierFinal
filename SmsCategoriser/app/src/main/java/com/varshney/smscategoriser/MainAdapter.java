package com.varshney.smscategoriser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yash on 22/11/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.mainViewHolder>{

    Context context;
    ArrayList<Message> mainMsgList;

    public MainAdapter(Context context, ArrayList<Message> mainMsgList) {
        this.context = context;
        this.mainMsgList = mainMsgList;
    }

    @Override
    public mainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.list_item_recyclerview,parent,false);
        return new mainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(mainViewHolder holder, int position) {
        Message thisMsg = mainMsgList.get(position);
        holder.tvNumber.setText(thisMsg.getAddress());
        holder.tvBody.setText(thisMsg.getBody());
    }

    @Override
    public int getItemCount() {
        return mainMsgList.size();
    }

    public class mainViewHolder extends RecyclerView.ViewHolder{

        TextView tvNumber;
        TextView tvBody;
        public mainViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvBody =  (TextView) itemView.findViewById(R.id.tvBody);

        }
    }
}
