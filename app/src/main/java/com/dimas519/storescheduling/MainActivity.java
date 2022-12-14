package com.dimas519.storescheduling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
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
import android.view.MenuItem;
import android.widget.Toast;

import com.dimas519.storescheduling.Code.Algorithm;
import com.dimas519.storescheduling.Code.Pages;
import com.dimas519.storescheduling.Code.Permission;
import com.dimas519.storescheduling.Code.PopUp;
import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.Storage.AppConfiguration;
import com.dimas519.storescheduling.Storage.Database.database;
import com.dimas519.storescheduling.Model.Jobs;
import com.dimas519.storescheduling.Model.Pelanggan;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.View.FSSP.Fssp_Fragment;
import com.dimas519.storescheduling.View.FSSP.Result.ResultFragment;
import com.dimas519.storescheduling.View.FragmentAbout;
import com.dimas519.storescheduling.View.FragmentSetting;
import com.dimas519.storescheduling.View.MainFragment;
import com.dimas519.storescheduling.View.Order.Fragment_Order;
import com.dimas519.storescheduling.View.Order.OrderLogic;
import com.dimas519.storescheduling.View.Order.Pemesanan.FragmentPesanan;
import com.dimas519.storescheduling.View.Pelanggan.Fragment_Pengguna_Detail;
import com.dimas519.storescheduling.View.Pelanggan.PenggunaList;
import com.dimas519.storescheduling.View.Produk.Fragment_produk_detail;
import com.dimas519.storescheduling.View.Produk.ProdukList;
import com.dimas519.storescheduling.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements
        FragmentResultListener, NavigationView.OnNavigationItemSelectedListener {


    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Permission permission;

    private ActivityMainBinding binding;
    private Fragment[] fragments;


    public static Gson gson;

    private AppConfiguration configuration;
    private database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding=ActivityMainBinding.inflate(getLayoutInflater());



        //check permission
        this.permission=new Permission();
        this.permission.checkPermission(this,1);

        //init-init
        this.fm=getSupportFragmentManager();
        this.gson=new Gson();
        this.db=new database(getApplicationContext());
        this.configuration=new AppConfiguration(getApplicationContext());

        //
        this.fragments=new Fragment[Pages.numOfPages];


//        this.fragments[Pages.Login_Page]= LoginPage.newInstance(new LoginPresenter());
//        this.changePage(Pages.Login_Page);
//        getSupportActionBar().hide();

        //temporary
        //-------------------------------------------------------------------
        this.fragments[Pages.Main_Page]=new MainFragment();
        this.changePage(Pages.Main_Page);
        getSupportActionBar().show();
        //-------------------------------------------------------------------

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.actionBarDrawerToggle=new ActionBarDrawerToggle(this,this.binding.layout,R.string.endHeader,R.string.createBtn);



        this.binding.layout.addDrawerListener(   this.actionBarDrawerToggle);

        this.binding.navView.setNavigationItemSelectedListener(this);

        //mengatur action bar dan sandwich
        this.actionBarDrawerToggle.syncState();



        this.ft=this.fm.beginTransaction();

        this.fm.setFragmentResultListener("show result", this,this);
        this.fm.setFragmentResultListener("show details", this,this);
        this.fm.setFragmentResultListener("login", this,this);
        this.fm.setFragmentResultListener("savePelanggan",this,this);
        this.fm.setFragmentResultListener("saveProduk",this,this);

        this.fm.setFragmentResultListener("openPelangganDetail",this,this);
        this.fm.setFragmentResultListener("openProcessDetail",this,this);
        this.fm.setFragmentResultListener("saveConfiguration",this,this);
        this.fm.setFragmentResultListener("OrderSomething",this,this);

        this.fm.setFragmentResultListener("addOrder",this,this);
        this.fm.setFragmentResultListener("ProcessData",this,this);
        this.fm.setFragmentResultListener("openProdukDetail",this,this);
        this.fm.setFragmentResultListener("deleteOrder",this,this);
        this.fm.setFragmentResultListener("moreOrder",this,this);


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
            fragments[Pages.resultPage]= Fssp_Fragment.newInstance(result.getString("filePath"));
            changePage(Pages.resultPage);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Hasil Perhitungan");
        }else if(requestKey.equals("show details")){
            String json=result.getString("result");
            Jobs jobs=gson.fromJson(json,Jobs.class);


            //not good
            ArrayList<Pesanan> pesananArrayList=this.db.getAllPesanan();

            fragments[Pages.detailPages]=new ResultFragment(jobs,pesananArrayList);
            changePage(Pages.detailPages);
            Objects.requireNonNull(getSupportActionBar()).setTitle(Algorithm.getAlgorithm(result.getInt("algorithm"))+" Result");
        }else if(requestKey.equals("login")) {
            this.fragments[Pages.Main_Page] = new MainFragment();
            this.changePage(Pages.Main_Page);
            getSupportActionBar().show();
        }else if(requestKey.equals("savePelanggan")){
            String strPelanggan=result.getString("data");
            Pelanggan pelanggan=gson.fromJson(strPelanggan,Pelanggan.class);
            long id=this.db.insertPelanggan(pelanggan);
            pelanggan.setId(id);
            ((PenggunaList) this.fragments[Pages.pagesListPengguna]).addItem(pelanggan);
            this.changePage(Pages.pagesListPengguna);
        }else if(requestKey.equals("saveProduk")){
            Produk newProduk=this.db.insertProduk(result);
            ((ProdukList) this.fragments[Pages.pagesListProduk]).addItem(newProduk);
            this.changePage(Pages.pagesListProduk);
        }else if(requestKey.equals("openPelangganDetail")){
            String strPelanggan=result.getString("detail");
            Pelanggan curr=gson.fromJson(strPelanggan,Pelanggan.class);
            this.fragments[Pages.pagesDetailPengguna]=new Fragment_Pengguna_Detail(curr);
            this.changePage(Pages.pagesDetailPengguna);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Pelanggan");
        }else if(requestKey.equals("saveConfiguration")){
            int numOfMachine=result.getInt("machineNumber");
            this.configuration.saveMachineNumber(numOfMachine);
        }else if(requestKey.equals("OrderSomething")){
            String strPelanggan=result.getString("data");
            Pelanggan curr=gson.fromJson(strPelanggan,Pelanggan.class);
            this.fragments[Pages.pagesPemesanan]=new Fragment_Order(curr,this.db.getProduk());
            this.changePage(Pages.pagesPemesanan);
        }else if(requestKey.equals("addOrder")){
            String strJson=result.getString("personOrder");
            Pelanggan pelanggan=gson.fromJson(strJson,Pelanggan.class);

            strJson=result.getString("orderProduk");
            Produk produk=gson.fromJson(strJson,Produk.class);

            int quantity=result.getInt("quantity");

            this.db.insertOrder(pelanggan,produk,quantity);
            Toast.makeText(getApplicationContext(), "Order Berhasil", Toast.LENGTH_SHORT).show();

            this.changePage(Pages.pagesPemesanan);

            this.fragments[Pages.pagesListPemesanan]=null;

        }else if(requestKey.equals("ProcessData")){
            String filePath=OrderLogic.createTxt(this.db.getAllPesanan());
            this.fragments[Pages.resultPage]= Fssp_Fragment.newInstance(filePath);
            changePage(Pages.resultPage);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Algorithm Result");


        }else if(requestKey.equals("openProdukDetail")){
            String json=result.getString("detail");
            Produk currProduk=gson.fromJson(json,Produk.class);

            this.fragments[Pages.pagesDetailProduk]=new Fragment_produk_detail(currProduk);
            this.changePage(Pages.pagesDetailProduk);
        }else if(requestKey.equals("deleteOrder")){
            this.db.deleteAllPesanan();
        }else if(requestKey.equals("moreOrder")){
            if(this.fragments[Pages.pagesListPengguna]==null ){
                this.fragments[Pages.pagesListPengguna]=new PenggunaList(this.db.getPelanggan());
            }
            this.changePage(Pages.pagesListPengguna);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Pilih Pelanggan");
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int clickItem=item.getItemId();
        if(clickItem==R.id.nav_main){
            this.changePage(Pages.Main_Page);
            Objects.requireNonNull(getSupportActionBar()).setTitle("Penjadwalan Produksi");
        }else if(clickItem==R.id.navPelanggan){
            if(this.fragments[Pages.pagesListPengguna]==null ){
                this.fragments[Pages.pagesListPengguna]=new PenggunaList(this.db.getPelanggan());
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle(PopUp.getAlgorithm(PopUp.pelangganPopUp));
            this.changePage(Pages.pagesListPengguna );
        }else if(clickItem==R.id.navProduk) {
            if(this.fragments[Pages.pagesListProduk]==null){
                this.fragments[Pages.pagesListProduk]=new ProdukList(this.db.getProduk());
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle(PopUp.getAlgorithm(PopUp.produkpopUp));
            this.changePage(Pages.pagesListProduk);
        } else if (clickItem==R.id.navOrder){
            if(this.fragments[Pages.pagesListPemesanan]==null){
                this.fragments[Pages.pagesListPemesanan]=new FragmentPesanan(this.db.getAllPesanan());
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle(PopUp.getAlgorithm(PopUp.order));
            this.changePage(Pages.pagesListPemesanan);



            this.db.getAllPesanan();
        }else if(clickItem==R.id.navSetting){
            if(this.fragments[Pages.pagesPengaturan]==null){
                this.fragments[Pages.pagesPengaturan]=new FragmentSetting();
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle(PopUp.getAlgorithm(PopUp.setting));
            this.changePage(Pages.pagesPengaturan);
        }else if(clickItem==R.id.nav_about){
            if(this.fragments[Pages.about]==null){
                this.fragments[Pages.about]=new FragmentAbout();
            }
            Objects.requireNonNull(getSupportActionBar()).setTitle(PopUp.getAlgorithm(PopUp.about));
            this.changePage(Pages.about);
        }


        this.binding.layout.closeDrawers();

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}