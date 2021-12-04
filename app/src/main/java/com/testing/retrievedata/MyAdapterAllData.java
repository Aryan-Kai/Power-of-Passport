package com.testing.retrievedata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapterAllData extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<CountryVisaData> countryVisaDataArrayList;

    public MyAdapterAllData(Context context,ArrayList<CountryVisaData> countryVisaDataArrayList) {
        this.context = context;
        this.countryVisaDataArrayList = countryVisaDataArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
        return new MyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        CountryVisaData countryVisaData = countryVisaDataArrayList.get(position);
        holder.name2.setText(countryVisaData.name);
        holder.visa.setText(countryVisaData.code);
    }

    @Override
    public int getItemCount() {
        return countryVisaDataArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //TextView visa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          //  visa = itemView.findViewById(R.id.txtvisa);
        }
    }
}
