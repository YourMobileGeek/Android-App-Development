package com.example.arianad.alohamusic;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;
    MediaPlayer mpChance, mpKendrick, mpSza;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        button1 = (Button) findViewById(R.id.btnChance);
        button2 = (Button) findViewById(R.id.btnSza);
        button3 = (Button) findViewById(R.id.btnKendrick);

        button1.setOnClickListener(bChance);
        button2.setOnClickListener(bSza);
        button3.setOnClickListener(bKendrick);

        mpChance = new MediaPlayer();
        mpChance = MediaPlayer.create(this, R.raw.chance);

        mpSza = new MediaPlayer();
        mpSza = MediaPlayer.create(this, R.raw.sza);
        playing = 0;

        mpKendrick = new MediaPlayer();
        mpKendrick = MediaPlayer.create(this, R.raw.kendrick);


    }

    Button.OnClickListener bChance= new Button.OnClickListener() {
        @Override
        public void onClick(View v){

            switch(playing) {
                case 0:
                    mpChance.start();
                    playing = 1;
                    button1.setText("Pause Chance Song");
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpChance.pause();
                    playing = 0;
                    button1.setText("Play Chance Song");
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    break;
            }

        }
    };

    Button.OnClickListener bSza= new Button.OnClickListener() {
        @Override
        public void onClick(View v){

            switch(playing) {
                case 0:
                    mpSza.start();
                    playing = 1;
                    button2.setText("Pause SZA Song");
                    button1.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpSza.pause();
                    playing = 0;
                    button2.setText("Play SZA Song");
                    button1.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    break;
            }

        }
    };

    Button.OnClickListener bKendrick= new Button.OnClickListener() {
        @Override
        public void onClick(View v){

            switch(playing) {
                case 0:
                    mpKendrick.start();
                    playing = 1;
                    button3.setText("Pause Kendrick Song");
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpKendrick.pause();
                    playing = 0;
                    button3.setText("Play Kendrick Song");
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    break;
            }

        }
    };
}
