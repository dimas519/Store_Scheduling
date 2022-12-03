package com.dimas519.storescheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dimas519.storescheduling.Code.Algorithm;
import com.dimas519.storescheduling.Code.Pages;
import com.dimas519.storescheduling.Code.Permission;
import com.dimas519.storescheduling.Model.Jobs;
import com.dimas519.storescheduling.Presenter.LoginPresenter;
import com.dimas519.storescheduling.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements
        FragmentResultListener, View.OnClickListener {
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Permission permission;

    private ActivityMainBinding binding;
    private Fragment[] fragments;

    private Gson gson;


    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding=ActivityMainBinding.inflate(getLayoutInflater());
        this.fm=getSupportFragmentManager();
        this.gson=new Gson();

        //check permission
        this.permission=new Permission();
        this.permission.checkPermission(this,1);

        //
        this.fragments=new Fragment[Pages.numOfPages];


        this.fragments[Pages.Login_Page]= LoginPage.newInstance(new LoginPresenter());
        this.changePage(Pages.Login_Page);
        getSupportActionBar().hide();

        this.actionBarDrawerToggle=new ActionBarDrawerToggle(this,this.binding.layout,R.string.endHeader,R.string.createBtn);
        this.binding.layout.addDrawerListener(   this.actionBarDrawerToggle);

        this.actionBarDrawerToggle.setToolbarNavigationClickListener(this);


        //mengatur action bar dan sandwich
        this.actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.ft=this.fm.beginTransaction();

        this.fm.setFragmentResultListener("show result", this,this);
        this.fm.setFragmentResultListener("show details", this,this);
        this.fm.setFragmentResultListener("login", this,this);



        setContentView(this.binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!permission.isAllEnabled(getApplicationContext())) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }
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

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if(requestKey.equals("show result")){
            fragments[Pages.resultPage]=Fssp_Fragment.newInstance(result.getString("filePath"));
            changePage(Pages.resultPage);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Algorithm Result");
        }else if(requestKey.equals("show details")){
            String json=result.getString("result");
            Jobs jobs=gson.fromJson(json,Jobs.class);
            fragments[Pages.detailPages]=new ResultFragment(jobs);
            changePage(Pages.detailPages);
            Objects.requireNonNull(getSupportActionBar()).setTitle(Algorithm.getAlgorithm(result.getInt("algorithm"))+" Result");
        }else if(requestKey.equals("login")){
            this.fragments[Pages.Main_Page]=new MainFragment();
            this.changePage(Pages.Main_Page);
            getSupportActionBar().show();




        }
    }

    private void changePage(int page){
        this.ft=this.fm.beginTransaction();
        this.hideOtherFragments(page);
        if(this.fragments[page].isAdded()) {
            this.ft.show(this.fragments[page]);
        }else {
            this.ft.add(this.binding.FragmentFrame.getId(), this.fragments[page], null);
        }
        this.ft.commit();
    }

    private void hideOtherFragments(int pageToShow){
        for (int i=0;i<this.fragments.length;i++){
            if(i!=pageToShow && this.fragments[i]!=null){
                this.ft.hide(fragments[i]);
            }
        }
    }


    @Override
    public void onClick(View view) {
//        if (this.binding.layout.isDrawerOpen(GravityCompat.START)){
//            this.binding.layout.closeDrawer(GravityCompat.START);
//        }else{
//            this.binding.layout.openDrawer((int) Gravity.START);
//        }

    }
}