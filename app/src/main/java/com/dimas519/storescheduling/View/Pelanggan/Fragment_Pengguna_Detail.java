package com.dimas519.storescheduling.View.Pelanggan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dimas519.storescheduling.Model.Pelanggan;

import com.dimas519.storescheduling.R;
import com.dimas519.storescheduling.databinding.FragmentPenggunaDetailBinding;


public class Fragment_Pengguna_Detail extends Fragment implements View.OnClickListener {
    private FragmentPenggunaDetailBinding binding;
    Pelanggan currPelanggan;


    public Fragment_Pengguna_Detail(Pelanggan currPelanggan) {
        this.currPelanggan=currPelanggan;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentPenggunaDetailBinding.inflate(inflater);

        this.binding.nama.setText(this.currPelanggan.getNama());
        this.binding.kontak.setText(this.currPelanggan.getKontak());
        this.binding.alamat.setText(this.currPelanggan.getAlamat());
        this.binding.email.setText(this.currPelanggan.getEmail());
        this.binding.telp.setText(this.currPelanggan.getTelepon());

        this.binding.orderBtn.setOnClickListener(this);
        this.binding.btnEdit.setOnClickListener(this);
        this.binding.btnDelete.setOnClickListener(this);


        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.btnEdit){
            EditText [] allForm={this.binding.nama,this.binding.kontak,this.binding.alamat
            ,this.binding.email,this.binding.telp};

            if(this.binding.btnEdit.getText().toString().equals(getResources().getString(R.string.btnEdit))) {
                for (EditText curr : allForm) {
                    curr.setFocusable(true);
                    curr.setFocusableInTouchMode(true);
                    curr.setCursorVisible(true);
                }

                this.binding.nama.setInputType(InputType.TYPE_CLASS_TEXT);
                this.binding.kontak.setInputType(InputType.TYPE_CLASS_PHONE);
                this.binding.alamat.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                this.binding.alamat.setLines(5);

                this.binding.email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                this.binding.telp.setInputType(InputType.TYPE_CLASS_PHONE);
                this.binding.btnEdit.setText(getResources().getString(R.string.btnSave));

            }else{
                for (EditText curr : allForm) {
                    curr.setFocusable(false);
                    curr.setFocusableInTouchMode(false);
                    curr.setCursorVisible(false);
                    this.binding.nama.setInputType(InputType.TYPE_NULL);
                }

                this.binding.alamat.setLines(5);
                this.binding.btnEdit.setText(getResources().getString(R.string.btnEdit));

            }

        }
    }




}