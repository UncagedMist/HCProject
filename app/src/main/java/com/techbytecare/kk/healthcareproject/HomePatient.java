package com.techbytecare.kk.healthcareproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techbytecare.kk.healthcareproject.Common.Common;
import com.techbytecare.kk.healthcareproject.Model.UserPatient;

import info.hoang8f.widget.FButton;
import io.paperdb.Paper;

public class HomePatient extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtName,txtPhone,txtEmail,txtAge,txtBloodGP,txtFullName;

    FButton btnTemp,btnMoist,btnPulse,btnAccelerate,btnLat,btnLng;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Health Management");
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("UserPatient");

        Paper.init(this);

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtAge = findViewById(R.id.txtAge);
        txtBloodGP = findViewById(R.id.txtBlood);

        btnTemp = findViewById(R.id.btnTemp);
        btnMoist = findViewById(R.id.btnMoisture);
        btnPulse = findViewById(R.id.btnPulse);
        btnAccelerate = findViewById(R.id.btnAccelerate);
        btnLat = findViewById(R.id.btnLat);
        btnLng = findViewById(R.id.btnLng);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentPatient.getName());

        getDetails();

        clickOnButton();
    }

    private void clickOnButton() {
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePatient.this,TempActivity.class));
            }
        });

        btnMoist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePatient.this,MoistActivity.class));
            }
        });

        btnPulse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePatient.this,PulseActivity.class));
            }
        });

        btnAccelerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePatient.this,AccelerateActivity.class));
            }
        });
        btnLng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePatient.this,LngActivity.class));
            }
        });

        btnLat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePatient.this,LatActivity.class));
            }
        });
    }

    private void getDetails() {
        txtName.setText(Common.currentPatient.getName());
        txtEmail.setText(Common.currentPatient.getEmail());
        txtBloodGP.setText(Common.currentPatient.getBloodGP());
        txtPhone.setText(Common.currentPatient.getPhone());
        txtAge.setText(Common.currentPatient.getAge());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_patient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CharSequence sequence = "";

        if (item.getItemId() == R.id.menu_search)   {
            loadBloodGp(sequence);
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadBloodGp(CharSequence text) {

        String bloodGp = Common.currentPatient.getBloodGP().toLowerCase();

        if (bloodGp.equals(users.getDatabase().getReference("bloodGP")))    {

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action
        }
        else if (id == R.id.nav_hospital) {
            startActivity(new Intent(HomePatient.this,HospitalActivity.class));
        }
        else if (id == R.id.nav_chat) {

        }
        else if (id == R.id.nav_signOut) {
            Paper.book().destroy();

            Intent signOutIntent = new Intent(HomePatient.this,MainActivity.class);
            signOutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signOutIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
