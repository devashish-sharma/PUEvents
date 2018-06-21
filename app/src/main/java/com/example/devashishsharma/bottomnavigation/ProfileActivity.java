package com.example.devashishsharma.bottomnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    private CircleImageView profilepic;
    private TextView profileemail, profilefname, profilemobile, profilecity, profileuni, profilegen, profilepass;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Button profileupdate, profilepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilepic = (CircleImageView) findViewById(R.id.profileimage);
        profileemail = (TextView) findViewById(R.id.pemail);
        profilefname = (TextView) findViewById(R.id.pfname);
        profilemobile = (TextView) findViewById(R.id.pmobile);
        profilecity = (TextView) findViewById(R.id.pcity);
        profileuni = (TextView) findViewById(R.id.puni);
        profilegen = (TextView) findViewById(R.id.pgender);
        profilepass = (TextView) findViewById(R.id.ppass);
        profilepassword = (Button) findViewById(R.id.updatepassword);
        profileupdate = (Button) findViewById(R.id.updateprofile);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        profilepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdatePassword.class);
                startActivity(intent);
            }
        });
        profileupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
//        loadUserInformation();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {

                    Eventusers eventusers = dataSnapshot.getValue(Eventusers.class);
                    profileemail.setText("Email: " + eventusers.getEmail());
                    profilefname.setText("FullName: " + eventusers.getFname());
                    profilemobile.setText("Mobile No: " + eventusers.getMobile());
                    profilecity.setText("City: " + eventusers.getCity());
                    profileuni.setText("University: " + eventusers.getUni());
                    profilegen.setText("Gender: " + eventusers.getGender());
                    profilepass.setText("Password: " + eventusers.getPass());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadUserInformation();
    }

    private void loadUserInformation() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(profilepic);
            }
        }
    }
}