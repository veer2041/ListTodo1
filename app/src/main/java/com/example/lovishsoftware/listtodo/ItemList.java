package com.example.lovishsoftware.listtodo;

/**
 * Created by Lovish Software on 5/29/2017.
 */

public class ItemList {
    private int id;
    private String list;
    private int b;


    public int getId(){
        return id;

    }
    public void setId(int id){
        this.id=id;
    }

    public String getList(){
        return list;

    }
    public void setList(String list){
        this.list=list;
    }
    public int getDone(){
        return b;

    }
    public void setDone(int b){
    this.b=b;
    }

}
