package com.dimas519.storescheduling.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppConfiguration {
    protected SharedPreferences sp;
    private String machineNumber="numOfMachine";


    public AppConfiguration(Context c){
        this. sp= PreferenceManager.getDefaultSharedPreferences(c);
    }

    public int getSize(){
        return this.sp.getAll().size();
    }

    public void saveMachineNumber(int number){
        SharedPreferences.Editor ed=this.sp.edit();
        ed.putInt(this.machineNumber,number);
        ed.commit();
    }

    public int getMachineNumber(){
        return sp.getInt(this.machineNumber,0);
    }




}
