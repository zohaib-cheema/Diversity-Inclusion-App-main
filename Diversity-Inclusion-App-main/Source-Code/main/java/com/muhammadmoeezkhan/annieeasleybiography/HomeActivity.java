package com.muhammadmoeezkhan.annieeasleybiography;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.muhammadmoeezkhan.annieeasleybiography.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    // Global Static Variables
    public static final int FROM_DONATE_ACTIVITY = 1;
    public static final int CREDIT_CARD_LAST_FOUR_INDEX = 15;

    // Member Variables
    private ActivityHomeBinding binding;

    // Global Variables
    String message;

    // Return From DonateActivity - Done
    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        super.onActivityResult(reqCode, resCode, data);
        if(reqCode == FROM_DONATE_ACTIVITY && resCode == Activity.RESULT_OK){
            String fullName = data.getStringExtra(DonateActivity.EXTRA_FULL_NAME);
            String phoneNumber = data.getStringExtra(DonateActivity.EXTRA_PHONE_NUMBER);
            String creditCardNumber = data.getStringExtra(DonateActivity.EXTRA_CREDIT_CARD_NUMBER);
            int cvcNumber = data.getIntExtra(DonateActivity.EXTRA_CVC_NUMBER,0);
            double amount = data.getDoubleExtra(DonateActivity.EXTRA_AMOUNT, 0.0);
            boolean ifReceiveReceipt = data.getBooleanExtra(DonateActivity.EXTRA_RECEIVE_RECEIPT, false);
            message = "Thank you " + fullName +" for your donation of $ " + amount + " using card number ending in " + creditCardNumber.substring(CREDIT_CARD_LAST_FOUR_INDEX);
            if(ifReceiveReceipt) {
                Intent myIntent = new Intent();
                myIntent.setAction(Intent.ACTION_SENDTO);
                myIntent.setData(Uri.parse("sms:"+phoneNumber+""));
                myIntent.putExtra("sms_body", message);

                if (myIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(myIntent);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewBirthLocation.setOnClickListener(this);
        binding.textViewLocation.setOnClickListener(this);
        binding.buttonBiography.setOnClickListener(this);
        binding.buttonMoreInfo.setOnClickListener(this);
        binding.buttonDonate.setOnClickListener(this);
    }

    // Clicking 2 Locations and 3 Buttons - Done
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.textView_birth_location):{
                Intent myIntent = new Intent();
                myIntent.setAction(Intent.ACTION_VIEW);
                myIntent.setData(Uri.parse("geo:33.5186, -86.8104"));
                startActivity(myIntent);
                break;
            }
            case(R.id.textView_location):{
                Intent myIntent = new Intent();
                myIntent.setAction(Intent.ACTION_VIEW);
                myIntent.setData(Uri.parse("geo:35.7598877, -95.3403549"));
                startActivity(myIntent);
                break;
            }
            case(R.id.button_biography):{
                Intent myIntent = new Intent(this, BiographyActivity.class);
                startActivity(myIntent);
                break;
            }
            case(R.id.button_more_info):{
                Intent myIntent = new Intent();
                myIntent.setAction(Intent.ACTION_VIEW);
                myIntent.setData(Uri.parse("https://en.wikipedia.org/wiki/Annie_Easley"));
                startActivity(myIntent);
                break;
            }
            case(R.id.button_donate):{
                Intent myIntent = new Intent(HomeActivity.this, DonateActivity.class);
                startActivityForResult(myIntent, FROM_DONATE_ACTIVITY);

                break;
            }
        }
    }
}

