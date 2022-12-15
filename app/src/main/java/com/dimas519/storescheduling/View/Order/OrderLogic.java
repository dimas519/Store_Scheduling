package com.dimas519.storescheduling.View.Order;

import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.Model.Produk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class OrderLogic {
    private final static String fileName="tempData";

    public static String createTxt(ArrayList<Pesanan> pesananArrayList){

        File fileTemp = null;
        try {
            fileTemp = File.createTempFile("temporary_file", ".txt");
            fileTemp.deleteOnExit();

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

            OutputStream outputStream = new FileOutputStream(fileTemp);
            outputStream.write((jumlahProses+", "+jumlahOrder+"\n").getBytes());


            for(int i=0;i<jumlahProses;i++){
                for(int p=0;p<jumlahOrder;p++){
                    if(p>0){
                        System.out.print(", ");
                        outputStream.write((", ").getBytes());
                    }
                    System.out.print(arr[i][p]);
                    outputStream.write((arr[i][p]+"").getBytes());
                }
                System.out.println();
                outputStream.write(("\n").getBytes());
            }


            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileTemp.getAbsolutePath();
    }












}
