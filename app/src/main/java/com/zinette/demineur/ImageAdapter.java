package com.zinette.demineur;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yassine on 30/01/2017.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    ImageView imageView;
    int init;
    private int[] tab1 = new int[180];
    private int[] tab2 = new int[180];
    DecouvreCase decouvreCase = new DecouvreCase();
    public ImageAdapter(Context c, int[] tableau1,int[] tableau2,int i) {
        tab1 = tableau1;
        tab2 = tableau2;
        init=i;
        context = c;
    }

    public int getCount() {
        return tab1.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {return 0;}

    //CHAQUE IMAGEVIEW REPRESENTE UNE CASE, ON INITIALISE ICI CHAQUE IMAGEVIEW ET ON LUI ATTRIBUE UNE RESSOURCE
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams((int)context.getResources().getDimension(R.dimen.dimension_case_w), (int) context.getResources().getDimension(R.dimen.dimension_case_h)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
        {
            imageView = (ImageView) convertView;
            imageView.setLayoutParams(new GridView.LayoutParams((int)context.getResources().getDimension(R.dimen.dimension_case_w), (int) context.getResources().getDimension(R.dimen.dimension_case_h)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        return decouvreCase.decouvre_image(imageView,init,position,tab1,tab2);
    }


}
