package com.akawi.marah.marahparking;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;////ka2in mnst3mlo 3shan ni3mal signIn w signOut w signUp
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;// mo2ashir l3inwan ka3dit lbyanat fe el firebase
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    /**
     * tjhez mo2shrat fe el activity
     */

    private GoogleMap mMap;
    private Button btnAddParking;
    private ListView listView;
    private MyAdapterParking adapterParking;//ka2in min no3 MyAdpterParking
    private LocationManager locationManager;
    private Location mylocation;
    private Button refresh;
    private Button signOut;

    /**
     * t7ded el keyam bwasitat findViewById
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)//he awal 3malye tonafaz tlka2iyan(Called when the activity is first created)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);//setContentView btrbot ben el xml wil java
        btnAddParking = (Button) findViewById(R.id.btnAddParking);
        listView = (ListView) findViewById(R.id.listView);
        adapterParking = new MyAdapterParking(this, R.layout.item_my_parking);//
        listView.setAdapter(adapterParking);//
        refresh=(Button)findViewById(R.id.refresh);
        signOut=(Button)findViewById(R.id.signOut);
        eventHandler();// astid3a2 ldalit eventHandler 3shan ni2dar nista3mil ldali

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.(el7osol 3la supportMapFargment wtlaki tnbeh lma l5areta jahzi
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);////this code line will retrieve the map view in Ul that you need to interact with  programmatlically
        mapFragment.getMapAsync(this);// get.MapAsync this function will start map service
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, new android.location.LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                //initListView();
                mylocation=location;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });
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

    private void eventHandler()// dalit mo3aljit el 7adath ll2zrar(onclick dalit rad el fi3il)
    {

        btnAddParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, AddParkingActivity.class);// ainti2al min shashit el map l shashit addParking
                startActivity(intent);//tsh3"el activity tanye bwasitit el intent
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initListView();
            }
        });
     signOut.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FirebaseAuth.getInstance().signOut();
             Intent i=new Intent(MapActivity.this,LogInActivity.class);
             startActivity(i);
         }
     });
    }

    @Override
    protected void onStart()//dali tab3a ll activity (Called when the activity is becoming visible to the use)
    {
        super.onStart();
        initListView();// aistd3a2 dalit initListView(3shan ywrjena lt3delat wil t3'yerat 3l activity)
    }

    /**
     * after succesfullystarting map service thin onMapReady will be called
     * @param googleMap
     */

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
        LatLng danon = new LatLng(32.9937, 35.1534);// creating a sydney position with latitude 32.9937 and longitude 35.1534
        mMap.addMarker(new MarkerOptions().position(danon).title("Marker in danon"));// addmarker will add a marker at sydney position to the map with title "Marker in danon"
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(danon, 12));// to zoom sydney in level 12 use newLatLngZoom(sudney,12)
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);// zyade
        mMap.setMyLocationEnabled(true);

    }


    private void initListView() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();//
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();// aist5raj el email
        email = email.replace(".", "_");//asma2 el jothor fe ka3dit lbyanat binfa3ish ti7we romoz ma3da( _)
        reference.child("Parkings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapterParking.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Parking myParking = ds.getValue(Parking.class);
                    myParking.setId(ds.getKey());
                    adapterParking.add(myParking);
                    LatLng parkLoc = new LatLng(myParking.getLat(), myParking.getLng());
                    if(mylocation!=null) {

                        float[]results={0,0,0};
                        LatLng myLoc = new LatLng(mylocation.getLatitude(), mylocation.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 12));
                        Location.distanceBetween(myParking.getLat(),myParking.getLng(),mylocation.getLatitude(),mylocation.getLongitude(),results);//rad
                        if (results[0]<10*1000)

                            adapterParking.add(myParking);
                            //mMap.addPolyline(new PolylineOptions().add(parkLoc,myLoc)
                                  //  .width(10)
                                    //.color(Color.RED)


                            //);
                    }



                    mMap.addMarker(new MarkerOptions().position(parkLoc).title(myParking.getAdress()));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
