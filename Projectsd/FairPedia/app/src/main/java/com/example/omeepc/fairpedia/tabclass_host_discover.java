package com.example.omeepc.fairpedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.drawable.ic_menu_gallery;

/**
 * Created by OMEE PC on 22/1/2018.
 */

public class tabclass_host_discover extends Fragment {
    String fnm, fcat, fct, far, fsd, fed, fdst, fdet, fnos, fsp, fcnt, fimgnm;
    EditText editTextfnto, editTextfstto, editTextfedto, editTextfdsttto, editTextfdetto, editTextnosto, editTextspto, editTextcntto;
    Spinner spinnerctto, spinnerarto, spinnercatto;
    Button buttonarrato;
    ImageButton imageButtonfupto;
    ImageView imageViewfupto;
    public final int IMG_REQUEST = 1;
    Bitmap bitmap;
    RequestQueue requestQueue;
    String fairinfo_url = "http://192.168.0.100/imageuploadapp/fairinfo.php";
    String forhostprofile_url = "http://192.168.0.100/forhostprofile.php";
    String uid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View huview = inflater.inflate(R.layout.tab_host_discover, container, false);


        Bundle bv = getArguments();
        final String emailfromlogin = bv.getString("em");
        final String passwordfromlogin = bv.getString("pas");

        editTextfnto = (EditText)huview.findViewById(R.id.editTextfn);
        editTextfstto = (EditText)huview.findViewById(R.id.editTextfst);
        editTextfedto = (EditText)huview.findViewById(R.id.editTextfed);
        editTextfdsttto = (EditText)huview.findViewById(R.id.editTextfdst);
        editTextfdetto = (EditText)huview.findViewById(R.id.editTextfdet);
        editTextnosto = (EditText)huview.findViewById(R.id.editTextnos);
        editTextspto = (EditText)huview.findViewById(R.id.editTextsp);
        editTextcntto = (EditText)huview.findViewById(R.id.editTextfcnt);


        spinnercatto = (Spinner)huview.findViewById(R.id.spinnercat);
        spinnerctto = (Spinner)huview.findViewById(R.id.spinnerct);
        spinnerarto = (Spinner)huview.findViewById(R.id.spinnerar);

        buttonarrato = (Button) huview.findViewById(R.id.buttonarra);
        imageButtonfupto = (ImageButton) huview.findViewById(R.id.imageButtonfup);
        imageViewfupto = (ImageView)huview.findViewById(R.id.imageViewfup);


        //spinner
        //final Spinner spinner_toucountry, spinner_toucity;
        // hashmap object containing data of spinner1 as 'keys' with relevant
        // data of spinner2 in List<String> object as 'values'
        final Map<String, List<String>> data = new HashMap<>();
        data.put("Australia", Arrays.asList("Adelaide", "Canberra", "Perth", "Melbourne", "Sydney"));
        data.put("Dhaka", Arrays.asList("Uttara", "Bonosree", "Iskaton", "Elephant Road", "Dhanmondi", "Mirpur", "Azimpur", "Komolapur", "Farmgate", "Motijheel", "Hatirpul"));
        data.put("Brazil", Arrays.asList("Sau Paulo", "Brasilia"));
        data.put("Canada", Arrays.asList("Ottowa", "Toronto"));
        data.put("England", Arrays.asList("London", "New Castle", "Hampshire", "Stoke City", "Chelsea", "Tottenham", "Liverpool", "Manchester", "Sussex", "Buckingham"));
        data.put("France", Arrays.asList("Paris", "Lyon", "Lille"));
        data.put("Germany", Arrays.asList("Berlin", "Stuttgart", "Dortmund", "Munich", "Leipzig"));
        data.put("India", Arrays.asList("Kolkata", "Bangalore", "Asam", "Pune", "Mumbai"));
        data.put("Italy", Arrays.asList("Rome", "Milan", "Florence"));
        data.put("Spain", Arrays.asList("Barcelona", "Madrid", "Valencia", "Sevilla", "Villareal"));
        data.put("United States of America", Arrays.asList("Washington", "Boston", "California", "Florida", "North Carolina", "South Carolina", "Massachusettes", "Mississippi", "North Dakota", "South Dakota", "Dallas", "New York", "Detroit", "Michigan", "Pennsylvania", "Texas", "Ohio", "Indiana", "Illinois"));

        // obtaining a string array containing keys(data of spinner1) of above hashmap
        final String[] dataSpinner1 = new String[data.keySet().size()];
        data.keySet().toArray(dataSpinner1);


        // initializing an string type, ArrayAdapter for spinner1
        // spinner content(as string array) as arguments to create an array adapter
        final ArrayAdapter<String> spinner1Adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner1);
        spinnerctto.setAdapter(spinner1Adapter);

        // setting listner for spinner1 to trigger when an spinner item is being
        // clicked by the user
        spinnerctto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // obtaining relevant data for spinner2
                List<String> dataSpinner2 = data.get(dataSpinner1[position]);

                // crating an setting array adapter for spinner2
                ArrayAdapter<String> spinner2Adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, dataSpinner2);
                spinnerarto.setAdapter(spinner2Adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner
        //spinner
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Book");
        spinnerArray.add("Trade");
        spinnerArray.add("Medicine");
        spinnerArray.add("Tree");
        spinnerArray.add("Electronics");
        spinnerArray.add("New Year");
        spinnerArray.add("Varieties");
        spinnerArray.add("Computer");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(tabclass_host_discover.this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercatto.setAdapter(adapter);
        //spinner









        requestQueue = Volley.newRequestQueue(tabclass_host_discover.this.getActivity());
        StringRequest ror = new StringRequest(Request.Method.POST, forhostprofile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject j = jsonArray.getJSONObject(0);
                            uid = j.getString("host_id");
                            //Toast.makeText(tabclass_authority_profile.this.getActivity(),nm, Toast.LENGTH_LONG).show();
                            //JSONObject jsonObject = new JSONObject(response);
                            //String Response = jsonObject.getString("response");
                            //Toast.makeText(tabclass_authority_profile.this.getActivity(), Response, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tabclass_host_discover.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<>();
                p.put("email", emailfromlogin);
                p.put("pass", passwordfromlogin);
                //Toast.makeText(tabclass_authority_profile.this.getActivity(),emailfromlogin + passwordfromlogin, Toast.LENGTH_LONG).show();
                return p;
            }
        };

        requestQueue.add(ror);







        imageButtonfupto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fairPicGet();
            }
        });
        buttonarrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //productPicGet();
                fnm = editTextfnto.getText().toString();
                fsd = editTextfstto.getText().toString();
                fed = editTextfedto.getText().toString();
                fdst = editTextfdsttto.getText().toString();
                fdet = editTextfedto.getText().toString();
                fnos = editTextnosto.getText().toString();
                fsp = editTextspto.getText().toString();
                fcnt = editTextcntto.getText().toString();
                fimgnm = fnm + fnos + fsp;
                fcat = spinnercatto.getSelectedItem().toString();
                fct = spinnerctto.getSelectedItem().toString();
                far = spinnerarto.getSelectedItem().toString();
                uploadEvent();
            }
        });



        return huview;
    }
    public void fairPicGet() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
        //uploadImage();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == getActivity().RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                imageViewfupto.setImageDrawable(null);
                imageViewfupto.setImageBitmap(bitmap);
                //uploadImage();
                //uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void uploadEvent() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, fairinfo_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(tabclass_host_discover.this.getActivity(), Response, Toast.LENGTH_LONG).show();
                            imageViewfupto.setImageResource(ic_menu_gallery);
                            editTextfnto.getText().clear();
                            editTextfstto.getText().clear();
                            editTextfedto.getText().clear();
                            editTextfdsttto.getText().clear();
                            editTextfdetto.getText().clear();
                            editTextnosto.getText().clear();
                            editTextspto.getText().clear();
                            editTextcntto.getText().clear();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tabclass_host_discover.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<>();
                p.put("uid",uid);
                p.put("fnm", fnm);
                p.put("fimg", imageToString(bitmap));
                p.put("fimgnm", fimgnm);
                p.put("fcat", fcat);
                p.put("fct", fct);
                p.put("far", far);
                p.put("fsd", fsd);
                p.put("fed", fed);
                p.put("fdst", fdst);
                p.put("fdet", fdet);
                p.put("fnos", fnos);
                p.put("fsp", fsp);
                p.put("fcnt", fcnt);
                return p;
            }
        };
        MySingleton.getmInstance(tabclass_host_discover.this.getActivity()).addToRequestQueue(stringRequest);
    }
    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
