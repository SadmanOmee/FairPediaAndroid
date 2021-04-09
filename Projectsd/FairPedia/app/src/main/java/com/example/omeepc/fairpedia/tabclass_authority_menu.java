package com.example.omeepc.fairpedia;

import android.content.Intent;
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
 * Created by OMEE PC on 19/1/2018.
 */

public class tabclass_authority_menu extends Fragment implements fair_adapter.ItemClickCallback{
    String emailfromlogin, passwordfromlogin;
    TextView ressffto;
    EditText searchforfairto;
    ImageButton sffbto;
    String idf,nmf;

    RequestQueue requestQueue;
    RecyclerView recfair;
    List<fair> fairList;
    fair_adapter fadapter;

    String uid, huid, loc, searchkey;
    String fnm, fcat, fct, far, fsd, fed, fdst, fdet, fnos, fsp, fcnt, fimgname;
    String forauthorityprofile_url = "http://192.168.0.100/forauthorityprofile.php";
    String retrievefair_url = "http://192.168.0.100/imageuploadapp/retrievefair.php";
    String joinedinfair_url = "http://192.168.0.100/imageuploadapp/joinedinfair.php";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View auview = inflater.inflate(R.layout.tab_authority_menu, container, false);

        Bundle bv = getArguments();
        emailfromlogin = bv.getString("em");
        passwordfromlogin = bv.getString("pas");
        //Toast.makeText(tabclass_authority_menu.this.getActivity(),emailfromlogin + " " + passwordfromlogin, Toast.LENGTH_LONG).show();
        searchforfairto = (EditText)auview.findViewById(R.id.searchforfair);
        sffbto = (ImageButton)auview.findViewById(R.id.sffb);
        ressffto = (TextView)auview.findViewById(R.id.ressff);

        requestQueue = Volley.newRequestQueue(tabclass_authority_menu.this.getActivity());
        StringRequest ror = new StringRequest(Request.Method.POST, forauthorityprofile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject j = jsonArray.getJSONObject(0);
                            uid = j.getString("authority_id");
                            //Toast.makeText(tabclass_authority_menu.this.getActivity(),uid, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(tabclass_authority_menu.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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






        recfair = (RecyclerView)auview.findViewById(R.id.aujfrec);
        sffbto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchkey = searchforfairto.getText().toString();
                StringRequest ror6 = new StringRequest(Request.Method.POST, retrievefair_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonArray = new JSONArray(response);
                                    fairList = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject j = jsonArray.getJSONObject(i);
                                        fnm = j.getString("fair_name");
                                        huid = j.getString("fair_id");
                                        fimgname = j.getString("fair_image_name");
                                        fcat = j.getString("fair_category");
                                        fct = j.getString("fair_city");
                                        far = j.getString("fair_area");
                                        fsd = j.getString("fair_starting_date");
                                        //Toast.makeText(tabclass_authority_menu.this.getActivity(),huid,Toast.LENGTH_LONG).show();
                                        fed = j.getString("fair_ending_date");
                                        fdst = j.getString("fair_daily_starting_time");
                                        fdet = j.getString("fair_daily_ending_time");
                                        fnos = j.getString("fair_number_of_stalls");
                                        fsp = j.getString("fair_sponsor");
                                        fcnt = j.getString("fair_contact_no");
                                        fimgname = "http://192.168.0.100/imageuploadapp/uploads/" + fimgname + ".jpg";
                                        //Toast.makeText(tabclass_normal_user_discover.this.getActivity(),prnm,Toast.LENGTH_LONG).show();
                                        fair p = new fair();
                                        if(!fnm.equals("-1")){
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
                                            p.setFimagename(fimgname);
                                            p.setFrid(huid);

                                            p.setTdowntext("Click here to book stalls");
                                            //Toast.makeText(tabclass_host_events.this.getActivity(),fsp+" "+fcnt,Toast.LENGTH_LONG).show();
                                            fairList.add(p);}
                                        else{
                                            ressffto.setText("No results found for " + searchkey);
                                            ressffto.setVisibility(View.VISIBLE);
                                        }

                                    }
                                    recfair.setLayoutManager(new LinearLayoutManager(tabclass_authority_menu.this.getActivity()));
                                    fadapter = new fair_adapter(fairList,tabclass_authority_menu.this.getActivity());
                                    fadapter.setItemClickCallback(tabclass_authority_menu.this);
                                    recfair.setAdapter(fadapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(tabclass_authority_menu.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> p = new HashMap<>();
                        p.put("key", searchkey);
                        return p;
                    }
                };

                requestQueue.add(ror6);
            }
        });


        return auview;
    }

    @Override
    public void fonItemClick(int p) {
        fair fro = fairList.get(p);
        idf = fro.getFrid();
        nmf = fro.getFname();
        //Toast.makeText(tabclass_authority_menu.this.getActivity(),fro.getFrid(),Toast.LENGTH_LONG).show();
        StringRequest fauit = new StringRequest(Request.Method.POST, joinedinfair_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(tabclass_authority_menu.this.getActivity(), Response, Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(tabclass_authority_menu.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> p = new HashMap<>();
                p.put("huid", idf);
                p.put("fnme", nmf);
                p.put("uid", uid);
                //Toast.makeText(tabclass_authority_profile.this.getActivity(),emailfromlogin + passwordfromlogin, Toast.LENGTH_LONG).show();
                return p;
            }
        };

        requestQueue.add(fauit);

    }
}
