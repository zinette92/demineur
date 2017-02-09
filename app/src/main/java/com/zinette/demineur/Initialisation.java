package com.zinette.demineur;

import java.util.Random;

/**
 * Created by yassine on 08/02/2017.
 */
public class Initialisation {

    Random rand;

    //INITIALISATION DE TOUTES LES CASES DE LA GRILLE
    public int[] initialisation_tab1(int nbr_bombes, int tab1[])
    {
        int i,ligne,colonne;
        rand = new Random();

        for(i=0;i<nbr_bombes;i++)
        {
            ligne=rand.nextInt(18);
            colonne=rand.nextInt(10);
            if(tab1[ligne*10+colonne]!=-1)tab1[ligne*10+colonne]=-1;
            else i--;
        }
        placement_cases_nombres(tab1);
        return tab1;
    }

    public void placement_cases_nombres(int tab1[])
    {
        int i;

        for(i=0;i<180;i++)
        {
            if(tab1[i]!=-1 && i==0)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else if(tab1[i]!=-1 && i==9)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else if(tab1[i]!=-1 && i==170)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else if(tab1[i]!=-1 && i==179)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else if((i!=0 && i!=170 && tab1[i]!=-1) && i%10==0)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else if((i!=9 && i!=179 && tab1[i]!=-1) && (i+1)%10==0)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else if((i>0 && i<9) && tab1[i]!=-1)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else if((i>170 && i<179) && tab1[i]!=-1)
            {
                tab1[i]=nombres_bombes(i,tab1);
            }
            else
            {
                if(tab1[i]!=-1)tab1[i]=nombres_bombes(i,tab1);
            }
        }
    }

    //ON COMPTE LE NOMBRES DE BOMBES CONNEXES A CHAQUE CASE
    public int nombres_bombes(int num_case, int tab1[])
    {
        int i,l,bombes=0;

        if(num_case==0) //CASE EN HAUT A GAUCHE
        {
            for(i=1;i<12;i++)
            {
                if(tab1[i]==-1)bombes++;
                if(i==1)i+=9;
            }
        }
        else if(num_case==9) //CASE EN HAUT A DROITE
        {
            for(i=8;i<20;i++)
            {
                if(tab1[i]==-1)bombes++;
                if(i==8)i+=9;
            }
        }
        else if(num_case==170) //CASE EN BAS A GAUCHE
        {
            for(i=171;i>159;i--)
            {
                if(tab1[i]==-1)bombes++;
                if(i==171)i-=9;
            }
        }
        else if(num_case==179) //CASE EN BAS A DROITE
        {
            for(i=178;i>167;i--)
            {
                if(tab1[i]==-1)bombes++;
                if(i==178)i-=8;
            }
        }
        else if(num_case%10==0 && (num_case!=0 && num_case!=170)) //CASE LA PREMIERE COLONNE
        {
            for(i=0;i<2;i++)
            {
                l = num_case-10+i;
                while(l<=num_case+10+i)
                {
                    if(l!=num_case)
                    {
                        if(tab1[l]==-1)bombes++;
                    }
                    l+=10;
                }
            }
        }
        else if(((num_case+1)%10==0) && (num_case!=9 && num_case!=179)) //CASE LA DERNIERE COLONNE
        {
            for(i=0;i<2;i++)
            {
                l = num_case-10-i;
                while(l<=num_case+10-i)
                {
                    if(l!=num_case)
                    {
                        if(tab1[l]==-1)bombes++;
                    }
                    l+=10;
                }
            }
        }
        else if(num_case>0 && num_case<9) //CASE LA PREMIERE LIGNE
        {
            for(i=0;i<3;i++)
            {
                l = num_case+(i-1);
                while(l<=num_case+10+(i-1))
                {
                    if(l!=num_case)
                    {
                        if(tab1[l]==-1)bombes++;
                    }
                    l+=10;
                }
            }
        }
        else if(num_case>170 && num_case<179)  //CASE LA DERNIERE LIGNE
        {
            for(i=0;i<3;i++)
            {
                l = num_case+(i-1);
                while(l>=num_case-10+(i-1))
                {
                    if(l!=num_case)
                    {
                        if(tab1[l]==-1)bombes++;
                    }
                    l-=10;
                }
            }
        }
        else //TOUTES LES AUTRES CASES
        {
            for (i=0;i<3;i++) {
                l = num_case - 10 + (i - 1);
                while (l <= num_case + 10 + (i - 1)) {
                    if (l != num_case)
                    {
                        if (tab1[l] == -1) bombes++;
                    }
                    l += 10;
                }
            }
        }
        return bombes;
    }

    //CHAQUE CASE EST COUVERTE EN DEBUT DE PARTIE
    public int[] initialisation_tab2(int value2[])
    {
        int i;
        for (i = 0; i < 180; i++) {
            value2[i] = 1;
        }
        return value2;
    }


}
