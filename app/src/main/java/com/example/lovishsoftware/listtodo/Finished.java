package com.example.lovishsoftware.listtodo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Finished extends Fragment {
    ArrayList finishedlist = new ArrayList();
    RecyclerView recyclerView;
    FinishedlistAdapter adapter;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Finished(ArrayList list){
        this.finishedlist=list;
        Log.d("Inside Finised fragment","condtructor "+list);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment2_main, container, false);



        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview2);
        RecyclerView.LayoutManager iLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(iLayoutManager);
        adapter=new FinishedlistAdapter(getActivity(),finishedlist);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        Log.d("Finished","recyclerview");
        return rootView;
    }
/*
    public void finisheddata(ArrayList<ItemList> finisheditemlist) {
        for (int j = 0; j < finisheditemlist.size(); j++) {
            finishedlist.add(finisheditemlist.get(j));

            Log.d("pendingdata", "updatelistfinished" + finishedlist);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        }
}
