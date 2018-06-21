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

public class SportsFragment extends ListFragment {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter simpleAdapter;
    Intent intent;
    private String[] sports = {
            "Box Cricket(Boys)\n Inter and Intra both available", "Box Cricket(Girls)\n Inter and Intra both available",
            "BasketBall(Boys)\n Inter and Intra both available", "BasketBall(Girls)\n Inter and Intra both available",
            "VolleyBall(Boys)\n Inter and Intra both available", "VolleyBall(Girls)\n Inter and Intra both available",
            "Table Tennis(Boys and girls)", "Chess(Boys and Girls)", "FootBall(Only Inter Boys)", "Kabaddi(Only Inter Boys)",
            "Sach Race(Boys and Girls)", "100M Race(Boys and Girls)", "200M Race(Boys and Girls)", "400 M Race(Boy and girls)"
    };
    private int[] images = {
            R.drawable.cricket, R.drawable.cricket, R.drawable.basketball, R.drawable.basketball, R.drawable.volleyball, R.drawable.volleyball,
            R.drawable.tabletennis, R.drawable.chess, R.drawable.football, R.drawable.kabaddi, R.drawable.sackrace, R.drawable.hundred,
            R.drawable.two_hundred, R.drawable.four_hundred
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HashMap<String, String> map = new HashMap<String, String>();
        setHasOptionsMenu(true);
        for (int i = 0; i < sports.length; i++) {
            map = new HashMap<String, String>();
            map.put("Sports", sports[i]);
            map.put("Image", Integer.toString(images[i]));
            data.add(map);
        }
        String[] from = {"Sports", "Image"};
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
                        intent = new Intent(view.getContext(), BoxCricket_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), Boxcricket_Boys_Girls_Inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), BasketBall_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), BasketBall_Boys_Girls_Inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(view.getContext(), VolleyBall_Boys_girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(view.getContext(), VolleyBall_Boys_Girls_Inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(view.getContext(), Table_Tennis_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(view.getContext(), Chess_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(view.getContext(), FootBall_Inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 9:
                        intent = new Intent(view.getContext(), Kabaddi_Boys_Inter.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 10:
                        intent = new Intent(view.getContext(), Sack_Race_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 11:
                        intent = new Intent(view.getContext(), Hundred_M__Race_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 12:
                        intent = new Intent(view.getContext(), Two_Hundred_M_Race_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                    case 13:
                        intent = new Intent(view.getContext(), Four_Hundred_M_Race_Boys_Girls.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;
                }
                Toast.makeText(getActivity(), data.get(position).get("Sports"), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sport_menu, menu);
    }
}