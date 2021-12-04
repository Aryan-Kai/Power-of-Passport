package com.testing.retrievedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllDataActivity extends AppCompatActivity {

    RecyclerView recyclerView2;
    ArrayList<CountryVisaData> countrydetailslist;
    MyAdapterAllData myAdapter2;
    FirebaseFirestore db;
    String docid;
    TextView cname;
    String countryName;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();
        cname = findViewById(R.id.txtcname);
        cname.setText(docid);
        countryName = getIntent().getStringExtra("Country");
        recyclerView2 = findViewById(R.id.recylerview2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        countrydetailslist = new ArrayList<CountryVisaData>();
        myAdapter2 = new MyAdapterAllData(AllDataActivity.this, countrydetailslist);
        recyclerView2.setAdapter(myAdapter2);

        EventChangeListener();
    //    EventChangeListener2();
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
                                countrydetailslist.add(dc.getDocument().toObject(CountryVisaData.class));
                           }
                            myAdapter2.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }
}