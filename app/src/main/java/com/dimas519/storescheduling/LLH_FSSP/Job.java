package com.dimas519.storescheduling.LLH_FSSP;

/*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */

    import java.util.Scanner;

    /**
     *
     * @author Heni
     */
    public class Job {
        int id;
        int machine_num;
        public Double [] processing_time = new Double [100];
        double due_date;
        double rel_date;
        double totProcTime;

        final double totalPT (int m, Double [] pt) {
            double tpt = 0.0;
            for (int i=0; i<m; i++) {
                tpt = tpt + pt[i].doubleValue();
            }
            return tpt;
        }

        Job (int id, int nmachine, int [] proct) {
            this.id = id;
            this.machine_num = nmachine;
            System.arraycopy(proct, 0, this.processing_time, 0, nmachine);
            totProcTime = totalPT (this.machine_num, this.processing_time);
        }

        Job (int id, int nmachine, Double [] proct) {
            this.id = id;
            this.machine_num = nmachine;
            System.arraycopy(proct, 0, this.processing_time, 0, nmachine);
            totProcTime = totalPT (this.machine_num, this.processing_time);
        }

        Job (int id, int nmachine, Double [] proct, double dd, double rd) {
            this.id = id;
            this.machine_num = nmachine;
            this.due_date = dd;
            this.rel_date = rd;
            System.arraycopy(proct, 0, this.processing_time, 0, nmachine);
            totProcTime = totalPT (this.machine_num, this.processing_time);
        }

        Job (int id) {
            this.id = id;
        }

        Job (int id, int nmachine){
            this.id = id;
            this.machine_num = nmachine;
            this.readAJob();
        }

        void printJob(){
            System.out.print(this.id);
            for (int i=0; i< machine_num;i++) {
                System.out.print(this.processing_time[i]+" ");
            }
            System.out.println();
        }

        void readAJob (){
            Scanner sc = new Scanner(System.in);
            for (int i=0; i< this.machine_num;i++){
                System.out.print("waktu proses job "+this.id+" di mesin "+i+" = ");
                this.processing_time[i] = sc.nextDouble();
            }
        }

        public double getDueDate(){
            return this.due_date;
        }


        public double getTotProcTime(){
            return this.totProcTime;
        }
    }


