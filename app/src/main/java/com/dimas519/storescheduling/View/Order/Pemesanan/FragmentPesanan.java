package com.dimas519.storescheduling.View.Order.Pemesanan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.databinding.FragmentPesananBinding;

import java.util.ArrayList;

public class FragmentPesanan extends Fragment implements View.OnClickListener {
    private final ArrayList<Pesanan> pesananArrayList;

    public FragmentPesanan(ArrayList<Pesanan> pesananArrayList) {
        this.pesananArrayList=pesananArrayList;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPesananBinding binding = FragmentPesananBinding.inflate(inflater);
        binding.pesananNumber.setText(""+this.pesananArrayList.size());



        binding.lvPemesanan.setLayoutManager(new LinearLayoutManager(getContext()));
        PesananAdapter adapter=new PesananAdapter(this.pesananArrayList);
        binding.lvPemesanan.setAdapter(adapter);



        binding.orderProcessButton.setOnClickListener(this);


        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        String json= MainActivity.gson.toJson(this.pesananArrayList);

        Bundle bundle=new Bundle();
        bundle.putString("data",json);
        getParentFragmentManager().setFragmentResult("ProcessData",bundle);
    }
}