package com.moringaschool.jot_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgottenpasswordActivity extends AppCompatActivity {

    private EditText mForgottenpassword;
    private Button mrecoverbutton;
    private TextView mgobacktologin;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgottenpassword);

        getSupportActionBar().hide();

        firebaseAuth=firebaseAuth.getInstance();

        mForgottenpassword=findViewById(R.id.Forgottenpassword);
        mrecoverbutton=findViewById(R.id.recoverbutton);
        mgobacktologin=findViewById(R.id.backtologin);

        mgobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(ForgottenpasswordActivity.this, LoginActivity.class);
           startActivity(intent);

            }
        });
        mrecoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=mForgottenpassword.getText().toString().trim();

                if(mail.isEmpty())
                {
                    Toast.makeText(ForgottenpasswordActivity.this, "Kindl input your email", Toast.LENGTH_SHORT).show();
                }
                   else{
                       //send password to  email to recover

                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull  Task<Void> task) {

if(task.isSuccessful())

{
    Toast.makeText(getApplicationContext(),"mail sent, recover password now", Toast.LENGTH_SHORT).show();
    finish();
    startActivity(new Intent(ForgottenpasswordActivity.this, LoginActivity.class));
}
 else
     {
         Toast.makeText(getApplicationContext(),"Mail not sent, Account is nonexistent", Toast.LENGTH_SHORT).show();
     }


                        }
                    });


                }

            }
            
        });

    }
}