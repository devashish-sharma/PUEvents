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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class TechnicalFragment extends ListFragment {
    private String[] technical = {
            "Riddlecon", "Mini Militia", "Counter Strike(Intra)", "Counter Strike(Inter)", "Robo Soccer", "Error 404"
    };
    private int[] images = {R.drawable.riddle, R.drawable.mini_militia, R.drawable.counter_strike, R.drawable.counter_strike,
            R.drawable.robo_soccer, R.drawable.error404
    };
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter simpleAdapter;
    Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HashMap<String, String> map = new HashMap<String, String>();
        setHasOptionsMenu(true);
        for (int i = 0; i < technical.length; i++) {
            map = new HashMap<String, String>();
            map.put("Technical", technical[i]);
            map.put("Image", Integer.toString(images[i]));
            data.add(map);
        }
        String[] from = {"Technical", "Image"};
        int[] to = {R.id.club, R.id.image};
        simpleAdapter = new SimpleAdapter(getActivity(), data, R.layout.clublist, from, to);
        setListAdapter(simpleAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(view.getContext(), Riddlecon.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), Mini_Militia.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), Counter_Strike.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), Counter_Strick_Inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(view.getContext(), Robo_soccer_inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(view.getContext(), Error404.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                }
                Toast.makeText(getActivity(), data.get(position).get("Sports"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tech_menu, menu);
    }
}