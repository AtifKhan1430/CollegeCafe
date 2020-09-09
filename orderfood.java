package com.example.final1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class orderfood extends AppCompatActivity {
    TextView name,email;
    TextView price;
    ImageButton ib_addnew,ib_remove;
    EditText edTextQuantity;
    double Total=0d;
    TextView tvTotal,editemail;
    Button button, button2;
    String nameHolder, priceHolder, quantityHolder, totalHolder;

    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderfood);
        editemail = findViewById(R.id.editemail);
        button2 = findViewById(R.id.button2);
        ib_addnew = findViewById(R.id.ib_addnew);
        ib_remove = findViewById(R.id.ib_remove);
        tvTotal = findViewById(R.id.tvTotal);
        edTextQuantity = findViewById(R.id.editTextQuantity);
        name = findViewById(R.id.textView);
        price = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        email = findViewById(R.id.email);
        Intent intent = getIntent();
        String receivedName = intent.getStringExtra("name");
        String receivedPrice = intent.getStringExtra("price");
        String receivedEmail = intent.getStringExtra("email");
        email.setText(receivedEmail);
        name.setText(receivedName);
        price.setText(receivedPrice);

        ib_addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food.CartQuantity = Food.CartQuantity + 1;
                edTextQuantity.setText(Food.CartQuantity + "");
            }
        });
        ib_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food.CartQuantity = Food.CartQuantity - 1;
                edTextQuantity.setText(Food.CartQuantity + "");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = new Integer(price.getText().toString());
                int y = new Integer(edTextQuantity.getText().toString());
                Total = Total + (y * x);
                tvTotal.setText(""+ Total);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(orderfood.this,payment.class);
                String name = ((TextView)findViewById(R.id.textView)).getText().toString();
                String prices = ((TextView)findViewById(R.id.textView2)).getText().toString();
                String price = ((TextView)findViewById(R.id.tvTotal)).getText().toString();
                String quantity = ((TextView)findViewById(R.id.editTextQuantity)).getText().toString();
                String email = ((TextView)findViewById(R.id.email)).getText().toString();
                intent.putExtra("email",email);
                intent.putExtra("price",price);
                intent.putExtra("prices",prices);
                intent.putExtra("name",name);
                intent.putExtra("quantity",quantity);
                startActivity(intent);
               // CheckEditTextStatus();
            }
                /*try{
                    sqLiteHelper.insertData3(
                            name.getText().toString().trim(),
                            price.getText().toString().trim(),
                            edTextQuantity.getText().toString().trim(),
                            tvTotal.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }*/

           /* private void CheckEditTextStatus() {

                // Getting value from All EditText and storing into String Variables.
                nameHolder = name.getText().toString();
                priceHolder = price.getText().toString();
                quantityHolder = edTextQuantity.getText().toString();
                totalHolder = tvTotal.getText().toString();

                if (TextUtils.isEmpty(nameHolder) || TextUtils.isEmpty(priceHolder) || TextUtils.isEmpty(quantityHolder) || TextUtils.isEmpty(totalHolder)) {

                    Toast.makeText(getApplicationContext(), "PLEASE SELECT TOTAL ORDER VALUE", Toast.LENGTH_SHORT).show();

                } else {

                    try {
                        sqLiteHelper.insertData3(
                                name.getText().toString().trim(),
                                price.getText().toString().trim(),
                                edTextQuantity.getText().toString().trim(),
                                tvTotal.getText().toString().trim()
                        );
                        Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(orderfood.this,payment.class);
                        String price = ((TextView)findViewById(R.id.tvTotal)).getText().toString();
                        intent.putExtra("price",price);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }*/
        });
    }
}
