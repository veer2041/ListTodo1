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

import static android.R.attr.id;
import static com.example.lovishsoftware.listtodo.MainActivity.*;

/**
 * Created by Lovish Software on 5/29/2017.
 */

public class ListAdpter extends RecyclerView.Adapter<ListAdpter.MyViewHolder> {

   List<ItemList> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button button;


        public MyViewHolder(final View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.itemtextview);
            button=(Button)itemView.findViewById(R.id.move);
         /*   button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId()==button.getId()) {


                        Toast.makeText(v.getContext(), "ItemClicked", Toast.LENGTH_LONG).show();
                      }
                    else {
                        Toast.makeText(v.getContext(), "ItemunClicked", Toast.LENGTH_LONG).show();
                       }
                    }
            });
        */}
    }
    public ListAdpter(Context context, ArrayList<ItemList> list){

        this.context=context;
           this.list=list;

    }
    @Override
    public ListAdpter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        Log.d("ListAdapter","inside oncreate view");

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListAdpter.MyViewHolder holder, int position) {
        final ItemList itemlist=list.get(position);
        Log.d("ListAdpter","inside bindview");
        holder.textView.setText(itemlist.getList());
        Log.d("ListAdapter","text holder in bind view");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  int i = itemlist.getId();
                    datasendid(i);
                    Log.d("ListAdapter id: ",String.valueOf(i));
                        Toast.makeText(v.getContext(), "Button pressed", Toast.LENGTH_LONG).show();
                   }
        });
    }

    public void datasendid(int id){
        Dbhandler.getInstance(context).updatedata(id);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}
