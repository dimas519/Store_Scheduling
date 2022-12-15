package com.dimas519.storescheduling.Model;

import java.util.ArrayList;

public class Produk {
    private long id;
    private String kode;
    private String nama;
    private ArrayList<Process> process;

    public Produk(String kode, String nama ) {
        this.kode = kode;
        this.nama = nama;
    }

    public Produk(long id, String kode, String nama) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;

    }

    public Produk(long id, String kode, String nama, ArrayList<Process> process) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.process = process;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKode() {
        return this.kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public ArrayList<Process> getProcess() {
        return this.process;
    }

    public void setProcess(ArrayList<Process> process) {
        this.process = process;
    }
}
