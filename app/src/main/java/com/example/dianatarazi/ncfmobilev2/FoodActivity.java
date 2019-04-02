package com.example.dianatarazi.ncfmobilev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        getSupportFragmentManager().beginTransaction().add(R.id.food_fragment_container,
                new FoodServiceOptions()).commit();

    }
}
