package com.example.arianad.currencyconverter;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double conversionEuroRate = 0.81;
    double conversionCanadaRate = 1.27;
    double conversionPesoRate = 18.77;
    double currencyEntered;
    double convertedCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Number of radio groups
        final EditText currency = (EditText) findViewById(R.id.txtCurrency);
        final RadioButton eurosCon = (RadioButton) findViewById(R.id.conEuros);
        final RadioButton pesosCon = (RadioButton) findViewById(R.id.conPesos);
        final RadioButton canadaCon = (RadioButton) findViewById(R.id.conCanada);
        final TextView result = (TextView) findViewById(R.id.txtResult);

        Button convert = (Button)findViewById(R.id.btnConvert);

        convert.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                currencyEntered = Double.parseDouble(currency.getText().toString());
                DecimalFormat euro = new DecimalFormat("€###,###.00");

                if (eurosCon.isChecked( )) {
                    if (currencyEntered <=100000) {
                        convertedCurrency = currencyEntered * conversionEuroRate;
                        result.setText(euro.format(convertedCurrency) + " Euros ");
                    }
                    else {
                        Toast.makeText(MainActivity.this,"US Dollars must be <= 100,000 and > 0",Toast.LENGTH_LONG).show();
                    }
                }

                if (pesosCon.isChecked( )) {
                    if (currencyEntered <=100000) {
                        convertedCurrency = currencyEntered * conversionPesoRate;
                        result.setText(euro.format(convertedCurrency) + " Pesos ");
                    }
                    else {
                        Toast.makeText(MainActivity.this,"US Dollars must be <= 100,000 and > 0",Toast.LENGTH_LONG).show();
                    }
                }

                if (canadaCon.isChecked( )) {
                    if (currencyEntered <=100000) {
                        convertedCurrency = currencyEntered * conversionCanadaRate;
                        result.setText(euro.format(convertedCurrency) + " Canadian ");
                    }
                    else {
                        Toast.makeText(MainActivity.this,"US Dollars must be <= 100,000 and > 0",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}
