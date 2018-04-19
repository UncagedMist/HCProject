package com.techbytecare.kk.healthcareproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.techbytecare.kk.healthcareproject.Common.Common;
import com.techbytecare.kk.healthcareproject.Model.DataPatient;
import com.techbytecare.kk.healthcareproject.ViewHolder.ViewHolder;

import info.hoang8f.widget.FButton;

public class GetPatient extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FButton btnData;

    FirebaseRecyclerAdapter<DataPatient,ViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference docData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_patient);

        recyclerView = findViewById(R.id.recyclerGetPatient);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btnData = findViewById(R.id.btnData);

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GetPatient.this, "Loading...", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
    }

    private void loadData() {

        Query search = docData.orderByChild("phone").equalTo(Common.currentPatient.getPhone());

        FirebaseRecyclerOptions<DataPatient> dataOptions = new FirebaseRecyclerOptions.Builder<DataPatient>()
                .setQuery(search,DataPatient.class).build();

        adapter = new FirebaseRecyclerAdapter<DataPatient, ViewHolder>(dataOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull DataPatient model) {
                if (Common.currentPatient != null)  {
                    holder.txtField.setText(model.getField());
                    holder.txtValue.setText(model.getValue());
                    holder.txtDate.setText(model.getDateTime());
                }
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.get_data,parent,false);

                return new ViewHolder(itemView);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)    {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null)    {
            adapter.stopListening();
        }
    }
}
