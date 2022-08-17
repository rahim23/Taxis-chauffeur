package com.example.oussama.taxis;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        final TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
        ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        // Toast custom

        EditText txt_email = (EditText) findViewById(R.id.editText2);
        EditText txt_password = (EditText) findViewById(R.id.editText7);

        String email = txt_email.getText().toString();
        String password=txt_password.getText().toString();


        if ((TextUtils.isEmpty(email) && (TextUtils.isEmpty(password) )) ){

            toastText.setText("Entrez vos identifiants.");
            toast.show();

        }
        else {
            if ((TextUtils.isEmpty(email) || (TextUtils.isEmpty(password) ))) {
                if ((TextUtils.isEmpty(email))) {
                    toastText.setText("Entrez votre Email.");
                    toast.show();
                }

                if ((TextUtils.isEmpty(password))) {
                    toastText.setText("Entrez votre password.");
                    toast.show();
                }

            }

            else {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance() ;
                firebaseAuth.signInWithEmailAndPassword(email,password)  .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            toastText.setText("" + task.getException().getMessage());
                            toast.show();

                        }


                        else
                        {

                            startActivity(new Intent(getApplicationContext(), AfterLoginOne.class));

                        }



                    }

                });









            }





        }}


    public void click(View view)
    {

        Intent myintent1 = new Intent(this,Inscription.class) ;


        startActivity(myintent1);


    }



}
