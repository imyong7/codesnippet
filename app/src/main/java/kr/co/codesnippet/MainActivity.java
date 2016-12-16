package kr.co.codesnippet;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar appToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appToolbar = (Toolbar) findViewById(R.id.appToolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout);

        setSupportActionBar(appToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();//create call to onPrepareOptionMenu()
            }
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); //create call to onPrepareOptionMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);


        LinearLayout snb_scroll_wrapper = (LinearLayout) findViewById(R.id.snb_scroll_wrapper);
        FrameLayout snb_menu_elem;

        for(int i = 1; i < 5; i++) {
            snb_menu_elem = (FrameLayout) snb_scroll_wrapper.findViewWithTag("snb_menu_elem"+i);
            snb_menu_elem.setOnClickListener(this);
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent i;

        switch(id) {
            case R.id.menu_compass : {
                i = new Intent(this, CompassActivity.class);
                startActivity(i);
                break;
            }

            case R.id.menu_camera : {
                i = new Intent(this, CameraActivity.class);
                startActivity(i);
                break;
            }

            case R.id.menu_startActivityFadeIn : {
                i = new Intent(this, FadeInActivity.class);
                startActivity(i);
                break;
            }

            case R.id.menu_InstallTwt : {
//                Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.twitter.android"));
//                marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET|Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(marketIntent);
                i = new Intent(this, DownAndInstallActivity.class);
                startActivity(i);
                break;
            }

            default : {
                break;
            }
        }
    }
}
