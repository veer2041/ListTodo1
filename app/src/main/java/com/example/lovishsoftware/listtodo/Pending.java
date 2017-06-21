package com.example.lovishsoftware.listtodo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class Pending extends Fragment {
    ArrayList<ItemList> pendinglist=new ArrayList();
    RecyclerView recyclerView;
    PendinglistAdapter adapter;

    public Pending(ArrayList<ItemList> list){
        this.pendinglist=list;
        Log.d("Inside Pending fragment","condtructor "+list);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerlist);
        Log.d("pending ","inside oncerateview");
        RecyclerView.LayoutManager iLayoutManager=new LinearLayoutManager(getActivity());  //create verticle view layout
        recyclerView.setLayoutManager(iLayoutManager);
        adapter=new PendinglistAdapter(getContext(),pendinglist);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        Log.d("Pending","recyclerview");
        return v;
    }
/*
    public void pendingdata(ArrayList<ItemList> pendingitemlist) {
        for (int j = 0; j < pendingitemlist.size(); j++) {
            int i=pendingitemlist.size();
            Log.d("pendingdata",""+i);
            pendinglist.add(pendingitemlist.get(j));
            Log.d("pendingdata", "updatelistfinished" + pendinglist);
            adapter=new PendinglistAdapter(getContext(),pendinglist);
            adapter.notifyDataSetChanged();
        }
    }*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
