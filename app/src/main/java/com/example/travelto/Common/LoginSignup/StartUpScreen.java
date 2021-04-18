package com.example.travelto.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.example.travelto.R;

public class StartUpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sa dispara partea de sus
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_up_screen);
    }
    
    public void callLoginScreen(View view){
        Intent intent = new Intent(getApplicationContext(),Login.class);

        //creating animation
        //animatia care pleaca din butonul login in activity_login
        //in pair
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.login_btn),"transition_login");

        //making transition
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(StartUpScreen.this,pairs);
            startActivity(intent, options.toBundle());
        }
        //daca e mai mica versiunea...doar intra in pagina nu mai face animatia
        else{
            startActivity(intent);
        }

    }

    public void callSignupScreen(View view){
        Intent intent = new Intent(getApplicationContext(),SignUp.class);

        //creating animation
        //animatia care pleaca din butonul login in activity_login
        //in pair
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.sign_btn),"transition_signup");

        //making transition
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(StartUpScreen.this,pairs);
            startActivity(intent, options.toBundle());
        }
        //daca e mai mica versiunea...doar intra in pagina nu mai face animatia
        else{
            startActivity(intent);
        }

    }
    
}