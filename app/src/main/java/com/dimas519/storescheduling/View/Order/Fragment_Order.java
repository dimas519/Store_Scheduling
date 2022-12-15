package com.dimas519.storescheduling.View.Order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Pelanggan;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.databinding.FragmentOrderBinding;

import java.util.ArrayList;


public class Fragment_Order extends Fragment implements View.OnClickListener {
    private FragmentOrderBinding binding;
    private Pelanggan selectedPelanggan;
    private ArrayList<Produk> produkArrayList;


    public Fragment_Order(Pelanggan selectedPelanggan, ArrayList<Produk> produkArrayList) {
        this.selectedPelanggan=selectedPelanggan;
        this.produkArrayList=produkArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding= FragmentOrderBinding.inflate(inflater);

        ArrayList<String> namaProduk=new ArrayList<>();

        for(Produk produk: this.produkArrayList){
            namaProduk.add(produk.getNama());
        }

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,namaProduk);


        this.binding.dropDown.setAdapter(adapter);

        this.binding.orderBtn.setOnClickListener(this);


        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if(view==this.binding.orderBtn){
            long selectedItem=this.binding.dropDown.getSelectedItemId();
            Produk orderedProduk=this.produkArrayList.get((int) selectedItem);

            String jsonOrdered= MainActivity.gson.toJson(orderedProduk);
            String orderPerson=MainActivity.gson.toJson(this.selectedPelanggan);


            Bundle bundle=new Bundle();
            bundle.putString("personOrder",orderPerson);
            bundle.putString("orderProduk",jsonOrdered);

            getParentFragmentManager().setFragmentResult("addOrder",bundle);
        }



    }
}