package com.example.omeepc.fairpedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpUser extends AppCompatActivity {
    private Button btn_tosignupuser;
    EditText edttxt_toufirstname, edttxt_toulastname, edttxt_touemail, edttxt_toupassword, edttxt_touretypepassword, edttxt_toubirthdate, edttxt_touprofession, edttxt_touzipcode, edttxt_toumobileno;
    RadioGroup radgrp_tougender;
    RadioButton radbtn_tomaleorfemale;
    Spinner spinner_toucountry, spinner_toucity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);

        //spinner
        //final Spinner spinner_toucountry, spinner_toucity;
        // hashmap object containing data of spinner1 as 'keys' with relevant
        // data of spinner2 in List<String> object as 'values'
        final Map<String, List<String>> data = new HashMap<>();
        data.put("Afghanistan", Arrays.asList("Kabul", "Kandahar"));
        data.put("Australia", Arrays.asList("Adelaide", "Canberra", "Perth", "Melbourne", "Sydney"));
        data.put("Bangladesh", Arrays.asList("Dhaka", "Chittagong", "Sylhet", "Khulna", "Comilla", "Rangpur", "Barisal", "Cox Bazar", "Rangamati", "Gazipur"));
        data.put("Brazil", Arrays.asList("Sau Paulo", "Brasilia"));
        data.put("Canada", Arrays.asList("Ottowa", "Toronto"));
        data.put("England", Arrays.asList("London", "New Castle", "Hampshire", "Stoke City", "Chelsea", "Tottenham", "Liverpool", "Manchester", "Sussex", "Buckingham"));
        data.put("France", Arrays.asList("Paris", "Lyon", "Lille"));
        data.put("Germany", Arrays.asList("Berlin", "Stuttgart", "Dortmund", "Munich", "Leipzig"));
        data.put("India", Arrays.asList("Kolkata", "Bangalore", "Asam", "Pune", "Mumbai"));
        data.put("Italy", Arrays.asList("Rome", "Milan", "Florence"));
        data.put("Spain", Arrays.asList("Barcelona", "Madrid", "Valencia", "Sevilla", "Villareal"));
        data.put("United States of America", Arrays.asList("Washington", "Boston", "California", "Florida", "North Carolina", "South Carolina", "Massachusettes", "Mississippi", "North Dakota", "South Dakota", "Dallas", "New York", "Detroit", "Michigan", "Pennsylvania", "Texas", "Ohio", "Indiana", "Illinois"));


        spinner_toucountry = (Spinner)findViewById(R.id.spinner_ucountry);
        spinner_toucity = (Spinner)findViewById(R.id.spinner_ucity);
        // obtaining a string array containing keys(data of spinner1) of above hashmap
        final String[] dataSpinner1 = new String[data.keySet().size()];
        data.keySet().toArray(dataSpinner1);


        // initializing an string type, ArrayAdapter for spinner1
        // spinner content(as string array) as arguments to create an array adapter
        final ArrayAdapter<String> spinner1Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner1);
        spinner_toucountry.setAdapter(spinner1Adapter);

        // setting listner for spinner1 to trigger when an spinner item is being
        // clicked by the user
        spinner_toucountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // obtaining relevant data for spinner2
                List<String> dataSpinner2 = data.get(dataSpinner1[position]);

                // crating an setting array adapter for spinner2
                ArrayAdapter<String> spinner2Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner2);
                spinner_toucity.setAdapter(spinner2Adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner
        goToNormalUserProfile();
    }
    public void goToNormalUserProfile() {
        btn_tosignupuser = (Button)findViewById(R.id.btn_signupuser);   //button

        radgrp_tougender = (RadioGroup) findViewById(R.id.radgrp_ugender);   //radiogroup

        spinner_toucountry = (Spinner) findViewById(R.id.spinner_ucountry);   //spinners
        spinner_toucity = (Spinner) findViewById(R.id.spinner_ucity);




        edttxt_toufirstname = (EditText)findViewById(R.id.edttxt_ufirstname);   //edittexts
        edttxt_toulastname = (EditText)findViewById(R.id.edttxt_ulastname);
        edttxt_touemail = (EditText)findViewById(R.id.edttxt_uemail);
        edttxt_toupassword = (EditText)findViewById(R.id.edttxt_upassword);
        edttxt_touretypepassword = (EditText)findViewById(R.id.edttxt_uretypepassword);
        edttxt_toubirthdate = (EditText)findViewById(R.id.edttxt_ubirthdate);
        edttxt_touprofession = (EditText)findViewById(R.id.edttxt_uprofession);
        edttxt_touzipcode = (EditText)findViewById(R.id.edttxt_uzipcode);
        edttxt_toumobileno = (EditText)findViewById(R.id.edttxt_umobileno);


        btn_tosignupuser.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        int selected_radbtn = radgrp_tougender.getCheckedRadioButtonId();
                        radbtn_tomaleorfemale = (RadioButton) findViewById(selected_radbtn);

                        String type = "registeruser";
                        String ufirstname = edttxt_toufirstname.getText().toString();
                        String ulastname = edttxt_toulastname.getText().toString();
                        String uemail = edttxt_touemail.getText().toString();
                        String upassword = edttxt_toupassword.getText().toString();

                        String ugender = radbtn_tomaleorfemale.getText().toString();
                        String ubirthdate = edttxt_toubirthdate.getText().toString();
                        String uprofession = edttxt_touprofession.getText().toString();
                        String ucountry = spinner_toucountry.getSelectedItem().toString();
                        String ucity = spinner_toucity.getSelectedItem().toString();
                        String uzipcode = edttxt_touzipcode.getText().toString();
                        String umobileno = edttxt_toumobileno.getText().toString();


                        String uretypepassword = edttxt_touretypepassword.getText().toString();

                        if(TextUtils.isEmpty(ufirstname))
                        {
                            edttxt_toufirstname.setError("Empty");
                            //Toast.makeText(getApplicationContext(),"Passwordshngjy are not matching!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(ulastname))
                        {
                            edttxt_toulastname.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(uemail))
                        {
                            edttxt_touemail.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(upassword))
                        {
                            edttxt_toupassword.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(uretypepassword))
                        {
                            edttxt_touretypepassword.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(ubirthdate))
                        {
                            edttxt_toubirthdate.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(uprofession))
                        {
                            edttxt_touprofession.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(uzipcode))
                        {
                            edttxt_touzipcode.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(umobileno))
                        {
                            edttxt_toumobileno.setError("Empty");
                            return;
                        }
                        else if(upassword.equals(uretypepassword))
                        {
                            //Intent intent  = new Intent("com.example.omeepc.fairpedia.NormalUserProfile");
                            BackgroundWorker backgroundWorkerur = new BackgroundWorker(SignUpUser.this);
                            backgroundWorkerur.execute(type, ufirstname, ulastname, uemail, upassword, ugender, ubirthdate, uprofession, ucountry, ucity, uzipcode, umobileno);
                            Intent intent = new Intent(SignUpUser.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Successfully signed up",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Passwords are not matching!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
