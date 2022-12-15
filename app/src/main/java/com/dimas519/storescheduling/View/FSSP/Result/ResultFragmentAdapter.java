package com.dimas519.storescheduling.View.FSSP.Result;

import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dimas519.storescheduling.Model.Machine;
import com.dimas519.storescheduling.Model.Pesanan;
import com.dimas519.storescheduling.Model.Process;
import com.dimas519.storescheduling.Model.Produk;
import com.dimas519.storescheduling.R;


public class ResultFragmentAdapter extends RecyclerView.Adapter<ResultFragmentAdapter.ViewHolder>{
    private final Machine[] machine;
    private Produk Produk;



    public ResultFragmentAdapter(Machine[] machine,Pesanan pesanan){
        this.machine=machine;
        this.Produk=pesanan.getProduk();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.result_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(this.machine[position],this.Produk.getProcess().get(position));
    }

    @Override
    public int getItemCount() {
        return this.machine.length;
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView machineNumber;
        private final TextView startTime;
        private final TextView endTime;

        public ViewHolder(View view) {
            super(view);
            this.machineNumber=view.findViewById(R.id.machineNumber);
            this.startTime=view.findViewById(R.id.startTime);
            this.endTime=view.findViewById(R.id.endTime);
        }

        @SuppressLint("SetTextI18n")
        public void setData(Machine currMachine, Process process) {
            this.machineNumber.setText(process.getNamaProses());
            this.startTime.setText(currMachine.getStart()+"");
            this.endTime.setText(currMachine.getEnd()+"");
        }
    }








}
