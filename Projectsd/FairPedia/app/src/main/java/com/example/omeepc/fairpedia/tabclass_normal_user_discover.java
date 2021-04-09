package com.example.omeepc.fairpedia;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OMEE PC on 16/1/2018.
 */

public class tabclass_normal_user_discover extends Fragment implements product_adapter.ItemClickCallback, fair_adapter.ItemClickCallback{
    Button bq,bw;
    RecyclerView recproduct;

    List<product> productList;
    List<fair> fairList;
    product_adapter adapter;
    fair_adapter fadapter;
    EditText edttxt_tosearchkey;
    TextView txtview_tosearchresult;
    ImageButton imgbtn_tosearchbtn, imgbtn_tosearchbtnfair;
    String searchkey;
    String prnm, prcat, prpr, prcf, prfl, prsn, prsm, prpa, prpd, prre, primgpath;
    String fnm, fcat, fct, far, fsd, fed, fdst, fdet, fnos, fsp, fcnt, fimgpath;
    RequestQueue requestQueue;
    String na;
    String retrieveproduct_url = "http://192.168.0.100/imageuploadapp/retrieveproduct.php";
    String retrievefair_url = "http://192.168.0.100/imageuploadapp/retrievefair.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View nuview = inflater.inflate(R.layout.tab_normal_user_discover, container, false);
        Bundle bv = getArguments();
        final String emailfromlogin = bv.getString("em");
        final String passwordfromlogin = bv.getString("pas");
        //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),"adkjfgdgd",Toast.LENGTH_LONG).show();


        edttxt_tosearchkey = (EditText)nuview.findViewById(R.id.edttxt_searchkey);
        txtview_tosearchresult = (TextView)nuview.findViewById(R.id.txtview_searchresult);
        imgbtn_tosearchbtn = (ImageButton)nuview.findViewById(R.id.imgbtn_searchbtn);
        imgbtn_tosearchbtnfair = (ImageButton)nuview.findViewById(R.id.imgbtn_searchbtnfair);

        requestQueue = Volley.newRequestQueue(tabclass_normal_user_discover.this.getActivity());
        recproduct = (RecyclerView)nuview.findViewById(R.id.normal_discover_rec);
        imgbtn_tosearchbtnfair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchkey = edttxt_tosearchkey.getText().toString();



                StringRequest ror = new StringRequest(Request.Method.POST, retrievefair_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonArray = new JSONArray(response);
                                    fairList = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject j = jsonArray.getJSONObject(i);
                                        fnm = j.getString("fair_name");
                                        fcat = j.getString("fair_category");
                                        fct = j.getString("fair_city");
                                        far = j.getString("fair_area");
                                        fsd = j.getString("fair_starting_date");
                                        fed = j.getString("fair_ending_date");
                                        //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),gn+" "+bt,Toast.LENGTH_LONG).show();
                                        fdst = j.getString("fair_daily_starting_time");
                                        fdet = j.getString("fair_daily_ending_time");
                                        fnos = j.getString("fair_number_of_stalls");
                                        fsp = j.getString("fair_sponsor");
                                        fcnt = j.getString("fair_contact_no");
                                        fimgpath = j.getString("fair_image_name");
                                        fimgpath = "http://192.168.0.100/imageuploadapp/uploads/" + fimgpath + ".jpg";
                                        //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),prnm,Toast.LENGTH_LONG).show();
                                        fair p = new fair();
                                        if(!fnm.equals("-1")){
                                        txtview_tosearchresult.setText("Showing results for fair search");
                                        txtview_tosearchresult.setVisibility(View.VISIBLE);
                                        p.setFname(fnm);
                                        p.setFcategory(fcat);
                                        p.setFlocation(far+", "+fct);
                                        p.setFsdate(fsd);
                                        p.setFedate(fed);
                                        p.setFdst(fdst);
                                        p.setFdet(fdet);
                                        p.setFnost(fnos);
                                        p.setFsponsor(fsp);
                                        p.setFcontact(fcnt);
                                        p.setFimagename(fimgpath);
                                        p.setTdowntext("");
                                        //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),prcat,Toast.LENGTH_LONG).show();
                                        fairList.add(p);}
                                        else{
                                            txtview_tosearchresult.setText("No results found for " + searchkey);
                                            txtview_tosearchresult.setVisibility(View.VISIBLE);
                                        }

                                    }
                                    recproduct.setLayoutManager(new LinearLayoutManager(tabclass_normal_user_discover.this.getActivity()));
                                    fadapter = new fair_adapter(fairList,tabclass_normal_user_discover.this.getActivity());
                                    fadapter.setItemClickCallback(tabclass_normal_user_discover.this);
                                    recproduct.setAdapter(fadapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(tabclass_normal_user_discover.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> p = new HashMap<>();
                        p.put("key", searchkey);
                        return p;
                    }
                };

                requestQueue.add(ror);
            }
        });

        imgbtn_tosearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchkey = edttxt_tosearchkey.getText().toString();



                StringRequest ror5 = new StringRequest(Request.Method.POST, retrieveproduct_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonArray = new JSONArray(response);
                                    productList = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject j = jsonArray.getJSONObject(i);
                                        prnm = j.getString("product_name");
                                        primgpath = j.getString("product_image_name");
                                        prcat = j.getString("product_category");
                                        prpr = j.getString("product_price");
                                        prcf = j.getString("current_fair");
                                        prfl = j.getString("fair_location");
                                        //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),gn+" "+bt,Toast.LENGTH_LONG).show();
                                        prsn = j.getString("stall_no");
                                        prsm = j.getString("stall_name");
                                        prpa = j.getString("product_amount");
                                        prpd = j.getString("product_demand");
                                        prre = j.getString("product_review");
                                        primgpath = "http://192.168.0.100/imageuploadapp/uploads/" + primgpath + ".jpg";
                                        //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),prnm,Toast.LENGTH_LONG).show();
                                        product p = new product();
                                        if(!prnm.equals("-1")){
                                            txtview_tosearchresult.setText("Showing results for search");
                                            txtview_tosearchresult.setVisibility(View.VISIBLE);
                                            p.setPname(prnm);
                                            p.setPcategory(prcat);
                                            p.setPprice(prpr);
                                            p.setCfair(prcf);
                                            p.setFlocation(prfl);
                                            p.setSno(prsn);
                                            p.setSname(prsm);
                                            p.setPamount(prpa);
                                            p.setPdemand(prpd);
                                            p.setPreview(prre);
                                            p.setPrdimgpath(primgpath);
                                            //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),prcat,Toast.LENGTH_LONG).show();
                                            productList.add(p);}
                                        else{
                                            txtview_tosearchresult.setText("No results found for " + searchkey);
                                            txtview_tosearchresult.setVisibility(View.VISIBLE);
                                        }

                                    }
                                    recproduct.setLayoutManager(new LinearLayoutManager(tabclass_normal_user_discover.this.getActivity()));
                                    adapter = new product_adapter(productList,tabclass_normal_user_discover.this.getActivity());
                                    adapter.setItemClickCallback(tabclass_normal_user_discover.this);
                                    recproduct.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(tabclass_normal_user_discover.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> p = new HashMap<>();
                        p.put("key", searchkey);
                        return p;
                    }
                };

                requestQueue.add(ror5);
            }
        });




        return nuview;
    }

    @Override
    public void onItemClick(int p) {
        product pro = productList.get(p);
        //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),pro.getPname(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void fonItemClick(int p) {
        fair fro = fairList.get(p);
        //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),fro.getFname(),Toast.LENGTH_LONG).show();
    }
}
