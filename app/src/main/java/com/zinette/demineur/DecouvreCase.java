package com.zinette.demineur;

import android.widget.ImageView;

/**
 * Created by yassine on 08/02/2017.
 */
public class DecouvreCase {

    //LA FONCTION RETOURNE UNE IMAGEVIEW EN FONCTION DE LA CASE SUR LAQUELLE LE JOUEUR A CLIQUER
    public ImageView decouvre_image(ImageView imageView, int init, int position, int[] tab1, int[] tab2) {
        if (init == 1)
        {
            imageView.setImageResource(R.drawable.bouton_grille);
        }
        else
        {
            switch (tab1[position]) {
                case -4:
                    imageView.setImageResource(R.drawable.bombe_touche);
                    break;
                case -3:
                    imageView.setImageResource(R.drawable.drapeau_barre);
                    break;
                case -2:
                    imageView.setImageResource(R.drawable.drapeau);
                    break;
                case -1:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.bombe);
                    break;
                case 0:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case0);
                    break;
                case 1:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case1);
                    break;
                case 2:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case2);
                    break;
                case 3:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case3);
                    break;
                case 4:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case4);
                    break;
                case 5:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case5);
                    break;
                case 6:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case6);
                    break;
                case 7:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case7);
                    break;
                default:
                    if (tab2[position] == 1) imageView.setImageResource(R.drawable.bouton_grille);
                    else if (tab2[position] == -1) imageView.setImageResource(R.drawable.drapeau);
                    else imageView.setImageResource(R.drawable.case8);
                    break;
            }
        }
        return imageView;
    }

    //SI LE JOUEUR CLIQUE SUR UNE CASE VIDE ON DECOUVRE TOUTES LES CASES VIDES CONNEXES
    //ON FERA ATTENTION A CE QUE LA POSITION SOIT BIEN COMPRISE DANS LA GRILLE

    public int[] decouvre_case_vide(int position, int[] tab1, int[] tab2)
    {
        decouvre_haut(position, tab1, tab2);
        decouvre_haut_droite(position, tab1, tab2);
        decouvre_haut_gauche(position, tab1, tab2);
        decouvre_gauche(position, tab1, tab2);
        decouvre_droite(position, tab1, tab2);
        decouvre_bas(position, tab1, tab2);
        decouvre_bas_droite(position, tab1, tab2);
        decouvre_bas_gauche(position, tab1, tab2);

        return tab2;
    }

    public void decouvre_haut(int position, int[] tab1, int[] tab2)
    {
        //LA CASE NE DOIT PAS SE TROUVER EN PREMIERE LIGNE
        if(position-10>=0 && tab1[position-10]==0 && tab2[position-10]==1)
        {
            if(tab2[position-10] != -1)tab2[position-10]=0;
            decouvre_case_vide(position-10, tab1, tab2);
        }
    }

    public void decouvre_haut_droite(int position, int[] tab1, int[] tab2)
    {
        //LA CASE NE DOIT PAS SE TROUVER EN PREMIERE LIGNE ET SUR LA DERNIERE COLONNE
        if(position-9>=0 && (position+1)%10!=0 && tab1[position-9]==0 && tab2[position-9]==1)
        {
            if(tab2[position-9] != -1)tab2[position-9]=0;
            decouvre_case_vide(position-9, tab1, tab2);
        }
    }

    public void decouvre_haut_gauche(int position, int[] tab1, int[] tab2) //LA CASE NE DOIT PAS SE TROUVER EN PREMIERE LIGNE
    {
        //LA CASE NE DOIT PAS SE TROUVER EN PREMIERE LIGNE ET SUR LA PREMIERE COLONNE
        if(position-11>=0 && position%10!=0 && tab1[position-11]==0 && tab2[position-11]==1)
        {
            if(tab2[position-11] != -1)tab2[position-11]=0;
            decouvre_case_vide(position-11, tab1, tab2);
        }
    }

    public void decouvre_droite(int position, int[] tab1, int[] tab2)
    {
        //LA CASE NE DOIT PAS SE TROUVER SUR LA DERNIERE COLONNE
        if(position+1<=179 && (position+1)%10!=0 && tab1[position+1]==0 && tab2[position+1]==1)
        {
            if(tab2[position+1] != -1)tab2[position+1]=0;
            decouvre_case_vide(position+1, tab1, tab2);
        }
    }


    public void decouvre_gauche(int position, int[] tab1, int[] tab2)
    {
        //LA CASE NE DOIT PAS SE TROUVER SUR LA PREMIERE COLONNE
        if(position-1>=0 && position%10!=0 && tab1[position-1]==0 && tab2[position-1]==1)
        {
            if(tab2[position-1] != -1)tab2[position-1]=0;
            decouvre_case_vide(position-1, tab1, tab2);
        }
    }

    public void decouvre_bas(int position, int[] tab1, int[] tab2)
    {
        //LA CASE NE DOIT PAS SE TROUVER SUR LA DERNIERE LIGNE
        if(position+10<=179 && tab1[position+10]==0 && tab2[position+10]==1)
        {
            if(tab2[position+10] != -1)tab2[position+10]=0;
            decouvre_case_vide(position+10, tab1, tab2);
        }
    }


    public void decouvre_bas_droite(int position, int[] tab1, int[] tab2)
    {
        //LA CASE NE DOIT PAS SE TROUVER SUR LA DERNIERE COLONNE ET SUR LA LIGNE DE DROITE
        if(position+11<=179 && (position+1)%10!=0 && tab1[position+11]==0 && tab2[position+11]==1)
        {
            if(tab2[position+11] != -1)tab2[position+11]=0;
            decouvre_case_vide(position+11, tab1, tab2);
        }
    }

    public void decouvre_bas_gauche(int position, int[] tab1, int[] tab2)
    {
        //LA CASE NE DOIT PAS SE TROUVER SUR LA DERNIERE COLONNE ET SUR LA LIGNE DE GAUCHE
        if(position+9<=179 && position%10!=0 && tab1[position+9]==0 && tab2[position+9]==1)
        {
            if(tab2[position+9] != -1)tab2[position+9]=0;
            decouvre_case_vide(position+9, tab1, tab2);
        }
    }




}
