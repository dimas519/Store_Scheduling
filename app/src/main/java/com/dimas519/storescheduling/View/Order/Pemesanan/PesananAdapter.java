package com.dimas519.storescheduling.View.Order.Pemesanan;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.R;


import java.util.ArrayList;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.ViewHolder>{
    private ArrayList<Pesanan> pesananArrayList;


    public PesananAdapter(ArrayList<Pesanan> pesananArrayList){
        this.pesananArrayList=pesananArrayList;

    }

    public void setPesananArrayList(ArrayList<Pesanan> pesananArrayList){
        this.pesananArrayList=pesananArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pesanan_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(this.pesananArrayList.get(position));
    }



    @Override
    public int getItemCount() {
        return this.pesananArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {

        private final TextView tvNama;
        private final TextView tvNamaBarang;
        private final TextView tvJumlah;

        public ViewHolder(View view) {
            super(view);
            this.tvNama=view.findViewById(R.id.nama);
            this.tvNamaBarang=view.findViewById(R.id.barang);
            this.tvJumlah=view.findViewById(R.id.jumlah);



        }

        @SuppressLint("SetTextI18n")
        public void setData(Pesanan curPesanan) {

            this.tvNama.setText(curPesanan.getPelanggan().getNama());
            this.tvNamaBarang.setText(curPesanan.getProduk().getNama());
            this.tvJumlah.setText(curPesanan.getQuantity()+"");

        }

    }





}
