package com.example.oussama.taxis;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
//com.google.android.gms.maps.SupportMapFragment
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Maps extends FragmentActivity implements OnMapReadyCallback
        , GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener {


    private GoogleMap mMap;
    double LatitudePosition;
    double LongitudePosition;
    double LatitudeDistination;
    double LongitudeDistination;
    int line = 0;

    public void Changer(View view) {
        mMap.clear();
        LatitudePosition = 0;
        LongitudePosition = 0;
        LatitudeDistination = 0;
        LongitudeDistination = 0;
        line = 0;

        TextView distance = (TextView) findViewById(R.id.ttxview1);
        distance.setText("Entrer d'abord num tel puis Cliquez  sur votre position en suite sur la destination");

    }

    public void buclick3(View view) {
        Location location1 = new Location("a");
        location1.setLatitude(LatitudePosition);
        location1.setLongitude(LongitudePosition);

        Location location2 = new Location("b");
        location2.setLatitude(LatitudeDistination);
        location2.setLongitude(LongitudeDistination);
        float x = location1.distanceTo(location2);
        x = (x / 1000);
        int y = Math.round(x);
        y = ((y * 25) / 100) + y;

         int k = y * 25;
        TextView distance = (TextView) findViewById(R.id.ttxview1);
        distance.setText("Le prix de votre course est : " + k + "DA" + " pour une distance de " + y + "KM");

 //insert data to real time database

        EditText txt_numtel = (EditText) findViewById(R.id.editText11);
        int numtel = Integer.parseInt(  txt_numtel.getText().toString()  ) ;

DatabaseReference  reff=FirebaseDatabase.getInstance().getReference().child("course" );

course information = new course(LatitudePosition ,LongitudePosition , LatitudeDistination,LongitudeDistination,k,y  , numtel);
reff.push().setValue(information) ;


}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener( this);



        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(36.944760, 7.728450);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

             return ;

        }

        mMap.setMyLocationEnabled(true) ;


//        final Context c = this;
  //      final GoogleMap[] mp = {mMap};
    //    final int[] mark = new int[1];


    }

    @Override
    public void onMapClick(LatLng latLng) {

        if (line < 1) {
            LatitudePosition = latLng.latitude;
            LongitudePosition = latLng.longitude;


            mMap.addMarker(new MarkerOptions().position(latLng).title("Position").snippet("Vous êtes ici.")).showInfoWindow();

        }

        LatitudeDistination = latLng.latitude;
        LongitudeDistination = latLng.longitude;


        final Polyline polyline = mMap.addPolyline(new PolylineOptions()
                  .add(new LatLng(LatitudePosition, LongitudePosition), new LatLng(LatitudeDistination, LongitudeDistination))
                  .width(15)
                  .color(Color.RED)
                  .geodesic(true));
          line++;




    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }



}
