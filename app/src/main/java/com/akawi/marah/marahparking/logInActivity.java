package com.akawi.marah.marahparking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class logInActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogIn;
    private Button btnNewAccount;
    private Button btnHelp;
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogIn=(Button) findViewById(R.id.btnLogIn);
       btnNewAccount=(Button) findViewById(R.id.btnNewAccount);
        btnHelp=(Button)findViewById(R.id.btnHelp);
        auth= FirebaseAuth.getInstance();

        eventHandler();





    }
    private void dataHandler()
    {
        String stEmail=etEmail.getText().toString();
        String stPassowrd=etPassword.getText().toString();
        boolean isok=true;
        if(stEmail.length()==0){
            etEmail.setError("wrong Email");
            isok=false;
        }
        if(stPassowrd.length()==0){
            etPassword.setError("wrong password");
            isok=false;
        }
        if(isok)
            signIn(stEmail,stPassowrd);



    }
    private void eventHandler(){
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();


            }
        });
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(logInActivity.this,signInActivity.class);
                startActivity(i);


            }
        });

    }
    private void signIn(String email, String passw) {

        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(logInActivity.this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())

                {

                    Toast.makeText(logInActivity.this, "signInActivity Successful.", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(logInActivity.this,mapActivity.class);

                    startActivity(i);

                    finish();

                }

                else

                {

                    Toast.makeText(logInActivity.this, "signInActivity failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    task.getException().printStackTrace();

                }

            }

        });

    }




}
