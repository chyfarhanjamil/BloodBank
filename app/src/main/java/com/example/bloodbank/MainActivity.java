package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bloodbank.ambulance.AmbulanceActivity;
import com.example.bloodbank.society.VoluntarySocietyActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    private CardView feedCardView, makeRequestCardView, voluntarySocietyCardView, ambulanceCardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        feedCardView = findViewById(R.id.feedCardViewId);
        makeRequestCardView = findViewById(R.id.makeRequestCardViewId);
        voluntarySocietyCardView = findViewById(R.id.voluntarySocieyCardViewId);
        ambulanceCardView = findViewById(R.id.ambulanceCardViewId);

        feedCardView.setOnClickListener(this);
        makeRequestCardView.setOnClickListener(this);
        voluntarySocietyCardView.setOnClickListener(this);
        ambulanceCardView.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.signOutMenuID){
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.feedCardViewId){
            Intent intent = new Intent(MainActivity.this, FeedActivity.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.makeRequestCardViewId){
            Intent intent = new Intent(MainActivity.this, MakeRequestActivity.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.voluntarySocieyCardViewId){
            Intent intent = new Intent(MainActivity.this, VoluntarySocietyActivity.class);
            startActivity(intent);
        }
        else if(view.getId()==R.id.ambulanceCardViewId){
            Intent intent = new Intent(MainActivity.this, AmbulanceActivity.class);
            startActivity(intent);
        }
    }
}