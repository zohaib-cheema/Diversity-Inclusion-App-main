package com.muhammadmoeezkhan.annieeasleybiography;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.muhammadmoeezkhan.annieeasleybiography.databinding.ActivityBiographyBinding;

public class BiographyActivity extends AppCompatActivity {

    ActivityBiographyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBiographyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent myIntent = getIntent();
    }
}