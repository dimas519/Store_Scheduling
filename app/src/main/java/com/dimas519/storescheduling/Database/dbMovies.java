package com.dimas519.storescheduling.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class dbMovies extends SQLiteOpenHelper {
    private static int DB_version=7;
    private static String DB_name="lisTify.db";


    private final static String pelangganTable="Pelanggan";
    private final static String produkTable="Produk";
    private final static String prosesTable="Proses";


    private static  final String CREATE_TABLE_PELANGGAN= "CREATE TABLE "+pelangganTable+
            " ( ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "NAMA TEXT , " +
            "KOBTAK TEXT  , " +
            "ALAMAT TEXT , " +
            "EMAIL TEXT , " +
            "TELEPON TEXT )";

    private static final String CREATE_TABLE_PRODUK= "CREATE TABLE "+produkTable +
            " ( ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "Kode_PRODUK TEXT , " +
            "NAMA_PRODUK TEXT ," +
            "Waktu_PROSES TEXT )";

    private static final String CREATE_TABLE_PROSES= "CREATE TABLE "+prosesTable +
            " ( ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "KODE_PROSES TEXT , " +
            "NAMA_PRODUK TEXT ," +
            "Waktu_PROSES TEXT )";

    private static final String CREATE_TABLE_LOG="CREATE TABLE Log ("+
            "ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "AKSI INTEGER , " +
            "JUDUL TEXT ," +
            "TABEL INTEGER , " +
            "TANGGAL DATETIME DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime'))" +
            ")";

    public dbMovies(Context context){
        super(context,DB_name,null,DB_version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PELANGGAN);
        db.execSQL(CREATE_TABLE_PRODUK);
        db.execSQL(CREATE_TABLE_PROSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String query= "DROP TABLE IF EXISTS Movies";
//        db.execSQL( query);
//        query="DROP TABLE IF EXISTS Series";
//        db.execSQL(query);
//        db.execSQL("DROP TABLE IF EXISTS Episode");
//        db.execSQL("DROP TABLE IF EXISTS Log");
    }

    public void create(){
        SQLiteDatabase db=this.getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS Movies");
//        db.execSQL("DROP TABLE IF EXISTS Series");
//        db.execSQL("DROP TABLE IF EXISTS Episode");
//       db.execSQL(CREATE_TABLE_MOVIES);
//        db.execSQL(CREATE_TABLE_SERIES);
     //   db.execSQL(CREATE_TABLE_EPISODE);

    }

//    public void insertDataMovies(MoviesModel movie){
//        String judul=movie.getJudul();
//        int bintang =movie.getBintang();
//        int status=movie.getStatusData();
//        String synopsis= movie.getSynopsis();
//        String comment=movie.getComment();
//        String foto=movie.getFoto();
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues newData=new ContentValues();
//        newData.put("JUDUL",judul);
//        newData.put("BINTANG",bintang);
//        newData.put("STATUS",status);
//        newData.put("SYNOPSIS",synopsis);
//        newData.put("COMMENT",comment);
//        newData.put("FOTO",foto);
//        db.insert("Movies",null,newData);
//        insertLog(0,0,judul);
//
//    }
//    public void insertDataSeries(SeriesModel series){
//        String judul=series.getJudul();
//        int bintang =series.getBintang();
//        int status=series.getStatusData();
//        String synopsis= series.getSynopsis();
//        String comment=series.getComment();
//        int episode=series.getNumber_episode();
//        String foto=series.getFoto();
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues newData=new ContentValues();
//        newData.put("JUDUL",judul);
//        newData.put("BINTANG",bintang);
//        newData.put("STATUS",status);
//        newData.put("SYNOPSIS",synopsis);
//        newData.put("COMMENT",comment);
//        newData.put("NUM_EPISODE",episode);
//        newData.put("FOTO",foto);
//        db.insert("Series",null,newData);
//
//        int insertedIndex= getLastIDInserted();
//        insertEpisode(insertedIndex,episode);
//        insertLog(0,1,judul);
//    }
//
//    public void insertEpisode(int fkSeries, int numberEpisode){
//        SQLiteDatabase db=this.getWritableDatabase();
//        for(int i=1;i<=numberEpisode;i++) {
//            ContentValues newData=new ContentValues();
//            newData.put("JUDUL", "EPISODE " +i);
//            newData.put("BINTANG",0);
//            newData.put("FKSERIES",fkSeries);
//            db.insert("Episode",null,newData);
//        }
//    }
//
//
//
//    public int getLastIDInserted(){
//        String query="SELECT ID FROM Series ORDER BY ID DESC LIMIT 1";
//        int lastIndex=-1;
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor c=db.rawQuery(query,null);
//        if(c!=null && c.moveToFirst()){
//            lastIndex=c.getInt(0);
//        }
//        return lastIndex;
//    }
//
//    public ArrayList <MoviesModel> converttoArrayMovies(Cursor c){
//        ArrayList <MoviesModel> arr=new ArrayList<>();
//        if(c.moveToFirst()) {
//            do {
//                int id = Integer.parseInt(c.getString(0));
//                String judul = c.getString(1);
//                int bintang = Integer.parseInt(c.getString(2));
//                int status = Integer.parseInt(c.getString(3));
//                String synopsis = c.getString(4);
//                String comments = c.getString(5);
//                String foto=c.getString(6);
//                MoviesModel x = new MoviesModel(id, judul, bintang, status, synopsis, comments,foto);
//                arr.add(x);
//            } while (c.moveToNext());
//        }
//        return arr;
//    }
//
//    public ArrayList <SeriesModel> converttoArraySeries(Cursor c){
//        ArrayList <SeriesModel> arr=new ArrayList<>();
//        if(c.moveToFirst()) {
//            do {
//                int id = Integer.parseInt(c.getString(0));
//                String judul = c.getString(1);
//                int bintang = Integer.parseInt(c.getString(2));
//                int status = Integer.parseInt(c.getString(3));
//                String synopsis = c.getString(4);
//                String comments = c.getString(5);
//                int numberEpisode=c.getInt(6);
//                String foto=c.getString(7);
//                SeriesModel x=new SeriesModel(id,judul,bintang,status,synopsis,comments,numberEpisode,foto);
//
//                arr.add(x);
//            } while (c.moveToNext());
//        }
//        return arr;
//    }
//
//    public ArrayList<MoviesModel> getDataMovies(String comand){
//        String query = this.SELECT_ALL_QUERY + comand;
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor c =db.rawQuery(query,null);
//        ArrayList <MoviesModel> arr=converttoArrayMovies(c);
//        return arr;
//    }
//
//    public ArrayList<EpisodeModel> getEpisodeData(int fk){
//        String query="SELECT * FROM Episode WHERE FKSERIES = "+fk+" ORDER BY 'JUDUL' ASC";
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor c=db.rawQuery(query,null);
//        ArrayList<EpisodeModel>arr=new ArrayList<EpisodeModel>();
//        if(c.moveToFirst()) {
//            do {
//                int id = c.getInt(0);
//                String judul = c.getString(1);
//                int bintang = c.getInt(2);
//                int  status=c.getInt(3);
//                fk = c.getInt(4);
//                arr.add(new EpisodeModel(id, judul, bintang, fk,status));
//            } while (c.moveToNext());
//        }
//            return arr;
//    }
//
//    public void update(String table,int id ,ContentValues cv){
//        SQLiteDatabase db= this.getWritableDatabase();
//        db.update(table,cv,"ID = "+id,null);
//
//        int idTable;
//        if(table.equals("Movies")){
//            idTable=0;
//        }else{
//            idTable=1;
//        }
//        insertLog(1,idTable,getJudul(table,id));
//    }
//
//    public void delete(String table,int id){
//        SQLiteDatabase db= this.getWritableDatabase();
//        db.delete(table,"ID = "+id,null);
//        int idTable;
//        if(table.equals("Movies")){
//            idTable=0;
//        }else{
//            idTable=1;
//        }
//        insertLog(2,idTable,getJudul(table,id));
//    }
//
//    public ArrayList<SeriesModel> getDataSerires(String command){
//        String query = this.SELECT_ALL_QUERY_SERIES + command;
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor c =db.rawQuery(query,null);
//        ArrayList <SeriesModel> arr=converttoArraySeries(c);
//        return arr;
//
//    }
//
//    public void insertLog(int aksi,int table,String judul){
//        ContentValues cv=new ContentValues();
//        cv.put("AKSI",aksi);
//        cv.put("JUDUL",judul);
//        cv.put("TABEL",table);
//
//        SQLiteDatabase db=this.getWritableDatabase();
//        Long d=db.insert("Log",null,cv);
//    }
//
//    public String getJudul(String table, int id){
//        String query="SELECT JUDUL FROM "+ table+" WHERE ID = "+ id;
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor c=db.rawQuery(query,null);
//        if(c.moveToFirst()) {
//            return c.getString(0);
//        }
//        return "";
//    }
//
//    public ArrayList<logModel> getDataLog(){
//        String query="SELECT * FROM Log";
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor c =db.rawQuery(query,null);
//        return convertdbToArray(c);
//    }
//
//
//    public ArrayList<logModel> convertdbToArray(Cursor c){
//        ArrayList <logModel> arr=new ArrayList<>();
//        if(c.moveToFirst()) {
//            do {
//                int aksi=c.getInt(1);
//                String judul=c.getString(2);
//                int table=c.getInt(3);
//                String tanggal=c.getString(4);
//                logModel x= new logModel(aksi,judul,table,tanggal);
//                arr.add(x);
//            } while (c.moveToNext());
//        }
//        return arr;
//    }


}
