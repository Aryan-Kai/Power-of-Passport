package com.testing.retrievedata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllDataActivity extends AppCompatActivity {

    ArrayList<CountryVisaData> countrydetailslist;
    MyAdapterAllData myAdapter2;
    FirebaseFirestore db;
    Button btnvisafree,btnvisaonarrival,btn2visarequired;
    String countryName2;
    TextView cname;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data);
        //progressDialog = new ProgressDialog(this);
        //progressDialog.setCancelable(false);
        //progressDialog.setMessage("Fetching Data....");
        //progressDialog.show();
        countryName2 = getIntent().getStringExtra("Country");
        btnvisafree = findViewById(R.id.btnvisafree);
        cname = findViewById(R.id.txtcname);
        cname.setText(countryName2);
        btnvisaonarrival = findViewById(R.id.btnvisaonarrival);
        btn2visarequired = findViewById(R.id.btn2visarequired);
        //recyclerView2 = findViewById(R.id.recylerview2);
        //recyclerView2.setHasFixedSize(true);
        //recyclerView2.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView2.setAdapter(myAdapter2);

        btnvisafree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllDataActivity.this,visafree.class);
                intent.putExtra("Country",countryName2);
                startActivity(intent);
            }
        });
        btnvisaonarrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllDataActivity.this,visaonarrival.class);
                intent.putExtra("Country",countryName2);
                startActivity(intent);
            }
        });
        btn2visarequired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AllDataActivity.this,visarequired.class);
                if(countryName2.isEmpty()) {
                    Toast.makeText(AllDataActivity.this,"Not null",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    intent2.putExtra("Country", countryName2);
                    startActivity(intent2);
                }
            }
        });
        db = FirebaseFirestore.getInstance();
        countrydetailslist = new ArrayList<CountryVisaData>();
       // myAdapter2 = new MyAdapterAllData(AllDataActivity.this, countrydetailslist);
        //EventChangeListener();
    //    EventChangeListener2();
    }

/*    private void EventChangeListener() {
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

    }*/


}