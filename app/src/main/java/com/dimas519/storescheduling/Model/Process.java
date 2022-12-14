package com.dimas519.storescheduling.Model;

public class Process {
    private long fkProduk;
    private String namaProses;
    private int waktuProses;
    private int order; //urutan

    public Process(int fkProduk, String namaProses, int order  , int waktuProses) {
        this.fkProduk = fkProduk;
        this.namaProses = namaProses;
        this.waktuProses = waktuProses;
        this.order=order;
    }

    public Process(String namaProses, int waktuProses) {
        this.namaProses = namaProses;
        this.waktuProses = waktuProses;
    }

    public long getFkProduk() {
        return fkProduk;
    }

    public void setFkProduk(long fkProduk) {
        this.fkProduk = fkProduk;
    }

    public String getNamaProses() {
        return namaProses;
    }

    public void setNamaProses(String namaProses) {
        this.namaProses = namaProses;
    }

    public int getWaktuProses() {
        return waktuProses;
    }

    public void setWaktuProses(int waktuProses) {
        this.waktuProses = waktuProses;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
