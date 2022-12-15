package com.dimas519.storescheduling.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dimas519.storescheduling.databinding.FragmentSettingBinding;


public class FragmentSetting extends Fragment implements View.OnClickListener {
    private FragmentSettingBinding binding;

    public FragmentSetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentSettingBinding.inflate(inflater);

        this.binding.saveBtn.setOnClickListener(this);
        // Inflate the layout for this fragment
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        int numOfMachine=Integer.parseInt(this.binding.confMachine.getText().toString());

        Bundle bundle=new Bundle();
        bundle.putInt("machineNumber",numOfMachine);

        getParentFragmentManager().setFragmentResult("saveConfiguration",bundle);


    }
}