package com.arlauunlimited.mrbinary;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;


/**
 * Created by criqulau on 11/10/2015.
 */
public class MainActivity extends ActionBarActivity{
    CountDownTimer Count;
    Button Start_game;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView txt1 = (TextView) findViewById(R.id.arlau_unlimited);
        txt1.setText("");
        TextView txt2 = (TextView) findViewById(R.id.present);
        txt2.setText("");
        TextView txt3 = (TextView) findViewById(R.id.MrBinary);
        txt3.setText("");
        Start_game = (Button) findViewById(R.id.start_game);
        Start_game.setVisibility(View.GONE);

        create_counter();

        Count.start();

    }

    public void create_counter() {
        Count = new CountDownTimer(4000, 500) {

            // Action to check at every tic
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 3500) {
                    TextView txt1 = (TextView) findViewById(R.id.arlau_unlimited);
                    txt1.setText("ARLAU UNLIMITED");
                }
                if (millisUntilFinished < 2500) {
                    TextView txt2 = (TextView) findViewById(R.id.present);
                    txt2.setText("presents...");
                }
                if (millisUntilFinished < 1500) {
                    TextView txt3 = (TextView) findViewById(R.id.MrBinary);
                    txt3.setText("Mr Binary");
                }
            }

            // Reset of the game when the timeout goes to 0
            public void onFinish() {
                Start_game = (Button) findViewById(R.id.start_game);
                Start_game.setVisibility(View.VISIBLE);
                this.cancel();
            }
        };
    }

    public void leave_activity(View view) {
        if(Count!=null){
            Count.cancel();
        }
        Intent intent = new Intent(this, Main_menu.class);
        startActivity(intent);
        finish();
    }
}
