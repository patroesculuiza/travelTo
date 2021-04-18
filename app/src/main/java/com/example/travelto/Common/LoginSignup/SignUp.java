package com.example.travelto.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelto.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    //varriables

    ImageView backBtn;
    Button next, login;
    TextView titleText;

    //get data variables

    TextInputLayout fullName, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sa dispara partea de sus
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //hooks
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_butn);
        login = findViewById(R.id.signup_login_butn);
        titleText = findViewById(R.id.signup_title);

        //hooks for getting data

        fullName = findViewById(R.id.signup_login_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_mail);
        password = findViewById(R.id.signup_passw);


    }

    public void callNextSignupScreen(View view) {

        //e ca un & cred
        if(!validateFullName() | !validateUsername() | !validateEmail() |!validatePassword()){
            return;
        }

        String fullnames =fullName.getEditText().getText().toString();
        String usernames =username.getEditText().getText().toString();
        String emails =email.getEditText().getText().toString();
        String passwords =password.getEditText().getText().toString();

        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);

        intent.putExtra("fullname",fullnames);
        intent.putExtra("username",usernames);
        intent.putExtra("email",emails);
        intent.putExtra("password",passwords);


        //Add transition

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backBtn, "transition_back_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(titleText, "transition_title_text");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    private boolean validateFullName() {
        //trim for spaces
        // get text care e in edittext
        String val = fullName.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            //is a building functions for edit text
            fullName.setError("Field is empty");
            return false;
        } else {
            fullName.setError(null);
            //we remove the extra space that setError gives
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        //trim for spaces
        // get text care e in edittext
        String val = username.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            //is a building functions for edit text
            username.setError("Field is empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Field is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("No spaces are allowed!");
            return false;
        } else {
            username.setError(null);
            //we remove the extra space that setError gives
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        //trim for spaces
        // get text care e in edittext
        String val = password.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                //"(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            //is a building functions for edit text
            password.setError("Field is empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Password should contain 4 letters!");
            return false;
        } else {
            password.setError(null);
            //we remove the extra space that setError gives
            password.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        //trim for spaces
        // get text care e in edittext
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
        if (val.isEmpty()) {
            //is a building functions for edit text
            email.setError("Field is empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            //we remove the extra space that setError gives
            email.setErrorEnabled(false);
            return true;
        }

    }

}