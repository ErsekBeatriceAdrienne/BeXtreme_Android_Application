package com.example.bextreme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bextreme.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Sign_Up_Activity extends AppCompatActivity {
    //create correct password patter
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$");
    /*
    * private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 4 characters
                    "$"); //end of string */

    //fields for data
    private TextInputLayout firstname_field;
    private TextInputEditText lastname_field, email_field, password_field, username_field,
               phone_field, location_field;

    //activity for map
    MainActivity activity = new MainActivity();

    //buttons used
    private Button register_button, clear_all_button, profile_button;
    //profile picture
    private ImageView profile_picture;
    private boolean correct_password = false, correct_email = false, correct_phone_number = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        initialize_all();
    }

    private void initialize_all() {
        register_button = new Button(this);
        clear_all_button = new Button(this);
        profile_picture = findViewById(R.id.profile_image);
        profile_button = (Button)findViewById(R.id.profile_bttn);

        //creating the fields in code
        firstname_field = (TextInputLayout) findViewById(R.id.firstname_code);
        lastname_field = (TextInputEditText)findViewById(R.id.lastname_code);
        email_field = (TextInputEditText)findViewById(R.id.email_code);
        password_field = (TextInputEditText)findViewById(R.id.password_code);
        username_field = (TextInputEditText)findViewById(R.id.username_code);
        phone_field = (TextInputEditText)findViewById(R.id.phone_code);
        location_field = (TextInputEditText)findViewById(R.id.location_code);

        //set the button to choose the photo from gallery
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });
    }

    //take the photo from the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri image = data.getData();
            profile_picture.setImageURI(image);
        }
    }

    private void open_sign_in_page_again() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private User create_user() {
        User user = null;
        if (valid_email() && valid_password()) {
            user.setEmail_address(get_email());
            user.setPassword(get_password());
        }
        return user;
    }

    private String get_email() {
        return email_field.getText().toString().trim();
    }

    private String get_password() {
        return password_field.getText().toString().trim();
    }

    private boolean valid_email() {
        String email = email_field.getText().toString().trim();

        if (email.isEmpty()) {
            email_field.setError("Required*");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_field.setError("Incorrect email");
            return false;
        }
        else {
            email_field.setError(null);
            return true;
        }
    }

    private boolean valid_password() {
        String password = password_field.getText().toString().trim();

        if (password.isEmpty()) {
            password_field.setError("Required*");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            password_field.setError("Password too weak");
            return false;
        }
        else {
            password_field.setError(null);
            return true;
        }
    }
}