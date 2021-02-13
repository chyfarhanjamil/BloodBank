package com.example.bloodbank.ambulance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.bloodbank.R;
import com.example.bloodbank.society.CustomAdapterSociety;
import com.example.bloodbank.society.VoluntarySociety;
import com.example.bloodbank.society.VoluntarySocietyActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AmbulanceActivity extends AppCompatActivity {

    private Button addAmbulanceButton;
    private ListView listView;
    private List<Ambulance> ambulanceList;
    private CustomAdapterAmbulance customAdapterAmbulance;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        this.setTitle("Ambulance Information");

        listView = findViewById((R.id.listViewId));

        databaseReference = FirebaseDatabase.getInstance().getReference("ambulance");
        ambulanceList = new ArrayList<>();
        customAdapterAmbulance = new CustomAdapterAmbulance(AmbulanceActivity.this, ambulanceList);

        addAmbulanceButton = findViewById(R.id.addAmbulanceButtonId);
        addAmbulanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.addAmbulanceButtonId){
                    Intent intent = new Intent(AmbulanceActivity.this, AmbulanceStoreActivity.class);
                    startActivity(intent);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ambulance ambulance = (Ambulance) (listView.getItemAtPosition(i));
                String phone = ambulance.getAmbulancePhone();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:+88"+phone));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ambulanceList.clear();
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    Ambulance ambulance = dataSnapshot1.getValue(Ambulance.class);
                    ambulanceList.add(ambulance);
                }
                listView.setAdapter(customAdapterAmbulance);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_layout, menu);

        MenuItem menuItem = menu.findItem(R.id.searchViewId);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Ambulance> filteredAmbulance = new ArrayList<>();
                for(Ambulance ambulance: ambulanceList){
                    if(ambulance.getAmbulanceArea().toLowerCase().contains(s.toLowerCase())){
                        filteredAmbulance.add(ambulance);
                    }
                    if(ambulance.getAmbulanceName().toLowerCase().contains(s.toLowerCase())){
                        filteredAmbulance.add(ambulance);
                    }
                }
                CustomAdapterAmbulance customAdapterAmbulance = new CustomAdapterAmbulance(AmbulanceActivity.this, filteredAmbulance);
                listView.setAdapter(customAdapterAmbulance);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}