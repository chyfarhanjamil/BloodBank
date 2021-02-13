package com.example.bloodbank.society;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.bloodbank.R;
import com.example.bloodbank.ambulance.Ambulance;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoluntarySocietyActivity extends AppCompatActivity {
    private ListView listView;
    private Button addSocietyButton;
    private List<VoluntarySociety> voluntarySocietyList;
    private CustomAdapterSociety customAdapterSociety;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntary_society);
        this.setTitle("Voluntary Society Information");

        listView = findViewById((R.id.listViewSocietyId));
        databaseReference = FirebaseDatabase.getInstance().getReference("society");
        voluntarySocietyList = new ArrayList<>();
        customAdapterSociety = new CustomAdapterSociety(VoluntarySocietyActivity.this, voluntarySocietyList);
        addSocietyButton = findViewById(R.id.addSocietyButtonId);
        addSocietyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.addSocietyButtonId){
                    Intent intent = new Intent(VoluntarySocietyActivity.this, VoluntarySocietyStoreActivity.class);
                    startActivity(intent);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                VoluntarySociety voluntarySociety = (VoluntarySociety) (listView.getItemAtPosition(i));
                String phone = voluntarySociety.getSocietyPhone();
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
                voluntarySocietyList.clear();
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    VoluntarySociety voluntarySociety = dataSnapshot1.getValue(VoluntarySociety.class);
                    voluntarySocietyList.add(voluntarySociety);
                }

                listView.setAdapter(customAdapterSociety);
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
                ArrayList<VoluntarySociety> filteredVoluntarySociety = new ArrayList<VoluntarySociety>();
                for(VoluntarySociety voluntarySociety: voluntarySocietyList){
                    if(voluntarySociety.getSocietyArea().toLowerCase().contains(s.toLowerCase())){
                        filteredVoluntarySociety.add(voluntarySociety);
                    }
                    if(voluntarySociety.getSocietyName().toLowerCase().contains(s.toLowerCase())){
                        filteredVoluntarySociety.add(voluntarySociety);
                    }

                }
                CustomAdapterSociety customAdapterSociety = new CustomAdapterSociety(VoluntarySocietyActivity.this,
                        filteredVoluntarySociety);
                listView.setAdapter(customAdapterSociety);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}