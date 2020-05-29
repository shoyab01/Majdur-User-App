package com.example.majdurapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    Button getOtp;
    EditText registerMobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getOtp = (Button) findViewById(R.id.btn_get_otp);
        registerMobileNumber = (EditText) findViewById(R.id.register_mobile_number);

        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerMobileNumber.getText().toString().length() < 10)
                {
                    registerMobileNumber.setError(Html.fromHtml("<font color='white'>Invalid Mobile Number</font>"));
                }
                else {
                    Intent intent = new Intent(Register.this, ConfirmOtp.class);
                    startActivity(intent);
                }
            }
        });
    }
}