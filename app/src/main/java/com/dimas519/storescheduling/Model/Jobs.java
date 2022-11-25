package com.dimas519.storescheduling.Model;

public class Jobs {

    protected Kerjaan[] job;
    protected double makeSpan=-1.0;

    public Jobs(int numberOfJob, double makeSpan){
        this.job=new Kerjaan[numberOfJob];
        this.makeSpan=makeSpan;
    }

    public Jobs(){

    }



    public int getNumberOfJob() {
        return this.job.length;
    }



    public Kerjaan[] getJobs() {
        return job;
    }

    public void setJobs(Kerjaan[] job) {
        this.job = job;
    }

    public double getMakeSpan() {
        return makeSpan;
    }

    public void setMakeSpan(double makeSpan) {
        this.makeSpan = makeSpan;
    }
}
