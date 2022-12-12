package com.dimas519.storescheduling.Model;

public class Pelanggan {
    private long id;
    private String nama;
    private String kontak;
    private String alamat;
    private String email;
    private String telepon;

    public Pelanggan(long id, String nama, String kontak, String alamat, String email, String telepon) {
        this.id = id;
        this.nama = nama;
        this.kontak = kontak;
        this.alamat = alamat;
        this.email = email;
        this.telepon = telepon;
    }

    public Pelanggan(String nama, String kontak, String alamat, String email, String telepon) {
        this.nama = nama;
        this.kontak = kontak;
        this.alamat = alamat;
        this.email = email;
        this.telepon = telepon;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
