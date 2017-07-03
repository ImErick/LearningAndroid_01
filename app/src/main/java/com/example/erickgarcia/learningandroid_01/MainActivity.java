package com.example.erickgarcia.learningandroid_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private Button nextButton;
    private final String CADENA = "que pasa prro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // forzar y cargar icono en la app action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);
        // segunda manera, MVC
        boton = (Button) findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "si me presionaste tambien woe :v", Toast.LENGTH_SHORT).show();
            }
        });

        nextButton = (Button) findViewById(R.id.button3);
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // acceder al segundo activity y mandarle un string
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("cadena", CADENA);
                startActivity(intent);
            }
        });
    }

    // primera manera, no MVC
    public void buttonMethod(View view){
        Toast.makeText(this, "si me presionaste woe :v", Toast.LENGTH_SHORT).show();
    }

    // tercera forma: implementando un View.OnClickListener en la clase MainActivity
}
