package com.dimas519.storescheduling.Model;


public class Kerjaan {
    protected int jobNumber;
    protected Machine[] machine;

    public Kerjaan(int jobNumber, Machine[] machine){
        this.jobNumber=jobNumber;
        this.machine=machine;
    }


    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Machine[] getMachine() {
        return machine;
    }

    public void setMachine(Machine[] machine) {
        this.machine = machine;
    }
}
