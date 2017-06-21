package com.example.lovishsoftware.listtodo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FinishedlistAdapter extends RecyclerView.Adapter<FinishedlistAdapter.MyViewHolder> {
    List<ItemList> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button button;


        public MyViewHolder(final View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.itemtextview);
            button=(Button)itemView.findViewById(R.id.move);
       }
    }
    public FinishedlistAdapter(Context context, ArrayList<ItemList> list){

        this.context=context;
        this.list=list;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        Log.d("ListAdapter","inside oncreate view");

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemList itemlist=list.get(position);
        Log.d("ListAdpter","inside bindview");
        holder.textView.setText(itemlist.getList());
        Log.d("ListAdapter","text holder in bind view");

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
