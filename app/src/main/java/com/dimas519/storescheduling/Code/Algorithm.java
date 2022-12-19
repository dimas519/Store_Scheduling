package com.dimas519.storescheduling.Code;


public class Algorithm {
    public final static int NumberOfAlgorithm=9;
    public final static int FCFS=0;
    public final static int CDS=1;
    public final static int NEH=2;
    public final static int PALMER=3;
    public final static int GUPTA=4;
    public final static int DANNENBRING=5;
    public final static int POUR=6;
    public final static int MOD=7;
    public final static int FA=8;

    public static String getAlgorithm(int codes){
        switch (codes) {
            case FCFS:
                return "FCFS";
            case CDS:
                return "CDS";
            case NEH:
                return "NEH";
            case PALMER:
                return "PALMER";
            case GUPTA:
                return "GUPTA";
            case DANNENBRING:
                return "DANNENBRING";
            case POUR:
                return "POUR";
            case MOD:
                return "MOD";
            case FA:
                return "FA";
            default:
                return "null";
        }
    }

}
