package com.dimas519.storescheduling.Model;


public class Jobs {

    protected Kerjaan[] kerjaan;
    protected double makeSpan=-1.0;



    public Jobs(int numberOfJob, double makeSpan){
        this.kerjaan=new Kerjaan[numberOfJob];
        this.makeSpan=makeSpan;
    }

    public Jobs(){}



    public int getNumberOfJob() {
        return this.kerjaan.length;
    }



    public Kerjaan[] getJKerjaan() {
        return kerjaan;
    }

    public void setKerjaan(Kerjaan[] kerjaan) {
        this.kerjaan = kerjaan;
    }

    public double getMakeSpan() {
        return makeSpan;
    }

    public void setMakeSpan(double makeSpan) {
        this.makeSpan = makeSpan;
    }
}

