package com.dimas519.storescheduling.Storage.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Pelanggan;
import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.Model.Produk;

import java.util.ArrayList;


public class database extends SQLiteOpenHelper {
    private static int DB_version=8;
    private static String DB_name="lisTify.db";


    private final static String pelangganTable="Pelanggan";
    private final static String produkTable="Produk";
    private final static String prosesTable="Proses";
    private final static String orderTable="orderTable";

    private static  final String CREATE_TABLE_PELANGGAN= "CREATE TABLE "+pelangganTable+
            " ( ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "NAMA TEXT , " +
            "KONTAK TEXT  , " +
            "ALAMAT TEXT , " +
            "EMAIL TEXT , " +
            "TELEPON TEXT )";

    private static final String CREATE_TABLE_PRODUK= "CREATE TABLE "+produkTable +
            " ( ID INTEGER PRIMARY KEY AUTOINCREMENT , " +
            "Kode_PRODUK TEXT , " +
            "NAMA_PRODUK TEXT )";

    private static final String CREATE_TABLE_PROSES= "CREATE TABLE "+prosesTable +
            "( FK_PRODUK INTEGER , "+
            "NAMA_PROSES TEXT ," +
            "URUTAN INTEGER ," +
            "WAKTU_PROSES TEXT )";

    private static final String CREATE_TABLE_Order= "CREATE TABLE "+orderTable +
            "( ID INTEGER PRIMARY KEY AUTOINCREMENT  , "+
            "FK_PELANGGAN INTEGER ," +
            "FK_PRODUK INTEGER ," +
            "QUANTITY INTEGER )";


    public database(Context context){
        super(context,DB_name,null,DB_version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PELANGGAN);
        db.execSQL(CREATE_TABLE_PRODUK);
        db.execSQL(CREATE_TABLE_PROSES);
        db.execSQL(CREATE_TABLE_Order);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String query= "DROP TABLE IF EXISTS Movies";
//        db.execSQL( query);
//        query="DROP TABLE IF EXISTS Series";
//        db.execSQL(query);
        db.execSQL("DROP TABLE IF EXISTS "+orderTable);
        db.execSQL(CREATE_TABLE_Order);
//        db.execSQL("DROP TABLE IF EXISTS Log");
    }


    public long insertPelanggan(Pelanggan movie){
        String nama=movie.getNama();
        String kontak =movie.getKontak();
        String alamat=movie.getAlamat();
        String email= movie.getEmail();
        String telepon=movie.getTelepon();

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newData=new ContentValues();
        newData.put("NAMA",nama);
        newData.put("KONTAK",kontak);
        newData.put("ALAMAT",alamat);
        newData.put("EMAIL",email);
        newData.put("TELEPON",telepon);
        long id=db.insert(pelangganTable,null,newData);
        db.close();
        return id;
    }

        public ArrayList<Pelanggan> getPelanggan(){
        String query ="SELECT * FROM  "+pelangganTable+" ORDER BY ID DESC";
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c =db.rawQuery(query,null);


        ArrayList <Pelanggan> arr=new ArrayList<>();

            if(c.moveToFirst()) {
            do {
                int id=c.getInt(0);
                String NAMA=c.getString(1);
                String KONTAK=c.getString(2);
                String ALAMAT=c.getString(3);
                String EMAIL=c.getString(4);
                String TELEPON=c.getString(5);


                arr.add(new Pelanggan(id,NAMA,KONTAK,ALAMAT,EMAIL,TELEPON));
            } while (c.moveToNext());
        }


        return arr;
    }

    public Produk insertProduk(Bundle res){
        String json=res.getString("data");


        //insert produk
        Produk newProduct= MainActivity.gson.fromJson(json,Produk.class);
        long idNewProduk=this.insertProduk(newProduct);

        //insert proses
        ArrayList<Process> newProcess=new ArrayList<>();
        int numOfProcess=res.getInt("numOfProcess");

        for(int i=0;i<numOfProcess;i++){
            json=res.getString("process"+i);
            Process x=MainActivity.gson.fromJson(json,Process.class);
            x.setFkProduk(idNewProduk);
            newProcess.add(x);
        }

        newProduct.setProcess(newProcess);
        this.insertProcess(newProcess);

        return newProduct;
    }



    private long insertProduk(Produk series){
        String kode=series.getKode();
        String nama =series.getNama();
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newData=new ContentValues();
        newData.put("Kode_PRODUK",kode);
        newData.put("NAMA_PRODUK",nama);
        long id=db.insert(produkTable,null,newData);
        db.close();
        return id;
    }

    public ArrayList<Produk> getProduk(){
        String query ="SELECT * FROM "+produkTable;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c =db.rawQuery(query,null);
        ArrayList <Produk> arr=new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                int id = Integer.parseInt(c.getString(0));
                String kode = c.getString(1);
                String nama = c.getString(2);
                Produk x = new Produk(id, kode, nama);

                ArrayList<Process> currProdukProcess=this.getProcess(id);
                x.setProcess(currProdukProcess);

                arr.add(x);
            } while (c.moveToNext());
        }
        return arr;
    }

    private void insertProcess(ArrayList<Process> process){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newData=new ContentValues();

        for (Process currProses:process){
            long fk=currProses.getFkProduk();
            String nama =currProses.getNamaProses();
            int waktu=currProses.getWaktuProses();
            int order=currProses.getOrder();

            newData.put("FK_PRODUK",fk);
            newData.put("NAMA_PROSES",nama);
            newData.put("URUTAN",order);
            newData.put("WAKTU_PROSES",waktu);

            db.insert(prosesTable,null,newData);
        }
        db.close();

    }



    private ArrayList<Process> getProcess(int fk){
        String query ="SELECT * FROM "+prosesTable+
                " WHERE FK_PRODUK = "+fk+
                " ORDER BY URUTAN ASC";
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c =db.rawQuery(query,null);

        ArrayList <Process> arrProcess=new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                fk =c.getInt(0);
                String nama = c.getString(1);
                int urutan=c.getInt(2);
                int waktu = c.getInt(3);
                Process x = new Process(fk, nama, urutan,waktu);

                arrProcess.add(x);
            } while (c.moveToNext());
        }

        return arrProcess;
    }

    public void insertOrder(Pelanggan pelanggan,Produk produk,int quantity){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues newData=new ContentValues();
        newData.put("FK_PELANGGAN", pelanggan.getId());
        newData.put("FK_PRODUK",produk.getId());
        newData.put("QUANTITY",quantity);
        db.insert(this.orderTable,null,newData);

    }

    public ArrayList <Pesanan> getAllPesanan(){
        String query ="SELECT * FROM "+orderTable+
                " INNER JOIN "+pelangganTable+
                    " ON "+orderTable+".FK_PELANGGAN="+pelangganTable+".ID"+
                        " INNER JOIN "+produkTable+
                            " ON "+produkTable+".ID="+orderTable+".FK_PRODUK";

        SQLiteDatabase db= this.getWritableDatabase();
        Cursor c =db.rawQuery(query,null);
        String allKolom[]=c.getColumnNames();

        for(String kolom:allKolom){
            System.out.println(kolom);
        }


        ArrayList <Pesanan> pesananArrayList=new ArrayList<>();
        if(c.moveToFirst()) {
            do {

                //kolom pelanggan
                int id=c.getInt(4);
                String nama=c.getString(5);
                String kontak=c.getString(6);
                String alamat=c.getString(7);
                String email=c.getString(8);
                String telepon=c.getString(9);

                Pelanggan curPelanggan=new Pelanggan(id,nama,kontak,alamat,email,telepon);
                System.out.println("db"+curPelanggan.getNama());

                //buat Produk
                id=c.getInt(10);
                kontak=c.getString(11);//nama
                nama=c.getString(12); //kode

                Produk curProduct=new Produk(id,kontak,nama);

                //kolom pesanan
                id=c.getInt(0);
                int fkProduk=c.getInt(2);
                int quantity=c.getInt(3);

                ArrayList<Process> newProcess=this.getProcess(fkProduk);
                curProduct.setProcess(newProcess);

                Pesanan curPesanan=new Pesanan(id,curPelanggan,curProduct,quantity);

                pesananArrayList.add(curPesanan);
            } while (c.moveToNext());
        }


        return pesananArrayList;
    }



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


}
