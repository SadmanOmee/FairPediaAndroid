package com.example.omeepc.fairpedia;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
 * Created by OMEE PC on 22/1/2018.
 */

public class tabclass_host_events extends Fragment implements fair_adapter.ItemClickCallback{
    String emailfromlogin, passwordfromlogin;
    String fimgpath;
    String forhostprofile_url = "http://192.168.0.100/forhostprofile.php";
    RequestQueue requestQueue;
    String uid;
    ImageButton imageButtonrleto;
    String myevents_url = "http://192.168.0.100/imageuploadapp/myevents.php";
    String fnm, fcat, fct, far, fsd, fed, fdst, fdet, fnos, fsp, fcnt, fimgname;

    RecyclerView recfair;
    List<fair> fairList;
    fair_adapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View huview = inflater.inflate(R.layout.tab_host_events, container, false);
        Bundle bv = getArguments();
        emailfromlogin = bv.getString("em");
        passwordfromlogin = bv.getString("pas");

        imageButtonrleto = (ImageButton)huview.findViewById(R.id.imageButtonrle);

        requestQueue = Volley.newRequestQueue(tabclass_host_events.this.getActivity());
        StringRequest ror = new StringRequest(Request.Method.POST, forhostprofile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject j = jsonArray.getJSONObject(0);
                            uid = j.getString("host_id");
                            //Toast.makeText(tabclass_host_events.this.getActivity(),uid, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(tabclass_host_events.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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




        recfair = (RecyclerView)huview.findViewById(R.id.host_events_rec);
        imageButtonrleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest ror4 = new StringRequest(Request.Method.POST, myevents_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonArray = new JSONArray(response);
                                    fairList = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject j = jsonArray.getJSONObject(i);
                                        fnm = j.getString("fair_name");
                                        fimgname = j.getString("fair_image_name");
                                        fcat = j.getString("fair_category");
                                        fct = j.getString("fair_city");
                                        far = j.getString("fair_area");
                                        fsd = j.getString("fair_starting_date");
                                        //Toast.makeText(tabclass_normal_user_profile.this.getActivity(),gn+" "+bt,Toast.LENGTH_LONG).show();
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

                                            p.setTdowntext("0 authority attended");
                                            //Toast.makeText(tabclass_host_events.this.getActivity(),fsp+" "+fcnt,Toast.LENGTH_LONG).show();
                                            fairList.add(p);}
                                        else{
                                            //txtview_tosearchresult.setText("No results found for " + searchkey);
                                            //txtview_tosearchresult.setVisibility(View.VISIBLE);
                                        }

                                    }
                                    recfair.setLayoutManager(new LinearLayoutManager(tabclass_host_events.this.getActivity()));
                                    adapter = new fair_adapter(fairList,tabclass_host_events.this.getActivity());
                                    adapter.setItemClickCallback(tabclass_host_events.this);
                                    recfair.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(tabclass_host_events.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> p = new HashMap<>();
                        p.put("uid", uid);
                        return p;
                    }
                };

                requestQueue.add(ror4);
            }
        });


        return huview;
    }

    @Override
    public void fonItemClick(int p) {
        fair fro = fairList.get(p);
    }
}
