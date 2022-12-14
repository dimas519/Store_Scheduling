package com.dimas519.storescheduling.View.Produk.Process;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.databinding.ProcessItemBinding;

import java.util.ArrayList;

public class ProcessAdapter extends BaseAdapter {
    private ArrayList<Process> processArrayList;
    private LayoutInflater inflater;
    public ProcessAdapter( ArrayList<Process> processArrayList, LayoutInflater inflater){
        this.processArrayList=processArrayList;
        this.inflater=inflater;
    }


    @Override
    public int getCount() {
        return processArrayList.size();
    }

    @Override
    public Process getItem(int i) {
        return processArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProcessItemBinding binding=ProcessItemBinding.inflate(this.inflater);

        Process curr=getItem(i);
        binding.nama.setText(curr.getNamaProses());
        binding.waktu.setText(curr.getWaktuProses()+"");



        return binding.getRoot();
    }
}
