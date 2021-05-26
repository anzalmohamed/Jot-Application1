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

public class LoginActivity extends AppCompatActivity {

    private EditText mloginemail,mloginpassword;
    private Button mloginButton, msignupButton;
    private RelativeLayout mlogin, msignup;
    private TextView mforgotpassword;

  private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        getSupportActionBar().hide();


        firebaseAuth=firebaseAuth.getInstance();
        FirebaseUser firebaseuser= firebaseAuth.getCurrentUser();

        if (firebaseuser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,CreatenotesActivity.class));
        }


        mloginemail=findViewById(R.id.loginemail);
        mforgotpassword=findViewById(R.id.loginpassword);
        mloginButton=findViewById(R.id.loginButton);
        mforgotpassword=findViewById(R.id.forgotpassword);
        msignupButton=findViewById(R.id.signupButton);



        msignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

        mforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgottenpasswordActivity.class));
            }
        });

        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String mail=mloginemail.getText().toString().trim();
                String password =mloginemail.getText().toString().trim();
                if(mail.isEmpty()|| password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fill in the stated fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    //login user

                    firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                checkmailverification();
                            }
                           else{  Toast.makeText(LoginActivity.this, "Account does not exist", Toast.LENGTH_SHORT).show();}

                        }
                    });

                }
            }
        });

    }

    private void checkmailverification() {

        FirebaseUser firebaseuser= firebaseAuth.getCurrentUser();
        if(firebaseuser.isEmailVerified()==true)
        {
            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
       finish();
       startActivity(new Intent(LoginActivity.this,CreatenotesActivity.class));
        }
else{
            Toast.makeText(LoginActivity.this, "Verify your Email", Toast.LENGTH_SHORT).show();
       firebaseAuth.signOut();
        }
    }
}