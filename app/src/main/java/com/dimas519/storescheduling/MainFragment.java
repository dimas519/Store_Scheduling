package com.dimas519.storescheduling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimas519.storescheduling.Code.Permission;
import com.dimas519.storescheduling.databinding.FragmentLoginPageBinding;
import com.dimas519.storescheduling.databinding.FragmentMainBinding;


public class MainFragment extends Fragment implements View.OnClickListener {
    private FragmentMainBinding binding;


    public MainFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentMainBinding.inflate(inflater);


        this.binding.inputBtn.setOnClickListener(this);




        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.inputBtn){

                this.intentFile();

        }
    }

    private void intentFile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        someActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Here, no request code
                        Intent data = result.getData();

                        Reader reader=new Reader();
                        reader.readFile("ttt");

                        System.out.println("bebe"+data);


                    }
                }
            });

}