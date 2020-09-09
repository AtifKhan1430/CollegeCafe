package com.example.final1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class vendorHome extends AppCompatActivity {
    TextView email;
    ImageButton food;
    ImageButton hotel;
    ImageButton stationary;
    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_home);

        email = (TextView) findViewById(R.id.email);
        Intent intent = getIntent();
        String receivedName = intent.getStringExtra("editemail");
        email.setText(receivedName);

        order = (Button) findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vendorHome.this, vieworder.class);
                startActivity(intent);
            }
        });

        food = (ImageButton) findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vendorHome.this,FoodMain.class);
                String email = ((TextView)findViewById(R.id.email)).getText().toString();
                intent.putExtra("editemail",email);
                startActivity(intent);
            }
        });

        hotel = (ImageButton) findViewById(R.id.hotel);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vendorHome.this,HotelMain.class);
                String email = ((TextView)findViewById(R.id.email)).getText().toString();
                intent.putExtra("editemail",email);
                startActivity(intent);
            }
        });

        stationary = (ImageButton) findViewById(R.id.stationary);
        stationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vendorHome.this,StationaryMain.class);
                String email = ((TextView)findViewById(R.id.email)).getText().toString();
                intent.putExtra("editemail",email);
                startActivity(intent);
            }
        });

    }
    public void openFood() {
        Intent intent = new Intent(this, FoodMain.class);
        startActivity(intent);
    }
    public void openHotel() {
        Intent intent = new Intent(this, HotelMain.class);
        startActivity(intent);
    }
    public void openStationary() {
        Intent intent = new Intent(this, StationaryMain.class);
        startActivity(intent);
    }
}
