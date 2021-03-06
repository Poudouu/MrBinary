package com.arlauunlimited.mrbinary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.facebook.FacebookSdk;

/**
 * Created by criqulau on 11/10/2015.
 */
public class MainActivity extends Activity {
    Typeface tf = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_screen);
        FacebookSdk.sdkInitialize(getApplicationContext());
        this.getWindow().addFlags(Window.FEATURE_NO_TITLE);
        tf = Typeface.createFromAsset(getAssets(), "fonts/binafont.ttf");
        TextView textView= (TextView) findViewById(R.id.start_game_txt);
        textView.setTypeface(tf);
    }

    public void leave_activity(final View view) {
        final Intent intent = new Intent(this, Main_menu.class);
        startActivity(intent);
    }
}
