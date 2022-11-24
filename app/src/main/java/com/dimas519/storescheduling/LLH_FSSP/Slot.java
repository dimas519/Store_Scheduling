package com.dimas519.storescheduling.LLH_FSSP;

public class Slot {
    Double start;
    Double finish;

    Slot (Double s, Double f) {
        start = s;
        finish = f;
    }

    public Double getStart() {
        return this.start;
    }

    public Double getFinish() {
        return this.finish;
    }
}
