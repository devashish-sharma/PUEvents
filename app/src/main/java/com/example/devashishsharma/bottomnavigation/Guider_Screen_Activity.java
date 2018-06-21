package com.example.devashishsharma.bottomnavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Guider_Screen_Activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView[] dotlin;
    private int[] layouts;
    private Button btn_next, btn_skip;
    private MyGuiderAdapter myGuiderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (!isFirstTimeStartApp()) {
            startMainactivity();
            finish();
        } else {
            if (mAuth.getCurrentUser() != null) {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }
        }
        setContentView(R.layout.activity_guider__screen_);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        linearLayout = (LinearLayout) findViewById(R.id.dotlayout);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_skip = (Button) findViewById(R.id.btn_skip);

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), Register_Activity.class));
                    finish();
                }
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentpage = viewPager.getCurrentItem() + 1;
                if (currentpage < layouts.length) {
                    viewPager.setCurrentItem(currentpage);
                } else {
                    if (mAuth.getCurrentUser() != null) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), Register_Activity.class));
                        finish();
                    }
                }
            }
        });


        layouts = new int[]{R.layout.slide1, R.layout.slide2, R.layout.slide3, R.layout.slide4};
        myGuiderAdapter = new MyGuiderAdapter(layouts, getApplicationContext());
        viewPager.setAdapter(myGuiderAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == layouts.length - 1) {
                    btn_next.setText("START");
                    btn_skip.setVisibility(View.GONE);
                } else {
                    btn_next.setText("NEXT");
                    btn_skip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);

    }

    private boolean isFirstTimeStartApp() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("IntroGuider", 0);
        return sharedPreferences.getBoolean("firsttimestartflag", true);
    }

    private void setFirstTimeStartApp(boolean start) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("IntroGuider", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firsttimestartflag", start);
        editor.commit();
    }

    private void setDotStatus(int page) {
        linearLayout.removeAllViews();
        dotlin = new TextView[layouts.length];
        for (int i = 0; i < dotlin.length; i++) {
            dotlin[i] = new TextView(this);
            dotlin[i].setText(Html.fromHtml("&#8226;"));
            dotlin[i].setTextSize(40);
            dotlin[i].setTextColor(Color.parseColor("#000000"));
            linearLayout.addView(dotlin[i]);
        }

        if (dotlin.length > 0) {
            dotlin[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void startMainactivity() {
        setFirstTimeStartApp(true);
        startActivity(new Intent(Guider_Screen_Activity.this, Register_Activity.class));
        finish();
    }
}