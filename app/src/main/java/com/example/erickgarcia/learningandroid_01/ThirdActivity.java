package com.example.erickgarcia.learningandroid_01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;

    private ImageButton imageButtonCamera;
    private ImageButton imageButtonViewWeb;
    private ImageButton imageButtonCall;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // activar flecha para ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);

        imageButtonCall = (ImageButton) findViewById(R.id.imageButtonPhone);
        imageButtonCamera = (ImageButton) findViewById(R.id.imageButtonCamera);
        imageButtonViewWeb = (ImageButton) findViewById(R.id.imageButtonMenuView);

        // boton para la llamada
        imageButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent implicito para abrir la app por default del telefono
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    // comprobar version actual de android en ejecucion
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // mayor o igual que marshmallow
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        olderVersions(phoneNumber);
                    }
                } else
                    Toast.makeText(ThirdActivity.this, "no hay ningun numero weon",Toast.LENGTH_SHORT).show();
            }

            private void olderVersions(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "no tienes permisos weon", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // boton para el navegador web
        imageButtonViewWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextWeb.getText().toString();
                String email = "3r1ck.442@gmail.com"; // podriamos hacerlo con un boton
                if (url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
                    // voy a agregar mas ejemplos de Intent aqui, no mas for the lulz
                    Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                    // email
                    Intent intentEmail = new Intent(Intent.ACTION_VIEW, Uri.parse(email)); // aqui lo podemos parsear con un boton
                    intentEmail.setType("plain/text");
                    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Mails Tittle"); // esto se puede agregar con mas botones
                    intentEmail.putExtra(Intent.EXTRA_TEXT, "testing the text");

                    startActivity(intentWeb);
                } else {
                    Toast.makeText(ThirdActivity.this, "no ingresaste ninguna direccion weon", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // boton de la camara
        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCamera);
                // habra que hacer un startActivityResult para poder obtener la imagen y hacer weas con ella
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // estamos en el caso del telefono
        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    // comprobar si ha sido aceptado o denegado la peticion
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // concedio el permiso
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            return;
                        startActivity(intentCall);
                    } else {
                        // no lo concedio
                        Toast.makeText(ThirdActivity.this, "denego el permiso", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    // esta wea solo va a comprobar que tengamos los permisos para usarlo (manifest)
    private boolean checkPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
