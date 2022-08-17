package com.example.chauffeur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public  class MainActivity extends AppCompatActivity

{

    ArrayList<course> courses ;
    ListView coursesList  ;
    DatabaseReference databaseReference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        courses = new ArrayList<>();
        coursesList = (ListView) findViewById(R.id.listview);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("course");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    course course = postSnapshot.getValue(course.class);
                    courses.add(course);


                }

                courseAdapter adapter = new courseAdapter(MainActivity.this, courses);
                coursesList.setAdapter(adapter);

                coursesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {

                     course c = courses.get(position);

                     Intent intent = new Intent(MainActivity.this , Maps.class) ;

               intent.putExtra("LatitudePosition" , c.LatitudePosition) ;
               intent.putExtra("LongitudePosition" , c.LongitudePosition) ;
               intent.putExtra("LatitudeDistination" , c.LatitudeDistination) ;
               intent.putExtra("LongitudeDistination" , c.LongitudeDistination) ;



                        startActivity(intent);
  




                    }

                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });



    }








}



