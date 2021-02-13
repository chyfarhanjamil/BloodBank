package com.example.bloodbank;

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
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private ListView listView;
    private List<MakeRequest> makeRequestList;
    private CustomAdapterFeed customAdapterFeed;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        this.setTitle("Request Feed");

        listView = findViewById((R.id.listViewFeedId));
        databaseReference = FirebaseDatabase.getInstance().getReference("request");

        makeRequestList = new ArrayList<>();
        customAdapterFeed = new CustomAdapterFeed(FeedActivity.this, makeRequestList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MakeRequest makeRequest = (MakeRequest) (listView.getItemAtPosition(i));
                String phone = makeRequest.getRecipientPhone();
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
                makeRequestList.clear();
                for(DataSnapshot dataSnapshot1: snapshot.getChildren()){
                    MakeRequest makeRequest = dataSnapshot1.getValue(MakeRequest.class);
                    makeRequestList.add(makeRequest);
                }
                listView.setAdapter(customAdapterFeed);
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
               ArrayList<MakeRequest> filteredRequest = new ArrayList<>();
               for(MakeRequest makeRequest: makeRequestList){
                   if(makeRequest.getRecipientArea().toLowerCase().contains(s.toLowerCase())){
                       filteredRequest.add(makeRequest);
                   }
                   if(makeRequest.getRecipientBloodGrp().toLowerCase().contains(s.toLowerCase())){
                       filteredRequest.add(makeRequest);
                   }
               }
               CustomAdapterFeed customAdapterFeed = new CustomAdapterFeed(FeedActivity.this, filteredRequest);
               listView.setAdapter(customAdapterFeed);
               return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}