package com.dimas519.storescheduling.Code;


public class Algorithm {
    public final static int NumberOfAlgorithm=8;
    public final static int FCFS=1;
    public final static int CDS=2;
    public final static int NEH=3;
    public final static int PALMER=4;
    public final static int GUPTA=5;
    public final static int DANNENBRING=6;
    public final static int POUR=7;
    public final static int MOD=8;

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
            default:
                return "null";
        }
    }

}
