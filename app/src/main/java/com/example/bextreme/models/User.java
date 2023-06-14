package com.example.bextreme.models;
import android.net.Uri;
import de.hdodenhof.circleimageview.CircleImageView;

public class User {
    private String firstname, lastname, username, email_address, password, phone_number;
    private Uri profile_picture;

    public User(String firstname, String lastname, String username, String phone_number, String email_address, String password, Uri profile_picture) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.password = password;
        this.profile_picture = profile_picture;
    }

    public Uri getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(Uri profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
