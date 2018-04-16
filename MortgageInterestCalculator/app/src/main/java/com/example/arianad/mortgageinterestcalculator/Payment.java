package com.example.arianad.mortgageinterestcalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_payment);

        TextView monthlyPayment = (TextView)findViewById(R.id.txtMonthlyPayment);
        ImageView image = (ImageView) findViewById(R.id.imgYears);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        int intYears = sharedPref.getInt("key1",0);
        float intLoan = sharedPref.getFloat("key2",0);
        float decInterest = sharedPref.getFloat("key3",0);
        float decMonthlyPayment;


        decMonthlyPayment = (intLoan * (intYears * 12)) - (decInterest);
        DecimalFormat currency = new DecimalFormat ("###,###.##");
        monthlyPayment.setText("Total Interest Paid: $" + currency.format(decMonthlyPayment));

        if (intYears == 10) {
            image.setImageResource(R.drawable.ten);
        }
        else if (intYears == 20) {
            image.setImageResource(R.drawable.twenty);
        }
        else if (intYears == 30) {
            image.setImageResource(R.drawable.thirty);
        }
        else {
            monthlyPayment.setText("Enter 10, 20,or 30 years");
        }

    }
}
