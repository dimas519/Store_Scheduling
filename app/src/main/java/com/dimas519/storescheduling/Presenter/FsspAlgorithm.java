package com.dimas519.storescheduling.Presenter;


import com.dimas519.storescheduling.LLH_FSSP.FSSP;
import com.dimas519.storescheduling.Model.Jobs;
import com.dimas519.storescheduling.View.FSSP.iFSSPAdapter;


public class FsspAlgorithm implements iFSSPAlgorithm  {
    private final int algorithm;
    private Jobs jobs;
    private final iFSSPAdapter ia;


    public FsspAlgorithm(int algorithm,String file,iFSSPAdapter ia){
        this.algorithm=algorithm;
        this.ia=ia;
        this.runJobs(file);
    }

    private void runJobs(String file){
        Thread other=new Thread(new FSSP(this.algorithm,file,this));
        other.start();
    }
    @Override
    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
        this.changed();
    }

    public int getAlgorithm() {
        return algorithm;
    }


    public Jobs getJobs() {
        return jobs;
    }

    public void changed(){
        this.ia.notifyChanges();
    }






}

