package com.dimas519.storescheduling.Model;

public class Kerjaan {
    protected Machine[] machine;
    protected int jobNumber;

    public Kerjaan(int jobNumber, Machine[] machine){
        this.jobNumber=jobNumber;
        this.machine=machine;
    }

}
