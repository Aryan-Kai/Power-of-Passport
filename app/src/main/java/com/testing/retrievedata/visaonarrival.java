package com.testing.retrievedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class visaonarrival extends AppCompatActivity {
    RecyclerView recyclerView2;
    ArrayList<CountryVisaData> countrydetailslist;
    visaonarrivaladpater myAdapter2;
    FirebaseFirestore db;
    String countryName;
    TextView view1,view2;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visaonarrival);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();
        progressDialog.dismiss();
        view1 = findViewById(R.id.txtvoacountryname);
        view2 = findViewById(R.id.txtvoastatus);
        countryName = getIntent().getStringExtra("Country");
        recyclerView2 = findViewById(R.id.recylerview2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        countrydetailslist = new ArrayList<CountryVisaData>();
        myAdapter2 = new visaonarrivaladpater(visaonarrival.this, countrydetailslist);
        recyclerView2.setAdapter(myAdapter2);
        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("visa_data").whereEqualTo("name",countryName)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null)
                        {
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc:value.getDocumentChanges()){
                            if(dc.getType()== DocumentChange.Type.ADDED){
                                countrydetailslist.add(parseData(dc.getDocument()));
                            }
                            myAdapter2.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }
    private CountryVisaData parseData(QueryDocumentSnapshot documentSnapshot) {
        String name = (String)documentSnapshot.getData().get("name");
        String code = (String)documentSnapshot.getData().get("code");
        List<HashMap<String,Object>> visaFree = (List<HashMap<String,Object>>) documentSnapshot.getData().get("visa_free");
        List<HashMap<String,Object>> visaOnArrival = (List<HashMap<String,Object>>) documentSnapshot.getData().get("visa_on_arrival");
        List<HashMap<String,Object>> visaRequired = (List<HashMap<String,Object>>) documentSnapshot.getData().get("visa_required");
        return new CountryVisaData(name,
                code,
                visaFree,
                visaOnArrival,
                visaRequired
        );
    }
    private List<VisaStatus> convertHashMapToVisaStatus(List<HashMap<String,Object>> list){
        List<VisaStatus> data = new ArrayList<VisaStatus>();

        for(HashMap<String, Object> entry : list){
            data.add(new VisaStatus((String)entry.get("name"), (boolean)entry.get("paid")));
        }

        return data;

    }
}