package com.testing.retrievedata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class visaonarrivaladpater extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<CountryVisaData> countryVisaDataArrayList;

    public visaonarrivaladpater(Context context,ArrayList<CountryVisaData> countryVisaDataArrayList) {
        this.context = context;
        this.countryVisaDataArrayList = countryVisaDataArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.visaonarrivalitem,parent,false);
        return new MyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        CountryVisaData countryVisaData = countryVisaDataArrayList.get(position);
        holder.visa_on_arrival.setAdapter(new SimpleAdapter(context,countryVisaData.getVisaOnArrival(),R.layout.visaonarrivallines,new String[]{"name","paid"},new int[]{R.id.txtdisplayname,R.id.txtdisplay2name})); }

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
