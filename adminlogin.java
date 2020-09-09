package com.example.final1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminlogin extends AppCompatActivity {
    Button LogInButton ;
    EditText Email, Password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        LogInButton = (Button)findViewById(R.id.buttonLogin);
        Email = (EditText) findViewById(R.id.editEmail);
        Password = (EditText) findViewById(R.id.editPassword);
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
LogInButton();
            }
        });
    }
    public void LogInButton(){
        String user=Email.getText().toString().trim();
        String pass=Password.getText().toString().trim();
        if (user.equals("admin")&&pass.equals("admin"))
        {
            Toast.makeText(this,"WELCOME ADMIN",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, registerstudent.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"INCORRECT USERNAME AND PASSWORD",Toast.LENGTH_LONG).show();
        }
    }
}
