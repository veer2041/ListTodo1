package com.example.lovishsoftware.listtodo;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    int code=1;
    Pending p1;
    Finished p2;
    Dbhandler dbhandler=new Dbhandler(this);
    String result;
    ArrayList<ItemList> pendinglist=new ArrayList<ItemList>();
    ArrayList<ItemList> finishedlist=new ArrayList<ItemList>();
    ArrayList<ItemList> updatedpending=new ArrayList<ItemList>();
    ArrayList<ItemList> updatedfinished=new ArrayList<ItemList>();


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




        Log.d("MainActivity","List from Dbhandle pending");
        pendinglist = dbhandler.getpending();
        for(int i=0;i<pendinglist.size();i++){
            updatedpending.add(pendinglist.get(i));

            Log.d("MainActivity","updatelistpending"+updatedpending);
        }

        int i=pendinglist.size();
        Log.d("MainActivity list size",""+i);
        Log.d("MainActivity","List of finished dbhandler");
        finishedlist=dbhandler.getfinished();
        for(int j=0;j<finishedlist.size();j++){
            updatedfinished.add(finishedlist.get(j));
            Log.d("MainActivity","updatelistfinished"+updatedfinished);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1) {
            if(resultCode==RESULT_OK) {
                result = data.getStringExtra("TEXT");
                Log.d("Main Act","OnactivityResult");
                String s=result.toString();
                Log.d("MainActivity",s);
                boolean b=dbhandler.insert(s);

                if(b==true){
                    Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"not Inserted",Toast.LENGTH_LONG).show();
                }
                    updatedpending.clear();
                    Log.d("MainActivity","List from Dbhandle pending");
                    pendinglist = dbhandler.getpending();
                    for(int i=0;i<pendinglist.size();i++){
                        updatedpending.add(pendinglist.get(i));

                        Log.d("MainActivity","updatelistpending"+updatedpending);
                    }

                    updatedfinished.clear();
                    int i=pendinglist.size();
                     Log.d("MainActivity list size",""+i);
                    Log.d("MainActivity","List of finished dbhandler");
                    finishedlist=dbhandler.getfinished();
                  for(int j=0;j<finishedlist.size();j++){
                    updatedfinished.add(finishedlist.get(j));
                      Log.d("MainActivity","updatelistfinished"+updatedfinished);
                   }
            }
        }
            else if(resultCode==Activity.RESULT_CANCELED) {

                Toast.makeText(this,"not Inserted",Toast.LENGTH_LONG).show();
            }
        }

    public void method(int a) {
        Dbhandler.getInstance(this).updatedata(a);
        updatedpending.clear();
        Log.e("MainActivity","inside method ");
        pendinglist = dbhandler.getpending();
        for(int i=0;i<pendinglist.size();i++){
            updatedpending.add(pendinglist.get(i));
            Log.d("MainActivity","updatelistpending"+updatedpending);
        }
        updatedfinished.clear();
        int i=pendinglist.size();
        Log.d("MainActivity list size",""+i);
        Log.e("MainActivity","inside method");
        finishedlist=dbhandler.getfinished();
        for(int j=0;j<finishedlist.size();j++){
            updatedfinished.add(finishedlist.get(j));
            Log.d("MainActivity","updatelistfinished"+updatedfinished);
        }

        Toast.makeText(this,"MainActivity",Toast.LENGTH_LONG).show();

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Log.d("SectionPagerAdapter","inside case 0");
                    p1=new Pending(updatedpending);
                   // p1.setrecyclerlist(pendinglist);
                    return p1;
                case 1:

                    Log.d("SectionPagerAdapter","inside case 1");
                     p2 = new Finished(updatedfinished);

                   // p2.getfinishlist(finishedlist);
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
