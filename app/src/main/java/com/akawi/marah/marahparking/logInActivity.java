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
import com.google.firebase.auth.FirebaseAuth;//ka2in mnst3mlo 3shan ni3mal signIn w signOut w signUp

/**
 * tjhez mo2shrat fe el activity
 */

public class LogInActivity extends AppCompatActivity {
    private EditText etEmail;//  email
    private EditText etPassword;//sisma
    private Button btnLogIn;
    private Button btnNewAccount;
    private FirebaseAuth auth;// ka2in mnst3mlo 3shan ni3mal signIn w signOut w signUp


    @Override
    /**
     * t7ded el keyam bwasitat findViewById
     * el oncreate he awal 3malye tonafaz tilka2iyan Called when the activity is first created)
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);//trbot ben el xml wil java
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnLogIn=(Button) findViewById(R.id.btnLogIn);
       btnNewAccount=(Button) findViewById(R.id.btnNewAccount);
        auth= FirebaseAuth.getInstance();//?
        eventHandler();// aistd3a2 el dali bil oncreate 3shan ni2dar nst3milha

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)//?
        {
            Intent intent=new Intent(LogInActivity.this,MapActivity.class);// ainti2al min lshashi login llshashi map
            startActivity(intent);//tsh3'el activity tani bwasitit el intent
            finish();//btinhi el activity ele ana mawjod fyo
        }
    }
    /**
     * aist5raj fa7wa l7okol wmo3aljit el mo3tayat(fa7s kanonyet elmod5lat)
     */
    private void dataHandler()

    {
        String stEmail=etEmail.getText().toString();// aist5raj fa7wa l7okol
        String stPassowrd=etPassword.getText().toString();//
        boolean isok=true;
        if(stEmail.length()==0){//fa7s kanonyet elmod5lat
            etEmail.setError("wrong Email");
            isok=false;
        }
        if(stPassowrd.length()==0){
            etPassword.setError("wrong password");
            isok=false;
        }
        if(isok)
            signIn(stEmail,stPassowrd);//aistd3a2 lldali signIn

    }

    /**
     *  dali ll2zrar min ajil  mo3aljit el 7adath
     *  onclick dalit rad el fi3il
     */
    private void eventHandler(){

        btnLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(LogInActivity.this,MapActivity.class);// bint2il min lshashi login llshashi map
                startActivity(i);//tsh3'el activity tani bwasitit el intent
                dataHandler();// aistd3a2 dalit dataHandler


            }
        });
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LogInActivity.this,signUpActivity.class);//bint2il min lshashi login llshashi signUp
                startActivity(i);//tsh3'el activity tani bwasitit el intent


            }
        });

    }

    /**
     * dali bti3mal do5ol ll app bttlaka email wsisma.
     * aiza kan el do5ol naji7 btib3at toast"signUpActivity Successful"
     * aiza fishil el do5ol btib3at toast "signUpActivity failed"
     * @param email
     * @param passw
     */
    private void signIn(String email, String passw) {

        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())//aiza kan el do5ol naji7 btib3at toast"signUpActivity Successful" wbtint12il min elshashi login llshashi map

                {

                    Toast.makeText(LogInActivity.this, "signUpActivity Successful.", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(LogInActivity.this,MapActivity.class);//wbtint12il min elshashi login llshashi map

                    startActivity(i);//tsh3'el activity tani bwasitit el intent

                    finish();//btinhi el activity ele ana mawjod fyo

                }

                else//aiza fishil el do5ol btib3at toast "signUpActivity failed"

                {

                    Toast.makeText(LogInActivity.this, "signUpActivity failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();//aiza fishil el do5ol btib3at toast "signUpActivity failed"

                    task.getException().printStackTrace();// bta3ti sbab lfashal

                }

            }

        });

    }



}
