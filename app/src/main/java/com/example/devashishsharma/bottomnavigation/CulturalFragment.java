package com.example.devashishsharma.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class CulturalFragment extends ListFragment {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter simpleAdapter;
    Intent intent;
    private String[] cultural = {
            "Group Dance", "Nukkad Natak", "Singing(Duet)", "War of DJ(Inter)", "Group Dance(Inter)", "Singing(DUET)Inter", "Solo" +
            "Dance", "Fashion Show", "Fashionista"
    };
    private int[] images = {
            R.drawable.group, R.drawable.nukkad, R.drawable.sing, R.drawable.dj, R.drawable.group2, R.drawable.solo2,
            R.drawable.solo, R.drawable.fashion, R.drawable.fashion};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HashMap<String, String> map = new HashMap<String, String>();
        setHasOptionsMenu(true);
        for (int i = 0; i < cultural.length; i++) {
            map = new HashMap<String, String>();
            map.put("Cultural", cultural[i]);
            map.put("Image", Integer.toString(images[i]));
            data.add(map);
        }
        String[] from = {"Cultural", "Image"};
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
                        intent = new Intent(view.getContext(), group1.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), nukkad.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), singing.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), dj.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(view.getContext(), group2.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(view.getContext(), singing2.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(view.getContext(), solo.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(view.getContext(), fashionshow.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(view.getContext(), fashioninsta.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                }
                Toast.makeText(getActivity(), data.get(position).get("Sports"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.culture_menu, menu);
    }
}