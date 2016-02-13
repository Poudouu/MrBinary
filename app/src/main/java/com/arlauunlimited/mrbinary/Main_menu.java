package com.arlauunlimited.mrbinary;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;


public class Main_menu extends Activity {

    Typeface tf = null;

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
        this.getWindow().addFlags(Window.FEATURE_NO_TITLE);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        tf = Typeface.createFromAsset(getAssets(),"fonts/binafont.ttf");
        TextView textView= (TextView) findViewById(R.id.playtv);
        textView.setTypeface(tf);
        textView = (TextView) findViewById(R.id.tutotv);
        textView.setTypeface(tf);

    }

    public void Switch_to_mode_1(View view) {
        Intent intent = new Intent(this, Tutorial.class);
        startActivity(intent);
        finish();
    }

    public void Switch_to_mode_2(View view) {
        Intent intent = new Intent(this, Player_1.class);
        String str=getString(R.string.normal_mode);
        intent.putExtra("string", str);
        startActivity(intent);
        finish();

}

    public void Switch_to_mode_3(View view) {
        Intent intent = new Intent(this, Player_1.class);
        String str=getString(R.string.god_mode);
        intent.putExtra("string", str);
        startActivity(intent);
        finish();
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
