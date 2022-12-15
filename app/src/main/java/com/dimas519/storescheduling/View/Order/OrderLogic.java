package com.dimas519.storescheduling.View.Order;

import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.Model.Produk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OrderLogic {
    private final static String fileName="tempData";

    public static String createTxt(ArrayList<Pesanan> pesananArrayList){

        File x = null;
        try {
            x = File.createTempFile("temporary_file", ".txt");
            x.deleteOnExit();

            int jumlahOrder=pesananArrayList.size();
            int jumlahProses=pesananArrayList.get(0).getProduk().getProcess().size();

            int arr[][]=new int[jumlahOrder][jumlahProses];
            int j=0;
            for (Pesanan currPesanan: pesananArrayList){
                int qty=currPesanan.getQuantity();
                Produk p=currPesanan.getProduk();
                int i=0;
                for (Process currProses: p.getProcess()){
                    arr[i][j]=(currProses.getWaktuProses()*qty);
                    i++;
                }

                j++;

            }


            for(int i=0;i<jumlahProses;i++){
                for(int p=0;p<jumlahOrder;p++){
                    System.out.println(arr[i][p]);
                }
            }







        } catch (IOException e) {
            e.printStackTrace();
        }
        return x.getAbsolutePath();
    }












}
