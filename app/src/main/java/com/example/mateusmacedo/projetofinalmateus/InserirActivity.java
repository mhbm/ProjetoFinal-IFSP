package com.example.mateusmacedo.projetofinalmateus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class InserirActivity extends AppCompatActivity {

    Button buttonVoltarInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        buttonVoltarInicio=(Button)findViewById(R.id.btnvoltarinicio);
        buttonVoltarInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
