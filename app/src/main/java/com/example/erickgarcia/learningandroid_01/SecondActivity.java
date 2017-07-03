package com.example.erickgarcia.learningandroid_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView text;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // activar flecha para ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text = (TextView) findViewById(R.id.textView2);

        // tomar los datos del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String cadenita = bundle.getString("cadena");
            text.setText(cadenita);
        } else {
            Toast.makeText(SecondActivity.this, "this shit is empty", Toast.LENGTH_SHORT).show();
        }

        boton = (Button) findViewById(R.id.buttonSecondToThird);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
