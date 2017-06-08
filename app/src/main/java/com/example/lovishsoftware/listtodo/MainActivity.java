package com.example.lovishsoftware.listtodo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Pending.OnFragmentInteractionListener,Finished.OnFragmentInteractionListener {

    int code=1;
    Dbhandler dbhandler=new Dbhandler(this);
    ListAdpter adpter;
    String result;
    ArrayList<ItemList> list=new ArrayList<ItemList>();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.



        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(i,code);

            }
        });
        Log.d("MainActivity","List from Dbhandle");
        list=dbhandler.getpending();
        Log.d("mainActivity","list from dbhandle" + list);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1) {
            if(resultCode==RESULT_OK) {
                result = data.getStringExtra("TEXT");
                Log.d("Main Act","OnactivityResult");
                int i=list.size();
                Log.d("MainActivity list size",""+i);
                Log.d("MainActivity result ", result);

                    list.clear();
                    Log.d("MainActivity","List from Dbhandle");
                    list = dbhandler.getpending();

                Log.d("main", "ksdjks" + list);



/*

                Cursor cursor = dbhandler.getpending();
                list.clear();
                while (cursor.moveToNext()) {
                    ItemList itemList = new ItemList();
                    itemList.setId(cursor.getInt(0));
                    itemList.setList(cursor.getString(1));
                    itemList.setDone(cursor.getInt(2));
                    Log.d("MainActivity", String.valueOf(itemList.getId()));
                    Log.d("MainActivity" + "itemlist", itemList.getList());
                    Log.d("MainActivity", String.valueOf(itemList.getDone()));
                    list.add(itemList);
                    dbhandler.close();
                }
*/
            }
        }
            else if(resultCode==Activity.RESULT_CANCELED) {

                Toast.makeText(this,"not Inserted",Toast.LENGTH_LONG).show();
            }
        }

    public  boolean data(int i){
        dbhandler.updatedata(i);
        return true;
    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {




        }

    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface FragmentLifecycle {
        public void onResumeFragment();
    }




    /*First Fragment class*/



/*

    public static class PlaceholderFragment extends Fragment implements FragmentLifecycle{
        */
/*
         * The fragment argument representing the section number for this
         * fragment.
         *//*

        RecyclerView recyclerView;
        Dbhandler db;
        ListAdpter adapter;
        ArrayList list=new ArrayList();
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }

        */
/**
         * Returns a new instance of this fragment for the given section
         * number.
         *//*

        public PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }





        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final SwipeRefreshLayout mSwipeRefreshLayout=(SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);;
            db=new Dbhandler(getActivity());

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // Refresh items
                    refreshItems();

                }

                void refreshItems(){
                    list.clear();

                    Cursor cursor=db.getpending();
                    Log.d("Pending","getpending");
                    while (cursor.moveToNext()) {
                        ItemList itemList = new ItemList();
                        itemList.setId(cursor.getInt(0));
                        itemList.setList(cursor.getString(1));
                        itemList.setDone(cursor.getInt(2));
                        //   Log.d("MainActivity", String.valueOf(itemList.getId()));
                        Log.d("MainActivity", itemList.getList());
                        //   Log.d("MainActivity", String.valueOf(itemList.getDone()));
                        list.add(itemList);
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                    }

                    db.close();

                    onItemsLoadComplete();


                }
                void onItemsLoadComplete() {
                    // Update the adapter and notify data set changed
                    // ...

                    // Stop refresh animation
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });




            recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerlist);
            RecyclerView.LayoutManager iLayoutManager=new LinearLayoutManager(getActivity());  //create verticle view layout
            recyclerView.setLayoutManager(iLayoutManager);
            adapter=new ListAdpter(getActivity(),list);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            refresh();


            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                    recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {

                    Toast.makeText(getContext(), "selected", Toast.LENGTH_SHORT).show();
                    ItemList itemList= (ItemList) list.get(position);
                    Log.d("Main activity id ",String.valueOf(itemList.getId()));
                    refresh();
                    int s=itemList.getId();
                    boolean b=db.updatedata(s);

                    adapter.notifyDataSetChanged();

                    if(b){
                        Toast.makeText(getContext(),"updated",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(),"unsuccessful update",Toast.LENGTH_LONG).show();
                    }
                    onResume();
                }
                @Override
                public void onLongClick(View view, int position) {

                }
            }));
       */
/*     Cursor cursor=db.getpending();
            while (cursor.moveToNext()){
                ItemList itemList=new ItemList();
                itemList.setId(cursor.getInt(0));
                itemList.setList(cursor.getString(1));
                itemList.setDone(cursor.getInt(2));
                 //   Log.d("MainActivity", String.valueOf(itemList.getId()));
                Log.d("MainActivity",itemList.getList());
                //   Log.d("MainActivity", String.valueOf(itemList.getDone()));
                list.add(itemList);
                adapter.notifyDataSetChanged();
            }
       *//*

         */
/*   Cursor cursor=db.getpending();
            list.clear();
            while (cursor.moveToNext()) {
                ItemList itemList = new ItemList();
                itemList.setId(cursor.getInt(0));
                itemList.setList(cursor.getString(1));
                itemList.setDone(cursor.getInt(2));
                //   Log.d("MainActivity", String.valueOf(itemList.getId()));
                Log.d("MainActivity", itemList.getList());
                //   Log.d("MainActivity", String.valueOf(itemList.getDone()));
                adapter = new ListAdpter(getActivity(), list);
                list.add(itemList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }*//*


        return rootView;
        }
     */
/*   public void setrecyclerlist(ArrayList<ItemList> list){

            adapter=new ListAdpter(getActivity(),list);
            adapter.notifyDataSetChanged();
        }
*//*


        public void refresh(){

            Cursor cursor=db.getpending();
            list.clear();

            while (cursor.moveToNext()){
                ItemList itemList=new ItemList();
                itemList.setId(cursor.getInt(0));
                itemList.setList(cursor.getString(1));
                itemList.setDone(cursor.getInt(2));
                //Log.d("MainActivity", String.valueOf(itemList.getId()));
                Log.d("PlaceholderFragmen2list",itemList.getList());
                //   Log.d("MainActivity", String.valueOf(itemList.getDone()));
                list.add(itemList);
                db.close();
            }


        }

        @Override
        public void onResumeFragment() {

        }

        @Override
        public void onResume() {
            super.onResume();
            refresh();
           // db.close();
        }
    }




    */
/*Second Fragment class*//*






    public static class PlaceholderFragment2 extends Fragment {
        ArrayList list = new ArrayList();
        Dbhandler db;
        RecyclerView recyclerView;
        ListAdpter adapter;
        */
/**
         * The fragment argument representing the section number for this
         * fragment.
         *//*

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment2() {
        }

        */
/**
         * Returns a new instance of this fragment for the given section
         * number.
         *//*

        public PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
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


                    Cursor cursor = db.getfinished();
                    while (cursor.moveToNext()) {
                        ItemList itemList = new ItemList();
                        itemList.setId(cursor.getInt(0));
                        itemList.setList(cursor.getString(1));
                        itemList.setDone(cursor.getInt(2));
                        //   Log.d("MainActivity", String.valueOf(itemList.getId()));
                        Log.d("Placeholder Fragment2", itemList.getList());
                        //   Log.d("MainActivity", String.valueOf(itemList.getDone()));
                        list.add(itemList);
                        adapter.notifyDataSetChanged();
                    }
                    db.close();

                    onItemsLoadComplete();


                }

                void onItemsLoadComplete() {
                    // Update the adapter and notify data set changed
                    // ...

                    // Stop refresh animation
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
            db = new Dbhandler(getActivity());
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview2);
            RecyclerView.LayoutManager iLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(iLayoutManager);
            adapter = new ListAdpter(getActivity(),list);
            recyclerView.setAdapter(adapter);
            return rootView;
        }

        @Override
        public void onResume() {
            list.clear();

            super.onResume();

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

        }
    }

*/
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        ViewPager pager;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Pending p1=new Pending();
                    p1.setrecyclerlist(list);
                    return p1;
                case 1:
                    Finished p2 = new Finished();
                    return p2;
            }
            return null;

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "pending";
                case 1:
                    return "finished";
            }
            return null;
        }


    }

}
