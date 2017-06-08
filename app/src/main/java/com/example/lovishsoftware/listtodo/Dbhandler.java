package com.example.lovishsoftware.listtodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.contextClickable;
import static android.R.attr.id;
import static android.R.id.list;

/**
 * Created by Lovish Software on 5/29/2017.
 */

public class Dbhandler extends SQLiteOpenHelper {

    private static final String Dbname="List";
    private static final String Dbtable="ListTable";
    private static final String id="id";
    private static final String textlist="list";
    private static final String done="done";
    private static final int version=1;
    ArrayList list=new ArrayList();
    Context context;
    private static Dbhandler dbhandler;
    public static Dbhandler getInstance(Context context) {
        if(dbhandler == null)
        {
            synchronized (Dbhandler.class)
            {
                if(dbhandler == null)
                {
                    dbhandler = new Dbhandler(context);
                }
            }
        }

        return dbhandler;
    }




    public Dbhandler(Context context) {
        super(context, Dbname, null, version);
        this.context=context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String quearycreate="CREATE TABLE " + Dbtable + "(id INTEGER PRIMARY KEY,list TEXT,done Integer DEFAULT 0)";
        db.execSQL(quearycreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS"+Dbtable);
        onCreate(db);
    }

    public boolean insert(String text){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(textlist,text);
        long r=db.insert(Dbtable,null,values);
        db.close();
        if(r==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public ArrayList getpending(){
        int i=0;
        list.clear();
        SQLiteDatabase db=this.getReadableDatabase();
        String s="SELECT * FROM " + Dbtable + " WHERE done="+i;
        Cursor cursor=db.rawQuery(s,null);
        while (cursor.moveToNext()) {
            ItemList itemList = new ItemList();
            itemList.setId(cursor.getInt(0));
            itemList.setList(cursor.getString(1));
            itemList.setDone(cursor.getInt(2));
            Log.d("DbHandle", String.valueOf(itemList.getId()));
            Log.d("Dbhandle" + "itemlist", itemList.getList());
            Log.d("Dbhandler", String.valueOf(itemList.getDone()));
            list.add(itemList);
            db.close();
        }
        return list;

    }

    public  boolean updatedata(int id){
        Log.d("Dbhandler",String.valueOf(id));
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(done,1);

       // long i="UPDATE "+ Dbtable +" SET done='1' WHERE id = " + id;
        db.execSQL("UPDATE "+ Dbtable +" SET done='1' WHERE id = " + id);

        Log.d("ListAdapter","updated");
        long i=db.update(Dbtable,contentValues,done,null);

        db.close();
        return i != -1;
    }
    public Cursor getfinished(){
        int i=1;
        SQLiteDatabase db=this.getReadableDatabase();
        String s="SELECT * FROM " + Dbtable + " WHERE done= "+i;
        Log.d("Dbhandler ", s);
        Cursor cursor=db.rawQuery(s,null);
        return cursor;
    }
}
