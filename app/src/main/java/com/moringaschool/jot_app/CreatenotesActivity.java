package com.moringaschool.jot_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class CreatenotesActivity extends AppCompatActivity {

    FloatingActionButton mcreaatenotesFab;
    private FirebaseAuth  fireaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnotes);

        mcreaatenotesFab=findViewById(R.id.createnotesFab);

        fireaseAuth=fireaseAuth.getInstance();

        getSupportActionBar().setTitle("All Notes");

        mcreaatenotesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreatenotesActivity.this,NotesActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
         case R.id.logout:

             fireaseAuth.signOut();
             finish();
        startActivity(new Intent(CreatenotesActivity.this,LoginActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }
}