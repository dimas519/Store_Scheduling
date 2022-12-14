package com.dimas519.storescheduling.View.Produk.Process;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.databinding.FragmentProcessPopupBinding;


public class Process_popup extends DialogFragment implements View.OnClickListener {
    private FragmentProcessPopupBinding binding;

    public Process_popup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentProcessPopupBinding.inflate(inflater);

        this.binding.saveBtn.setOnClickListener(this);
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {

        Bundle bundle=new Bundle();
        bundle.putString("nama",this.binding.nama.getText().toString());
        bundle.putInt("waktu",Integer.parseInt(this.binding.waktu.getText().toString()));


        getParentFragmentManager().setFragmentResult("addProcess",bundle);
        dismiss();

    }
}