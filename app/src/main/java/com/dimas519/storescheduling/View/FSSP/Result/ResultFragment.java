package com.dimas519.storescheduling.View.FSSP.Result;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimas519.storescheduling.Model.Jobs;
import com.dimas519.storescheduling.Model.Machine;
import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.databinding.FragmentResultBinding;

import java.util.ArrayList;


public class ResultFragment extends Fragment implements View.OnClickListener {
    private FragmentResultBinding binding;
    private ResultFragmentAdapter[] adapter;


    private final Jobs jobs;
    private ArrayList<Pesanan> pesananArrayList;
    private int idx=1;

    public ResultFragment(Jobs x, ArrayList<Pesanan> pesananArrayList) {
        this.jobs=x;
        this.pesananArrayList=pesananArrayList;
    }




    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentResultBinding.inflate(inflater);
        this.binding.numOfPages.setText(jobs.getNumberOfJob()+"");
        this.binding.pages.setText(this.idx+"");


        this.adapter=new ResultFragmentAdapter[jobs.getNumberOfJob()];
        this.binding.namaPelanggan.setText(pesananArrayList.get(idx-1).getPelanggan().getNama());
        initAdapter(0);


        this.binding.lvResult.setLayoutManager(new LinearLayoutManager(getContext()));
        this.binding.lvResult.setAdapter(this.adapter[0]);


        this.binding.btnBack.setOnClickListener(this);
        this.binding.btnNext.setOnClickListener(this);

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if(view==this.binding.btnNext){
            this.idx+=1;
            if(idx==this.jobs.getNumberOfJob()){
                this.binding.btnNext.setVisibility(View.GONE);
            }
            this.binding.btnBack.setVisibility(View.VISIBLE);




        }else if(view==this.binding.btnBack){
            this.idx-=1;
            if(idx==1){
                this.binding.btnBack.setVisibility(View.GONE);
            }
            this.binding.btnNext.setVisibility(View.VISIBLE);
        }

        this.initAdapter(idx-1);
        this.binding.lvResult.setAdapter(this.adapter[idx-1]);

        this.binding.namaPelanggan.setText(pesananArrayList.get(idx-1).getPelanggan().getNama());



        this.binding.pages.setText(this.idx+"");
    }

    private Machine[] getMachine(int idx){
        return this.jobs.getJKerjaan()[idx].getMachine();
    }


    private void initAdapter(int idx){
        if(this.adapter[idx]==null) {
            this.adapter[idx] = new ResultFragmentAdapter(getMachine(idx), pesananArrayList.get(idx));
        }
    }

}