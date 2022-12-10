package com.dimas519.storescheduling.View.Produk;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimas519.storescheduling.Model.Pelanggan;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.R;

import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder>{
    private ArrayList<Produk> ArrProduk;

    public ProdukAdapter(ArrayList<Produk> arrpelanggan){
        this.ArrProduk=arrpelanggan;
    }







    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produk_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(this.ArrProduk.get(position));
    }



    @Override
    public int getItemCount() {
        return this.ArrProduk.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvkode;
        private final TextView tvNama;
        private final TextView tvWaktu;



        public ViewHolder(View view) {
            super(view);
            this.tvkode=view.findViewById(R.id.kode);
            this.tvNama=view.findViewById(R.id.nama);
            this.tvWaktu=view.findViewById(R.id.waktu);

        }

        @SuppressLint("SetTextI18n")
        public void setData(Produk curProduk) {
            this.tvNama.setText(curProduk.getNama());
            this.tvkode.setText(curProduk.getKode());
            this.tvWaktu.setText(curProduk.getWaktu()+"");
        }
    }





}
