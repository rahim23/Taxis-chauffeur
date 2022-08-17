 package com.example.chauffeur;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/*import static com.example.chauffeur.course.LatitudeDistination;
import static com.example.chauffeur.course.LatitudePosition;
import static com.example.chauffeur.course.LongitudeDistination;
import static com.example.chauffeur.course.LongitudePosition;
*/
//com.google.android.gms.maps.SupportMapFragment
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;


/*public class Maps extends FragmentActivity implements OnMapReadyCallback
        , GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener {
*/


   public class Maps extends Activity implements OnMapReadyCallback {



    public void retour (View view) {

        Intent intent = new Intent(Maps.this , MainActivity.class) ;

        startActivity(intent);
    }



       @Override
       protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_maps2);
           // Obtain the SupportMapFragment and get notified when the map is ready to be used.
           MapFragment mapFragment = (MapFragment) getFragmentManager()
                   .findFragmentById(R.id.map);
           mapFragment.getMapAsync(this);

       }



       @Override
       public void onMapReady( GoogleMap mMap )   {



          double LatitudePosition = getIntent().getDoubleExtra("LatitudePosition",0) ;
          double LongitudePosition = getIntent().getDoubleExtra("LongitudePosition" , 0) ;
          double LatitudeDistination = getIntent().getDoubleExtra("LatitudeDistination" , 0) ;
          double LongitudeDistination = getIntent().getDoubleExtra("LongitudeDistination" , 0) ;



                       LatLng sydney = new LatLng(LatitudePosition,LongitudePosition);
                       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 8));
                       Marker melbourne = mMap.addMarker((new MarkerOptions().position(sydney).title("Position").snippet("de depart")));
                       melbourne.showInfoWindow();

                       mMap.setBuildingsEnabled(true);
                       mMap.getUiSettings().setZoomControlsEnabled(true);


                       Polyline poline = mMap.addPolyline(new PolylineOptions()
                               .add(new LatLng(LatitudePosition,LongitudePosition), new LatLng(LatitudeDistination,LongitudeDistination))
                               .width(35)
                               .color(Color.RED)
                               .geodesic(true));



       }







    }


