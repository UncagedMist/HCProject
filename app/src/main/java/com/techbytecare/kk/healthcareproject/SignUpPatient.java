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
import com.techbytecare.kk.healthcareproject.Model.UserPatient;

import info.hoang8f.widget.FButton;

public class SignUpPatient extends AppCompatActivity {

    TextView txtAlready;

    MaterialEditText edtPhone,edtName,edtPassword,edtEmail,edtAge,edtBloodGp;

    FButton btnRegister;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_patient);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("UserPatient");

        txtAlready = findViewById(R.id.already);

        edtPhone = findViewById(R.id.edtPhone);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtAge = findViewById(R.id.edtAge);
        edtBloodGp = findViewById(R.id.edtBlood);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSignUp();
            }
        });

        txtAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPatient.this,SignInPatient.class));
                finish();
            }
        });
    }

    private void processSignUp() {
        final ProgressDialog dialog = new ProgressDialog(SignUpPatient.this);
        dialog.setTitle("User Sign-UP");
        dialog.setMessage("Please wait! while we Register Your Account!!");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        final UserPatient userPatient = new UserPatient(edtPhone.getText().toString(),
                edtName.getText().toString(),
                edtAge.getText().toString(),
                edtEmail.getText().toString(),
                edtBloodGp.getText().toString(),
                edtPassword.getText().toString());

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (edtPhone.getText().toString().isEmpty()) {

                    dialog.dismiss();
                    Toast.makeText(SignUpPatient.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtName.getText().toString().isEmpty())  {

                    dialog.dismiss();
                    Toast.makeText(SignUpPatient.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtAge.getText().toString().isEmpty())  {

                    dialog.dismiss();
                    Toast.makeText(SignUpPatient.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtEmail.getText().toString().isEmpty())  {

                    dialog.dismiss();
                    Toast.makeText(SignUpPatient.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtBloodGp.getText().toString().isEmpty())  {

                    dialog.dismiss();
                    Toast.makeText(SignUpPatient.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else if (edtPassword.getText().toString().isEmpty())  {

                    dialog.dismiss();
                    Toast.makeText(SignUpPatient.this, "Kindly fill all the details!!!", Toast.LENGTH_SHORT).show();
                }
                else    {
                    if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                        dialog.dismiss();
                        Toast.makeText(SignUpPatient.this, "Already Registered!!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        dialog.dismiss();
                        users.child(userPatient.getPhone()).setValue(userPatient);
                        Toast.makeText(SignUpPatient.this, "Registration SuccessFull!!!", Toast.LENGTH_SHORT).show();
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
