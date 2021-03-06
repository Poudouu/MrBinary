package com.arlauunlimited.mrbinary;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nineoldandroids.animation.Animator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


/**
 * Created by i7-3930 on 17/08/2015.
 */
public class Player_1 extends Activity {
    //Initialization;
    int integer_to_enter=1;
    int max_number_low_difficulty=65;
    int initial_timer_value=30000;
    CountDownTimer Count;
    // Counter value used to create the counter
    long counter_value_global;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    // Flag used to start a new counter at every good input of the player

    OutputStreamWriter osw = null;
    InputStreamReader isr = null;
    String best_to_write_string="";
    boolean noob_mode=false;
    boolean normal_mode=false;
    boolean god_mode=false;
    Context context=Player_1.this;
    Dialog dialog = null;
    Typeface tf = null;

    String you_fail_try_again="";
    String time_over="";
    String playing="";
    String you_win="";
    String timeout_try_again="";
    String best_score_file ="";
    String ready_for_next_difficulty_level="";
    String play_now="";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.player_1_layout);


            you_fail_try_again=getString(R.string.you_fail_try_again);
            time_over=getString(R.string.time_over);
            playing=getString(R.string.playing);
            you_win=getString(R.string.you_win);
            timeout_try_again=getString(R.string.timeout_try_again);
            best_score_file =getString(R.string.best_score_file);
            ready_for_next_difficulty_level=getString(R.string.ready_for_next_difficulty_level);
            play_now=getString(R.string.play_now);

            dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_gameover);
        dialog.setCanceledOnTouchOutside(false);
        //Game mode parametrization
        tf = Typeface.createFromAsset(getAssets(),"fonts/binafont.ttf");
        TextView game_mode = (TextView) findViewById(R.id.game_mode);
        game_mode.setTypeface(tf);
        String str = (String) getIntent().getSerializableExtra("string");
        if ((str).equals(getString(R.string.noob_mode))) {
            noob_mode = true;
            normal_mode = false;
            god_mode = false;
            game_mode.setText(getString(R.string.active_mode_noob));
        }
        if ((str).equals(getString(R.string.normal_mode))) {
            noob_mode = false;
            normal_mode = true;
            god_mode = false;
            game_mode.setText(getString(R.string.active_mode_normal));
        }
        if ((str).equals(getString(R.string.god_mode))) {
            noob_mode = false;
            normal_mode = false;
            god_mode = true;
            game_mode.setText(getString(R.string.active_mode_god));
        }

        // Initialize the entered binary string to ""
        TextView output = (TextView) findViewById(R.id.output);
        output.setText("");
        output.setTypeface(tf, Typeface.BOLD);

        // Display of the first integer to enter
        String integer_to_display = "" + integer_to_enter;
        TextView int_to_disp = (TextView) findViewById(R.id.int_to_disp);
        int_to_disp.setTypeface(tf);
        int_to_disp.setText(integer_to_display);

        // Initialize the game status
        String game_status_temp = play_now;
        final TextView Actual_game_status = (TextView)findViewById(R.id.Actual_game_status);
        Actual_game_status.setTypeface(tf);
        Actual_game_status.setText(game_status_temp);

        TextView textic = (TextView) findViewById(R.id.counter);
        textic.setTypeface(tf);
        textic.setText("");

        TextView countertxt = (TextView) findViewById(R.id.countertxt);
        countertxt.setTypeface(tf);
        if (noob_mode) {
            textic.setText("");
            you_win=getString(R.string.noob_mode_good_answer);
        }

        try {
            check_and_update_high_score(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Define listener for buttons
        FrameLayout but1= (FrameLayout) findViewById(R.id.button3);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modify_string_to_one(v,Actual_game_status);
            }
        });

        FrameLayout but0= (FrameLayout) findViewById(R.id.button4);
        but0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modify_string_to_zero(v,Actual_game_status);
            }
        });
        TextView best_score_display = (TextView)findViewById(R.id.best_score);
        best_score_display.setTypeface(tf);
        TextView last_score_display = (TextView)findViewById(R.id.last_score);
        last_score_display.setTypeface(tf);
        AdView mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        TextView count=(TextView) findViewById(R.id.counter);
        count.setTypeface(tf);

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //Function used to create the counter and allow to call it itself when the player enter a good answer
    public void create_counter(final long counter_value){
        //if a counter is already running when trying to build a new one, cancel it (Bug fix).
        try{
            Count.cancel();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        Count =  new CountDownTimer(counter_value, 1000){

            // Action to check at every tic
            public void onTick(long millisUntilFinished) {
                TextView textic = (TextView) findViewById(R.id.counter);
                int seconds = (int) ((millisUntilFinished / 1000));
                textic.setText(seconds + "s ");

                counter_value_global= (millisUntilFinished + 2000);
            }

            // Reset of the game when the timeout goes to 0
            public void onFinish() {
                TextView textic = (TextView) findViewById(R.id.counter);
                textic.setText(time_over);

                TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
                act_game_stat.setText(timeout_try_again);
                try {
                    check_and_update_high_score(integer_to_enter);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                displayPopupGameOver();

                integer_to_enter=1;
                final RelativeLayout rl= (RelativeLayout) act_game_stat.getParent();
                final ImageView bot = (ImageView) rl.findViewById(R.id.small_binabot);
                bot.setImageResource(R.drawable.small_binabot);
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);

                TextView bin_seq_entered = (TextView)findViewById(R.id.output);
                bin_seq_entered.setText("");

            }
        };
    }

    public void displayPopupGameOver(){
        TextView tv = (TextView) dialog.findViewById(R.id.youlost);
        tv.setTypeface(tf);
        tv=(TextView) dialog.findViewById(R.id.textView6);
        tv.setTypeface(tf);
        tv=(TextView) dialog.findViewById(R.id.Score);
        tv.setTypeface(tf);
        integer_to_enter=integer_to_enter-1;
        tv.setText("" + integer_to_enter);
        tv=(TextView) dialog.findViewById(R.id.restart);
        tv.setTypeface(tf);
        tv=(TextView) dialog.findViewById(R.id.exit);
        tv.setTypeface(tf);
        FrameLayout fl = (FrameLayout)dialog.findViewById(R.id.leave_activity);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_to_main_activity(v);
            }
        });
        fl = (FrameLayout) dialog.findViewById(R.id.restart_activity);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart_activity(v);
            }
        });

        ImageView ib = (ImageView) dialog.findViewById(R.id.share);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shareOnFacebook(v);
                LinearLayout ll = (LinearLayout) v.getParent();
                TextView tv = (TextView) ll.findViewById(R.id.Score);
                String score = tv.getText().toString();

                FacebookSdk.sdkInitialize(getApplicationContext());
                callbackManager = CallbackManager.Factory.create();
                shareDialog = new ShareDialog(Player_1.this);

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(getString(R.string.new_highscore))
                            .setContentDescription(getString(R.string.my_new_highscore) + score)
                            .setContentUrl((Uri.parse("https://www.facebook.com/MBinaryTheGame/?skip_nax_wizard=true")))
                            .setImageUrl((Uri.parse("https://scontent-mrs1-1.xx.fbcdn.net/hphotos-xtp1/t39.2081-0/p128x128/12624136_188434544846718_1518465147_n.png")))
                            .build();
                    shareDialog.show(linkContent);


                }
            }
        });
        dialog.show();
    }

    public void check_and_update_high_score(int potential_high_score_from_game) throws FileNotFoundException {

        int potential_high_score= potential_high_score_from_game-1;

        int best_score_to_write=0;
        int actual_best_score=0;
        char [] inputBuffer = new char [3];
        String best_score_read_1="";
        String best_score_read_2="";
        String best_score_read_3="";

        // Read the actual high score stored in the file best_score_file stored in the app directory on the device.
        try {

            FileInputStream fileInputStream=openFileInput(best_score_file);
            isr= new InputStreamReader(fileInputStream);
            isr.read(inputBuffer);

            // See below for explanation of why the read data are put in two strings...
            best_score_read_1 = inputBuffer[0]+""+inputBuffer[1]+""+inputBuffer[2]+"";
            best_score_read_2= inputBuffer[0]+""+inputBuffer[1]+"";
            best_score_read_3= inputBuffer[0]+"";

            // For debugging only
            //Toast.makeText(getApplicationContext(),inputBuffer[0]+"", Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),inputBuffer[1]+"", Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),best_score_read_2+"", Toast.LENGTH_LONG).show();
            isr.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Merdier to parse the read high score in char to int... Coudn't find something else working.
        int match_int_1=0;
        int match_int_2=0;
        int match_int_3=0;
        int i=0;
        // Parse first for two digits integer based on valueOf(inputBuffer), for a reason that I can't explain, it doesn't work if int >10, most probably because there is nothing in inputBuffer[1] in this case...
        // Parse int was not working to convert the string to int so I recreate a function.
        while  (match_int_1 == 0){
            if (best_score_read_1 == null){
                match_int_1=1;
                actual_best_score=0;
            }else {
                String temp="";

                if(i<10){
                    temp=i+"";
                }else{
                    temp=i+"";
                }

                if ((temp).equals(best_score_read_1)) {
                    match_int_1 = 1;
                    actual_best_score =i;
                    // For debugging only
                    //Toast.makeText(getApplicationContext(), "Int 3 digits match found!", Toast.LENGTH_LONG).show();
                } else {
                    i++;
                }
                if (i==1000){
                    match_int_1=1;
                    // For debugging only
                    //Toast.makeText(getApplicationContext(),"Parse 3 digits int error...", Toast.LENGTH_LONG).show();
                    int j=0;
                    // Parse int<10 if the first while didn't work (i=100) based on the inputBuffer[0] convert to string only.
                    while  (match_int_2 == 0){
                        if (best_score_read_2 == null){
                            match_int_2=1;
                            actual_best_score=0;
                        }else {
                            String temp_1="";

                            if(j<10){
                                temp_1=j+"";
                            }else{
                                temp_1=j+"";
                            }

                            if ((temp_1).equals(best_score_read_2)) {
                                match_int_2 = 1;
                                actual_best_score =j;
                                // For debugging only
                                //Toast.makeText(getApplicationContext(),"Int 2 digit match found!", Toast.LENGTH_LONG).show();
                            } else {
                                j++;
                            }
                            if (j==100){
                                match_int_2=1;
                                // For debugging only
                                //Toast.makeText(getApplicationContext(),"Parse 2 digit int error...", Toast.LENGTH_LONG).show();
                                int k=0;
                                // Parse int<10 if the first while didn't work (i=100) based on the inputBuffer[0] convert to string only.
                                while  (match_int_3 == 0){
                                    if (best_score_read_3 == null){
                                        match_int_3=1;
                                        actual_best_score=0;
                                    }else {
                                        String temp_2="";

                                        if(k<10){
                                            temp_2=k+"";
                                        }else{
                                            temp_2=k+"";
                                        }

                                        if ((temp_2).equals(best_score_read_3)) {
                                            match_int_3 = 1;
                                            actual_best_score =k;
                                            // For debugging only
                                            //Toast.makeText(getApplicationContext(),"Int 1 digit match found!", Toast.LENGTH_LONG).show();
                                        } else {
                                            k++;
                                        }
                                        if (k==10){
                                            match_int_3=1;
                                            // For debugging only
                                            //Toast.makeText(getApplicationContext(),"Parse 1 digit int error...", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        if (potential_high_score>actual_best_score){
            best_score_to_write=potential_high_score;
        }else{
            best_score_to_write=actual_best_score;
        }

        best_to_write_string=best_score_to_write+"";

        // Wright the high score stored in the file best_score_file stored in the app directory on the device.
        try {
            FileOutputStream fileOutputStream = openFileOutput(best_score_file,MODE_PRIVATE);
            osw = new OutputStreamWriter(fileOutputStream);
            osw.write(best_to_write_string);
            osw.flush();
            osw.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Display best score
        TextView best_score_display = (TextView)findViewById(R.id.best_score);
        String best_to_display=getString(R.string.best_score)+best_score_to_write;
        best_score_display.setText(best_to_display);

        //Display score of last game
        TextView last_score_to_display = (TextView)findViewById(R.id.last_score);
        String last_score_to_display_string=getString(R.string.last_score)+potential_high_score;
        last_score_to_display.setText(last_score_to_display_string);
    }

    public void actualize_game_status(String  entered_bin_seq, String int_bin_to_compare, final TextView act_game_stat ){

        // Find the length of the temp_2 string
        int length_temp_2 = entered_bin_seq.length();
        final RelativeLayout rl= (RelativeLayout) act_game_stat.getParent();
        final ImageView bot = (ImageView) rl.findViewById(R.id.small_binabot);
        final TextView textic = (TextView) findViewById(R.id.counter);
        final TextView out = (TextView) findViewById(R.id.output);
        // Find the length of the int_bin_to_compare string
        int length_test = int_bin_to_compare.length();
        TextView tv = (TextView) rl.findViewById(R.id.output);
        // If both string have the same length, it means that the game has to define if the player can continue or stop (game finished and reset)
        if (length_temp_2 == length_test)
        {
            // If it is won, actualize the status accordingly and increment the integer to enter + display
            if ((int_bin_to_compare).equals(entered_bin_seq)){
                act_game_stat.setText(you_win);
                YoYo.with(Techniques.Bounce).duration(500).playOn(tv);
                tv.setTextColor(Color.parseColor("#ffA1BECE"));
                if (int_bin_to_compare.equals("111")) {
                     bot.setImageResource(R.drawable.small_mid_binabot);
                }
                if (int_bin_to_compare.equals("1111")) {

                    bot.setImageResource(R.drawable.middle_binabot);
                }
                if (int_bin_to_compare.equals("111111")) {
                   bot.setImageResource(R.drawable.stache_binabot);
                }
                if (int_bin_to_compare.equals("1111111")) {
                    bot.setImageResource(R.drawable.pimp_binabot);
                }
                //Restart counter with the new remaining time value
                if (!noob_mode) {
                    Count.cancel();
                    create_counter(counter_value_global);
                    Count.start();
                }
                integer_to_enter ++;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView) findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);

            }
            // If fail, actualize the status, reset the game + display
            else {
                YoYo.with(Techniques.Wobble).duration(1000).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        act_game_stat.setText(you_fail_try_again);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //Stop the timer if the player entered a wrong binary sequence (reset game)
                        if(!noob_mode) {
                            Count.cancel();
                            textic.setText("");
                            out.setText("");
                        }
                        try {
                            check_and_update_high_score(integer_to_enter);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        displayPopupGameOver();

                        integer_to_enter=1;
                        bot.setImageResource(R.drawable.small_binabot);
                        String integer_to_display = ""+integer_to_enter;
                        TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                        int_to_disp.setText(integer_to_display);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(tv);

            }

        }else {
            act_game_stat.setText(playing);
            tv.setTextColor(Color.parseColor("#FF000000"));
        }
        // Int to binary for the current integer to enter
        String length_test_current= Integer.toBinaryString(integer_to_enter);
        // Find the length of the int_bin_to_compare string
        int length_test_current_int = length_test_current.length();
        //Update help string

        if((noob_mode || normal_mode) && (integer_to_enter==max_number_low_difficulty)){
            //Stop the timer if the player entered a wrong binary sequence (reset game)
            if(!noob_mode) {
                Count.cancel();
                TextView texticx = (TextView) findViewById(R.id.counter);
                texticx.setText("");
            }
            try {
                check_and_update_high_score(integer_to_enter);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            integer_to_enter=1;
            String integer_to_display = ""+integer_to_enter;
            TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
            int_to_disp.setText(integer_to_display);

            TextView help = (TextView) findViewById(R.id.help_display);
            help.setText(ready_for_next_difficulty_level);
        }
    }

    public String Check_game_status(String act_game_status, String bin_seq_entered){
        String output="";
        // Check the actual game status, and reset output if necessary (end of a binary chain)
        if (((act_game_status).equals(you_win)) ||((act_game_status).equals(you_fail_try_again))|| ((act_game_status).equals(timeout_try_again)) ){
            output="";
        }else{
            output=bin_seq_entered;
        }
        return output;
    }

    public void Modify_string_to_one(View vieuw, TextView actual_game_status) {

        if (!actual_game_status.toString().equals(you_fail_try_again) || !actual_game_status.toString().equals(timeout_try_again)) {
            //When click to enter the first number (1), start the counter with the initial value 58s +2s= 60s (because he will give automatically a good answer for the first number)
            if ((integer_to_enter == 1 && (!noob_mode))) {
                counter_value_global = initial_timer_value;
                create_counter(counter_value_global);
                Count.start();
            }

            // Int to binary for the next integer to enter
            String int_bin_to_compare = Integer.toBinaryString(integer_to_enter);

            // Take the content of the actual entered binary sequence and convert to string type
            TextView bin_seq_entered = (TextView) findViewById(R.id.output);
            String bin_seq_entered_string = (String) bin_seq_entered.getText().toString();

            // Take the content of the actual game status and convert to string type
            TextView act_game_stat = (TextView) findViewById(R.id.Actual_game_status);
            String act_game_status_string = (String) act_game_stat.getText().toString();

            bin_seq_entered_string = Check_game_status(act_game_status_string, bin_seq_entered_string);

            // Add one to bin_seq_entered_string string (because it is the main purpose of the 1 button).
            String temp_seq_entered = bin_seq_entered_string + "1";

            actualize_game_status(temp_seq_entered, int_bin_to_compare, act_game_stat);

            bin_seq_entered.setText(temp_seq_entered);
        }
    }

    public void Modify_string_to_zero(View vieuw, TextView actual_game_status) {
        if (!actual_game_status.toString().equals(you_fail_try_again) || !actual_game_status.toString().equals(timeout_try_again)) {

            // Int to binary for the next integer to enter
            String int_bin_to_compare = Integer.toBinaryString(integer_to_enter);

            // Take the content of the actual entered binary sequence and convert to string type
            TextView bin_seq_entered = (TextView) findViewById(R.id.output);
            String bin_seq_entered_string = (String) bin_seq_entered.getText().toString();

            // Take the content of the actual game status and convert to string type
            TextView act_game_stat = (TextView) findViewById(R.id.Actual_game_status);
            String act_game_status_string = (String) act_game_stat.getText().toString();

            bin_seq_entered_string = Check_game_status(act_game_status_string, bin_seq_entered_string);

            // Add one to bin_seq_entered_string string (because it is the main purpose of the 0 button).
            String temp_seq_entered = bin_seq_entered_string + "0";

            actualize_game_status(temp_seq_entered, int_bin_to_compare, act_game_stat);

            bin_seq_entered.setText(temp_seq_entered);
        }
    }

    public void restart_activity(View view){
        dialog.cancel();
    }

    public void back_to_main_activity(View view){
        Intent intent = new Intent(Player_1.this, Main_menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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



}