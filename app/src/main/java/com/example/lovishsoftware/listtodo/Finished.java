package com.example.lovishsoftware.listtodo;

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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Finished.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Finished#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Finished extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList list = new ArrayList();
    Dbhandler db;
    RecyclerView recyclerView;
    ListAdpter adapter;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private OnFragmentInteractionListener mListener;

    public Finished() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment2_main, container, false);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }

            void refreshItems() {
                list.clear();


               /* Cursor cursor = db.getfinished();
                while (cursor.moveToNext()) {
                    ItemList itemList = new ItemList();
                    itemList.setId(cursor.getInt(0));
                    itemList.setList(cursor.getString(1));
                    itemList.setDone(cursor.getInt(2));
                    //   Log.d("MainActivity", String.valueOf(itemList.getId()));
                    Log.d("Placeholder Fragment22", itemList.getList());
                    //   Log.d("MainActivity", String.valueOf(itemList.getDone()));
                    list.add(itemList);
                    adapter.notifyDataSetChanged();
                }
                db.close();

                onItemsLoadComplete();

*/
            }

            void onItemsLoadComplete() {
                // Update the adapter and notify data set changed
                // ...

                // Stop refresh animation
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
  /*      db = new Dbhandler(getActivity());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview2);
        RecyclerView.LayoutManager iLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(iLayoutManager);
        adapter = new ListAdpter(getActivity(),list);
        recyclerView.setAdapter(adapter);
  */      return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onResume() {
        list.clear();

        super.onResume();

/*
        Cursor cursor = db.getfinished();
        while (cursor.moveToNext()) {
            ItemList itemList = new ItemList();
            itemList.setId(cursor.getInt(0));
            itemList.setList(cursor.getString(1));
            itemList.setDone(cursor.getInt(2));
            //   Log.d("MainActivity", String.valueOf(itemList.getId()));
            Log.d("fragment2", itemList.getList());
            //   Log.d("MainActivity", String.valueOf(itemList.getDone()));
            list.add(itemList);
            adapter.notifyDataSetChanged();
        }
        db.close();

*/
    }
}
