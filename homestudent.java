package com.example.final1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class homestudent extends AppCompatActivity {
    ImageButton food,hotel,stationary;
    TextView editemail;
    Button order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestudent);
        editemail = (TextView) findViewById(R.id.editemail);
        Intent intent = getIntent();
        String receivedName = intent.getStringExtra("editemail");
        editemail.setText(receivedName);

        stationary = (ImageButton) findViewById(R.id.stationary);

        stationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homestudent.this, StationaryList.class);
                startActivity(intent);
            }
        });

        order = (Button) findViewById(R.id.order);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homestudent.this, vieworder.class);
                startActivity(intent);
            }
        });

        food = (ImageButton) findViewById(R.id.food);
        hotel = (ImageButton) findViewById(R.id.hotel);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFood();
            }
        });
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHotel();
            }
        });
    }public void openFood() {
        Intent intent = new Intent(homestudent.this, FoodList.class);
        startActivity(intent);

    }
    public void openHotel() {
        Intent intent = new Intent(homestudent.this, HotelList.class);
        startActivity(intent);

    }
}
