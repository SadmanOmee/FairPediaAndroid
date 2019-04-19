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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.Map;

import static android.R.drawable.ic_menu_gallery;


/**
 * Created by OMEE PC on 19/1/2018.
 */

public class tabclass_authority_discover extends Fragment{
    String propicpath;
    public final int IMG_REQUEST = 1;
    Bitmap bitmap;
    //String productinfo_url = "http://192.168.0.118/imageuploadapp/productinfo.php";
    String productinfo_url = "http://192.168.0.100/imageuploadapp/productinfo.php";
    ImageView imgview_topi;
    ImageButton imgbtn_topp, imgbtn_toup;
    EditText edttxt_topn, edttxt_topc, edttxt_topr, edttxt_tocf, edttxt_tofl, edttxt_tosn, edttxt_tosm, edttxt_topa, edttxt_topd, edttxt_tore;
    String pn, pcat, pr, cf, fl, sn, sm, pa, pd, re , pimgnm;
    EditText dedto;
    ImageButton dbnto;
    RequestQueue requestQueue;
    String ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View auview = inflater.inflate(R.layout.tab_authority_discover, container, false);

        imgbtn_topp = (ImageButton)auview.findViewById(R.id.imgbtn_pp);
        imgbtn_toup = (ImageButton)auview.findViewById(R.id.imgbtn_up);
        imgview_topi = (ImageView)auview.findViewById(R.id.imgview_pi);


        edttxt_topn = (EditText)auview.findViewById(R.id.edttxt_pn);
        edttxt_topc = (EditText)auview.findViewById(R.id.edttxt_pc);
        edttxt_topr = (EditText)auview.findViewById(R.id.edttxt_pr);
        edttxt_tocf = (EditText)auview.findViewById(R.id.edttxt_cf);
        edttxt_tofl = (EditText)auview.findViewById(R.id.edttxt_fl);
        edttxt_tosn = (EditText)auview.findViewById(R.id.edttxt_sn);
        edttxt_tosm = (EditText)auview.findViewById(R.id.edttxt_sm);
        edttxt_topa = (EditText)auview.findViewById(R.id.edttxt_pa);
        edttxt_topd = (EditText)auview.findViewById(R.id.edttxt_pd);
        edttxt_tore = (EditText)auview.findViewById(R.id.edttxt_re);

        dedto = (EditText)auview.findViewById(R.id.ded);
        dbnto = (ImageButton)auview.findViewById(R.id.dbn);


        requestQueue = Volley.newRequestQueue(tabclass_authority_discover.this.getActivity());
        //Toast.makeText(tabclass_authority_discover.this.getActivity(), pimgnm, Toast.LENGTH_LONG).show();


        imgbtn_topp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPicGet();
            }
        });



        imgbtn_toup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //productPicGet();
                pn = edttxt_topn.getText().toString();
                pcat = edttxt_topc.getText().toString();
                pr = edttxt_topr.getText().toString();
                cf = edttxt_tocf.getText().toString();
                fl = edttxt_tofl.getText().toString();
                sn = edttxt_tosn.getText().toString();
                sm = edttxt_tosm.getText().toString();
                pa = edttxt_topa.getText().toString();
                pd = edttxt_topd.getText().toString();
                re = edttxt_tore.getText().toString();
                pimgnm = pn + pcat + pr;
                if(TextUtils.isEmpty(pn))
                {
                    edttxt_topn.setError("Empty");
                    //Toast.makeText(getApplicationContext(),"Passwordshngjy are not matching!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(pcat))
                {
                    edttxt_topc.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(pr))
                {
                    edttxt_topr.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(cf))
                {
                    edttxt_tocf.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(fl))
                {
                    edttxt_tofl.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(sn))
                {
                    edttxt_tosn.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(sm))
                {
                    edttxt_tosm.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(pa))
                {
                    edttxt_topa.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(pd))
                {
                    edttxt_topd.setError("Empty");
                    return;
                }
                else if(TextUtils.isEmpty(re))
                {
                    edttxt_tore.setError("Empty");
                    return;
                }
                else
                {
                    uploadProduct();
                }
            }
        });

        dbnto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll = dedto.getText().toString();
                StringRequest ror = new StringRequest(Request.Method.POST, "http://192.168.0.100/imageuploadapp/deletepr.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String Response = jsonObject.getString("response");
                                    //dedto.getText().clear();
                                    Toast.makeText(tabclass_authority_discover.this.getActivity(), Response, Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(tabclass_authority_discover.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> p = new HashMap<>();
                        p.put("pn", ll);
                        return p;
                    }
                };

                requestQueue.add(ror);
                Toast.makeText(tabclass_authority_discover.this.getActivity(), "Product deleted successfully", Toast.LENGTH_LONG).show();
            }
        });





        return auview;
    }
    public void productPicGet() {
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
                imgview_topi.setImageDrawable(null);
                imgview_topi.setImageBitmap(bitmap);
                //uploadImage();
                //uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void uploadProduct() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, productinfo_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(tabclass_authority_discover.this.getActivity(), Response, Toast.LENGTH_LONG).show();
                            imgview_topi.setImageResource(ic_menu_gallery);
                            edttxt_topn.getText().clear();
                            edttxt_topc.getText().clear();
                            edttxt_topr.getText().clear();
                            edttxt_tocf.getText().clear();
                            edttxt_tofl.getText().clear();
                            edttxt_tosn.getText().clear();
                            edttxt_tosm.getText().clear();
                            edttxt_topa.getText().clear();
                            edttxt_topd.getText().clear();
                            edttxt_tore.getText().clear();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tabclass_authority_discover.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<>();
                p.put("pn", pn);
                p.put("pimg", imageToString(bitmap));
                p.put("pimgnm", pimgnm);
                p.put("pcat", pcat);
                p.put("pr", pr);
                p.put("cf", cf);
                p.put("fl", fl);
                p.put("sn", sn);
                p.put("sm", sm);
                p.put("am", pa);
                p.put("dm", pd);
                p.put("re", re);
                return p;
            }
        };
        MySingleton.getmInstance(tabclass_authority_discover.this.getActivity()).addToRequestQueue(stringRequest);
    }

    public String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
