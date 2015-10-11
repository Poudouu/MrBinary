package com.arlauunlimited.mrbinary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Lamblarn on 11/10/2015.
 */
public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_gameover);
        Intent mIntent = getIntent();
        int integer_to_enter = mIntent.getIntExtra("score", 0);
        integer_to_enter=integer_to_enter-1;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        TextView txt = (TextView) findViewById(R.id.Score);
        txt.setText(""+integer_to_enter);
    }

    public void restart_activity(View view){
    finish();
    }

    public void back_to_main_activity(View view){
        Intent intent = new Intent(Pop.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
