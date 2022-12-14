package com.dimas519.storescheduling.View.FSSP;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.dimas519.storescheduling.Code.Algorithm;
import com.dimas519.storescheduling.Model.Jobs;
import com.dimas519.storescheduling.Presenter.FsspAlgorithm;
import com.dimas519.storescheduling.databinding.FsspItemBinding;
import com.google.gson.Gson;



public class FsspAdapter extends BaseAdapter implements iFSSPAdapter {
    private FsspAlgorithm[] fsspAlgorithms;
    private final Fssp_Fragment ff;

    //tester
    private Gson gson;

    public FsspAdapter(Fssp_Fragment ff){
        this.ff=ff;
    }

    public void setArray(FsspAlgorithm[] fsspAlgorithms){
        this.fsspAlgorithms=fsspAlgorithms;
    }



    @Override
    public int getCount() {
        return this.fsspAlgorithms.length ;
    }

    @Override
    public FsspAlgorithm getItem(int i) {
        return this.fsspAlgorithms[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FsspItemBinding binding=FsspItemBinding.inflate(this.ff.getLayoutInflater());

        if(gson==null) {
            gson = new Gson();
        }


        binding.algorithm.setText(Algorithm.getAlgorithm(getItem(i).getAlgorithm()));
        Jobs x=getItem(i).getJobs();
        if(x!=null){
            binding.resultSpan.setText(getItem(i).getJobs().getMakeSpan()+"");



            binding.itemsAlgo.setOnClickListener(view1 -> {

                Bundle bundle=new Bundle();
                String test= gson.toJson(x);
                bundle.putString("result",test);
                bundle.putInt("algorithm",getItem(i).getAlgorithm());


               ff.getParentFragmentManager().setFragmentResult("show details", bundle);

            });



        }


        return binding.getRoot();
    }



    @Override
    public void notifyChanges() {
        this.ff.requireActivity().runOnUiThread(this::notifyDataSetChanged);

    }
}
