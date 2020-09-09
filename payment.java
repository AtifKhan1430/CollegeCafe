package com.example.final1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class  payment extends AppCompatActivity {
    EditText amountEt, noteEt, nameEt, upiIdEt,statustxt;
    TextView name,price,quantity,editemail,email;
    Button send;
    final int UPI_PAYMENT = 0;
    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        sqLiteHelper = new SQLiteHelper(this);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ORDERS(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, price VARCHAR, quantity VARCHAR, total VARCHAR, status VARCHAR)");

        name = findViewById(R.id.names);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        editemail = findViewById(R.id.editemail);
        email = findViewById(R.id.email);
        initializeViews();

        Intent intent = getIntent();
        String receivedPrice = intent.getStringExtra("price");
        amountEt.setText(receivedPrice);
        String receivedName = intent.getStringExtra("name");
        name.setText(receivedName);
        String receivedPrice1 = intent.getStringExtra("prices");
        price.setText(receivedPrice1);
        String receivedQuantity = intent.getStringExtra("quantity");
        quantity.setText(receivedQuantity);
        String receivedEditemail = intent.getStringExtra("editemail");
        editemail.setText(receivedEditemail);
        String receivedEmail = intent.getStringExtra("email");
        email.setText(receivedEmail);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the values from the EditTexts
                String amount = amountEt.getText().toString();
                String note = noteEt.getText().toString();
                String name = nameEt.getText().toString();
                String upiId = upiIdEt.getText().toString();
                payUsingUpi(amount, upiId, name, note);
            }
        });
    }

    void initializeViews() {
        send = findViewById(R.id.send);
        amountEt = findViewById(R.id.amount_et);
        noteEt = findViewById(R.id.note);
        nameEt = findViewById(R.id.name);
        upiIdEt = findViewById(R.id.upi_id);
        statustxt = findViewById(R.id.statustxt);
    }

    void payUsingUpi(String amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(payment.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(payment.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "YOUR PAYEMENT IS CANCELLED.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                statustxt.setText("ORDER NUMBER: "+approvalRefNo);
                Toast.makeText(payment.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
               // CheckEditTextStatus();
                Intent intent = new Intent(payment.this,afterpayment.class);
                String names = ((TextView)findViewById(R.id.names)).getText().toString();
                String prices = ((TextView)findViewById(R.id.price)).getText().toString();
                String price = ((EditText)findViewById(R.id.amount_et)).getText().toString();
                String quantity = ((TextView)findViewById(R.id.quantity)).getText().toString();
                String statu = ((TextView)findViewById(R.id.statustxt)).getText().toString();
                String editemail = ((TextView)findViewById(R.id.editemail)).getText().toString();
                String email = ((TextView)findViewById(R.id.email)).getText().toString();
                intent.putExtra("email",email);
                intent.putExtra("price",price);
                intent.putExtra("prices",prices);
                intent.putExtra("name",names);
                intent.putExtra("quantity",quantity);
                intent.putExtra("status",statu);
                intent.putExtra("editemail",editemail);
                startActivity(intent);
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(payment.this, "Payment cancelled by user.Please Try Again!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(payment.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(payment.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;

    }
}
