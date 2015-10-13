package com.arlauunlimited.mrbinary;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.widget.EditText;


public class Main_menu extends ActionBarActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void Switch_to_mode_1(View view) {
        Intent intent = new Intent(this, Player_1.class);
        String str="noob";
        intent.putExtra("string", str);
        startActivity(intent);
    }

    public void Switch_to_mode_2(View view) {
        Intent intent = new Intent(this, Player_1.class);
        String str="normal";
        intent.putExtra("string", str);
        startActivity(intent);
    }

    public void Switch_to_mode_3(View view) {
        Intent intent = new Intent(this, Player_1.class);
        String str="god";
        intent.putExtra("string", str);
        startActivity(intent);
    }

    public void view_binary_explanation(View view) {
        Intent intent = new Intent(this, Player_2.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }

        //return super.onOptionsItemSelected(item);
    }
}
