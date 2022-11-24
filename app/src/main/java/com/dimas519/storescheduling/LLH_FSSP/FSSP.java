package com.dimas519.storescheduling.LLH_FSSP;

/**
 *
 * @author Heni
 *
 * modified from Main.java
 *
 */


import com.dimas519.storescheduling.Code.Algorithm;
import com.dimas519.storescheduling.Model.Jobs;

public class FSSP implements Runnable{
    private String filePath;
    private int selectedAlgorithm;
    public FSSP(int selectedAlgorithm, String filePath){
        this.selectedAlgorithm=selectedAlgorithm;
        this.filePath=filePath;
    }


    public Jobs experiment(String fileName, int selectedAlgorithm) {

        Problem p = new Problem();
        Scheduler schdr = new Scheduler(p);
        Schedule sch;

        p.ReadAProblem(fileName);

        if (selectedAlgorithm == Algorithm.FCFS) {
            sch = schdr.FCFS();
        } else if (selectedAlgorithm == Algorithm.CDS) {
            sch = schdr.CDS();
        } else if (selectedAlgorithm == Algorithm.NEH) {
            sch = schdr.NEH();
        } else if (selectedAlgorithm == Algorithm.PALMER) {
            sch = schdr.Palmer();
        }else if (selectedAlgorithm== Algorithm.GUPTA) {
            sch = schdr.Gupta1();
        }else if (selectedAlgorithm==Algorithm.DANNENBRING) {
            sch = schdr.Dannenbring();
        }else if (selectedAlgorithm==Algorithm.POUR) {
            sch = schdr.Pour();
        }


        return null;
    }

    @Override
    public void run() {
        experiment(this.filePath,this.selectedAlgorithm);
    }
}
