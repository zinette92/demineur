package com.zinette.demineur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    Intent jouer;
    Resources resources;
    AlertDialog.Builder  builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_jouer = (Button) findViewById(R.id.button_jouer);
        Button button_regles = (Button) findViewById(R.id.button_regles);
        resources = getResources();
        builder = new AlertDialog.Builder(this);

        button_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jouer = new Intent(getApplicationContext(), ChoixDifficulte.class);
                startActivity(jouer);
                /*Intent intent = getIntent();
                finish();
                startActivity(intent);*/
            }
        });

        button_regles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               builder.setMessage(resources.getString(R.string.regles)).setPositiveButton(("Ok"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                }).show();
            }

            });
    }

}