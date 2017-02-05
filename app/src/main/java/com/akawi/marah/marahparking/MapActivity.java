package com.akawi.marah.marahparking;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    /**
     *  tjhez mo2shrat fe el activity
     */

    private GoogleMap mMap;
    private Button btnAddParking;
    private ListView listView;
    private MyAdapterParking adapterParking;

    /**
     * t7ded el keyam bwasitat findViewById
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        btnAddParking = (Button) findViewById(R.id.btnAddParking);
        listView = (ListView) findViewById(R.id.listView);
        adapterParking = new MyAdapterParking(this, R.layout.item_my_parking);
        listView.setAdapter(adapterParking);
        eventHandler();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmLogOut:
                Toast.makeText(getBaseContext(), "logout", Toast.LENGTH_LONG).show();
                break;
            case R.id.itmSettings:
                Toast.makeText(getBaseContext(), "settings", Toast.LENGTH_LONG).show();
                break;


        }
        return true;

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


   
    private void eventHandler(){

        btnAddParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapActivity.this, AddParkingActivity.class);
                startActivity(intent);
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                reference.push().setValue("hello word");

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initListView();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);// find my location
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    private void initListView(){
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().replace('.','_');
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference(email);
        reference.child("myParking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              adapterParking.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Parking myParking=ds.getValue(Parking.class);
                    myParking.setId(ds.getKey());
                    adapterParking.add(myParking);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
