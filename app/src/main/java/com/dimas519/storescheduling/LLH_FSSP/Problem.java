package com.dimas519.storescheduling.LLH_FSSP;
/**
 *
 * @author Heni
 */
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Problem {
    public int MACHINE_NUM;
    public int JOB_NUM;
    public int OPERATION_NUM;
    public Job [] Jobs = new Job [500];
    int [] optSchedule = new int [500];
    double lbMakeSpan; // lb benchmark
    double ubMakeSpan; // ub benchmark
    Double [] gIndex; // gupta's slope index
    Double [] pIndex; // palmer's slode index

    public Problem (){
    }

    void ReadAProblem1() {
        this.MACHINE_NUM = 6;
        this.JOB_NUM = 5;
        Jobs[0] = new Job(0, this.MACHINE_NUM);
    }

    @Deprecated
    void ReadAProblem(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Jumlah mesin: ");
        this.MACHINE_NUM = sc.nextInt();

        System.out.println("Jumlah job: ");
        this.JOB_NUM = sc.nextInt();

        for (int i=0; i<this.JOB_NUM;i++){
            Jobs[i] = new Job(i, this.MACHINE_NUM);
        }
    }

    public void ReadAProblem(String fileName){
        String line = "";
        String separator = ",";
        BufferedReader br = null;

        System.out.println(fileName);

        try{
            br = new BufferedReader(new FileReader(fileName));
            System.out.println("proses baca file.....");
            line = br.readLine();
            String [] jm = line.split(separator);
            System.out.println("Jumlah job ="+jm[0]);
            System.out.println("Jumlah machine ="+jm[1]);
//            System.out.println("Batas bawah ="+jm[3]);
//            System.out.println("Batas atas ="+jm[4]);

            this.JOB_NUM = Integer.parseInt(jm[0].trim());
            this.MACHINE_NUM = Integer.parseInt(jm[1].trim());
//            this.lbMakeSpan  = Integer.parseInt(jm[3].trim());
//            this.ubMakeSpan = Integer.parseInt(jm[4].trim());
            //           System.out.println("Jumlah job ="+jobN);
            //           System.out.println("Jumlah machine ="+machN);

            Double [][] pt = new Double[this.JOB_NUM][this.MACHINE_NUM];
            double [] tpt = new double [this.JOB_NUM]; // job's total processing time

            for (int i=0; i<this.MACHINE_NUM; i++){
                line = br.readLine();
                jm = line.split(separator);
                for (int j=0; j< this.JOB_NUM; j++){
                    System.out.println("mesin ke-"+ i+" job ke-"+j+ " = "+ Double.parseDouble(jm[j].trim()));
                    pt[j][i] = new Double (Double.parseDouble(jm[j].trim()));
                }
            }

            for (int i=0;i<this.JOB_NUM;i++) {
                Jobs[i] = new Job(i, this.MACHINE_NUM, pt[i]);
            }

        }catch(IOException ex){
            System.err.println("File tidak ditemukan ");
        } finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException ex){
                    // ex.printStackTrace();
                }
            }
        }
    }

    @Deprecated
    // 13.08.2020
    public void ReadAProblemDD(String fileName){
        String line = "";
        String separator = " ";
        BufferedReader br = null;

        System.out.println(fileName);

        try{
            br = new BufferedReader(new FileReader(fileName));
            System.out.println("proses baca file.....");
            line = br.readLine();
            String [] jm = line.split(separator);
            System.out.println("Banyak job ="+jm[0]);
            System.out.println("Banyak mesin ="+jm[1]);
            System.out.println("Banyak operasi ="+jm[2]);
//            System.out.println("Batas atas ="+jm[4]);

            this.JOB_NUM = Integer.parseInt(jm[0].trim());
            this.MACHINE_NUM = Integer.parseInt(jm[1].trim());
            this.OPERATION_NUM = Integer.parseInt(jm[2].trim());
//            this.lbMakeSpan  = Integer.parseInt(jm[3].trim());
//            this.ubMakeSpan = Integer.parseInt(jm[4].trim());
            //           System.out.println("Jumlah job ="+jobN);
            //           System.out.println("Jumlah machine ="+machN);

            Double [][] pt = new Double[this.JOB_NUM][this.MACHINE_NUM];
            double [] tpt = new double [this.JOB_NUM]; // job's total processing

            for (int i=0; i<this.JOB_NUM; i++){
                int rd = 0;
                int dd = 0;
//                double w = 0;
//                int mnum = 0;
                line = br.readLine();
                jm = line.split(separator);
                rd = Integer.parseInt(jm[0].trim()); //rel date
                dd = Integer.parseInt(jm[1].trim()); // due date
//                w = Integer.parseInt(jm[2].trim()); // weight
//                mnum = Integer.parseInt(jm[3].trim()); // machine num

                for (int j=0; j<this.MACHINE_NUM; j++) {
                    // pasangan nomor mesin dan processing time

                    int mn = Integer.parseInt(jm[4+j*2].trim())-1;
                    double pti = Double.parseDouble(jm[5+j*2].trim());
//                    System.out.println(j+" "+(4+j*2)+" "+(5+j*2));
//                    System.out.println("job ke-"+ i+" mesin ke-"+mn+ " = "+ pti);
                    pt[i][mn] = pti; //new Double (Double.parseDouble(jm[j].trim()));
                }
//                for (int i=0;i<this.JOB_NUM;i++) {
                Jobs[i] = new Job(i, this.MACHINE_NUM, pt[i],rd, dd);
//                }
            }

        }catch(IOException ex){
            System.err.println("File tidak ditemukan ");
        } finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException ex){
                    // ex.printStackTrace();
                }
            }
        }
    }

    public int getJobsNumber () {
        return this.JOB_NUM;
    }

    public int getMachinesNumber () {
        return this.MACHINE_NUM;
    }

    public Double [] getGuptaIndex(){
        return this.gIndex;
    }

    public Double [] getPalmerIndex(){
        return this.pIndex;
    }

    public double getLBMakeSpan(){
        return this.lbMakeSpan;
    }

    public double getUBMakeSpan(){
        return this.ubMakeSpan;
    }

    public void setGuptaIndex(Double [] gi){
        this.gIndex = gi;
    }

    public void setPalmerIndex(Double [] pi){
        this.pIndex = pi;
    }

    public Job [] getJobs() {
        return this.Jobs;
    }


}