package com.example.oussama.taxis;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Inscription extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

    }

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.toast_root));

        Toast toast = new Toast(getApplicationContext());
        toast.setView(layout);
    }

    public void buclick(View view) {
        // Toast error
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.toast_root));
        final TextView toastText = (TextView) layout.findViewById(R.id.toast_text);
        ImageView toastImage = (ImageView) layout.findViewById(R.id.toast_image);
        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        // Toast error

        // Toast succes
        LayoutInflater inflater1 = getLayoutInflater();
        View layout1 = inflater1.inflate(R.layout.toastsucces, (ViewGroup) findViewById(R.id.toast_root1));
        final TextView toastText1 = (TextView) layout1.findViewById(R.id.toast_text1);
        ImageView toastImage1 = (ImageView) layout1.findViewById(R.id.toast_image1);
        final Toast toast1 = new Toast(getApplicationContext());
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.setDuration(Toast.LENGTH_LONG);
        toast1.setView(layout1);
        // Toast succes

        EditText txt_Nom = (EditText) findViewById(R.id.editText3);
        EditText txt_Prenom = (EditText) findViewById(R.id.editText4);
        EditText txt_email = (EditText) findViewById(R.id.editText5);
        EditText txt_mot_de_passe = (EditText) findViewById(R.id.editText6);
        EditText txt_confirmermot_de_passe = (EditText) findViewById(R.id.editText);


        final String Nom = txt_Nom.getText().toString();
        final String Prenom = txt_Prenom.getText().toString();
        final String email = txt_email.getText().toString();
        final String mot_de_passe = txt_mot_de_passe.getText().toString();
        String confirmermot_de_passe = txt_confirmermot_de_passe.getText().toString();


     //   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("client");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


if (TextUtils.isEmpty(Nom) || (TextUtils.isEmpty(Prenom)) || (TextUtils.isEmpty(email)) || (TextUtils.isEmpty(mot_de_passe)) || (TextUtils.isEmpty(confirmermot_de_passe))) {

            toastText.setText("Tout les champs sont obligatoires.");
            toast.show();
        } else if (!(Nom.matches("[a-z|A-Z]{3,}")) && (!(TextUtils.isEmpty(Nom)))) {
            toastText.setText("Minimum 3 caractéres pour le Nom et interdit d'utiliser des numéros.");
            toast.show();

        } else if (!(Prenom.matches("[a-z|A-Z]{3,}")) && (!(TextUtils.isEmpty(Prenom)))) {
            toastText.setText("Minimum 3 caractéres pour le Prénom et interdit d'utiliser des numéros.");
            toast.show();

        } else if (!(email.matches("^(.+)@(.+)\\.(.+)$")) && (!(TextUtils.isEmpty(email)))) {

            toastText.setText("entrer email correct");
            toast.show();

        } else if (!(mot_de_passe.matches("^.{6,}$")) && (!(TextUtils.isEmpty(mot_de_passe)))) {

            toastText.setText("password incorect min 6 carctéres");
            toast.show();

        } else if (!(confirmermot_de_passe.equals(mot_de_passe))) {

            toastText.setText("Mot de passe non confirmé.");
            toast.show();
        }

        else {

            firebaseAuth.createUserWithEmailAndPassword(email, mot_de_passe)
                    .addOnCompleteListener(Inscription.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {

                                toastText.setText("" + task.getException().getMessage());
                                toast.show();

                            } else {

                                client information = new client(Nom, Prenom, email, mot_de_passe);
                                FirebaseDatabase.getInstance().getReference("client")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        toastText1.setText("Votre compte a été créé avec succés.");
                                        toast1.show();

                                        startActivity(new Intent(Inscription.this, Login.class));
                                    }
                                });
                            }


                        }


                    });


        }


    }


}