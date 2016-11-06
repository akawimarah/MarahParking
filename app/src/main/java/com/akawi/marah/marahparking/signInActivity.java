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
import com.google.firebase.auth.FirebaseUser;

public class signInActivity extends AppCompatActivity {
    private EditText etName, etMail,etPass1, etPass2;
    private Button btnsignIn;
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etMail=(EditText)findViewById(R.id.etMail);
       etName=(EditText)findViewById(R.id.etName);
        etPass1=(EditText)findViewById(R.id.etPass1);
        etPass2=(EditText)findViewById(R.id.etPass2);
        btnsignIn=(Button)findViewById(R.id.btnSignIn);
        auth=FirebaseAuth.getInstance();



    }
    private void dataHandler(){
        String stMail= etMail.getText().toString();
        String stName= etName.getText().toString();
        String stPass1= etPass1.getText().toString();
        String stPass2= etPass2.getText().toString();
        boolean isok=true;
        if (stMail.length()==0){
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
            creatAcount(stMail, stPass1);





    }
    private void eventHandler(){
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(signInActivity.this,mapActivity.class);
                startActivity(i);
                dataHandler();

            }
        });

    }
    private FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {

        @Override

        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            //4.

            FirebaseUser user=firebaseAuth.getCurrentUser();

            if(user!=null)

            {

                //user is signed in

                Toast.makeText(signInActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();


            }

            else

            {

                //user signed out

                Toast.makeText(signInActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();


            }

        }

    };
    @Override

    protected void onStart() {

        super.onStart();

        auth.addAuthStateListener(authStateListener);

    }

    @Override

    protected void onStop() {

        super.onStop();

        if(authStateListener!=null)

            auth.removeAuthStateListener(authStateListener);

    }

    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email,passw).addOnCompleteListener(signInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(signInActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                }
                else
                {
                    Toast.makeText(signInActivity.this, "Authentication failed."+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }









}
