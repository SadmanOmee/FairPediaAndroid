package com.example.omeepc.fairpedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by OMEE PC on 16/1/2018.
 */
public class tabclass_normal_user_profile extends Fragment {
    Button btntry, btntry2, bvcto;
    TextView txtview_touserdisplayname, txtview_touserdisplayemail, txtview_touserdisplaygender, txtview_touserdisplayage, txtview_touserdisplayprofession, txtview_touserdisplayaddress, txtview_touserdisplayzipcode, txtview_touserdisplaymobileno;
    ImageButton imageButton;
    ImageView imgview_topropic;
    ViewPager mViewPager;
    String nm, el, gn, bt, pf, ar, zc, mn;
    String uid;
    String propicpath;
    public final int IMG_REQUEST = 1;
    Bitmap bitmap;
    String fornormaluserprofile_url = "http://192.168.0.100/fornormaluserprofile.php";
    String userpropic_url = "http://192.168.0.100/imageuploadapp/userpropic.php";

    //String fornormaluserprofile_url = "http://192.168.0.118/fornormaluserprofile.php";
    //String userpropic_url = "http://192.168.0.118/imageuploadapp/userpropic.php";
    RequestQueue requestQueue;

    public tabclass_normal_user_profile() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View nuview = inflater.inflate(R.layout.tab_normal_user_profile, container, false);

        Bundle bv = getArguments();
        //NormalUserProfile n = new NormalUserProfile();

        //btntry = (Button)nuview.findViewById(R.id.trybtn);
        //btntry2 = (Button)nuview.findViewById(R.id.trybtn2);
        //btntry.setText(bv.getString("em"));
        //btntry2.setText(bv.getString("pas"));
        /*tabclass_normal_user_discover td = new tabclass_normal_user_discover();
        Bundle bdl = new Bundle();
        bdl.putString("em", bv.getString("em"));
        bdl.putString("pas", bv.getString("pas"));
        td.setArguments(bdl);
        //btntry.setText("em");
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.qwert, td).commit();*/

        final String emailfromlogin = bv.getString("em");
        final String passwordfromlogin = bv.getString("pas");
        txtview_touserdisplayemail = (TextView) nuview.findViewById(R.id.txtview_userdisplayemail);
        //txtview_touserdisplayemail.setText(bv.getString("em"));
        imageButton = (ImageButton) nuview.findViewById(R.id.imgbtn_propicchange);
        txtview_touserdisplayname = (TextView) nuview.findViewById(R.id.txtview_userdisplayname);
        txtview_touserdisplaygender = (TextView) nuview.findViewById(R.id.txtview_userdisplaygender);
        txtview_touserdisplayage = (TextView) nuview.findViewById(R.id.txtview_userdisplayage);
        txtview_touserdisplayprofession = (TextView) nuview.findViewById(R.id.txtview_userdisplayprofession);
        txtview_touserdisplayaddress = (TextView) nuview.findViewById(R.id.txtview_userdisplayaddress);
        txtview_touserdisplayzipcode = (TextView) nuview.findViewById(R.id.txtview_userdisplayzipcode);
        txtview_touserdisplaymobileno = (TextView) nuview.findViewById(R.id.txtview_userdisplaymobileno);

        imgview_topropic = (ImageView) nuview.findViewById(R.id.imgview_propic);
        //Toast.makeText(getActivity().getApplicationContext(),"Accrweqrretetetount doesn't exist!",Toast.LENGTH_SHORT).show();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),"s;fskf",Toast.LENGTH_LONG).show();
                //imgview_topropic.setImageDrawable(null);
                //imgview_topropic.setImageDrawable(null);
                //imgview_topropic.setImageBitmap(null);
                //imgview_topropic.setBackground(null);
                //imgview_topropic.setImageResource(android.R.color.transparent);
                //imgview_topropic.setImageResource(R.drawable.gradient);
                //txtview_touserdisplayname.setText("Sadman");
                //imgview_topropic.setImageResource(R.drawable.flogo);
                changeProPic();
            }
        });


        requestQueue = Volley.newRequestQueue(tabclass_normal_user_profile.this.getActivity());

        StringRequest ror = new StringRequest(Request.Method.POST, fornormaluserprofile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject j = jsonArray.getJSONObject(0);
                            nm = j.getString("user_first_name") + " " + j.getString("user_last_name");
                            el = j.getString("user_email_address");
                            gn = j.getString("user_gender");
                            bt = j.getString("user_birthdate");
                            pf = j.getString("user_profession");
                            //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),gn+" "+bt,Toast.LENGTH_LONG).show();
                            ar = j.getString("user_city") + ", " + j.getString("user_country");
                            zc = j.getString("user_zipcode");
                            mn = j.getString("user_mobileno");
                            uid = j.getString("user_id");


                            txtview_touserdisplayname.setText(nm);
                            txtview_touserdisplayemail.setText(el);
                            txtview_touserdisplaygender.setText(gn);
                            txtview_touserdisplayage.setText(bt);
                            txtview_touserdisplayprofession.setText(pf);
                            txtview_touserdisplayaddress.setText(ar);
                            txtview_touserdisplayzipcode.setText(zc);
                            txtview_touserdisplaymobileno.setText(mn);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tabclass_normal_user_profile.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<>();
                p.put("email", emailfromlogin);
                p.put("pass", passwordfromlogin);
                return p;
            }
        };

        requestQueue.add(ror);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),"id is "+uid,Toast.LENGTH_LONG).show();

                StringRequest propicr = new StringRequest(Request.Method.POST, "http://192.168.0.100/imageuploadapp/retrievepropic.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);

                                    JSONObject j = jsonArray.getJSONObject(0);
                                    propicpath = j.getString("profile_pic_name");

                                    if(!propicpath.equals("no pro pic")){
                                        propicpath = "http://192.168.0.100/imageuploadapp/uploads/" + propicpath + ".jpg";
                                        Picasso.with(tabclass_normal_user_profile.this.getActivity().getApplicationContext()).load(propicpath).into(imgview_topropic);


                                    }
                                    else{
                                        //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),"pro pic not found",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(tabclass_normal_user_profile.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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






        /*if(propicpath.equals("no pro pic"))
        {

        }
        else
        {
            propicpath = "http://192.168.0.118/imageuploadapp/uploads/" + propicpath + ".jpg";
            Picasso.with(tabclass_normal_user_profile.this.getActivity().getApplicationContext()).load(propicpath).into(imgview_topropic);
        }*/






        /*Downloader downl = new Downloader(tabclass_normal_user_profile.this.getActivity(), fornormaluserprofile_url, nm, el, gn, bt, pf, ar, zc, mn);
        downl.execute();
        txtview_touserdisplayname.setText(nm);
        txtview_touserdisplayemail.setText(el);
        txtview_touserdisplaygender.setText(gn);
        txtview_touserdisplayage.setText(bt);
        txtview_touserdisplayprofession.setText(pf);
        txtview_touserdisplayaddress.setText(ar);
        txtview_touserdisplayzipcode.setText(zc);
        txtview_touserdisplaymobileno.setText(mn);*/

        return nuview;
    }


    public void changeProPic() {
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
                imgview_topropic.setImageDrawable(null);
                imgview_topropic.setImageBitmap(bitmap);
                uploadImage();
                //uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void uploadImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, userpropic_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(tabclass_normal_user_profile.this.getActivity(), Response, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tabclass_normal_user_profile.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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
        MySingleton.getmInstance(tabclass_normal_user_profile.this.getActivity()).addToRequestQueue(stringRequest);
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
