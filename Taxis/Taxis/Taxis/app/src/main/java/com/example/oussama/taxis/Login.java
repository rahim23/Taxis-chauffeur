package com.example.oussama.taxis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DataBase db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DataBase(this);
    }
    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.toast_root));

        Toast toast = new Toast(getApplicationContext());
        toast.setView(layout);
    }

    public void click1(View view) {
        // Toast custom
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
        ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        // Toast custom
        String c = "";


        EditText username = (EditText)findViewById(R.id.editText2) ;
        EditText password = (EditText)findViewById(R.id.editText7) ;
        int res=db.Testt(username.getText().toString(),password.getText().toString());
        if ((username.getText().toString()).equals(c) && (password.getText().toString()).equals(c)) {
            toastText.setText("Entrez vos identifiants.");
            toast.show();
        } else {
            if ((username.getText().toString()).equals(c) || (password.getText().toString()).equals(c)) {
                if ((username.getText().toString()).equals(c)) {
                    toastText.setText("Entrez votre nom d'utilisateur.");
                    toast.show();
                }

                if ((password.getText().toString()).equals(c)) {
                    toastText.setText("Entrez votre mot de passe.");
                    toast.show();
                }
            } else {
                if (res==-1) {
                    toastText.setText("Identifiants invalides, Réessayez.");
                    toast.show(); }
                else { Intent myintent11 = new Intent(this,AfterLoginOne.class) ;


                    startActivity(myintent11); }


            } }}


    public void click(View view)
    {

        Intent myintent1 = new Intent(this,Inscription.class) ;


        startActivity(myintent1);


    }



}
