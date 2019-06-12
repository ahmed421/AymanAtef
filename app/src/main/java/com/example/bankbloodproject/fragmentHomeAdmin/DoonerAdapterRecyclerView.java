package com.example.bankbloodproject.fragmentHomeAdmin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankbloodproject.R;
import com.example.bankbloodproject.model.DonnerModel;

import java.util.List;

public class DoonerAdapterRecyclerView extends RecyclerView.Adapter<DoonerAdapterRecyclerView.DoonerAdapterRecyclerViewHolder> {
    private List<DonnerModel> doonerModelList;
    private Context context;

    public DoonerAdapterRecyclerView(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public DoonerAdapterRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DoonerAdapterRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_dooner,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoonerAdapterRecyclerViewHolder doonerAdapterRecyclerViewHolder, int i) {

        final DonnerModel donnerModel=doonerModelList.get(i);
//        Log.i("onBind",donnerModel.getName());

        doonerAdapterRecyclerViewHolder.txtname.setText(donnerModel.getName());
        doonerAdapterRecyclerViewHolder.txtAge.setText(donnerModel.getAge());
        doonerAdapterRecyclerViewHolder.txtBloodGroup.setText(donnerModel.getGender());
        doonerAdapterRecyclerViewHolder.txtGender.setText(donnerModel.getPhone_number());
        doonerAdapterRecyclerViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + donnerModel.getPhone_number()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return doonerModelList!=null?doonerModelList.size():0;
    }

    public void setDataSource(List<DonnerModel> doonerModelList) {
        this.doonerModelList=doonerModelList;
    }

    class DoonerAdapterRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView txtname,txtAge,txtBloodGroup,txtGender;
        ImageView imageView;
public DoonerAdapterRecyclerViewHolder(View view){
    super(view);
        txtname=itemView.findViewById(R.id.tvNameDooner);
        txtAge=itemView.findViewById(R.id.tvAgeDooner);
        txtBloodGroup=itemView.findViewById(R.id.tvBloodGroup);
    txtGender=itemView.findViewById(R.id.tvcity);
    imageView=itemView.findViewById(R.id.imvcall);



}

    }
}
