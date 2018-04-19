package com.techbytecare.kk.healthcareproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.techbytecare.kk.healthcareproject.Model.UserDoctor;

import info.hoang8f.widget.FButton;

public class SignUpDoctor extends AppCompatActivity {

    TextView txtAlready;

    MaterialEditText edtPhone,edtClinic,edtPassword,edtEmail,edtAddress;

    FButton btnRegister;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("UserDoctor");

        txtAlready = findViewById(R.id.already);

        edtPhone = findViewById(R.id.edtPhone);
        edtClinic = findViewById(R.id.edtClinicName);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);

        btnRegister = findViewById(R.id.btnRegister);

        txtAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpDoctor.this,SignInDoctor.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSignUp();
            }
        });
    }

    private void processSignUp() {
        final ProgressDialog dialog = new ProgressDialog(SignUpDoctor.this);
        dialog.setTitle("Doctor Sign-UP");
        dialog.setMessage("Please wait! while we Register Your Account!!");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        final UserDoctor userDoctor = new UserDoctor(edtPhone.getText().toString(),
                edtClinic.getText().toString(),
                edtEmail.getText().toString(),
                edtAddress.getText().toString(),
                edtPassword.getText().toString());

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (edtPhone.getText().toString().isEmpty()) {

                    dialog.dismiss();
                    Toast.makeText(SignUpDoctor.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtClinic.getText().toString().isEmpty()) {

                    dialog.dismiss();
                    Toast.makeText(SignUpDoctor.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtEmail.getText().toString().isEmpty()) {

                    dialog.dismiss();
                    Toast.makeText(SignUpDoctor.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtAddress.getText().toString().isEmpty()) {

                    dialog.dismiss();
                    Toast.makeText(SignUpDoctor.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtPassword.getText().toString().isEmpty()) {

                    dialog.dismiss();
                    Toast.makeText(SignUpDoctor.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else    {
                    if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                        dialog.dismiss();
                        Toast.makeText(SignUpDoctor.this, "Already Registered!!!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dialog.dismiss();
                        users.child(userDoctor.getPhone()).setValue(userDoctor);
                        Toast.makeText(SignUpDoctor.this, "Registration SuccessFull!!!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
