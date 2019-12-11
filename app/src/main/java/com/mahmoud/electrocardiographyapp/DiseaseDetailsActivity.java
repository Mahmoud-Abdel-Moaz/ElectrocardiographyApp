package com.mahmoud.electrocardiographyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DiseaseDetailsActivity extends AppCompatActivity {

    TextView txt_name,txt_info;

    String information,name;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);

        txt_info=findViewById(R.id.txt_info);
        txt_name=findViewById(R.id.txt_name);

        intent=getIntent();

        information=intent.getStringExtra("info");
        name=intent.getStringExtra("name");

        txt_info.setText(information);
        txt_name.setText(name);
    }
}
