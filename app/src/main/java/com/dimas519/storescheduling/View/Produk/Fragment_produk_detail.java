package com.dimas519.storescheduling.View.Produk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.R;
import com.dimas519.storescheduling.View.Pelanggan.PelangganAdapter;
import com.dimas519.storescheduling.View.Produk.Process.ProcessAdapter;
import com.dimas519.storescheduling.databinding.FragmentProdukDetailBinding;

public class Fragment_produk_detail extends Fragment {
    private Produk produk;

    public Fragment_produk_detail(Produk produk) {
        this.produk=produk;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProdukDetailBinding binding=FragmentProdukDetailBinding.inflate(inflater);

        binding.kode.setText(this.produk.getKode());
        binding.nama.setText(this.produk.getNama());

        int total=0;
        for(Process process: this.produk.getProcess()){
            total+=process.getWaktuProses();
        }

        System.out.println("test"+total);


       // binding.lvProduk.setLayoutManager(new LinearLayoutManager(getContext()));


        ProcessAdapter adapter=new ProcessAdapter(produk.getProcess(),inflater);
        binding.lvProduk.setAdapter(adapter);

        binding.waktuProses.setText(""+total);




        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}