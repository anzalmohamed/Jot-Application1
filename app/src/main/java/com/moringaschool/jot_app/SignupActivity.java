package com.moringaschool.jot_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

   private EditText msignuppassword, msignupemail;
   private Button msignupButton;
   private TextView mgotologin;
   RelativeLayout msignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        msignupemail=findViewById(R.id.Signupemail);
        msignuppassword=findViewById(R.id.signuppassword);
        msignupButton=findViewById(R.id.signupButton);
        mgotologin=findViewById(R.id.gotologin);

        mgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }

        });
         msignupButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String mail=msignupemail.getText().toString().trim();
                 String password=msignuppassword.getText().toString().trim();
 if(mail.isEmpty() || password.isEmpty()){
     Toast.makeText(getApplicationContext(),"Fill In AllThe Fields", Toast.LENGTH_SHORT).show();
 }
else if(password.length()<7)
 {
     Toast.makeText(getApplicationContext(),"Password is weak", Toast.LENGTH_SHORT).show();

 }
else{

    //register user
 }

             }
         });

    }
}