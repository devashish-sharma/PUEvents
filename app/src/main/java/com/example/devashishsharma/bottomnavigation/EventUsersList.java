package com.example.devashishsharma.bottomnavigation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DEVASHISH SHARMA on 12-06-2018.
 */

public class EventUsersList extends ArrayAdapter<Eventusers> {
    private Activity context;
    private List<Eventusers> datalist;

    public EventUsersList(Activity context, List<Eventusers> datalist) {
        super(context, R.layout.list_layout, datalist);
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View listviewitem = layoutInflater.inflate(R.layout.list_layout, null, true);

        TextView t1 = (TextView) listviewitem.findViewById(R.id.textView);
        TextView t2 = (TextView) listviewitem.findViewById(R.id.textView2);
        TextView t3 = (TextView) listviewitem.findViewById(R.id.textView3);
        TextView t4 = (TextView) listviewitem.findViewById(R.id.textView4);
        TextView t5 = (TextView) listviewitem.findViewById(R.id.textView5);
        TextView t6 = (TextView) listviewitem.findViewById(R.id.textView6);
        TextView t7 = (TextView) listviewitem.findViewById(R.id.textView7);
        TextView t8 = (TextView) listviewitem.findViewById(R.id.textView8);

        Eventusers data = datalist.get(position);
        t1.setText(data.getEmail());
        t2.setText(data.getPass());
        t3.setText(data.getCon_pass());
        t4.setText(data.getFname());
        t5.setText(data.getMobile());
        t6.setText(data.getGender());
        t7.setText(data.getCon_pass());
        t8.setText(data.getUni());

        return listviewitem;
    }
}
