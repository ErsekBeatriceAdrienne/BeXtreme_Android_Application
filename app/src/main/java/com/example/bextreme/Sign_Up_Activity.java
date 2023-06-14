package com.example.bextreme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bextreme.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.nio.charset.CharsetEncoder;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

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

    // dropdown for gender
    private String[] drop_down_gender_options = {"Female","Male"};
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter <String> adapter;

    //fields for data
    private TextInputLayout firstname_field, lastname_field, email_field, password_field, username_field, phone_field, location_field;;

    //activity for map
    private MainActivity user_map = new MainActivity();

    //buttons used
    private Button register_button, clear_all_button, profile_button;

    //profile picture
    private Uri profile_picture_uri;
    private CircleImageView profile_picture;
    private boolean correct_password = false, correct_email = false, correct_phone_number = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        initialize_all();
    }


    /* MAIN */
    private void initialize_all() {
        register_button = new Button(this);
        clear_all_button = new Button(this);
        profile_picture = findViewById(R.id.profile_image);
        profile_button = (Button)findViewById(R.id.profile_bttn);

        //creating the fields in code
        firstname_field = (TextInputLayout) findViewById(R.id.firstname_code);
        lastname_field = (TextInputLayout)findViewById(R.id.lastname_code);
        email_field = (TextInputLayout)findViewById(R.id.email_code);
        password_field = (TextInputLayout)findViewById(R.id.password_code);
        username_field = (TextInputLayout)findViewById(R.id.username_code);
        phone_field = (TextInputLayout)findViewById(R.id.phone_code);
        location_field = (TextInputLayout)findViewById(R.id.location_code);

        //set the button to choose the photo from gallery
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });

        ///dropdown gender
        autoCompleteTextView = findViewById(R.id.gender_code);
        adapter = new ArrayAdapter<>(this,R.layout.gender_dropdown, drop_down_gender_options);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(Sign_Up_Activity.this,item,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //take the photo from the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            profile_picture_uri = data.getData();
            Uri image = data.getData();
            profile_picture.setImageURI(image);
        }
    }

    private boolean valid_firstname() {
        String firstname = firstname_field.getEditText().getText().toString().trim();
        char[] array = firstname.toCharArray();
        for (Character c : array) {
            if (!Character.isDigit(c)) {
                firstname_field.setError("Can't contain numbers");
                return false;
            }
        }
        if (firstname.isEmpty()) {
            firstname_field.setError("Required*");
            return false;
        }
        else {
            firstname_field.setError(null);
            firstname_field.setErrorEnabled(false);
            return true;
        }
    }

    private boolean valid_lastname() {
        String lastname = lastname_field.getEditText().getText().toString().trim();
        char[] array = lastname.toCharArray();
        for (Character c : array) {
            if (!Character.isDigit(c)) {
                firstname_field.setError("Can't contain numbers");
                return false;
            }
        }
        if (lastname.isEmpty()) {
            lastname_field.setError("Required*");
            return false;
        }
        else {
            lastname_field.setError(null);
            lastname_field.setErrorEnabled(false);
            return true;
        }
    }

    private boolean valid_username() {
        String username = username_field.getEditText().getText().toString().trim();
        String spaces = "\\A\\w{1,20}\\z";

        if (user_map.getUsers().containsKey(username)) {
            username_field.setError("Username is already used");
            return false;
        }
        if (username.isEmpty()) {
            username_field.setError("Required*");
            return false;
        }
        else if (username.length() > 20) {
            username_field.setError("Username is too long");
            return false;
        }
        else if (username.matches(spaces)) {
            username_field.setError("Username can't contain spaces");
            return false;
        }
        else {
            username_field.setError(null);
            username_field.setErrorEnabled(false);
            return true;
        }
    }

    private boolean valid_email() {
        String email = email_field.getEditText().getText().toString().trim();
        String checkemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (email.isEmpty()) {
            username_field.setError("Required*");
            return false;
        }
        else if (email.matches(checkemail)) {
            username_field.setError("Invalid email");
            return false;
        }
        else {
            username_field.setError(null);
            username_field.setErrorEnabled(false);
            return true;
        }
    }

    private boolean valid_password() {
        String password = password_field.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            username_field.setError("Required*");
            return false;
        }
        else if (password.matches(PASSWORD_PATTERN.toString())) {
            username_field.setError("Weak password");
            return false;
        }
        else {
            username_field.setError(null);
            username_field.setErrorEnabled(false);
            return true;
        }
    }

    private boolean valid_location() {
        String location = location_field.getEditText().getText().toString().trim();
        char[] array = location.toCharArray();
        for (Character c : array) {
            if (!Character.isDigit(c)) {
                location_field.setError("Can't contain numbers");
                return false;
            }
        }
        return true;
    }

    private boolean valid_phone_number() {
        String phone = phone_field.getEditText().getText().toString().trim();
        if (phone.length() != 10) {
            phone_field.setError("Must be 10 digits");
            return false;
        }
        if (phone.charAt(0) != '0') {
            phone_field.setError("First digit must be 0");
            return false;
        }
        return true;
    }

    private boolean trying_to_register() {
        if (!valid_firstname() | !valid_lastname() | !valid_email() | !valid_password() | !valid_username() | !valid_location() | !valid_phone_number()) return false;


        User user = new User(firstname_field.getEditText().getText().toString().trim(),lastname_field.getEditText().getText().toString().trim(),
                            username_field.getEditText().getText().toString().trim(),phone_field.getEditText().getText().toString().trim(),
                            email_field.getEditText().getText().toString().trim(),password_field.getEditText().getText().toString().trim(),
                            profile_picture_uri);
        if (user_map.getUsers().containsKey(user.getUsername())) return false;
        else {
            user_map.getUsers().put(user.getUsername(),user);
            return true;
        }
    }

    private void registration_is_complete() {
        if (trying_to_register()) {
            open_sign_in_page_again();
        }
        else return;
    }

    private void open_sign_in_page_again() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}