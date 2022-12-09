package com.dimas519.storescheduling;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimas519.storescheduling.databinding.FragmentListPageBinding;


public class PageOfList extends Fragment implements View.OnClickListener {
    private FragmentListPageBinding binding;
    public static  String Title;




    public PageOfList(String title) {
        Title=title;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentListPageBinding.inflate(inflater);
        //setAdapter



        this.binding.createBtn.setOnClickListener(this);
        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        DialogFragment dialogFragment=new Pelanggan_Modal();
        dialogFragment.show(getParentFragmentManager(),"dialog");




    }


}