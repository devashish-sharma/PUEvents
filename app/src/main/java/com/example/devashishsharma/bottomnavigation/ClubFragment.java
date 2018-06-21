package com.example.devashishsharma.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ClubFragment extends ListFragment {
    String[] club = {
            "Short Film", "Photography", "Photography(Inter)", "JAM", "Clay Modelling", "MASQUERADE", "MIND STORM", "Tattoo with Mehndi", "Comic Writing",
            "IPL Auction", "SCAVENGER HUNT", "KAVITA", "T-Shirt Design", "B-Plan", "Angry Bird", "Bob the Builder"
    };
    int[] images = {R.drawable.shortfilm, R.drawable.photography, R.drawable.photography2, R.drawable.jam, R.drawable.claymodel, R.drawable.m,
            R.drawable.q, R.drawable.tattoo, R.drawable.comic, R.drawable.iplauction, R.drawable.scavangerhunt, R.drawable.poetry,
            R.drawable.tshirt, R.drawable.bplan, R.drawable.angrybird, R.drawable.bob,
    };
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter simpleAdapter;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //map
        HashMap<String, String> map = new HashMap<String, String>();

        //fill
        for (int i = 0; i < club.length; i++) {
            map = new HashMap<String, String>();
            map.put("Club", club[i]);
            map.put("Image", Integer.toString(images[i]));
            data.add(map);
        }
        String[] from = {"Club", "Image"};
        int[] to = {R.id.club, R.id.image};
        simpleAdapter = new SimpleAdapter(getActivity(), data, R.layout.clublist, from, to);
        setListAdapter(simpleAdapter);
        setHasOptionsMenu(true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(view.getContext(), ShortFilm.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), Photography_inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.club_menu, menu);
    }
}