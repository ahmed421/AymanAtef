package com.example.bankbloodproject.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bankbloodproject.Home.Patient.SearchDonnerActivity;
import com.example.bankbloodproject.Home.Patient.SendRequestActivity;
import com.example.bankbloodproject.R;
import com.example.bankbloodproject.auth.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button searchdonnerbtn,sendrequestbtn,logout;
    Spinner spinnerbloodgroup;
    TextView donnertxt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth=FirebaseAuth.getInstance();
        searchdonnerbtn=findViewById(R.id.btnsearchdonner);
        sendrequestbtn=findViewById(R.id.btnsendrequest);

        searchdonnerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          startActivity(new Intent(HomeActivity.this, SearchDonnerActivity.class));

            }
        });
        sendrequestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, SendRequestActivity.class));

            }
        });

        donnertxt=findViewById(R.id.donnertxt);
        donnertxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HomeDonnerActivity.class));

            }
        });


        //log out
        logout=findViewById(R.id.btnlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//                firebaseAuth.signOut();
//                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//                finish();
//
//
//        return super.onOptionsItemSelected(item);
//    }
}
