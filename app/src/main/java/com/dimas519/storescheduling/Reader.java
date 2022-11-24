package com.dimas519.storescheduling;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    public String readFile(String path){
        File txtFile=new File("/storage/emulated/0/Download/example.txt");
        try {
            Scanner reader=new Scanner(txtFile);
            Log.d("ppppp", "readFile: "+reader.next());
        } catch (FileNotFoundException e) {
            System.out.println("ppppp error"+e.getMessage());
        }

        return "haha";
    }

}
