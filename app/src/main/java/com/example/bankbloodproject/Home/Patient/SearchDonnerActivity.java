package com.example.bankbloodproject.Home.Patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankbloodproject.Home.HomeDonnerActivity;
import com.example.bankbloodproject.R;
import com.example.bankbloodproject.model.DonnerDetails;
import com.example.bankbloodproject.model.DonnerModel;
import com.example.bankbloodproject.model.patientModel;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SearchDonnerActivity extends AppCompatActivity {
    Button btnsearchdonner,btnpicker;
    Spinner bloodGroupSpinner;
    TextView showlocation;
    private DatabaseReference mFirebaseDatabase;
    int place_picker_request=1;
    private String latitude,longtude;
    private String mResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donner);
        mFirebaseDatabase =  FirebaseDatabase.getInstance().getReference().child("patient");
        btnsearchdonner=findViewById(R.id.btnsearchdonner);
        bloodGroupSpinner=findViewById(R.id.spinnerblood);


        btnpicker=findViewById(R.id.btn_picker);
        btnpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(SearchDonnerActivity.this),place_picker_request);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        //textview to show the lat and long for donner location
        showlocation=findViewById(R.id.edtlocation);
        //get bloodtype from spinner
        String bloodgroup = bloodGroupSpinner.getSelectedItem().toString();

        btnsearchdonner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get bloodtype from spinner
                String bloodgroup = bloodGroupSpinner.getSelectedItem().toString();

                if (!TextUtils.isEmpty(bloodgroup))
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("patient", MODE_PRIVATE);
                    String key1 = sharedPreferences.getString("key", "null");
                    Log.i("key",key1);

                    try {
                        File f = new File(SearchDonnerActivity.this.getFilesDir().getPath() + "/" + "patient.json");
                        //check whether file exists
                        FileInputStream is = new FileInputStream(f);
                        int size = is.available();
                        byte[] buffer = new byte[size];
                        is.read(buffer);
                        is.close();
                        mResponse = new String(buffer);
                    } catch (IOException e) {
                        Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());

                    }

                    Gson gson=new Gson();
                    patientModel patientModel = gson.fromJson(mResponse, patientModel.class);
                    patientModel.setBloodGroup(bloodgroup);
                    patientModel.setLatidute(latitude);
                    patientModel.setLongtude(longtude);



                    mFirebaseDatabase.child(key1).setValue(patientModel);


                    Toast.makeText(SearchDonnerActivity.this, "Data added....", Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(SearchDonnerActivity.this, DonnerListActivity.class));
                }
                //getdonner


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==place_picker_request)
        {
            if (resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);
                StringBuilder stringBuilder=new StringBuilder();
                latitude = String.valueOf(place.getLatLng().latitude);
                longtude = String.valueOf(place.getLatLng().longitude);
                stringBuilder.append("LATITUDE :");
                stringBuilder.append(latitude);
                stringBuilder.append("\n");
                stringBuilder.append("LONGTUDE :");
                stringBuilder.append(longtude);
                showlocation.setText(stringBuilder.toString());


            }
        }
    }
}
