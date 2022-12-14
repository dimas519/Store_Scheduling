package com.dimas519.storescheduling.View.Produk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.databinding.FragmentListPageBinding;

import java.util.ArrayList;


public class ProdukList extends Fragment implements View.OnClickListener {

    private  ArrayList<Produk> ProdukArrayList;
    private  ProdukAdapter adapter;



    public ProdukList(ArrayList<Produk> pelangganArrayList) {
        this.ProdukArrayList=pelangganArrayList;
    }


    public void addItem(Produk newProduk){
        this.ProdukArrayList.add(newProduk);
        this.adapter.notifyItemChanged(this.ProdukArrayList.size());
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentListPageBinding binding = FragmentListPageBinding.inflate(inflater);
        //setAdapter
        binding.lvTampilan.setLayoutManager(new LinearLayoutManager(getContext()));




        this.adapter=new ProdukAdapter(this.ProdukArrayList);
        binding.lvTampilan.setAdapter(adapter);


        binding.createBtn.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        DialogFragment dialogFragment=new Produk_Modal();
        dialogFragment.show(getParentFragmentManager(),"dialog");
    }


}