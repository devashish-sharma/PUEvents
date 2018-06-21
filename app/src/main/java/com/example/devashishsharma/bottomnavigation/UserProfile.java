package com.example.devashishsharma.bottomnavigation;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    private static final int REQUEST_CAMERA = 1, SELECT_FILE = 0;
    Button b1;
    ImageView camera;
    CircleImageView camera2;
    StorageReference mStorage;
    Uri uriimage;
    String profileImgUrl;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    Register_Activity register_activity;
    String email, fname, mobile, gen, city, uni, pass, con_pass, img;
    EditText emailid, fullname, mobileno, district, university, password, confirm_password;
    Spinner gender;
    String[] sgen = new String[]{
            "Select Gender",
            "Male",
            "Female",
            "Other"
    };
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        b1 = (Button) findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveinfo();
                saveUserInformation();
                finish();
            }
        });
        camera2 = (CircleImageView) findViewById(R.id.camera2);
        camera = (ImageView) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser().getUid());
        register_activity = new Register_Activity();

        emailid = (EditText) findViewById(R.id.e1);
        fullname = (EditText) findViewById(R.id.e2);
        mobileno = (EditText) findViewById(R.id.e3);
        district = (EditText) findViewById(R.id.e4);
        university = (EditText) findViewById(R.id.e5);
        password = (EditText) findViewById(R.id.e6);
        confirm_password = (EditText) findViewById(R.id.e7);
        gender = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.gen_spinner, sgen
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.gen_spinner);
        gender.setAdapter(spinnerArrayAdapter);

    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
        builder.setTitle("Insert Image");
        builder.setIcon(R.drawable.pu_event);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (items[i].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[i].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Profile Image :"), SELECT_FILE);
                } else if (items[i].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void saveUserInformation() {
        fname = fullname.getText().toString();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null && profileImgUrl != null && firebaseUser.getDisplayName() != null) {
            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(Uri.parse(profileImgUrl))
                    .setDisplayName(fname)
                    .build();
            ;
            firebaseUser.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    }
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "No changes", Toast.LENGTH_SHORT).show();
                }
            });
        }
        loadUserInformation();

    }


    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(camera2);
            }
            if (user.getDisplayName() != null) {
                fullname.setText(user.getDisplayName());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, UserProfile.class));
        }
    }

    private void saveinfo() {
        String id = databaseReference.push().getKey();
        email = emailid.getText().toString();
        fname = fullname.getText().toString();
        mobile = mobileno.getText().toString();
        gen = gender.getSelectedItem().toString();
        city = district.getText().toString();
        uni = university.getText().toString();
        pass = password.getText().toString();
        con_pass = confirm_password.getText().toString();
        if (fname.isEmpty()) {
            fullname.setError("Enter Full Name");
            fullname.requestFocus();
            return;
        }
        if (uni.isEmpty()) {
            university.setError("Enter University Name");
            university.requestFocus();
            return;
        }
        if (city.isEmpty()) {
            district.setError("Enter City Name");
            district.requestFocus();
            return;
        }
        if (mobile.isEmpty()) {
            mobileno.setError("Enter Mobile Number");
            mobileno.requestFocus();
            return;
        }


        Eventusers data = new Eventusers(id, email, fname, mobile, gen, city, uni, pass, con_pass);
        databaseReference.setValue(data);
        Toast.makeText(this, "Data Send Successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                progressDialog.setMessage("Uploading Image Please Wait for a moment");
                progressDialog.show();
                try {
                    Uri uri = data.getData();
                    camera.setImageURI(uri);
                    StorageReference filepath = mStorage.child("profilepics").child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                uriimage = data.getData();
                camera2.setImageURI(uriimage);
                uploadImagetoFirebaseStorage();

            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Nothing Affected", Toast.LENGTH_SHORT).show();
        }

    }

    private void uploadImagetoFirebaseStorage() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        progressDialog.setMessage("Uploading Image Please Wait for a moment");
        progressDialog.show();
        if (uriimage != null) {
            storageReference.putFile(uriimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                //                @SuppressWarnings("VisibleForTests")
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    profileImgUrl = taskSnapshot.getDownloadUrl().toString();
                    Toast.makeText(getApplicationContext(), "ImageUploaded", Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}