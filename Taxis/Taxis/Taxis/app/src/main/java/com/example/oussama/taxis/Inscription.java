package com.example.oussama.taxis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Inscription extends AppCompatActivity {

    DataBase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        db = new DataBase(this);
    }
    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.toast_root));

        Toast toast = new Toast(getApplicationContext());
        toast.setView(layout);
    }

    public void  buclick(View view)
    {
        // Toast error
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
        ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        // Toast error

        // Toast succes
        LayoutInflater inflater1 = getLayoutInflater();
        View layout1 = inflater1.inflate(R.layout.toastsucces, (ViewGroup) findViewById(R.id.toast_root1));
        TextView toastText1 = (TextView) layout1.findViewById(R.id.toast_text1);
        ImageView toastImage1 = (ImageView) layout1.findViewById(R.id.toast_image1);
        Toast toast1 = new Toast(getApplicationContext());
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.setDuration(Toast.LENGTH_LONG);
        toast1.setView(layout1);
        // Toast succes
        String c = "";
        EditText firstname = (EditText)findViewById(R.id.editText3) ;
        EditText lastname = (EditText)findViewById(R.id.editText4);
        EditText username = (EditText)findViewById(R.id.editText5);
        EditText password = (EditText)findViewById(R.id.editText6);
        EditText phone = (EditText)findViewById(R.id.editText8);
        DataBase db = new DataBase(this) ;
        db.InsertRowAdmin(username.getText().toString(), password.getText().toString());
        if ((firstname.getText().toString()).equals(c) || (lastname.getText().toString()).equals(c) || (username.getText().toString()).equals(c) || (password.getText().toString()).equals(c) || (phone.getText().toString()).equals(c)) {
            toastText.setText("Tout les champs sont obligatoires.");
            toast.show();
        }
        else {

            toastText1.setText("Votre compte a été créé avec succés");
            toast1.show();

        Intent myintent11 = new Intent(this,Login.class) ;


            startActivity(myintent11);

        }


    }


}