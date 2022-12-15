package com.dimas519.storescheduling.View.FSSP;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimas519.storescheduling.Code.Algorithm;
import com.dimas519.storescheduling.Presenter.FsspAlgorithm;
import com.dimas519.storescheduling.databinding.FragmentFsspBinding;


public class Fssp_Fragment extends Fragment {
    private static final String ARG_FILEPATH = "filePath";
    private String filePath;



    public static Fssp_Fragment newInstance(String param1) {
        Fssp_Fragment fragment = new Fssp_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_FILEPATH, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filePath = getArguments().getString(ARG_FILEPATH);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.dimas519.storescheduling.databinding.FragmentFsspBinding binding = FragmentFsspBinding.inflate(inflater);

        FsspAdapter adapter=new FsspAdapter(this);

        FsspAlgorithm[] fsspAlgorithms={
                new FsspAlgorithm(Algorithm.FCFS,this.filePath,adapter)
//                ,new FsspAlgorithm(Algorithm.CDS,this.filePath,adapter)
                ,new FsspAlgorithm(Algorithm.NEH,this.filePath,adapter)
//                ,new FsspAlgorithm(Algorithm.PALMER,this.filePath,adapter)
//                ,new FsspAlgorithm(Algorithm.GUPTA,this.filePath,adapter)
//                ,new FsspAlgorithm(Algorithm.DANNENBRING,this.filePath,adapter)
//                ,new FsspAlgorithm(Algorithm.POUR,this.filePath,adapter)
//                ,new FsspAlgorithm(Algorithm.MOD,this.filePath,adapter)
        };

        adapter.setArray(fsspAlgorithms);
        binding.lvResult.setAdapter(adapter);





        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}