package com.akawi.marah.marahparking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * tjhez mo2shrat fe el activity
 */
public class AddParkingActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks ,GoogleApiClient.OnConnectionFailedListener {
    DatabaseReference reference;// mo2ashir l3inwan ka3dit lbyanat fe el firebase
    private CheckBox cb7enam;// payment or not
    private CheckBox cbShagira2;//Vacant or not
    private EditText etAddress;//address
    private TextClock textClock;//Promotional clock
    private Button btnSaveParking;
    private GoogleApiClient mGoogleApiClient;//
    private Location mLastLocation;//
    private TextView tvAddress;
    private ImageButton btnAddress;

    @Override
    /**
     * t7ded el keyam bwasitat findViewById
     */
    protected void onCreate(Bundle savedInstanceState)//he awal 3malye tonafaz tlka2iyan(Called when the activity is first created)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);//trbot ben el xml wil java
        cb7enam = (CheckBox) findViewById(R.id.cb7enam);
        cbShagira2 = (CheckBox) findViewById(R.id.cbShagira2);
        etAddress = (EditText) findViewById(R.id.etAddress);
        textClock = (TextClock) findViewById(R.id.textClock);
        btnSaveParking = (Button) findViewById(R.id.btnSaveParking);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        btnAddress = (ImageButton) findViewById(R.id.btnAddress);
        eventHandler();// aistd3a2 ldalit eventHandler 3shan ni2dar nista3mil ldali
        if (mGoogleApiClient == null)//
        {
            // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            mGoogleApiClient = new GoogleApiClient.Builder(this)//
                    .addConnectionCallbacks(this)//
                    .addOnConnectionFailedListener(this)//
                    .addApi(LocationServices.API)//
                    .addApi(AppIndex.API).build();//
        }

    }
    /**
     * aist5raj fa7wa l7okol wmo3aljit el mo3tayat(fa7s kanonyet elmod5lat)
     */
    private void dataHandler() {
        String stAddress = etAddress.getText().toString();//aist5raj fa7wa l7okol
        boolean isOk = true;//mot3'yer min no3 boolean
        if (stAddress.length() == 0)//fa7s kanonyet elmod5lat
        {
            etAddress.setError("wrong Address");
            isOk = false;
        }
        if (isOk) {
            Intent i=new Intent(AddParkingActivity.this,MapActivity.class);// ainti2al min shashit el add lshashit el map
            startActivity(i);// tsh3'el activity tani bwasitit el intent
            Date date = Calendar.getInstance().getTime();//get the current time (date and time)
            Parking myParking = new Parking();// bina2 ka2in min fi2it parking
            //myParking.setIsShagira(stIsShagira);
            //myParking.setIs7enam(stIs7enam);
            //// TODO: 2/12/2017  get cuurent gps location and address
            myParking.setLat(mLastLocation.getLatitude());//
            myParking.setLng(mLastLocation.getLongitude());//
            myParking.setAdress(etAddress.getText().toString());//
            myParking.setWhen(date);//
            myParking.setIs7enam(cb7enam.isChecked()+"");//
            myParking.setIsShagira(cbShagira2.isChecked()+"");//
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();//
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();//aist5raj el email
            email=email.replace(".","_");// t7wel el (.) la (_) l2ano asma2 ljozor fe ka3dit el byanat la yomkin an t7we romoz ma 3da (_)
            myParking.setOwner(email);//
            reference.child("Parkings").push().setValue(myParking, new DatabaseReference.CompletionListener()// mo2ashir l3inwan ka3dit el byanat l parkings ( push ll2idafi)(child btzed aibn lmbna el mo3tyat)
            {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference)//
                {
                    if (databaseError == null) {
                        Toast.makeText(getBaseContext(), "save ok", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getBaseContext(), "save Error" + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                        databaseError.toException().printStackTrace();
                    }
                }
            });


        }
    }
    /**
     * ll2zrar min ajil  mo3aljit el 7adath
     *dalit onClick lrdod lfi3il
     * el listener  l7ifiz el kiyam bil firebase
     */
    private void eventHandler() {
        
        btnSaveParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }
        });
        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cb7enam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cb7enam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        cbShagira2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cbShagira2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

    }

    protected void onStart()// dali tab3a ll activity (Called when the activity is becoming visible to the user)
        {
        mGoogleApiClient.connect();//
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddParkingActivity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.akawi.marah.marahparking/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);//
    }

    protected void onStop()// dali tab3a ll activity (Called when the activity is no longer visible to the user)
    {
        mGoogleApiClient.disconnect();
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddParkingActivity Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.akawi.marah.marahparking/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);//
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)//
    {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);//
        if (mLastLocation != null)//
        {

//            etAdress.setText(String.valueOf(mLastLocation.getLatitude()));
//            etAdress.append(","+String.valueOf(mLastLocation.getLongitude()));

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());//

            List<android.location.Address> addresses  = null;//
            try {
                addresses = geocoder.getFromLocation(mLastLocation.getLatitude(),mLastLocation.getLongitude(), 1);//
                String city = addresses.get(0).getLocality();//
                String state = addresses.get(0).getAdminArea();//
                String zip = addresses.get(0).getPostalCode();//
                String country = addresses.get(0).getCountryName();//
                etAddress.setText(city+","+state+","+zip+","+country);//
            } catch (IOException e)//
            {
                e.printStackTrace();//

            }



        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

    }

    @Override
    public void onConnectionSuspended(int i)//
    {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
