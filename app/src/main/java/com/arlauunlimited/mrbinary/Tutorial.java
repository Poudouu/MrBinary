package com.arlauunlimited.mrbinary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Tutorial extends Activity {
    Typeface tf = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.help_binary);
        tf = Typeface.createFromAsset(getAssets(),"fonts/binafont.ttf");
        TextView tv = (TextView) findViewById(R.id.okbut);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.helptxt1);
        tv.setTypeface(tf);
        FrameLayout fl=(FrameLayout) findViewById(R.id.fl2);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTuto(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void step2 (View view){
        setContentView(R.layout.activity_tutorial2);
        TextView tv = (TextView) findViewById(R.id.int_to_disp);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.tutotxt4);
        tv.setTypeface(tf);
        FrameLayout fl=(FrameLayout) findViewById(R.id.fl2);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step3(v);
            }
        });
    }
    public void step3 (View view){
        setContentView(R.layout.activity_tutorial3);
        TextView tv = (TextView) findViewById(R.id.output);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.zero);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.ouane);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.ouane);
        tv.setTypeface(tf);
        tv = (TextView) findViewById(R.id.tutotxt6);
        tv.setTypeface(tf);
        FrameLayout fl=(FrameLayout) findViewById(R.id.fl2);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step4(v);
            }
        });
    }
    public void step4 (View view){
        setContentView(R.layout.activity_tutorial4);
        TextView tv = (TextView) findViewById(R.id.tutotxt8);
        tv.setTypeface(tf);
        FrameLayout fl=(FrameLayout) findViewById(R.id.fl2);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(v);
            }
        });

    }
    public void startGame (View view) {
        Intent intent = new Intent(this, Player_1.class);
        String str = "god";
        intent.putExtra("string", str);
        startActivity(intent);
        finish();
    }
    public void startTuto(View view){
        setContentView(R.layout.activity_tutorial);
        TextView tv = (TextView) findViewById(R.id.tutotxt2);
        tv.setTypeface(tf);
    }
}
