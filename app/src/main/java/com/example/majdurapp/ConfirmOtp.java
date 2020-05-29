package com.example.majdurapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmOtp extends AppCompatActivity {

    Button submitOTP;
    TextView resendOTP;
    EditText enterOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_otp);

        submitOTP = (Button) findViewById(R.id.btn_submit_otp);
        resendOTP = (TextView) findViewById(R.id.btn_resend_otp);
        enterOTP = (EditText) findViewById(R.id.enter_otp);

        submitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterOTP.getText().toString().length() < 4)
                {
                    enterOTP.setError(Html.fromHtml("<font color='white'>OTP too short</font>"));
                }
                else {
                    Intent intent = new Intent(ConfirmOtp.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Resend OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}