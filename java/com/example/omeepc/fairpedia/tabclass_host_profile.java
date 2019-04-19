package com.example.omeepc.fairpedia;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by OMEE PC on 22/1/2018.
 */

public class tabclass_host_profile extends Fragment {
    TextView textViewhunto, textViewheato, textViewhcnto, textViewhctto, textViewhmnto;
    ImageButton imageButtonhppto;
    ImageView imageViewhppto;
    RequestQueue requestQueue;
    Button bfa;
    String nm, el, cu, ct, mn;
    String uid;
    String forhostprofile_url = "http://192.168.0.100/forhostprofile.php";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View huview = inflater.inflate(R.layout.tab_host_profile, container, false);


        Bundle bv = getArguments();
        final String emailfromlogin = bv.getString("em");
        final String passwordfromlogin = bv.getString("pas");


        bfa = (Button)huview.findViewById(R.id.buttonfa);
        textViewhunto = (TextView)huview.findViewById(R.id.textViewhun);
        textViewheato = (TextView)huview.findViewById(R.id.textViewhea);
        textViewhcnto = (TextView)huview.findViewById(R.id.textViewhcn);
        textViewhctto = (TextView)huview.findViewById(R.id.textViewhct);
        textViewhmnto = (TextView)huview.findViewById(R.id.textViewhmn);

        imageViewhppto = (ImageView)huview.findViewById(R.id.imageViewhpp);
        imageButtonhppto = (ImageButton)huview.findViewById(R.id.imageButtonhpp);




        requestQueue = Volley.newRequestQueue(tabclass_host_profile.this.getActivity());
        //txtview_toauthoritydisplayemail.setText(emailfromlogin);
        StringRequest ror = new StringRequest(Request.Method.POST, forhostprofile_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject j = jsonArray.getJSONObject(0);
                            nm = j.getString("host_user_name");
                            el = j.getString("host_email_address");
                            cu = j.getString("host_country");
                            ct = j.getString("host_city");
                            mn = j.getString("host_mobile_no");
                            uid = j.getString("host_id");
                            //Toast.makeText(tabclass_authority_profile.this.getActivity(),nm, Toast.LENGTH_LONG).show();


                            textViewhunto.setText(nm);
                            textViewheato.setText(el);
                            textViewhcnto.setText(cu);
                            textViewhctto.setText(ct);
                            textViewhmnto.setText(mn);
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
                        Toast.makeText(tabclass_host_profile.this.getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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
        bfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ina = new Intent(getActivity(), NormalUserProfile.class);
                ina.putExtra("nm",nm);
                ina.putExtra("ct",ct);
                startActivity(ina);
            }
        });

        return huview;
    }
}
