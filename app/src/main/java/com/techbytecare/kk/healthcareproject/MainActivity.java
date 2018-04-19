package com.techbytecare.kk.healthcareproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techbytecare.kk.healthcareproject.Common.Common;
import com.techbytecare.kk.healthcareproject.Model.UserDoctor;
import com.techbytecare.kk.healthcareproject.Model.UserPatient;

import info.hoang8f.widget.FButton;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {



    FButton btnLogin,btnRegister;

    FirebaseDatabase database;
    DatabaseReference usersPat;
    DatabaseReference usersDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        usersDoc = database.getReference("UserDoctor");
        usersPat = database.getReference("UserPatient");

        Paper.init(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("LOG-IN AS");

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View log_reg = inflater.inflate(R.layout.log_or_reg,null);

                FButton btnDoc = log_reg.findViewById(R.id.btnDoc);
                FButton btnPatient = log_reg.findViewById(R.id.btnPatient);

                builder.setIcon(R.drawable.ic_person_black_24dp);
                builder.setView(log_reg);

                btnDoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,SignInDoctor.class));
                    }
                });

                btnPatient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,SignInPatient.class));
                    }
                });
                builder.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("REGISTER AS");

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View log_reg = inflater.inflate(R.layout.log_or_reg,null);

                FButton btnDoc = log_reg.findViewById(R.id.btnDoc);
                FButton btnPatient = log_reg.findViewById(R.id.btnPatient);

                builder.setIcon(R.drawable.ic_person_black_24dp);
                builder.setView(log_reg);

                btnDoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,SignUpDoctor.class));
                    }
                });

                btnPatient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,SignUpPatient.class));
                    }
                });
                builder.show();
            }
        });

        String userPhonePat = Paper.book().read(Common.USER_KEY_PAT);
        String pwdPat = Paper.book().read(Common.PWD_KEY_PAT);

        if (userPhonePat != null && pwdPat != null)    {
            if (!userPhonePat.isEmpty() && !pwdPat.isEmpty())  {
                loginPatient(userPhonePat,pwdPat);
            }
        }

        String userPhoneDoc = Paper.book().read(Common.USER_KEY_DOC);
        String pwdDoc = Paper.book().read(Common.PWD_KEY_DOC);

        if (userPhoneDoc != null && pwdDoc != null)    {
            if (!userPhoneDoc.isEmpty() && !pwdDoc.isEmpty())  {
                loginDoc(userPhoneDoc,pwdDoc);
            }
        }
    }

    private void loginDoc(final String userPhoneDoc, final String pwdDoc) {

        final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setTitle("Automatic LOG-IN");
        mDialog.setMessage("Please wait! while we check your credential!!");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        usersDoc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(userPhoneDoc).exists()) {

                    if (!userPhoneDoc.isEmpty() && !pwdDoc.isEmpty())   {

                        UserDoctor logIn = dataSnapshot.child(userPhoneDoc).getValue(UserDoctor.class);

                        if (logIn.getPassword().equals(pwdDoc))    {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                            Intent homeActivity = new Intent(MainActivity.this,HomeDoctor.class);
                            Common.currentDoctor = logIn;
                            startActivity(homeActivity);
                            finish();
                        }
                        else    {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else    {
                        mDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Please Enter Your Phone", Toast.LENGTH_SHORT).show();
                    }
                }
                else    {
                    mDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "User Doesn't Exist in Database!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loginPatient(final String userPhonePat, final String pwdPat) {

        final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setTitle("Automatic LOG-IN");
        mDialog.setMessage("Please wait! while we check your credential!!");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        usersPat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userPhonePat).exists()) {

                    if (!userPhonePat.isEmpty() && !pwdPat.isEmpty())   {

                        UserPatient logIn = dataSnapshot.child(userPhonePat).getValue(UserPatient.class);

                        if (logIn.getPassword().equals(pwdPat))    {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                            Intent homeActivity = new Intent(MainActivity.this,HomePatient.class);
                            Common.currentPatient = logIn;
                            startActivity(homeActivity);
                            finish();
                        }
                        else    {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else    {
                        mDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Please Enter Your Phone", Toast.LENGTH_SHORT).show();
                    }
                }
                else    {
                    mDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "User Doesn't Exist in Database!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
