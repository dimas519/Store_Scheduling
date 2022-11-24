package com.dimas519.storescheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.dimas519.storescheduling.Code.Permission;
import com.dimas519.storescheduling.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Permission permission;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding=ActivityMainBinding.inflate(getLayoutInflater());

        //check permission
        this.permission=new Permission();
        this.permission.checkPermission(this,1);




        this.fm=getSupportFragmentManager();

        MainFragment mf=new MainFragment();

        this.ft=this.fm.beginTransaction();
        this.ft.add(this.binding.FragmentFrame.getId(), mf, null);
        this.ft.commit();




        setContentView(this.binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", getPackageName(), null);
//        intent.setData(uri);
//        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int i=0;i<permissions.length;i++){
            if(grantResults[i]== PackageManager.PERMISSION_DENIED){
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R) { //mulai dari andro 11 cuma bisa 2x nolak
                    if (permission.deniedTwice(permissions[i])) {
                        this.finishAffinity();
                        Toast.makeText(this, "Please Manual Enable All Permission", Toast.LENGTH_SHORT).show();
                    }
                }
                    Toast.makeText(this, "Please Enable All Permission", Toast.LENGTH_SHORT).show();
                    permission.checkPermission(this, requestCode + 1);
            }
        }
    }
}