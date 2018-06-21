package com.example.devashishsharma.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GridEventFragment extends Fragment {

    String[] text = {
            "Lakshya Annual Fest",
            "Enchantment Day",
            "Department Day",
            "TechFest",
            "Manthan",
            "About PU"};
    int[] img = {
            R.drawable.lak,
            R.drawable.enchant,
            R.drawable.dep,
            R.drawable.techfest,
            R.drawable.debate,
            R.drawable.pu};
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_grid_event, container, false);

        GridView grid = (GridView) view.findViewById(R.id.gridviews);
        GridCustomAdapter gridCustomAdapter = new GridCustomAdapter(getActivity(), text, img);
        grid.setAdapter(gridCustomAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(getActivity(), TabLakshyaActivity.class));
                    Toast.makeText(getActivity(), "You Clicked at " + text[+position], Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    startActivity(new Intent(getActivity(), TechFestActivity.class));
                    Toast.makeText(getActivity(), "You Clicked at " + text[+position], Toast.LENGTH_SHORT).show();
                } else if (position == 2) {
                    Toast.makeText(getActivity(), "You Clicked at " + text[+position], Toast.LENGTH_SHORT).show();
                } else if (position == 3) {
                    Toast.makeText(getActivity(), "You Clicked at " + text[+position], Toast.LENGTH_SHORT).show();
                } else if (position == 4) {
                    Toast.makeText(getActivity(), "You Clicked at " + text[+position], Toast.LENGTH_SHORT).show();
                } else if (position == 5) {
                    Toast.makeText(getActivity(), "You Clicked at " + text[+position], Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
