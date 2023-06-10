package com.example.bextreme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sign_Up_Activity extends AppCompatActivity {

    //private EditText firstname_field, lastname_field, email_field, password_field, username_field,
               // phone_field, hobbies_field, location_field;
    private Button register_button, clear_all_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        register_button = new Button(this);
        clear_all_button = new Button(this);

        setContentView(R.layout.sign_up);
    }

    private void open_sign_in_page_again() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}