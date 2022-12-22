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
    private FragmentPesananBinding binding;
    private ArrayList<Pesanan> pesananArrayList;
    private PesananAdapter adapter;

    public FragmentPesanan(ArrayList<Pesanan> pesananArrayList) {
        this.pesananArrayList=pesananArrayList;
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentPesananBinding.inflate(inflater);
        this.binding.pesananNumber.setText(""+this.pesananArrayList.size());



        this.binding.lvPemesanan.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter=new PesananAdapter(this.pesananArrayList);
        this.binding.lvPemesanan.setAdapter(this.adapter);



        this.binding.orderProcessButton.setOnClickListener(this);
        this.binding.btnDelete.setOnClickListener(this);
        this.binding.tambahPesananBtn.setOnClickListener(this);

        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.orderProcessButton) {
            String json = MainActivity.gson.toJson(this.pesananArrayList);

            Bundle bundle = new Bundle();
            bundle.putString("data", json);
            getParentFragmentManager().setFragmentResult("ProcessData", bundle);
        }else if(view==this.binding.btnDelete){
            getParentFragmentManager().setFragmentResult("deleteOrder", null);
            this.pesananArrayList=new ArrayList<>();
            this.adapter.setPesananArrayList(this.pesananArrayList);
            this.adapter.notifyDataSetChanged();

            this.binding.pesananNumber.setText(""+this.pesananArrayList.size());

        }else if(view==this.binding.tambahPesananBtn){
            getParentFragmentManager().setFragmentResult("moreOrder", null);
        }
    }
}