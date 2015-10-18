package com.arlauunlimited.mrbinary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

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
        Intent intent = new Intent(Pop.this, Main_menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void shareOnFacebook(View view){
        Intent mIntent = getIntent();
        ShareDialog shareDialog;
        int integer_to_enter = mIntent.getIntExtra("score", 0);
        shareDialog = new ShareDialog(this);
        if (ShareDialog.canShow(ShareLinkContent.class))
        {
            Log.e("Test", "inside shareOnFacebook()");

            //            ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().setContentDescription(content).build();
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("New high score !")
                    .setContentDescription(
                            "score :" + integer_to_enter)
                    .build();
            if(linkContent!=null) {
                shareDialog.show(linkContent);
            }
            else {
                Toast.makeText(this, "NULL", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "NOT CALLED", Toast.LENGTH_SHORT).show();
        }
    }
}
