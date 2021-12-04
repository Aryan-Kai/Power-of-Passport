package com.testing.retrievedata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Country> countryArrayList;
    ArrayList<Country> countryArrayListnew;
    ItemClickListener itemClickListener;
    public MyAdapter(Context context, ArrayList<Country> countryArrayList,ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.countryArrayList = countryArrayList;
//        this.countryArrayList = new ArrayList<>(countryArrayListnew);
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    public void filterList(ArrayList<Country> filteList){
        countryArrayList = filteList;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Country country = countryArrayList.get(position);
        holder.name.setText(country.name);
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.OnItemClick(countryArrayList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return countryArrayList.size();
    }

/*    private final Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Country> filteredcountrylist = new ArrayList<>();
            if (charSequence==null || charSequence.length()==0)
            {
                filteredcountrylist.addAll(countryArrayListnew);
            }
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Country country: countryArrayListnew){
                    if(country.name.toLowerCase().contains(filterPattern))
                        filteredcountrylist.add(country);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values= filteredcountrylist;
            filterResults.count = filteredcountrylist.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            countryArrayList.clear();
            countryArrayList.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };


    @Override
    public Filter getFilter() {
        return countryFilter;
    }*/

    public interface ItemClickListener{
        void OnItemClick(Country country);
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView name2;
        TextView visa;
        TextView visa_free;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtname);
            name2 = itemView.findViewById(R.id.txtname2);
            visa = itemView.findViewById(R.id.txtvisa);
            visa_free = itemView.findViewById(R.id.txtvisafree);
        }
    }
}

