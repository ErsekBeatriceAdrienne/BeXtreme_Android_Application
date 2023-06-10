package com.example.bextreme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button sign_in_button, register_button;
    private EditText email_text;
    private String entered_email;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //if the sign in is correct -> then
        sign_in_button = (Button) findViewById(R.id.sign_in_button);
        register_button = (Button) findViewById(R.id.register_butt);
        email_text = (EditText) findViewById(R.id.email);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_app_home_page();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_registration_page();
            }
        });
    }

    private void open_app_home_page() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    private void open_registration_page() {
        Intent intent = new Intent(this,Sign_Up_Activity.class);
        startActivity(intent);
    }
}