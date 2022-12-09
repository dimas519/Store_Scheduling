package com.dimas519.storescheduling;

import android.os.Bundle;


import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimas519.storescheduling.databinding.FragmentPelangganModalBinding;


public class Pelanggan_Modal extends DialogFragment implements View.OnClickListener {
    private FragmentPelangganModalBinding binding;


    public Pelanggan_Modal() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentPelangganModalBinding.inflate(inflater);





        this.binding.saveBtn.setOnClickListener(this);

        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {


        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }
}