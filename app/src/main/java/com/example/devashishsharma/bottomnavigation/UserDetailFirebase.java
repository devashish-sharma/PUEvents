package com.example.devashishsharma.bottomnavigation;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDetailFirebase extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    List<Eventusers> datalist;
    String[] sgen = new String[]{
            "Select Gender",
            "Male",
            "Female",
            "Other"
    };
    Button b1;
//    FirebaseUser user;
    String uid;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_firebase);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.listview);
        firebaseAuth = FirebaseAuth.getInstance();
//        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("");

        datalist = new ArrayList<>();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Eventusers data = datalist.get(i);
                updateUser(data.getId(), data.getEmail(), data.getFname(), data.getMobile(), data.getGender(), data.getCity(), data.getUni(), data.getPass(), data.getCon_pass());
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                datalist.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Eventusers eventusers = dataSnapshot1.getValue(Eventusers.class);
                    datalist.add(eventusers);
                }
                EventUsersList adapter = new EventUsersList(UserDetailFirebase.this, datalist);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateUser(final String id, String email, String pass, String con_pass, String fname, String mobile, String city, String gender, String uni) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.activity_user_profile, null);
        builder.setView(view);

        final EditText uemailid = (EditText) view.findViewById(R.id.e1);
        final EditText ufullname = (EditText) view.findViewById(R.id.e2);
        final EditText umobileno = (EditText) view.findViewById(R.id.e3);
        final EditText udistrict = (EditText) view.findViewById(R.id.e4);
        final EditText uuniversity = (EditText) view.findViewById(R.id.e5);
        final EditText upassword = (EditText) view.findViewById(R.id.e6);
        final EditText uconfirm_password = (EditText) view.findViewById(R.id.e7);
        final Spinner ugender = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.gen_spinner, sgen
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.gen_spinner);
        ugender.setAdapter(spinnerArrayAdapter);
        b1 = (Button) view.findViewById(R.id.btn1);
        builder.setTitle("Updating User Profile\n " + email);

        final AlertDialog alertDialoga = builder.create();
        alertDialoga.show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uemail = uemailid.getText().toString();
                String ufname = ufullname.getText().toString();
                String umobile = umobileno.getText().toString();
                String ugen = ugender.getSelectedItem().toString();
                String ucity = udistrict.getText().toString();
                String uuni = uuniversity.getText().toString();
                String upass = upassword.getText().toString();
                String ucon_pass = uconfirm_password.getText().toString();
                if (ufname.isEmpty()) {
                    ufullname.setError("Enter Full Name");
                    ufullname.requestFocus();
                    return;
                }
                if (uuni.isEmpty()) {
                    uuniversity.setError("Enter University Name");
                    uuniversity.requestFocus();
                    return;
                }
                if (ucity.isEmpty()) {
                    udistrict.setError("Enter City Name");
                    udistrict.requestFocus();
                    return;
                }
                if (umobile.isEmpty()) {
                    umobileno.setError("Enter Mobile Number");
                    umobileno.requestFocus();
                    return;
                }
                updateprofile(id, uemail, ufname, umobile, ugen, ucity, uuni, upass, ucon_pass);
                alertDialoga.dismiss();
            }
        });

    }

    private void updateprofile(String id, String email, String fname, String mobile, String gen, String city, String uni, String pass, String con_pass) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(firebaseAuth.getCurrentUser().getUid());
        Eventusers data = new Eventusers(id, email, fname, mobile, gen, city, uni, pass, con_pass);
        databaseReference.setValue(data);
        Toast.makeText(getBaseContext(), "Data value Updated", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}