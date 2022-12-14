package com.dimas519.storescheduling.View.Produk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimas519.storescheduling.MainActivity;
import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.View.Produk.Process.ProcessAdapter;
import com.dimas519.storescheduling.View.Produk.Process.Process_popup;
import com.dimas519.storescheduling.databinding.FragmentProdukModalBinding;

import java.util.ArrayList;


public class Produk_Modal extends DialogFragment implements View.OnClickListener, FragmentResultListener {
    private FragmentProdukModalBinding binding;
    private ArrayList<Process> processArrayList;

    private ProcessAdapter adapter;

    public Produk_Modal() {
        this.processArrayList = new ArrayList<>();

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentProdukModalBinding.inflate(inflater);
        this.adapter=new ProcessAdapter(this.processArrayList,inflater);


        this.binding.saveBtn.setOnClickListener(this);
        this.binding.addProsess.setOnClickListener(this);

        this.binding.lvProduk.setAdapter(adapter);

        getParentFragmentManager().setFragmentResultListener("addProcess", this, this);


        return this.binding.getRoot();
    }

    @Override
    public void onClick(View view) {

        if (view == this.binding.saveBtn) {
            Produk baru = new Produk(
                    this.binding.kode.getText().toString(),
                    this.binding.nama.getText().toString()
            );

            String result = MainActivity.gson.toJson(baru);
            Bundle bundle = new Bundle();
            bundle.putString("data", result);
            bundle.putInt("numOfProcess",this.processArrayList.size());

            int i=0;
            for( Process process : this.processArrayList){
                result=MainActivity.gson.toJson(process);
                bundle.putString("process"+i,result);
                i++;
            }

            getParentFragmentManager().setFragmentResult("saveProduk", bundle);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();





            dismiss();





        } else if (view == this.binding.addProsess) {
            DialogFragment dialogFragment = new Process_popup();
            dialogFragment.show(getParentFragmentManager(), "process");
        }
    }

    @Override
    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
        if (requestKey.equals("addProcess")) {
            String nama=result.getString("nama");
            int waktu=result.getInt("waktu");


            Process newProcess=new Process(nama,waktu);
            newProcess.setOrder(this.processArrayList.size()); //kalau yg sebelumnya didelete mungkin error
            this.processArrayList.add(newProcess);
            this.adapter.notifyDataSetChanged();
        }
    }

}