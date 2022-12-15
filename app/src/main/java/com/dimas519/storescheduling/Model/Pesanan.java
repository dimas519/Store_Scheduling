package com.dimas519.storescheduling.Model;

public class Pesanan {
    private int id;
    private Pelanggan pelanggan;
    private Produk produk;
    private int quantity;

    public Pesanan(int id, Pelanggan pelanggan, Produk produk, int quantity) {
        this.id = id;
        this.pelanggan = pelanggan;
        this.produk = produk;
        this.quantity = quantity;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
