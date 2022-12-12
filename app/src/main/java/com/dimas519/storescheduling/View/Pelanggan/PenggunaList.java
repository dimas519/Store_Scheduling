package com.dimas519.storescheduling.View.Pelanggan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimas519.storescheduling.Model.Pelanggan;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.databinding.FragmentListPageBinding;

import java.util.ArrayList;
import java.util.Objects;


public class PenggunaList extends Fragment implements View.OnClickListener,iPelanggan {
    private FragmentListPageBinding binding;

    private  ArrayList<Pelanggan> pelangganArrayList;
    private   PelangganAdapter adapter;



    public PenggunaList( ArrayList<Pelanggan> pelangganArrayList) {
        this.pelangganArrayList=pelangganArrayList;
    }

    public void addItem(Pelanggan newPelanggan){
        this.pelangganArrayList.add(newPelanggan);
        this.adapter.notifyItemChanged(this.pelangganArrayList.size());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentListPageBinding.inflate(inflater);
        //setAdapter
        this.binding.lvTampilan.setLayoutManager(new LinearLayoutManager(getContext()));


        this.adapter=new PelangganAdapter(this.pelangganArrayList,this);
        this.binding.lvTampilan.setAdapter(adapter);


        this.binding.createBtn.setOnClickListener(this);
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        DialogFragment dialogFragment=new Pelanggan_Modal();
        dialogFragment.show(getParentFragmentManager(),"dialog");
    }


    @Override
    public void openDetail(String pelanggan) {
        Bundle bundle=new Bundle();
        bundle.putString("detail",pelanggan);
        getParentFragmentManager().setFragmentResult("openPelangganDetail",bundle);
    }
}