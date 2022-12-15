package com.dimas519.storescheduling.View.Produk;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Pelanggan;
import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.R;

import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder>{
    private ArrayList<Produk> ArrProduk;
    private iProduk iProduk;

    public ProdukAdapter(ArrayList<Produk> ArrProduk, iProduk iProduk){
        this.ArrProduk=ArrProduk;
        this.iProduk=iProduk;
    }







    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produk_item,parent,false);

        return new ViewHolder(view,iProduk);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(this.ArrProduk.get(position));
    }



    @Override
    public int getItemCount() {
        return this.ArrProduk.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvkode;
        private final TextView tvNama;
        private final TextView tvWaktu;


        private Produk produk;
        private iProduk iProduk;


        public ViewHolder(View view,iProduk iProduk) {
            super(view);
            this.tvkode=view.findViewById(R.id.kode);
            this.tvNama=view.findViewById(R.id.nama);
            this.tvWaktu=view.findViewById(R.id.waktu);

            LinearLayout layout=view.findViewById(R.id.layout);
            layout.setOnClickListener(this);

            this.iProduk=iProduk;
        }

        @SuppressLint("SetTextI18n")
        public void setData(Produk curProduk) {
            this.produk=curProduk;

            this.tvNama.setText(curProduk.getNama());
            this.tvkode.setText(curProduk.getKode());
            int total=0;
            for(Process process: curProduk.getProcess()){
                total+= process.getWaktuProses();
            }
            this.tvWaktu.setText(total+"");

        }

        @Override
        public void onClick(View view) {
            String json= MainActivity.gson.toJson(this.produk);
            iProduk.openDetail(json);
        }
    }





}
