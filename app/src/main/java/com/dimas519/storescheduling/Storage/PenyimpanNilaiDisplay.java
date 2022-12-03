package com.dimas519.storescheduling.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PenyimpanNilaiDisplay {
    protected SharedPreferences sp;
    protected final static String NAMA_SHARED_PREF="sp_nilai_display";
    protected final static String KEY_BARANG="Barang";
    protected final static String KEY_HARGA="harga";
    protected final static String KEY_KETERANGAN="keterangan";


    public PenyimpanNilaiDisplay(Context c){
        this. sp= PreferenceManager.getDefaultSharedPreferences(c);
    }

    public int getSize(){
        return this.sp.getAll().size();
    }

    public void saveBarang(String barang){
        SharedPreferences.Editor ed=this.sp.edit();
        ed.putString(KEY_BARANG,barang);
        ed.commit();
    }

    public void saveHarga(int Harga){
        SharedPreferences.Editor ed=this.sp.edit();
        ed.putInt(KEY_HARGA,Harga);
        ed.commit();
    }

    public void saveKeterangan(String keterangan){
        SharedPreferences.Editor ed=this.sp.edit();
        ed.putString(KEY_KETERANGAN,keterangan);
        ed.commit();
    }

    public String getBarang(){
        return sp.getString(KEY_BARANG,"");
    }

    public int getHarga(){
        return sp.getInt(KEY_HARGA,0);
    }

    public String getKeyKeterangan(){
        return sp.getString(KEY_KETERANGAN,"");
    }


}
