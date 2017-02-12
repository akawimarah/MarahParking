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

/**
 * tjhez mo2shrat fe el activity
 */

public class LogInActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogIn;
    private Button btnNewAccount;
    private FirebaseAuth auth;




    @Override
    /**
     * t7ded el keyam bwasitat findViewById
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogIn=(Button) findViewById(R.id.btnLogIn);
       btnNewAccount=(Button) findViewById(R.id.btnNewAccount);
        auth= FirebaseAuth.getInstance();

        eventHandler();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent=new Intent(LogInActivity.this,MapActivity.class);
            startActivity(intent);
            finish();//btinhi el activity ele ana mawjod fyo
        }





    }
    private void dataHandler()
    /**
     * aist5raj fa7wa l7okol wmo3aljit el mo3tayat(fa7s kanonyet elmod5lat)
     */
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

    /**
     *  ll2zrar min ajil  mo3aljit el 7adath
     */
    private void eventHandler(){

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(LogInActivity.this,MapActivity.class);
                startActivity(i);
                dataHandler();


            }
        });
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LogInActivity.this,signInActivity.class);
                startActivity(i);


            }
        });

    }

    /**
     * dalit bti3mal do5ol ll app bttlaka email wsisma
     * aiza kan el do5ol naji7 btib3at toast"signInActivity Successful"
     * aiza fishil el do5olbtib3at toast "signInActivity failed"
     * @param email
     * @param passw
     */
    private void signIn(String email, String passw) {

        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())

                {

                    Toast.makeText(LogInActivity.this, "signInActivity Successful.", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(LogInActivity.this,MapActivity.class);

                    startActivity(i);

                    finish();

                }

                else

                {

                    Toast.makeText(LogInActivity.this, "signInActivity failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    task.getException().printStackTrace();

                }

            }

        });

    }




}
