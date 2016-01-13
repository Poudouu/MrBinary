package com.arlauunlimited.mrbinary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import tyrantgit.explosionfield.ExplosionAnimator;
import tyrantgit.explosionfield.ExplosionField;


/**
 * Created by criqulau on 11/10/2015.
 */
public class MainActivity extends Activity {
    CountDownTimer Count;
    Button Start_game;
    private ExplosionField mExplosionField;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        mExplosionField = ExplosionField.attach2Window(this);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_screen);
        FacebookSdk.sdkInitialize(getApplicationContext());
        this.getWindow().addFlags(Window.FEATURE_NO_TITLE);
    }

    public void leave_activity(final View view) {
        final Intent intent = new Intent(this, Main_menu.class);
        RelativeLayout rl = (RelativeLayout) view.getParent();
        ImageView v = (ImageView) rl.findViewById(R.id.binary_opening);
        //mExplosionField.explode(v);
        Count = new CountDownTimer(1000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(intent);
                finish();
                Count.cancel();
            }
        };
        Count.start();
    }
}
