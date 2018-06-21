package com.example.devashishsharma.bottomnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by DEVASHISH SHARMA on 05-06-2018.
 */

public class GridCustomAdapter extends BaseAdapter {
    private Context context;
    private String[] text;
    private int[] imageid;

    public GridCustomAdapter(Context context, String[] text, int[] imageid) {
        this.context = context;
        this.text = text;
        this.imageid = imageid;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridview;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            gridview = new View(context);
            gridview = layoutInflater.inflate(R.layout.gridlist, null);
            TextView textView = (TextView) gridview.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_image);
            textView.setText(text[position]);
            imageView.setImageResource(imageid[position]);
        } else {
            gridview = (View) convertView;
        }
        return gridview;
    }
}