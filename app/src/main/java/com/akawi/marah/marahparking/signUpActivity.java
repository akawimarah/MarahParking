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
import com.google.firebase.auth.FirebaseUser;

/**
 * tjhez mo2shrat fe el activity
 */
public class signUpActivity extends AppCompatActivity {
    private EditText etName, etMail,etPass1, etPass2;
    private Button btnsignIn;
    private FirebaseAuth auth;// ka2in mnst3mlo 3shan ni3mal signIn w signOut w signUp
    private FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener()//?
    {

        @Override

        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if(user!=null)
            {
                //user is signed in

                Toast.makeText(signUpActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();
            }

            else
            {

                //user signed out

                Toast.makeText(signUpActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     * t7ded el keyam bwasitat findViewById fe dalit onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)// he awal 3malye tonafaz tlka2iyan(Called when the activity is first created)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);//trbot ben el xml wil java
        etMail=(EditText)findViewById(R.id.etMail);
       etName=(EditText)findViewById(R.id.etName);
        etPass1=(EditText)findViewById(R.id.etPass1);
        etPass2=(EditText)findViewById(R.id.etPass2);
        btnsignIn=(Button)findViewById(R.id.btnSignIn);
        auth=FirebaseAuth.getInstance();
        eventHandler();// aistd3a2 ldalit eventHandler 3shan ni2dar nist3mlha
    }

    /**
     * aist5raj fa7wa l7okol wmo3aljit el mo3tayat(fa7s kanonyet elmod5lat)
     */
    private void dataHandler(){
        String stMail= etMail.getText().toString();//aist5raj fa7wa l7okol wmo3aljit el mo3tayat
        String stName= etName.getText().toString();
        String stPass1= etPass1.getText().toString();
        String stPass2= etPass2.getText().toString();
        boolean isok=true;
        if (stMail.length()==0)// fa7s kanonyet elmod5lat
        {
            etMail.setError("wrong Email");
            isok=false;
        }
        if (stName.length()==0){
            etName.setError("wrong Name");
            isok=false;
        }
        if (stPass1.length()==0){
            etPass1.setError("wrong Password1");
            isok=false;
        }
        if (stPass2.length()==0){
            etPass2.setError("wrong Password2");
            isok=false;
        }
        if (isok)
            creatAcount(stMail, stPass1);// aistd3a2 dalit creatAcount aiza kant kanonyet el mod5lat mola2mi ytim bina2 acount bil app


    }

    /**
     *  ll2zrar min ajil  mo3aljit el 7adath
     *  onclick dalit rad el fi3il
     */
    private void eventHandler(){
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(signUpActivity.this,MapActivity.class);// ynt2il min lshashi signup llshashi map
                startActivity(i);//tsh3'el activity tani bwasitit el intent
                dataHandler();// aistd3a2 ldalit dataHandler

            }
        });

    }



    @Override

    protected void onStart()//dali tab3a ll activity (Called when the activity is becoming visible to the user)
    {

        super.onStart();

        auth.addAuthStateListener(authStateListener);//

    }

    @Override

    protected void onStop()//dali tab3a ll activity (Called when the activity is no longer visible to the user)
    {

        super.onStop();

        if(authStateListener!=null)//

            auth.removeAuthStateListener(authStateListener);

    }

    /**
     * dali bti3mal new acount bttla2a email w pass
     * @param email
     * @param passw
     */

    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(signUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())// aiza bina2 el acunt niji7
                {
                    Toast.makeText(signUpActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();// btib3at toast aino l3malye nj7at
                    //updateUserProfile(task.getResult().getUser());
                    finish();//btinhi el activity ele ana mawjod fyo
                }
                else// aiza bina2 el acount fishil
                {
                    Toast.makeText(signUpActivity.this, "Authentication failed."+task.getException().getMessage(),Toast.LENGTH_SHORT).show();//  btb3at toast
                    task.getException().printStackTrace();// sbab lfashal fe bna2 acount
                }
            }
        });
    }









}
