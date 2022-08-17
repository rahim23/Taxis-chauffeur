package com.example.chauffeur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class courseAdapter extends ArrayAdapter <course> {


    public courseAdapter(Context context , ArrayList<course> courses){

        super(context,0,courses) ;

    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      course course=getItem(position) ;

    if(convertView == null ) {
    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_course, parent, false);
    }

       TextView distance ,  prix ,numtel ;

         distance=convertView.findViewById(R.id.id_distance);
         prix=convertView.findViewById(R.id.id_prix)  ;
         numtel=convertView.findViewById(R.id.id_numtel)  ;

       distance.setText( "distance : "+  (Integer.toString(course.getDistance()) ) +"km" );
        prix.setText( "prix : " + (Integer.toString(    course.getprix() )) + "Da"  );
        numtel.setText( "num tel : " + (Integer.toString(    course.getNumtel() )) );





        return convertView ;


    }



}


