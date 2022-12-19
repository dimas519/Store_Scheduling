package com.dimas519.storescheduling.LLH_FSSP;

/**
 *
 * @author Heni
 *
 * modified from Main.java
 *
 */


import com.dimas519.storescheduling.Code.Algorithm;
import com.dimas519.storescheduling.LLH_FSSP.FA.gphh.FSFrameWork;
import com.dimas519.storescheduling.Model.Jobs;
import com.dimas519.storescheduling.Model.Kerjaan;
import com.dimas519.storescheduling.Model.Machine;
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
        if(selectedAlgorithm==Algorithm.FA){
            FSFrameWork fsf=new FSFrameWork();
            fsf.setBeta0("0.1");
            fsf.setGamma("1.0");
            fsf.setMaxRun("100");
            fsf.setPopSize("10");

            String output1=fsf.getFAStr(p);

            Kerjaan[] job=new Kerjaan[p.JOB_NUM];


            Slot[][] slots=fsf.getFA().getSlots();
            for (int a=0;a<slots.length;a++){
                Machine machine[]=new Machine[p.MACHINE_NUM];
                for(int b=0;b<slots[a].length;b++){
                    machine[b]=new Machine(a+1,slots[a][b].getStart(),slots[a][b].getFinish());
                }
                job[a]=new Kerjaan(a,machine);
            }

            Jobs faJobs=new Jobs();
            faJobs.setKerjaan(job);
            faJobs.setMakeSpan(fsf.getFA().getBestInd().getMakespan());


            this.iFSSPAlgorithm.setJobs(faJobs);



        }else if (selectedAlgorithm == Algorithm.FCFS) {
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


        if(selectedAlgorithm!=Algorithm.FA) {
            this.iFSSPAlgorithm.setJobs(schdr.getJobs());
        }
    }

    @Override
    public void run() {
        experiment(this.filePath,this.selectedAlgorithm);

    }
}
