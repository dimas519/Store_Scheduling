/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimas519.storescheduling.LLH_FSSP.FA.gfa;

import java.util.ArrayList;
import java.util.List;
import com.dimas519.storescheduling.LLH_FSSP.*;

/**
 * kelas ini merepresentasikan kandidat solusi
 * di GA disebut chromosome, di FA disebut firefly
 * @author FTIS
 */

public class individual {
    int size;
    int [] ordering;
    double makespan;
    double fitness;
    double tardiness = 0.0;
    double Cmean = 0.0;
    
    individual(int nJob){
        size = nJob;
        List<Integer> li = new ArrayList<Integer>(); 
        ordering = new int [size];
        
        for (int i=0; i<size; i++){
            li.add(i);
        }
        java.util.Collections.shuffle(li);
        for (int i=0; i<size; i++){
            ordering[i] = li.get(i);
        }
        makespan = 0;
    }

    public int [] getOrdering(){
        return this.ordering;
    }
    
    public double getMakespan(){
        return this.makespan;
    }
    
    public double getFitness(){
        return this.fitness;
    }
    
    public void setFitness(Problem p){
        Schedule S = new Schedule(0, p.getJobsNumber());
    }

    public void setFitness(double f){
        this.fitness = f;
    }

    void setDefaultOrdering(){
        for (int i=0; i<this.size;i++) {
            this.ordering[i]=i;
        }
    }
    
    int getSize() {
        return this.size;
    }
    
    public Double getMakeSpan() {
        return this.makespan;
    }
    
    public void setMakeSpan(Double ms){
        this.makespan = ms;
    }
    
    public void setOrdering(int [] ord){
        System.arraycopy(ord, 0, this.ordering, 0, this.size);
    }
    
/*    public double getTardiness(Problem p, Slot [][] S){
        double tar = 0;
        for (int i = 0; i<p.JOB_NUM;i++){
            double tj = S[i][p.MACHINE_NUM-1].getFinish()-p.getJobs()[i].getDueDate();
            tar = tar + tj;
        }
        return tar;    
    }
*/
// 06062021 --> mengikuti paper A1    
    public double getTardiness(Problem p, Slot [][] S){
        double tar = 0;
        for (int i = 0; i<p.JOB_NUM;i++){
            System.out.println(S[i][p.MACHINE_NUM-1].getFinish());
            System.out.println(p.getJobs()[i].getDueDate());
            if (S[i][p.MACHINE_NUM-1].getFinish()> p.getJobs()[i].getDueDate()) {
                tar = tar + (1.0/p.getJobsNumber());
            }
        }
//        tar = tc/p.getJobsNumber(); 
        System.out.println(tar+" "+p.getJobsNumber());
        return tar/p.getJobsNumber();    
    }

    public void setTardiness(double t){
        this.tardiness = t;
    }
    
    public void setCmean(double c){
        this.Cmean = c;
    }
    
    public double getTardiness(){
        return this.tardiness; 
    }
    
    public double getCmean(){
        return this.Cmean;
    }

    public double getCmean(Problem p, Slot [][] S){
        double cm = 0;
        for (int i = 0; i<p.JOB_NUM;i++){
            cm = cm + (S[i][p.MACHINE_NUM-1].getFinish()-p.Jobs[i].getTotProcTime())/ p.Jobs[i].getTotProcTime();
        }
        return cm/p.getJobsNumber();
    }
    
    public void printSchedule(){    
        System.out.print("Schedule [" );
        for (int i=0; i<this.size;i++){
            System.out.print((this.ordering[i]+1)+" ");
        }
        System.out.println("] Makespan = "+this.makespan);
    }

    public String getStrSchedule(){    
        String s = "";
        s = "Schedule: "+(this.ordering[0]+1);
        for (int i=1; i<this.size;i++){
            s = s + " - " +(this.ordering[i]+1);
        }
        return s+"\n";
    }
    
}
