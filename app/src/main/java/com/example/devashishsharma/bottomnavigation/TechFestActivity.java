package com.example.devashishsharma.bottomnavigation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TechFestActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigation;
    Toolbar toolbar;
    Intent intent;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techfest);
        tablayout = (TabLayout) findViewById(R.id.tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        setupviewpager(viewpager);
        tablayout.setupWithViewPager(viewpager);
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigation = (NavigationView) findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("TECHFEST");
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.pink));
                    toolbar.setTitle("CODEVITA");
                    tablayout.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.pink));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TechFestActivity.this, R.color.pink));
                    }
                } else if (tab.getPosition() == 1) {
                    toolbar.setTitle("QUIZAARD");
                    toolbar.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.sky));
                    tablayout.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.sky));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TechFestActivity.this, R.color.sky));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setTitle("HACKATHON");
                    toolbar.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.toolbar));
                    tablayout.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.toolbar));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TechFestActivity.this, R.color.toolbar));
                    }
                } else if (tab.getPosition() == 3) {
                    toolbar.setTitle("WORKSHOPS");
                    toolbar.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.materialgreen));
                    tablayout.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.materialgreen));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TechFestActivity.this, R.color.materialgreen));
                    }
                } else if (tab.getPosition() == 4) {
                    toolbar.setTitle("WEBINARS");
                    toolbar.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.deeppurple));
                    tablayout.setBackgroundColor(ContextCompat.getColor(TechFestActivity.this, R.color.deeppurple));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TechFestActivity.this, R.color.deeppurple));
                    }
                }
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //for removing the color of first icon when switched to next tab
                tablayout.getTabAt(0).getIcon().clearColorFilter();
                tablayout.getTabAt(1).getIcon().clearColorFilter();
                tablayout.getTabAt(2).getIcon().clearColorFilter();
                tablayout.getTabAt(3).getIcon().clearColorFilter();
                //for other tabs
                tab.getIcon().clearColorFilter();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setuptabicons();

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        finish();
                        intent = new Intent(TechFestActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(TechFestActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        finish();
                        intent = new Intent(TechFestActivity.this, HomeScreen.class);
                        startActivity(intent);
                        Toast.makeText(TechFestActivity.this, "Courses", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        finish();
                        intent = new Intent(TechFestActivity.this, TechFestActivity.class);
                        startActivity(intent);
                        Toast.makeText(TechFestActivity.this, "PU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        finish();
                        intent = new Intent(TechFestActivity.this, LoginPage.class);
                        startActivity(intent);
                        Toast.makeText(TechFestActivity.this, "News", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item5:
                        finish();
                        intent = new Intent(TechFestActivity.this, TabLakshyaActivity.class);
                        startActivity(intent);
                        Toast.makeText(TechFestActivity.this, "News", Toast.LENGTH_SHORT).show();
                        break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setuptabicons() {
        tablayout.getTabAt(0).setIcon(R.drawable.club);
        tablayout.getTabAt(1).setIcon(R.drawable.technical);
        tablayout.getTabAt(2).setIcon(R.drawable.sports);
        tablayout.getTabAt(3).setIcon(R.drawable.culturl);
        tablayout.getTabAt(4).setIcon(R.drawable.club);

    }

    private void setupviewpager(ViewPager viewpager) {
        ViewPagerAdapter viewpagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewpagerAdapter.addFragment(new codevita(), "Code Vita");
        viewpagerAdapter.addFragment(new Quiz(), "Quiz competition: ");
        viewpagerAdapter.addFragment(new Hackathon(), "Hackathon");
        viewpagerAdapter.addFragment(new Workshops(), "Work Shops & Seminars");
        viewpagerAdapter.addFragment(new Webinar(), "Webinar session");
        viewpager.setAdapter(viewpagerAdapter);

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentListTitle = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentListTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentListTitle.get(position);
        }

        public void addFragment(Fragment fragment, String Title) {
            fragmentList.add(fragment);
            fragmentListTitle.add(Title);
        }

    }
}

