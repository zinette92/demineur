package com.zinette.demineur;

/**
 * Created by yassine on 08/02/2017.
 */
public class FinPartie {

    //ON DETERMINE SI LE JOUEUR A GAGNE LA PARTIE OU PAS
    public int test_victoire(int[] tab1, int[] tab2)
    {
        int i,test=1;

        for(i=0;i<180;i++)
        {
            if((tab1[i]>=0 && tab1[i]<=8 && tab2[i]!=0) || (tab2[i]==-1)) test=0;
        }

        return test;
    }
}
