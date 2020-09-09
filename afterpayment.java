package com.example.final1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


public class afterpayment extends AppCompatActivity {
TextView txt,name,price,quantity,total,editemail,email;
Button button,vieworder;
EditText txtMobile;
    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterpayment);
        txt = findViewById(R.id.tid);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        total = findViewById(R.id.total);
        button = findViewById(R.id.button3);
        vieworder = findViewById(R.id.vieworder);
        txtMobile = findViewById(R.id.txtMobile);
        email = findViewById(R.id.email);
        Intent intent = getIntent();
        String receivedName = intent.getStringExtra("name");
        name.setText(receivedName);
        String receivedPrice1 = intent.getStringExtra("prices");
        price.setText(receivedPrice1);
        String receivedQuantity = intent.getStringExtra("quantity");
        quantity.setText(receivedQuantity);
        String receivedTotal = intent.getStringExtra("price");
        total.setText(receivedTotal);
        String receivedStatus = intent.getStringExtra("status");
        txt.setText(receivedStatus);
        String receivedEmail = intent.getStringExtra("email");
        email.setText(receivedEmail);

        sqLiteHelper = new SQLiteHelper(this);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ITEMORDERS(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, quantity VARCHAR, total VARCHAR, status VARCHAR,email VARCHAR)");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sqLiteHelper.insertData3(
                            name.getText().toString().trim(),
                            price.getText().toString().trim(),
                            quantity.getText().toString().trim(),
                            total.getText().toString().trim(),
                            txt.getText().toString().trim(),
                            email.getText().toString().trim()

                    );
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("smsto:"));
                    i.setType("vnd.android-dir/mms-sms");
                    i.putExtra("address", new String(txtMobile.getText().toString()));
                    i.putExtra("sms_body",txt.getText().toString());
                    startActivity(Intent.createChooser(i, "Send sms via:"));
                    Toast.makeText(afterpayment.this, "Order Id Sent To Your Registerd Number Successfully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "ORDER CREATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    addNotification();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(afterpayment.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(afterpayment.this,vieworder.class);
                startActivity(intent);
            }
        });

    }
    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(afterpayment.this)
                        .setSmallIcon(R.drawable.admin)
                        .setContentTitle("ORDER CREATED SUCCESSFULLY")
                        .setContentText("ORDER NUMBER:"+txt);

        Intent notificationIntent = new Intent(this, afterpayment.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
