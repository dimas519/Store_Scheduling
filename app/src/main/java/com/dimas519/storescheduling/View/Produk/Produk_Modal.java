package com.dimas519.storescheduling.View.Produk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.databinding.FragmentProdukModalBinding;


public class Produk_Modal extends DialogFragment implements View.OnClickListener {
    private FragmentProdukModalBinding binding;


    public Produk_Modal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentProdukModalBinding.inflate(inflater);

        this.binding.saveBtn.setOnClickListener(this);



        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Produk baru=new Produk(
                this.binding.kode.getText().toString(),
                this.binding.nama.getText().toString(),
                this.binding.waktu.getText().toString()
        );

        String result= MainActivity.gson.toJson(baru);
        Bundle bundle=new Bundle();
        bundle.putString("data",result);

        getParentFragmentManager ().setFragmentResult("saveProduk", bundle);

        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}