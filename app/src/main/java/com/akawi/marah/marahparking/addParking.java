package com.akawi.marah.marahparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addParking extends AppCompatActivity {
    private  EditText etIsShagira;
    private EditText etIs7enam;
    private EditText etDate;
    private EditText etAdress;
    private TextClock textClock;
    private Button btnSaveParking;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);
        etIsShagira=(EditText) findViewById(R.id.etIsShagira);
        etIs7enam=(EditText) findViewById(R.id.etIs7enam);
        etDate=(EditText)findViewById(R.id.etDate);
        etAdress=(EditText)findViewById(R.id.etAdress);
        textClock=(TextClock)findViewById(R.id.textClock);
        btnSaveParking=(Button)findViewById(R.id.btnSaveParking);



    }
    private void dataHandler(){
        String stIsShagira=etIsShagira.getText().toString();
        String stIs7enam=etIs7enam.getText().toString();
        String stDate=etDate.getText().toString();
        String stAdress=etAdress.getText().toString();
        boolean isOk=true;
        if (stIsShagira.length()==0){
            etIsShagira.setError("wrong Text");
            isOk=false;
        }
        if (stIs7enam.length()==0){
          etIs7enam.setError("wrong Text");
            isOk=false;
        }
        if (stDate.length()==0){
           etDate.setError("wrong Text");
            isOk=false;
        }
        if (stAdress.length()==0){
            etAdress.setError("wrong Text");
            isOk=false;

        }
        if(isOk){
            Intent intent=new Intent(addParking.this,MapsActivity1.class);
            startActivity(intent);
            Parking myParking=new Parking();
            myParking.setIsShagira(stIsShagira);
            myParking.setIs7enam(stIs7enam);
            myParking.setAdress(stAdress);
            myParking.setWhen(stDate);
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
            String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
            reference.child(email).child("myParking").push().setValue(myParking, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                  if (databaseError==null){
                      Toast.makeText(getBaseContext(),"save ok",Toast.LENGTH_LONG).show();

                  }
                    else {
                      Toast.makeText(getBaseContext(),"save Error"+databaseError.getMessage(),Toast.LENGTH_LONG).show();
                      databaseError.toException().printStackTrace();
                  }
                }
            });



        }
    }
}
