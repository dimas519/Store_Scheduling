package com.dimas519.storescheduling.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AlgorithmStorage {
    protected SharedPreferences sp;

    public AlgorithmStorage(Context c){
        this. sp= PreferenceManager.getDefaultSharedPreferences(c);
    }

    public int getSize(){
        return this.sp.getAll().size();
    }

    public void saveAlgorithm(String barang,boolean active){
        SharedPreferences.Editor ed=this.sp.edit();
        ed.putBoolean(barang,active);
        ed.commit();
    }

    public boolean getStatusAlgorithm(String barang){
        return sp.getBoolean(barang,false);
    }




}
