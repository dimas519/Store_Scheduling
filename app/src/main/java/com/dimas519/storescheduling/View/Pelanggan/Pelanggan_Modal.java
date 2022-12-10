package com.dimas519.storescheduling.View.Pelanggan;

import android.os.Bundle;


import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Pelanggan;
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
        Pelanggan baru=new Pelanggan(
                this.binding.nama.getText().toString(),
                this.binding.kontak.getText().toString(),
                this.binding.alamat.getText().toString(),
                this.binding.email.getText().toString(),
                this.binding.telp.getText().toString()
                );

        String result= MainActivity.gson.toJson(baru);
        Bundle bundle=new Bundle();
        bundle.putString("data",result);

        getParentFragmentManager ().setFragmentResult("savePelanggan", bundle);

        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}