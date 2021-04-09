package com.example.omeepc.fairpedia;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class AuthorityProfileAndStuffs extends AppCompatActivity {

    String em,pas;
    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority_profile_and_stuffs);

        // Create the adapter that is returned a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container2);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        Bundle bundle = getIntent().getExtras();
        em = bundle.getString("emailid");
        pas = bundle.getString("passw");
        //Button nbutton = (Button)findViewById(R.id.trybtn);
        //nbutton.setText("em");

        //Bundle bn = new Bundle();

        //bn.putString("em", "ggjf");
        // set Fragmentclass Arguments
        //tabclass_normal_user_profile fragobj = new tabclass_normal_user_profile();
        // fragobj.setArguments(bn);



/* the ViewPager is required a minimum of 1 as OffscreenPageLimit */
//after change, it is retained also after switching tabs
        int limit = (mSectionsPagerAdapter.getCount() > 1 ? mSectionsPagerAdapter.getCount() - 1 : 1);

        ViewPager viewPager = (ViewPager) findViewById(R.id.container2);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(limit);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this is added items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authority_profile_and_stuffs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar is
        //  done clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch (position)
            {
                case 0:
                    tabclass_authority_profile t1 = new tabclass_authority_profile();
                    Bundle bn = new Bundle();

                    bn.putString("em", em);
                    bn.putString("pas", pas);
                    t1.setArguments(bn);
                    return t1;
                case 1:
                    tabclass_authority_discover t2 = new tabclass_authority_discover();
                    Bundle cn = new Bundle();

                    cn.putString("em", em);
                    cn.putString("pas", pas);
                    t2.setArguments(cn);
                    return t2;
                case 2:
                    tabclass_authority_menu t3 = new tabclass_authority_menu();
                    Bundle dn = new Bundle();

                    dn.putString("em", em);
                    dn.putString("pas", pas);
                    t3.setArguments(dn);
                    return t3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Profile";
                case 1:
                    return "Discover";
                case 2:
                    return "Menu";
            }
            return null;
        }
    }
}
