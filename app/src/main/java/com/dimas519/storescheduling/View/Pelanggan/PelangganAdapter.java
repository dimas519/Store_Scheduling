package com.dimas519.storescheduling.View.Pelanggan;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Pelanggan;
import com.dimas519.storescheduling.R;


import java.util.ArrayList;

public class PelangganAdapter  extends RecyclerView.Adapter<PelangganAdapter.ViewHolder>{
    private ArrayList<Pelanggan> Arrpelanggan;
    private iPelanggan iPelanggan;

    public PelangganAdapter(ArrayList<Pelanggan> arrpelanggan, iPelanggan iPelanggan){
        this.Arrpelanggan=arrpelanggan;
        this.iPelanggan=iPelanggan;
    }







    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pelanggan_item,parent,false);

        return new ViewHolder(view,iPelanggan);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(this.Arrpelanggan.get(position));
    }



    @Override
    public int getItemCount() {
        return this.Arrpelanggan.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvNama;
        private final TextView tvKontak;
        private final TextView tvAlamat;
        private final TextView tvEmail;
        private final TextView tvTelp;

        private  Pelanggan currPelanggan;
        private iPelanggan iPelanggan;


        public ViewHolder(View view,iPelanggan iPelanggan) {
            super(view);
            this.tvNama=view.findViewById(R.id.namalv);
            this.tvKontak=view.findViewById(R.id.kontaklv);
            this.tvAlamat=view.findViewById(R.id.alamatlv);

            this.tvEmail=view.findViewById(R.id.emaillv);
            this.tvTelp=view.findViewById(R.id.telplv);

            LinearLayout layout=view.findViewById(R.id.layout);
            layout.setOnClickListener(this);

            this.iPelanggan=iPelanggan;
        }

        @SuppressLint("SetTextI18n")
        public void setData(Pelanggan currPelanggan) {
            this.currPelanggan=currPelanggan;
            this.tvNama.setText(currPelanggan.getNama());
            this.tvKontak.setText(currPelanggan.getKontak());
            this.tvAlamat.setText(currPelanggan.getAlamat());

            this.tvEmail.setText(currPelanggan.getEmail());
            this.tvTelp.setText(currPelanggan.getTelepon());
        }

        @Override
        public void onClick(View view) {
            String json= MainActivity.gson.toJson(this.currPelanggan);

            this.iPelanggan.openDetail(json);
        }
    }





}
