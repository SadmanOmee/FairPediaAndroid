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

public class SignUpAuthority extends AppCompatActivity {
    private Button btn_tosignupauthority;
    EditText edttxt_toausername, edttxt_toaemail, edttxt_toapassword, edttxt_toaretypepassword, edttxt_toastallname, edttxt_toastalltype, edttxt_toafrequency, edttxt_toazipcode, edttxt_toamobileno;
    Spinner spinner_toacountry, spinner_toacity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_authority);


        //spinner
        //final Spinner spinner_toacountry, spinner_toacity;
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


        spinner_toacountry = (Spinner)findViewById(R.id.spinner_acountry);
        spinner_toacity = (Spinner)findViewById(R.id.spinner_acity);
        // obtaining a string array containing keys(data of spinner1) of above hashmap
        final String[] dataSpinner1 = new String[data.keySet().size()];
        data.keySet().toArray(dataSpinner1);


        // initializing an string type, ArrayAdapter for spinner1
        // spinner content(as string array) as arguments to create an array adapter
        final ArrayAdapter<String> spinner1Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner1);
        spinner_toacountry.setAdapter(spinner1Adapter);

        // setting listner for spinner1 to trigger when an spinner item is being
        // clicked by the user
        spinner_toacountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // obtaining relevant data for spinner2
                List<String> dataSpinner2 = data.get(dataSpinner1[position]);

                // crating an setting array adapter for spinner2
                ArrayAdapter<String> spinner2Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner2);
                spinner_toacity.setAdapter(spinner2Adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner
        goToAuthorityProfile();
    }



    public void goToAuthorityProfile() {
        btn_tosignupauthority = (Button)findViewById(R.id.btn_signupauthority);   //button

        spinner_toacountry = (Spinner) findViewById(R.id.spinner_acountry);   //spinners
        spinner_toacity = (Spinner) findViewById(R.id.spinner_acity);




        edttxt_toausername = (EditText)findViewById(R.id.edttxt_ausername);   //edittexts
        edttxt_toaemail = (EditText)findViewById(R.id.edttxt_aemail);
        edttxt_toapassword = (EditText)findViewById(R.id.edttxt_apassword);
        edttxt_toaretypepassword = (EditText)findViewById(R.id.edttxt_aretypepassword);
        edttxt_toastallname = (EditText)findViewById(R.id.edttxt_astallname);
        edttxt_toastalltype = (EditText)findViewById(R.id.edttxt_astalltype);
        edttxt_toafrequency = (EditText)findViewById(R.id.edttxt_afrequency);
        edttxt_toazipcode = (EditText)findViewById(R.id.edttxt_azipcode);
        edttxt_toamobileno = (EditText)findViewById(R.id.edttxt_amobileno);


        btn_tosignupauthority.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {


                        String type = "registerauthority";
                        String ausername = edttxt_toausername.getText().toString();
                        String aemail = edttxt_toaemail.getText().toString();
                        String apassword = edttxt_toapassword.getText().toString();

                        String astallname = edttxt_toastallname.getText().toString();
                        String astalltype = edttxt_toastalltype.getText().toString();
                        String afrequency = edttxt_toafrequency.getText().toString();
                        String acountry = spinner_toacountry.getSelectedItem().toString();
                        String acity = spinner_toacity.getSelectedItem().toString();
                        String azipcode = edttxt_toazipcode.getText().toString();
                        String amobileno = edttxt_toamobileno.getText().toString();


                        String aretypepassword = edttxt_toaretypepassword.getText().toString();

                        if(TextUtils.isEmpty(ausername))
                        {
                            edttxt_toausername.setError("Empty");
                            //Toast.makeText(getApplicationContext(),"Passwordshngjy are not matching!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(aemail))
                        {
                            edttxt_toaemail.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(apassword))
                        {
                            edttxt_toapassword.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(aretypepassword))
                        {
                            edttxt_toaretypepassword.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(astallname))
                        {
                            edttxt_toastallname.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(astalltype))
                        {
                            edttxt_toastalltype.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(azipcode))
                        {
                            edttxt_toazipcode.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(amobileno))
                        {
                            edttxt_toamobileno.setError("Empty");
                            return;
                        }
                        else if(apassword.equals(aretypepassword))
                        {
                            //Intent intent  = new Intent("com.example.omeepc.fairpedia.NormalUserProfile");
                            BackgroundWorker backgroundWorkerur = new BackgroundWorker(SignUpAuthority.this);
                            backgroundWorkerur.execute(type, ausername, aemail, apassword, astallname, astalltype, afrequency, acountry, acity, azipcode, amobileno);
                            Intent intent = new Intent(SignUpAuthority.this, MainActivity.class);
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
