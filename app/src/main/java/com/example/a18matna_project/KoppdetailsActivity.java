package com.example.a18matna_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class KoppdetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koppdetails);
        TextView incomingData = (TextView) findViewById(R.id.text_message);
        TextView header=(TextView) findViewById(R.id.text_header);

        Intent incomingIntent = getIntent();
        String incomingname = incomingIntent.getStringExtra("Name");
        String incomingsize = incomingIntent.getStringExtra("Size");
        String incomingcategory = incomingIntent.getStringExtra("Category");
        String incomingprice = incomingIntent.getStringExtra("Price");
        String incomingcolor = incomingIntent.getStringExtra("Color");

        header.setText(" "+(incomingname));
        incomingData.setText("" + (incomingsize) + "\n" + (incomingcolor)+ "\n" + (incomingcategory)+ "\n" + (incomingprice));

        ImageView bild = findViewById(R.id.bild);

        if (incomingcategory.equals("Category: Coffee")) {
            bild.setImageResource(R.drawable.kaffekopp);

        }

        else if(incomingcategory.equals("Category: Tea")){
            bild.setImageResource(R.drawable.tekopp);
        }
    }
}
