package com.dimas519.storescheduling.Code;

public class PopUp {
    public final static int numOfPopUp = 4;

    public final static int pelangganPopUp = 0;
    public final static int produkpopUp = 1;

    public static String getAlgorithm(int codes){
        switch (codes) {
            case pelangganPopUp:
                return "Pelanggan";
            case produkpopUp:
                return "Produk";
            default:
                return "null";
        }
    }

}

