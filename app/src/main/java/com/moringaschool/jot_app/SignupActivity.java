package com.moringaschool.jot_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

   private EditText msignuppassword, msignupemail;
   private Button msignupButton;
   private TextView mgotologin;
   RelativeLayout msignup;

private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);




        getSupportActionBar().hide();

        firebaseAuth= FirebaseAuth.getInstance();

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

     firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {


             if(task.isSuccessful())

             {

                 Toast.makeText(getApplicationContext(), "Sign up Successful", Toast.LENGTH_SHORT).show();

                 sendEmailVerification();
             }
             else{
                 Toast.makeText(getApplicationContext(), "Registration was unsuccessful", Toast.LENGTH_SHORT).show();
             }
         }
     });


 }

             }
         });

    }
    private void sendEmailVerification() {

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)

        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Verification Email has been sent! verify to login", Toast.LENGTH_SHORT).show();
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                }
            });

        }
else{
            Toast.makeText(getApplicationContext(), "Verification Failed!", Toast.LENGTH_SHORT).show();
        }
    }

}