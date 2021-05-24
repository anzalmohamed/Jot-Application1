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

public class LoginActivity extends AppCompatActivity {

    private EditText mloginemail,mloginpassword;
    private Button mloginButton, msignupButton;
    private RelativeLayout mlogin, msignup;
    private TextView mforgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        getSupportActionBar().hide();

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
                }
            }
        });

    }
}