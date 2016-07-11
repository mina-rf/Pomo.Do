package com.sharif.PomoDo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends  FragmentActivity  {
    /**
     * Called when the activity is first created.
     */

    private ViewPager viewPager;
    private ActionBar actionBar;
    private TabsPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.main);


        getActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        String [] tabs = new String[]{"capture", "upload", "stats" , "setting"};

//        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/ArchitectsDaughter.ttf");

        for(String tab : tabs)
        {
            TextView t = new TextView(this);
            t.setAllCaps(true);
            t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER_VERTICAL));
            t.setGravity(Gravity.CENTER_VERTICAL);
            t.setTextColor(Color.WHITE);
            t.setText(tab);
//            t.setTypeface(typeFace);

            actionBar.addTab(actionBar.newTab()
                    .setCustomView(t)
                    .setTabListener(this));
        }

        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
//        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                actionBar.setSelectedNavigationItem(position);
//            }
        });
    }*/



}
