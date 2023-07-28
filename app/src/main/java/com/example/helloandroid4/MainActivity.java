package com.example.helloandroid4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    // setting "next" button to a variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClickListener();
    }

    private void buttonClickListener(){
        // identifying next button inside of the UI with the findViewById method
        Button nextButton = findViewById(R.id.portalButton);
        // identifying radioGroup
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        nextButton.setOnClickListener(new View.OnClickListener(){
            // overriding setOnClicklisteners onCLick method
            @Override
            public void onClick(View v){

                int checkRadioButtonId = radioGroup.getCheckedRadioButtonId();
                // checking to see if the user picked a radioButton option before moving forward
                // if the user does not pick a currency, intent will not be created
                if(checkRadioButtonId != -1){
                    // setting the currency the user chose to a variable to send it over an Intent
                    RadioButton checkedRadioButton = findViewById(checkRadioButtonId);
                    String selectedOption = checkedRadioButton.getText().toString();

                    // sending currency selected by user to new intent while also initiating the new intent
                    Intent conversionPage = new Intent(MainActivity.this, SecondActivity.class);
                    conversionPage.putExtra("chosenCurrency", selectedOption);
                    startActivity(conversionPage);

                }

            }
        });
    }


}