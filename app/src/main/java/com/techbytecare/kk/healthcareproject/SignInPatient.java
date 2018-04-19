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
import com.rey.material.widget.CheckBox;
import com.techbytecare.kk.healthcareproject.Common.Common;
import com.techbytecare.kk.healthcareproject.Model.UserPatient;

import info.hoang8f.widget.FButton;
import io.paperdb.Paper;

public class SignInPatient extends AppCompatActivity {

    TextView txtCreate;

    MaterialEditText edtPhone,edtPassword;

    CheckBox ckbRemember;

    FButton btnLogIn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_patient);

        Paper.init(this);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("UserPatient");

        txtCreate = findViewById(R.id.createOne);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogIn = findViewById(R.id.btnLogin);

        ckbRemember = findViewById(R.id.ckbRemember);

        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInPatient.this,SignUpPatient.class));
                finish();
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(edtPhone.getText().toString(),edtPassword.getText().toString());

                if (ckbRemember.isChecked())    {
                    Paper.book().write(Common.USER_KEY_PAT,edtPhone.getText().toString());
                    Paper.book().write(Common.PWD_KEY_PAT,edtPassword.getText().toString());
                }
            }
        });

    }

    private void signIn(final String userPhone, final String pwd) {
        final ProgressDialog mDialog = new ProgressDialog(SignInPatient.this);
        mDialog.setTitle("User LOG-IN");
        mDialog.setMessage("Please wait! while we check your credential!!");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(userPhone).exists()) {

                    if (!userPhone.isEmpty() && !pwd.isEmpty())   {

                        UserPatient logIn = dataSnapshot.child(userPhone).getValue(UserPatient.class);

                        if (logIn.getPassword().equals(pwd))    {
                            mDialog.dismiss();
                            Toast.makeText(SignInPatient.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                            Intent homeActivity = new Intent(SignInPatient.this,HomePatient.class);
                            Common.currentPatient = logIn;
                            startActivity(homeActivity);
                            finish();
                        }
                        else    {
                            mDialog.dismiss();
                            Toast.makeText(SignInPatient.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else    {
                        mDialog.dismiss();
                        Toast.makeText(SignInPatient.this, "Please Enter Your Phone", Toast.LENGTH_SHORT).show();
                    }
                }
                else    {
                    mDialog.dismiss();
                    Toast.makeText(SignInPatient.this, "User Doesn't Exist in Database!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
