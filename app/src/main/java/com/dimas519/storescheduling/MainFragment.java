package com.dimas519.storescheduling;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.FileUtils;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimas519.storescheduling.Code.Pages;
import com.dimas519.storescheduling.Code.Permission;
import com.dimas519.storescheduling.databinding.FragmentLoginPageBinding;
import com.dimas519.storescheduling.databinding.FragmentMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

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
                        Log.d("konci", "onActivityResult: "+data.getData());
                        File x=null;
                        try {
                            x = File.createTempFile("temporary_file", ".txt");
                            x.deleteOnExit();


                            OutputStream outputStream = new FileOutputStream(x);
                            InputStream is = requireActivity().getContentResolver().openInputStream(data.getData());
                            IOUtils.copy(is, outputStream);


                        }catch (IOException e) {
                                e.printStackTrace();
                        }






                        String filePath=x.getAbsolutePath();


                        Bundle bundle=new Bundle();
                        bundle.putString("filePath",filePath);

                        getParentFragmentManager().setFragmentResult("show result", bundle);
                    }

                }
            });







}