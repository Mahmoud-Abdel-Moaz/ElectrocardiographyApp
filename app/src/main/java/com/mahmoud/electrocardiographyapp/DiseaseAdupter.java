package com.mahmoud.electrocardiographyapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class DiseaseAdupter extends RecyclerView.Adapter<DiseaseAdupter.DiseaseViewHolder> {

      private List<Disease> itemsList = new ArrayList<>();

    private Context context;

    public DiseaseAdupter(List<Disease> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    public DiseaseAdupter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiseaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, final int position) {

        final Disease disease=itemsList.get(position);
        holder.txt_name.setText(disease.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context.getApplicationContext(),DiseaseDetailsActivity.class);
                intent.putExtra("name",disease.getName());
                intent.putExtra("info",disease.getInformation());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public void setitemsList(List<Disease> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    public class DiseaseViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.txt_name);
        }
    }
}
