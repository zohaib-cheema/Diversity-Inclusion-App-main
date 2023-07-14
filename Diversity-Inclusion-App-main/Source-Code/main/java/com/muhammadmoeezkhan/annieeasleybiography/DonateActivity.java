package com.muhammadmoeezkhan.annieeasleybiography;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.muhammadmoeezkhan.annieeasleybiography.databinding.ActivityDonateBinding;

public class DonateActivity extends AppCompatActivity implements View.OnClickListener {

    // Global Static Variables
    public static final int CARD_ENTRY_LIMIT = 19;
    public static final int PHONE_NUMBER_LIMIT = 8;
    public static final int CVC_LIMIT = 3;

    // Static Intent Extra Variables
    public static final String EXTRA_FULL_NAME = "com.muhammadmoeezkhan.annieeasleybiography.EXTRA_FULL_NAME";
    public static final String EXTRA_PHONE_NUMBER = "com.muhammadmoeezkhan.annieeasleybiography.EXTRA_PHONE_NUMBER";
    public static final String EXTRA_CREDIT_CARD_NUMBER = "com.muhammadmoeezkhan.annieeasleybiography.EXTRA_CREDIT_CARD_NUMBER";
    public static final String EXTRA_CVC_NUMBER = "com.muhammadmoeezkhan.annieeasleybiography.EXTRA_CVC_NUMBER";
    public static final String EXTRA_AMOUNT = "com.muhammadmoeezkhan.annieeasleybiography.EXTRA_AMOUNT";
    public static final String EXTRA_RECEIVE_RECEIPT = "com.muhammadmoeezkhan.annieeasleybiography.EXTRA_RECEIVE_RECEIPT";

    // Member Variables
    ActivityDonateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonDonate.setOnClickListener(this);
    }

    // Clicking on the Donate Button - Done
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.button_donate):{
                String fullName = binding.editTextFullName.getText().toString();
                String phoneNumber = binding.editTextPhoneNumber.getText().toString();
                String creditCardNumber = binding.editTextCreditCardNumber.getText().toString();
                int cvc = Integer.valueOf(binding.editTextCvcNumber.getText().toString());
                double amount = Double.valueOf(binding.editTextAmount.getText().toString());
                boolean receiveReceipt = binding.switchReceiveReceipt.isChecked();

                // If fields filled
                boolean allFieldsFilled = true;
                if(fullName==null || phoneNumber.length()<PHONE_NUMBER_LIMIT || String.valueOf(cvc).length()<CVC_LIMIT || String.valueOf(amount)==null){
                    allFieldsFilled = false;
                }
                // If Credit Card Valid
                int cardEntryLength = creditCardNumber.length();
                boolean validCardEntry = false;
                if(cardEntryLength == CARD_ENTRY_LIMIT){
                    int counter=0;
                    for(int i=0; i<cardEntryLength ;i++){
                        if(creditCardNumber.charAt(i)=='-'){
                            counter++;
                        }
                    }
                    if(counter==3){
                        for(int i=4; i<cardEntryLength; i=i+5){
                            if(creditCardNumber.charAt(i)=='-'){
                                validCardEntry=true;
                            }
                            else{
                                validCardEntry=false;
                            }
                        }
                    }
                }
                // Intent
                if(validCardEntry && allFieldsFilled) {
                    Intent returnIntent = new Intent();

                    returnIntent.putExtra(EXTRA_FULL_NAME, fullName);
                    returnIntent.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);
                    returnIntent.putExtra(EXTRA_CREDIT_CARD_NUMBER, creditCardNumber);
                    returnIntent.putExtra(EXTRA_CVC_NUMBER, cvc);
                    returnIntent.putExtra(EXTRA_AMOUNT, amount);
                    returnIntent.putExtra(EXTRA_RECEIVE_RECEIPT, receiveReceipt);

                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                // AlertDialog
                else if(validCardEntry==false){
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
                    myBuilder.setTitle(R.string.alertDialogError)
                            .setMessage(R.string.alertDialogMessage)
                            .setNeutralButton(R.string.alertDialogButton, new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogClicked, int buttonClicked){
                                    dialogClicked.cancel();
                                }
                            });
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();
                    break;
                }
            }
        }
    }
}