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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpHost extends AppCompatActivity {
    Spinner spinnerhcnto, spinnerhctto;
    EditText editTexthunto, editTextheato, editTexthpwto, editTexthrpto, editTexthmnto;
    Button btn_tosignuphost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_host);


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


        spinnerhcnto = (Spinner)findViewById(R.id.spinnerhcn);
        spinnerhctto = (Spinner)findViewById(R.id.spinnerhct);
        // obtaining a string array containing keys(data of spinner1) of above hashmap
        final String[] dataSpinner1 = new String[data.keySet().size()];
        data.keySet().toArray(dataSpinner1);


        // initializing an string type, ArrayAdapter for spinner1
        // spinner content(as string array) as arguments to create an array adapter
        final ArrayAdapter<String> spinner1Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner1);
        spinnerhcnto.setAdapter(spinner1Adapter);

        // setting listner for spinner1 to trigger when an spinner item is being
        // clicked by the user
        spinnerhcnto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // obtaining relevant data for spinner2
                List<String> dataSpinner2 = data.get(dataSpinner1[position]);

                // crating an setting array adapter for spinner2
                ArrayAdapter<String> spinner2Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner2);
                spinnerhctto.setAdapter(spinner2Adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner


        goToHostProfile();
    }
    public void goToHostProfile()
    {
        btn_tosignuphost = (Button)findViewById(R.id.btn_hostsignup);
        editTexthunto = (EditText)findViewById(R.id.editTexthun);
        editTextheato = (EditText)findViewById(R.id.editTexthea);
        editTexthpwto = (EditText)findViewById(R.id.editTexthpw);
        editTexthrpto = (EditText)findViewById(R.id.editTexthrp);
        editTexthmnto = (EditText)findViewById(R.id.editTexthmn);

        btn_tosignuphost.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {


                        String type = "registerhost";
                        String husername = editTexthunto.getText().toString();
                        String hemail = editTextheato.getText().toString();
                        String hpassword = editTexthpwto.getText().toString();
                        String hretypepassword = editTexthrpto.getText().toString();
                        String hmobileno = editTexthmnto.getText().toString();
                        String hcountry = spinnerhcnto.getSelectedItem().toString();
                        String hcity = spinnerhctto.getSelectedItem().toString();


                        if(TextUtils.isEmpty(husername))
                        {
                            editTexthunto.setError("Empty");
                            //Toast.makeText(getApplicationContext(),"Passwordshngjy are not matching!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else if(TextUtils.isEmpty(hemail))
                        {
                            editTextheato.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(hpassword))
                        {
                            editTexthpwto.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(hretypepassword))
                        {
                            editTexthrpto.setError("Empty");
                            return;
                        }
                        else if(TextUtils.isEmpty(hmobileno))
                        {
                            editTexthmnto.setError("Empty");
                            return;
                        }
                        else if(hpassword.equals(hretypepassword))
                        {
                            //Intent intent  = new Intent("com.example.omeepc.fairpedia.NormalUserProfile");
                            BackgroundWorker backgroundWorkerur = new BackgroundWorker(SignUpHost.this);
                            backgroundWorkerur.execute(type, husername, hemail, hpassword, hcountry, hcity, hmobileno);
                            Intent intent = new Intent(SignUpHost.this, MainActivity.class);
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
