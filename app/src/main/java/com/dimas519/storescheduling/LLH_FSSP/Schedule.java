package com.dimas519.storescheduling.LLH_FSSP;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Heni
 */
public class Schedule {
    int id;
    int size;
    public int [] ordering; // = new int [10];
    Double makespan = 0.0;
    double tardiness = 0;

    public Schedule (int id, int size){
        this.id = id;
        this.size = size;
        ordering = new int [size];
    }

    void setDefaultOrdering(){
        for (int i=0; i<this.size;i++) {
            this.ordering[i]=i;
        }
    }

    int getId(){
        return this.id;
    }

    int getSize() {
        return this.size;
    }

    public Double getMakeSpan() {
        return this.makespan;
    }

    int [] getOrdering(){
        return this.ordering;
    }

    void setId(int id){
        this.id = id;
    }

    public void setMakeSpan(Double ms){
        this.makespan = ms;
    }

    public void setOrdering(int [] ord){
        System.arraycopy(ord, 0, this.ordering, 0, this.size);
    }

    public double getTardiness(Problem p, Slot [][] S){
        double tar = 0;
        for (int i = 0; i<p.JOB_NUM;i++){
            double tj = S[i][p.MACHINE_NUM-1].getFinish()-p.getJobs()[i].getDueDate();
            tar = tar + tj;
        }
        return tar;
    }

    public void setTardiness(double t){
        this.tardiness = t;
    }

    public double getTardiness(){
        return this.tardiness;
    }

    void printSchedule(){
        System.out.print("Schedule "+this.id+"[" );
        for (int i=0; i<this.size;i++){
            System.out.print(this.ordering[i]+" ");
        }
        System.out.println("] Makespan = "+this.makespan);
    }

    public String getStrSchedule(){
        String s = "";
        s = "Schedule: "+this.ordering[0];
        for (int i=1; i<this.size;i++){
            s = s + " - " +this.ordering[i];
        }
        return s+"\n";
    }

}
