package com.dimas519.storescheduling;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dimas519.storescheduling.Code.Algorithm;
import com.dimas519.storescheduling.Model.Jobs;
import com.dimas519.storescheduling.Presenter.FsspAlgorithm;
import com.dimas519.storescheduling.databinding.FsspItemBinding;

public class FsspAdapter extends BaseAdapter implements iFSSPAdapter {
    private FsspAlgorithm[] fsspAlgorithms;
    private Fssp_Fragment ff;

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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FsspItemBinding binding=FsspItemBinding.inflate(ff.getLayoutInflater());
        binding.algorithm.setText(Algorithm.getAlgorithm(getItem(i).getAlgorithm()));
        Jobs x=getItem(i).getJobs();
        if(x!=null){
            System.out.println("berhasil "+x.getMakeSpan());
            binding.resultSpan.setText("Make Span: "+getItem(i).getJobs().getMakeSpan());
        }



        return binding.getRoot();
    }

    public void set(){

    }


    @Override
    public void notifyChanges() {
        this.ff.getActivity().runOnUiThread(()->{
            this.notifyDataSetChanged();
        });

    }
}
