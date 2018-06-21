package com.example.devashishsharma.bottomnavigation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import ru.whalemare.sheetmenu.SheetMenu;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    DrawerLayout drawerLayout;
    NavigationView navigation;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Dialog dialog;
    FirebaseAuth firebaseAuth;
    CircleImageView img;
    TextView headeremail, headername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new GridEventFragment()).commit();
        }

        dialog = new Dialog(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigation = (NavigationView) findViewById(R.id.navigation);
        View hView = navigation.getHeaderView(0);
        img = (CircleImageView) hView.findViewById(R.id.headerimage);
        headername = (TextView) hView.findViewById(R.id.headername);
        headeremail = (TextView) hView.findViewById(R.id.headeremail);
        loadUserInformation();
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //header

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorRed));
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "Search Mode Choose", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "Updating data", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.detail:
                        dialog.setContentView(R.layout.passcode);
                        String items[] = {"dev", "devu", "deva"};
                        final EditText ep = (EditText) dialog.findViewById(R.id.ep);
                        final Button bp3 = (Button) dialog.findViewById(R.id.bp3);
                        bp3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String passcode = ep.getText().toString();
                                if (passcode.equals("0000")) {
                                    intent = new Intent(getApplicationContext(), UserDetailFirebase.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Wrong Pass Code", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        dialog.show();
                        break;

                    case R.id.bottomsheet:
                        showsheet();
                        break;

                    case R.id.exit:
                        finish();
                        Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
//                        intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
//                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new Hackathon()).commit();
                        break;
                    case R.id.item2:
                        intent = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        intent = new Intent(getApplicationContext(), TabLakshyaActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        intent = new Intent(getApplicationContext(), Register_Activity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item5:
                        intent = new Intent(getApplicationContext(), TechFestActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item6:
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        intent = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Your are successfully signed out", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void loadUserInformation() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(img);
            }
            if (user.getDisplayName() != null) {
                headername.setText(user.getDisplayName());
            }
            headeremail.setText(user.getEmail());
        }
    }

    private void showsheet() {
        SheetMenu.with(this)
                .setTitle("Select Options")
                .setMenu(R.menu.reach_menu)
                .setClick(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.callus) {
                            Toast.makeText(getApplicationContext(), "Call Clicked", Toast.LENGTH_SHORT).show();
                        } else if (item.getItemId() == R.id.messageus) {
                            Toast.makeText(getApplicationContext(), "Message Clicked", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                }).show();
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


}