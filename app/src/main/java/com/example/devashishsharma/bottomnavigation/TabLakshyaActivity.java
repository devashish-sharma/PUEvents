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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TabLakshyaActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigation;
    Intent intent;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    SearchView searchView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_lakshya);
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigation = (NavigationView) findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        toolbar.setTitle("CLUB");
        toolbar.setSubtitle("LAKSHYA EVENT");
        toolbar.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.toolbar));
        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow(); // in Activity's onCreate() for instance
//            w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
        setupviewpager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.pink));
                    toolbar.setTitle("CLUB");
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.pink));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.pink));
                    }
                } else if (tab.getPosition() == 1) {
                    toolbar.setTitle("TECHNICAL");
                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.sky));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.sky));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.sky));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setTitle("SPORTS");
                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.toolbar));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.toolbar));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.toolbar));
                    }
                } else if (tab.getPosition() == 3) {
                    toolbar.setTitle("CULTURAL");
                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.materialgreen));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.materialgreen));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLakshyaActivity.this, R.color.materialgreen));
                    }
                }
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //for removing the color of first icon when switched to next tab
                tabLayout.getTabAt(0).getIcon().clearColorFilter();
                tabLayout.getTabAt(1).getIcon().clearColorFilter();
                tabLayout.getTabAt(2).getIcon().clearColorFilter();
                tabLayout.getTabAt(3).getIcon().clearColorFilter();
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
                        intent = new Intent(TabLakshyaActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(TabLakshyaActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        finish();
                        intent = new Intent(TabLakshyaActivity.this, HomeScreen.class);
                        startActivity(intent);
                        Toast.makeText(TabLakshyaActivity.this, "Courses", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        finish();
                        intent = new Intent(TabLakshyaActivity.this, TabLakshyaActivity.class);
                        startActivity(intent);
                        Toast.makeText(TabLakshyaActivity.this, "PU", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        finish();
                        intent = new Intent(TabLakshyaActivity.this, LoginPage.class);
                        startActivity(intent);
                        Toast.makeText(TabLakshyaActivity.this, "News", Toast.LENGTH_SHORT).show();
                        break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setuptabicons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.club);
        tabLayout.getTabAt(1).setIcon(R.drawable.technical);
        tabLayout.getTabAt(2).setIcon(R.drawable.sports);
        tabLayout.getTabAt(3).setIcon(R.drawable.culturl);

    }

    private void setupviewpager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ClubFragment(), "Club");
        viewPagerAdapter.addFragment(new TechnicalFragment(), "Technical");
        viewPagerAdapter.addFragment(new SportsFragment(), "Sports");
        viewPagerAdapter.addFragment(new CulturalFragment(), "Cultural");
        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search Event");
        mSearchView.setIconifiedByDefault(true);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //
                return true;
            }
        });

        return true;
    }

    //custom view pager code here
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> stringtitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super((fragmentManager));
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            stringtitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return stringtitleList.get(position);
        }
    }
}