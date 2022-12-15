package com.dimas519.storescheduling.Code;

public class PopUp {
    public final static int numOfPopUp = 4;

    public final static int pelangganPopUp = 0;
    public final static int produkpopUp = 1;
    public final static int about=2;
    public final static int setting=3;
    public final static int order=4;

    public static String getAlgorithm(int codes){
        switch (codes) {
            case pelangganPopUp:
                return "Pelanggan";
            case produkpopUp:
                return "Produk";
            case about:
                return "About";
            case setting:
                return "Pengaturan";
            case order:
                return "Pemesanan";
            default:
                return "null";
        }
    }

}

