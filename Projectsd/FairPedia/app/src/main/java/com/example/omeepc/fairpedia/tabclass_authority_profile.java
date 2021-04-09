package com.example.omeepc.fairpedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OMEE PC on 19/1/2018.
 */

public class tabclass_authority_profile extends Fragment {
    TextView txtview_toauthoritydisplayname, txtview_toauthoritydisplayemail, txtview_toauthoritydisplaystallname, txtview_toauthoritydisplaystalltype, txtview_toauthoritydisplayfairfrequency, txtview_toauthoritydisplayaddress, txtview_toauthoritydisplayzipcode, txtview_toauthoritydisplaymobileno;
    ImageView imgview_toaupropic;
    ImageButton imgbtn_toaupropicchange;
    RequestQueue requestQueue;
    String forauthorityprofile_url = "http://192.168.0.100/forauthorityprofile.php";
    //String forauthorityprofile_url = "http://192.168.0.118/forauthorityprofile.php";
    String authoritypropic_url = "http://192.168.0.100/imageuploadapp/authoritypropic.php";
    String retrieveauthoritypropic_url = "http://192.168.0.100/imageuploadapp/retrieveauthoritypropic.php";
    String nm, el, sn, sp, fr, ar, zc, mn;
    String uid;
    String aupropic;
    public final int IMG_REQUEST = 1;
    Bitmap bitmap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View auview = inflater.inflate(R.layout.tab_authority_profile, container, false);

        Bundle bv = getArguments();
        final String emailfromlogin = bv.getString("em");
        final String passwordfromlogin = bv.getString("pas");
        //Toast.makeText(tabclass_authority_profile.this.getActivity(),emailfromlogin + " " + passwordfromlogin, Toast.LENGTH_LONG).show();


        txtview_toauthoritydisplayname = (TextView)auview.findViewById(R.id.txtview_authoritydisplayname);
        txtview_toauthoritydisplayemail = (TextView)auview.findViewById(R.id.txtview_authoritydisplayemail);
        txtview_toauthoritydisplaystallname = (TextView)auview.findViewById(R.id.txtview_authoritydisplaystallname);
        txtview_toauthoritydisplaystalltype = (TextView)auview.findViewById(R.id.txtview_authoritydisplaystalltype);
        txtview_toauthoritydisplayfairfrequency = (TextView)auview.findViewById(R.id.txtview_authoritydisplayfairfreq);
        txtview_toauthoritydisplayaddress = (TextView)auview.findViewById(R.id.txtview_authoritydisplayaddress);
        txtview_toauthoritydisplayzipcode = (TextView)auview.findViewById(R.id.txtview_authoritydisplayzipcode);
        txtview_toauthoritydisplaymobileno = (TextView)auview.findViewById(R.id.txtview_authoritydisplaymobileno);

        imgview_toaupropic = (ImageView)auview.findViewById(R.id.imgview_aupropic);
        imgbtn_toaupropicchange = (ImageButton)auview.findViewById(R.id.imgbtn_aupropicchange);



        requestQueue = Volley.newRequestQueue(tabclass_authority_profile.this.getActivity());
        //txtview_toauthoritydisplayemail.setText(emailfromlogin);
        StringRequest ror = new StringRequest(Request.Method.POST, forauthorityprofile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject j = jsonArray.getJSONObject(0);
                            nm = j.getString("authority_username");
                            el = j.getString("authority_email_address");
                            sn = j.getString("authority_stall_name");
                            sp = j.getString("authority_stall_type");
                            fr = j.getString("authority_fair_attending_frequency_per_year");
                            ar = j.getString("authority_city") + ", " + j.getString("authority_country");
                            zc = j.getString("authority_zipcode");
                            mn = j.getString("authority_mobileno");
                            uid = j.getString("authority_id");
                            //Toast.makeText(tabclass_authority_profile.this.getActivity(),nm, Toast.LENGTH_LONG).show();


                            txtview_toauthoritydisplayname.setText(nm);
                            txtview_toauthoritydisplayemail.setText(el);
                            txtview_toauthoritydisplaystallname.setText(sn);
                            txtview_toauthoritydisplaystalltype.setText(sp);
                            txtview_toauthoritydisplayfairfrequency.setText(fr);
                            txtview_toauthoritydisplayaddress.setText(ar);
                            txtview_toauthoritydisplayzipcode.setText(zc);
                            txtview_toauthoritydisplaymobileno.setText(mn);
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
                        Toast.makeText(tabclass_authority_profile.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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



        imgbtn_toaupropicchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAuthorityProPic();
            }
        });



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),"id is "+uid,Toast.LENGTH_LONG).show();

                StringRequest propicr = new StringRequest(Request.Method.POST, retrieveauthoritypropic_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);

                                    JSONObject j = jsonArray.getJSONObject(0);
                                    aupropic = j.getString("authority_profile_pic_name");
                                    //Toast.makeText(tabclass_authority_profile.this.getActivity(),aupropic,Toast.LENGTH_LONG).show();

                                    if(!aupropic.equals("no pro pic")){
                                        aupropic = "http://192.168.0.117/imageuploadapp/uploads/" + aupropic + ".jpg";
                                        Picasso.with(tabclass_authority_profile.this.getActivity().getApplicationContext()).load(aupropic).into(imgview_toaupropic);


                                    }
                                    else{
                                        //Toast.makeText(tabclass_authority_profile.this.getActivity(),"pro pic not found",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(tabclass_authority_profile.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> p = new HashMap<>();
                        p.put("uidno", uid);
                        return p;
                    }
                };

                requestQueue.add(propicr);


            }
        }, 200);




        return auview;
    }
    public void  changeAuthorityProPic()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == getActivity().RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                imgview_toaupropic.setImageDrawable(null);
                imgview_toaupropic.setImageBitmap(bitmap);
                uploadAuthorityImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void uploadAuthorityImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, authoritypropic_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(tabclass_authority_profile.this.getActivity(), Response, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tabclass_authority_profile.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<>();
                p.put("uidno", uid);
                p.put("uimg", imageToString(bitmap));
                p.put("uimgnm", nm);
                return p;
            }
        };
        MySingleton.getmInstance(tabclass_authority_profile.this.getActivity()).addToRequestQueue(stringRequest);
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
