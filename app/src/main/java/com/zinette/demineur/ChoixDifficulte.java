package com.zinette.demineur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by yassine on 02/02/2017.
 */
public class ChoixDifficulte extends Activity{

    Intent choix;
    Button button_facile, button_normal, button_difficile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixdifficulte);

        button_facile = (Button)findViewById(R.id.facile);
        button_normal = (Button)findViewById(R.id.normal);
        button_difficile = (Button)findViewById(R.id.difficile);

        //MODE FACILE
        button_facile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choix = new Intent(getApplicationContext(),Jeu.class);
                choix.putExtra("difficulte",1);
                startActivity(choix);
            }
        });

        //MODE NORMAL
        button_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choix = new Intent(getApplicationContext(),Jeu.class);
                choix.putExtra("difficulte",2);
                startActivity(choix);
            }
        });

        //MODE DIFFICILE
        button_difficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choix = new Intent(getApplicationContext(),Jeu.class);
                choix.putExtra("difficulte",3);
                startActivity(choix);
            }
        });
    }
}
