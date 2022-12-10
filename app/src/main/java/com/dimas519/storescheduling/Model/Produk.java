package com.dimas519.storescheduling.Model;

public class Produk {
    private int id;
    private String kode;
    private String nama;
    private int waktu;

    public Produk(int id, String kode, String nama, int waktu) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.waktu = waktu;
    }

    public Produk(String kode, String nama, String waktu) {
        this.kode = kode;
        this.nama = nama;
        this.waktu =Integer.parseInt( waktu);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }
}
