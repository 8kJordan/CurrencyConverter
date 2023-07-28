package com.example.helloandroid4;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {
    // flags that will prevent the event listeners from coinciding with one anther
    private boolean usdTextChangedByCode = false;
    private boolean foreignTextChangedByCode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        returnButton();
        // receiving all data sent from mainActivity intent
        String receivedData = getIntent().getStringExtra("chosenCurrency");
        TextView foreignCurrency = findViewById(R.id.foreignCurrency);
        foreignCurrency.setText(receivedData);
        // setting both foreign currency and USD edit text fields to a variable for later use
        EditText usdEditText = findViewById(R.id.textFieldUSD);
        EditText foreignEditText = findViewById(R.id.foreignCurrencyField);

        // setting default values for Second Activity loads up
        if(receivedData.equals("YEN"))
            foreignEditText.setText("109.94");
        else if(receivedData.equals("CAD"))
            foreignEditText.setText("1.26");
        else
            foreignEditText.setText("0.85");


        // this event listener will listen for any character changes within the USD edit text field
        // after the user inputs any number, the new currency will be calculated dynamically
        usdEditText.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!usdTextChangedByCode){
                    // setting this flag to true will prevent the event listeners from causing recursion and crashing the app
                    foreignTextChangedByCode = true;
                    // this will set the foreign edit text box to the correct conversion
                    foreignEditText.setText(conversionToForeign(receivedData, String.valueOf(usdEditText.getText())));
                    // flipping the flag back to false so the event listeners can proceed
                    foreignTextChangedByCode = false;
                }
            }

        });
            // this event listener is the same concept as the one above except reversed
            // when the user makes any adjustments to foreign currency edit text field
            // the USD conversion will update dynamically without the need for buttons
            foreignEditText.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!foreignTextChangedByCode){
                    // setting this flag to true will prevent the event listeners from causing recursion and crashing the app
                    usdTextChangedByCode = true;
                    // this will set the foreign edit text box to the correct conversion
                    usdEditText.setText(conversionToUsd(receivedData, String.valueOf(foreignEditText.getText())));
                    // flipping the flag back to false so the event listeners can proceed
                    usdTextChangedByCode = false;
                }

            }

        });

    }

    // when return button is clicked,current view will be closed returning to Main Activity
    private void returnButton(){
        Button returnBttn = findViewById(R.id.returnButton);
        returnBttn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                finish();

            }
        });
    }

    // method that will convert any USD value to a foreign currency
    private String conversionToForeign(String currencyType, String usd){
        // try and catch statements to prevent any number format exceptions
        try{
            // parsing USD value to int to perform operations
            double usdInt = Double.parseDouble(usd);

            // conditional block that checks what currency the user would like convert and performs operation
            if(currencyType.equals("YEN"))
                return Double.toString(109.94*usdInt);
            else if(currencyType.equals("CAD"))
                return Double.toString(1.26*usdInt);
            else
                return Double.toString(0.85*usdInt);

        }catch(NumberFormatException e){
            // if the user has noting typed in, this method will return an empty string to display
            e.printStackTrace();
            return "";
        }

    }
    // method that will foreign currency value into USD
    private String conversionToUsd(String currencyType, String currency){

        try{
            double currencyInt = Double.parseDouble(currency);
            if(currencyType.equals("YEN"))
                return Double.toString(currencyInt/109.94);
            else if(currencyType.equals("CAD"))
                return Double.toString(currencyInt/1.26);
            else
                return Double.toString(currencyInt/0.85);

        }catch(NumberFormatException e){
            e.printStackTrace();
            return"";
        }
    }
}