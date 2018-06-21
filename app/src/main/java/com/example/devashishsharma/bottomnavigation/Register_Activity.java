package com.example.devashishsharma.bottomnavigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Register_Activity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1, SELECT_FILE = 0;
    private static Animation shakeAnimation;
    EditText emailid, fullname, mobileno, district, university, password, confirm_password;
    Spinner gender;
    Button b1, b2;
    StorageReference mStorage;
    DatabaseReference databaseReference;
    String email, pass, con_pass;
    RadioGroup rg1;
    ImageView imageview2;
    private FirebaseAuth mAuth;
    private LinearLayout register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailid = (EditText) findViewById(R.id.e1);
        fullname = (EditText) findViewById(R.id.e2);
        mobileno = (EditText) findViewById(R.id.e3);
        district = (EditText) findViewById(R.id.e4);
        university = (EditText) findViewById(R.id.e5);
        password = (EditText) findViewById(R.id.e6);
        confirm_password = (EditText) findViewById(R.id.e7);
        gender = (Spinner) findViewById(R.id.spinner);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        imageview2 = (ImageView) findViewById(R.id.imageView2);
        databaseReference = FirebaseDatabase.getInstance().getReference("eventusers ");
        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_Activity.this, LoginPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailid.setText("");
                fullname.setText("");
                mobileno.setText("");
                district.setText("");
                university.setText("");
                password.setText("");
                confirm_password.setText("");
            }
        });
        register = (LinearLayout) findViewById(R.id.register);
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

    }

    private void savedata() {
        String id = databaseReference.push().getKey();
        email = emailid.getText().toString();
        pass = password.getText().toString();
        con_pass = confirm_password.getText().toString();


        if (email.equals("") || pass.equals("")) {
            register.startAnimation(shakeAnimation);
            Toast toast = Toast.makeText(getApplicationContext(), "You are not Registered !!! \n Please Register Yourself", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
        if (email.isEmpty()) {
            emailid.setError("Enter Email Address.");
            emailid.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailid.setError("Enter Valid Email Address");
            emailid.requestFocus();
            return;
        }
        if (pass.isEmpty() || pass.length() < 4 || pass.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            password.requestFocus();
            return;
        }
        if (con_pass.isEmpty() || con_pass.length() < 4 || con_pass.length() > 10 ||
                !con_pass.equals(pass)) {
            confirm_password.setError("Password not Matched from Above Password");
            confirm_password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(Register_Activity.this, UserProfile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already register", Toast.LENGTH_SHORT).show();
                        //intent jump
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}