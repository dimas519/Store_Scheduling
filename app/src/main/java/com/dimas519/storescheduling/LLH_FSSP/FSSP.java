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
import com.dimas519.storescheduling.Presenter.iFSSPAlgorithm;


public class FSSP implements Runnable{
    private String filePath;
    private int selectedAlgorithm;
    private iFSSPAlgorithm iFSSPAlgorithm;


    public FSSP(int selectedAlgorithm, String filePath, iFSSPAlgorithm iFSSPAlgorithm){
        this.selectedAlgorithm=selectedAlgorithm;
        this.filePath=filePath;
        this.iFSSPAlgorithm=iFSSPAlgorithm;
    }


    public void experiment(String fileName, int selectedAlgorithm) {

        Problem p = new Problem();
        Scheduler schdr = new Scheduler(p);
        Schedule sch = null;

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
        }else if (selectedAlgorithm==Algorithm.MOD) {
            sch = schdr.MOD();
        }



        this.iFSSPAlgorithm.setJobs(schdr.getJobs() );
    }

    @Override
    public void run() {
        experiment(this.filePath,this.selectedAlgorithm);

    }
}
