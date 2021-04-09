package com.example.omeepc.fairpedia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by OMEE PC on 16/1/2018.
 */

public class tabclass_normal_user_menu extends Fragment{
    Button abouttobtn, lotobtn;
    TextView textView1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View nuview = inflater.inflate(R.layout.tab_normal_user_menu, container, false);
        abouttobtn = (Button)nuview.findViewById(R.id.aboutbtn);
        lotobtn = (Button)nuview.findViewById(R.id.lobtn);
        textView1 = (TextView)nuview.findViewById(R.id.abouttxt);

        abouttobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("Fair Pedia is a fair related app for finding particular products and fair's location, price, review etc.");
            }
        });
        lotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tabclass_normal_user_menu.this.getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return nuview;
    }
}
