package com.example.travelto.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.travelto.Common.LoginSignup.StartUpScreen;
import com.example.travelto.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.travelto.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.travelto.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE=0.7f;
    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    LinearLayout contentView;
    //Drawer menu

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sa dispara partea de sus
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        //trebuie sa fac clasele....la fel ca la featured
        featuredRecycler = findViewById(R.id.featured_recycler);
        //mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        //categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.conent);
        //Menu hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        navigationDrawer();


        //Recycler Views Functions Calss
        featuredRecycler();
        //mostViewedRecycler();
        //categoriesRecycler();


    }

    //normal functions
    //onclick method from xml butonul de plus
    public void callScreenlog(View view){
        startActivity(new Intent(getApplicationContext(), StartUpScreen.class));
    }

    private void navigationDrawer() {

        //navigation drawer
        //because we want to interact with the nav
        navigationView.bringToFront();
        //to click those items
        navigationView.setNavigationItemSelectedListener(this);
        //home by def selected
        navigationView.setCheckedItem(R.id.nav_home);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the draw is visible, we want to close
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();


    }
    //random animation found...its mathematic
    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!

        //To make it transparent use Color.Transparent in side setScrimColor();
        drawerLayout.setScrimColor(getResources().getColor(R.color.design_default_color_secondary_variant));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
//move to horizontal axis
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                //content view -- asign id
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    //what happens when you press back button
    //if the activity in the stack go back to it..search more
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //there is some item selected

        switch (item.getItemId()){
            case R.id.nav_all_categories:
                Intent intent = new Intent(getApplicationContext(),AllCategories.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    private void featuredRecycler() {
        //load only views that are visible to user
        featuredRecycler.setHasFixedSize(true);
        //its orientation vertical
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_1, "Portugal", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_2, "Lets go", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_3, "to the bch", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }

    /*private void mostViewedRecycler() {
        //load only views that are visible to user
        featuredRecycler.setHasFixedSize(true);
        //its orientation vertical
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_1, "Portugal", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_2, "Lets go", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_3, "to the bch", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }
    private void categoriesRecycler() {
        //load only views that are visible to user
        featuredRecycler.setHasFixedSize(true);
        //its orientation vertical
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_1, "Portugal", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_2, "Lets go", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.beach_3, "to the bch", "nnefkjgnrjktbgejkgrnejrbgerjgbkejrbgjkefejbfejrbgejkfergegeg"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }

     */
}