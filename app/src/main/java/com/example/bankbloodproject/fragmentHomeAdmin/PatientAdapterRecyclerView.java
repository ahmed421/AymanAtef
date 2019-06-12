package com.example.bankbloodproject.fragmentHomeAdmin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bankbloodproject.R;
import com.example.bankbloodproject.model.DonnerModel;
import com.example.bankbloodproject.model.patientModel;

import java.util.List;

public class PatientAdapterRecyclerView extends RecyclerView.Adapter<PatientAdapterRecyclerView.PatientAdapterRecyclerViewHolder> {
    private List<patientModel> patientModelList ;
    private Context context;

    public PatientAdapterRecyclerView(Context context) {
this.context=context;
    }

    @NonNull
    @Override
    public PatientAdapterRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PatientAdapterRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_dooner,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapterRecyclerViewHolder patientAdapterRecyclerViewHolder, int i) {
        patientModel patientModel=patientModelList.get(i);
//        Log.i("onBind",patientModel.getName());
        patientAdapterRecyclerViewHolder.txtname.setText(patientModel.getName());
        patientAdapterRecyclerViewHolder.txtAge.setText(patientModel.getAge());
        patientAdapterRecyclerViewHolder.txtBloodGroup.setText(patientModel.getGender());
        patientAdapterRecyclerViewHolder.txtcity.setText(patientModel.getPhone_number());

    }

    @Override
    public int getItemCount() {
        return patientModelList!=null?patientModelList.size():0;
    }

    public void setDataSource(List<patientModel> patientModelList) {
        this.patientModelList=patientModelList;
    }

    class PatientAdapterRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView txtname,txtAge,txtBloodGroup,txtcity;

        public PatientAdapterRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.tvNameDooner);
            txtAge=itemView.findViewById(R.id.tvAgeDooner);
            txtBloodGroup=itemView.findViewById(R.id.tvBloodGroup);
            txtcity=itemView.findViewById(R.id.tvcity);

        }
    }
}
