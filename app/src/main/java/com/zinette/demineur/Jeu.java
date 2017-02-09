package com.zinette.demineur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Script;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by yassine on 29/01/2017.
 */
public class Jeu extends Activity{

    private int[]tableau1 = new int[180];
    private int[]tableau2 = new int[180];
    private Intent intent;
    private Bundle bundle;
    private Random rand;
    private TextView compteur,nbr_drapeaux;
    private GridView gridview;
    private ImageView smiley,drapeau;
    private AlertDialog.Builder  builder;
    private Handler mHandler = new Handler();
    private int minutes,secondes,nbr_bombes,time=0,clicktimer=1,click_drapeau=0,drapeaux_restants;
    private Resources resources;
    Initialisation initialisation = new Initialisation();
    DecouvreCase decouvreCase= new DecouvreCase();
    FinPartie finPartie = new FinPartie();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu);

        gridview = (GridView) findViewById(R.id.gridView);
        smiley = (ImageView)findViewById(R.id.bouton_smiley);
        drapeau = (ImageView)findViewById(R.id.drapeau);
        compteur = (TextView) findViewById(R.id.timer);
        nbr_drapeaux = (TextView)findViewById(R.id.drapeaux_restants);
        resources = getResources();
        intent = getIntent();
        bundle = intent.getExtras();

        //NOMBRES DE BOMBES A EVITER
        drapeaux_restants=nbr_bombes=(int)bundle.get("difficulte")*25;

        //INITIALISATION DU GRIDVIEW AVEC TOUTES LES CASES
        tableau1 = initialisation.initialisation_tab1(nbr_bombes, tableau1);
        tableau2 = initialisation.initialisation_tab2(tableau2);
        gridview.setAdapter(new ImageAdapter(this, tableau1, tableau2, 1));

        //NOMBRE DE DRAPEAUX DISPONIBLE EN DEBUT DE PARTIE
        nbr_drapeaux.setText("" + drapeaux_restants);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                //ON DECLENCHE LE COMPTEUR
                if (clicktimer == 1) {
                    mHandler.postDelayed(mUpdateTimeTask, 1000);
                    clicktimer = 0;
                }

                //SI LE JOUEUR CLIQUE SUR UNE CASE CONTENANT UNE BOMBE ET QU'IL SANS POSER DE DRAPEAU DESSUS IL PERD
                if (tableau1[position] == -1 && click_drapeau != 1)
                {
                    tableau1[position] = -4; //LA BOMBE SUR LAQUELLE LE JOUEUR A CLIQUE APPARAIT SUR FOND ROUGE
                    mHandler.removeCallbacks(mUpdateTimeTask); //ON STOP LE COMPTEUR
                    fin_partie(0); //ON INDIQUE AU JOUEUR QU'IL A PERDUE LA PARTIE
                    gridview.setAdapter(new ImageAdapter(getApplicationContext(), tableau1, tableau2, 0)); //ON REINITIALISE LE GRIDVIEW
                    smiley.setImageResource(R.drawable.smiley2);
                }
                else
                {
                    if (click_drapeau == 1 && tableau2[position] == 1 && drapeaux_restants >0) //LE JOUEUR POSE UN DRAPEAU SUR UNE CASE
                    {
                        tableau2[position]=-1;
                        gridview.setAdapter(new ImageAdapter(getApplicationContext(), tableau1, tableau2, 0));
                        drapeaux_restants--;
                        nbr_drapeaux.setText("" + drapeaux_restants);
                    }
                    else if (click_drapeau == 1 && tableau2[position] == -1 && drapeaux_restants < nbr_bombes) //LE JOUEUR RETIRE UN DRAPEAU D'UNE CASE
                    {
                        tableau2[position] = 1;
                        gridview.setAdapter(new ImageAdapter(getApplicationContext(), tableau1, tableau2, 0));
                        drapeaux_restants++;
                        nbr_drapeaux.setText("" + drapeaux_restants);
                    }
                    else
                    {
                        //LE JOUEUR DECOUVRE UNE CASE PAS ENCORE DECOUVERTE ET NE CONTENANT PAS DE DRAPEAU
                        if (tableau2[position] != -1 && click_drapeau != 1 && tableau2[position] == 1)
                        {
                            tableau2[position] = 0;
                            //SI LA CASE DECOUVERTE EST VIDE ON DECOUVRE TOUTES LES CASES VIDES EN LIEN AVEC CELLE-CI
                            if (tableau1[position]==0)tableau2=decouvreCase.decouvre_case_vide(position,tableau1,tableau2);

                            if (finPartie.test_victoire(tableau1,tableau2) == 1)//ON REGARDE SI LE JOUEUR GAGNE LA PARTIE
                            {
                                mHandler.removeCallbacks(mUpdateTimeTask); //ON STOP LE COMPTEUR
                                fin_partie(1);//ON INDIQUE AU JOUEUR QU'IL A GAGNE LA PARTIE
                                gridview.setAdapter(new ImageAdapter(getApplicationContext(), tableau1, tableau2, 0));
                            }
                            else gridview.setAdapter(new ImageAdapter(getApplicationContext(), tableau1, tableau2, 0));
                        }
                    }
                }
            }
    });

        smiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message_smiley();
            }
        });

        drapeau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click_drapeau == 0) //LORSQUE LE JOUEUR VEUT POSER DES DRAPEAUX
                {
                    click_drapeau = 1;
                    nbr_drapeaux.setTextColor(Color.GREEN);
                }
                else //LORSQUE LE JOUEUR NE VEUT PLUS POSER DE DRAPEAUX
                {
                    click_drapeau = 0;
                    nbr_drapeaux.setTextColor(Color.RED);
                }
            }
        });
    }



    //LE COMPTEUR EST APPELE TOUTES LES SECONDES
    private Runnable mUpdateTimeTask = new Runnable()
    {

        public void run()
        {
            time+=1;
            minutes = time / 60 %60;
            secondes  = time % 60;

            if(secondes < 10 && minutes<10)
            {
                compteur.setText("0"+minutes+":0"+secondes );
            }
            else if(secondes >= 10 && minutes<10)
            {
                compteur.setText("0"+minutes+":"+secondes );
            }
            else if(secondes < 10 && minutes>=10)
            {
                compteur.setText(""+minutes+":0"+secondes );
            }
            else
            {
                compteur.setText(""+minutes+":"+secondes );
            }

            mHandler.postDelayed(this, 1000);
        }
    };


    public void fin_partie(int n)
    {
        int i;

        //ON DECOUVRE TOUTES LES CASES NON DECOUVERTES SAUF CELLE CONTENANT UN DRAPEAU
        for(i=0;i<180;i++)
        {
            if(tableau2[i] != -1)tableau2[i]=0; //SI IL N'Y A PAS DE DRAPEAU ON DECOUVRE LA CASE
            else
            {
                if(tableau1[i]==-1)tableau1[i]=-2; // INDIQUE AU JOUEUR QUE SON DRAPEAU ETAIT BIEN PLACE
                else tableau1[i]=-3; //INDIQUE AU JOUEUR QUE SON DRAPEAU ETAIT MAL PLACE
            }
        }

        builder=new AlertDialog.Builder(this);
        if(n==1) //SI LE JOUEUR GAGNE LA PARTIE
        {
            builder.setMessage(resources.getString(R.string.victoire)).setPositiveButton(resources.getString(R.string.rejouer), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intent = getIntent();
                    finish();
                    startActivity(intent);
                }

            }).setNegativeButton(resources.getString(R.string.retour_menu), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                    startActivity(intent);
                }

            }).show();
        }
        else //SI LE JOUEUR PERD LA PARTIE
        {
            builder.setMessage(resources.getString(R.string.defaite)).setPositiveButton(resources.getString(R.string.rejouer), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intent = getIntent();
                    finish();
                    startActivity(intent);
                }

            }).setNegativeButton(resources.getString(R.string.retour_menu), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                    startActivity(intent);
                }

            }).show();
        }
    }



    private void message_smiley()
    {
        builder=new AlertDialog.Builder(this);
        builder.setMessage(resources.getString(R.string.choix)).setPositiveButton(resources.getString(R.string.rejouer), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent = getIntent();
                finish();
                startActivity(intent);
            }

        }).setNeutralButton(resources.getString(R.string.difficulte), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent = new Intent(getApplicationContext(), ChoixDifficulte.class);
                finish();
                startActivity(intent);
            }
        }).setNegativeButton(resources.getString(R.string.retour_menu), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
            }

        }).show();

    }

}
