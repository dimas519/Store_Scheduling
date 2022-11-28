package com.dimas519.storescheduling.Model;


public class Machine {
    protected int id;
    protected double start;
    protected double end;


    public Machine(int id,double start,double end){
        this.id=id;
        this.start=start;
        this.end=end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }
}
