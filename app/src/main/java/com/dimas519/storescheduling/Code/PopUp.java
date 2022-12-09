package com.dimas519.storescheduling.Code;

public class PopUp {
    public final static int numOfPopUp = 4;

    public final static int pelangganPopUp = 0;

    public static String getAlgorithm(int codes){
        switch (codes) {
            case pelangganPopUp:
                return "Pelanggan";
            default:
                return "null";
        }
    }

}

