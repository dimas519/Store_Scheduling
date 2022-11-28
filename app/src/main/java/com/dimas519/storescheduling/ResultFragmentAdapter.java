package com.dimas519.storescheduling;

import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dimas519.storescheduling.Model.Machine;


public class ResultFragmentAdapter extends RecyclerView.Adapter<ResultFragmentAdapter.ViewHolder>{
    private final Machine[] machine;



    public ResultFragmentAdapter(Machine[] machine){
        this.machine=machine;
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
        System.out.println("testdd"+position);
        holder.setData(this.machine[position]);
    }

    @Override
    public int getItemCount() {
        return this.machine.length;
    }





    public static class ViewHolder extends RecyclerView.ViewHolder {

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
        public void setData(Machine currMachine) {
            this.machineNumber.setText(currMachine.getId()+"");
            this.startTime.setText(currMachine.getStart()+"");
            this.endTime.setText(currMachine.getEnd()+"");

        }
    }








}
