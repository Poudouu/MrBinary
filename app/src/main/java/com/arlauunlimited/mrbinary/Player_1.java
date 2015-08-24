package com.arlauunlimited.mrbinary;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.os.CountDownTimer;

/**
 * Created by i7-3930 on 17/08/2015.
 */
public class Player_1 extends ActionBarActivity {
    //Initialization;
    int integer_to_enter=1;
    CountDownTimer Count;
    // Counter value used to create the counter
    long counter_value;
    // Flag used to start a new counter at every good input of the player
    boolean flag_new_counter=false;

    String play_now="Play now!";
    String you_fail_try_again="You fail! Try again!";
    String time_over="Time over!";
    String you_win="You win!";
    String playing="Playing...";
    String timeout_try_again="Timeout... try again!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_1_layout);

        // Initialize the entered binary string to ""
        TextView output = (TextView)findViewById(R.id.output);
        output.setText("");

        // Display of the first integer to enter
        String integer_to_display = ""+integer_to_enter;
        TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
        int_to_disp.setText(integer_to_display);


        // Initialize the game status
        String game_status_temp = play_now;
        TextView Actual_game_status = (TextView)findViewById(R.id.Actual_game_status);
        Actual_game_status.setText(game_status_temp);

        TextView textic = (TextView) findViewById(R.id.counter);
        textic.setText("");

    }

    //Function used to create the counter and allow to call it itself when the player enter a good answer
    public void create_counter(){

        Count =  new CountDownTimer(counter_value, 500){

            // Action to check at every tic
            public void onTick(long millisUntilFinished) {
                TextView textic = (TextView) findViewById(R.id.counter);
                int seconds = (int) ((millisUntilFinished / 1000));

                textic.setText(seconds + "s " + (millisUntilFinished - (seconds * 1000)) + "ms");

                TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
                String temp_4 = (String) act_game_stat.getText().toString();

                //Stop the timer if the player entered a wrong binary sequence (reset game)
                if ((temp_4).equals(you_fail_try_again)) {
                    Count.cancel();
                    textic.setText("");
                }
                // If the player enter a good answer, the counter is canceled, the counter value is updated and a new counter is launched
                if (flag_new_counter) {
                    flag_new_counter=false;
                    Count.cancel();
                    counter_value= (millisUntilFinished + 2000);
                    create_counter();
                    Count.start();
                }

            }

            // Reset of the game when the timeout goes to 0
            public void onFinish() {
                TextView textic = (TextView) findViewById(R.id.counter);
                textic.setText(time_over);

                TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
                act_game_stat.setText(timeout_try_again);
                integer_to_enter=1;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);

                TextView bin_seq_entered = (TextView)findViewById(R.id.output);
                bin_seq_entered.setText("");

            }
        };
    }

    public void actualize_game_status(String int_bin_to_compare, String entered_bin_seq, TextView act_game_stat ){

        // Find the length of the temp_2 string
        int length_temp_2 = entered_bin_seq.length();

        // Find the length of the int_bin_to_compare string
        int length_test = int_bin_to_compare.length();

        // If both string have the same length, it means that the game has to define if the player can continue or stop (game finished and reset)
        if (length_temp_2 == length_test)
        {
            // If it is won, actualize the status accordingly and increment the integer to enter + display
            if ((int_bin_to_compare).equals(entered_bin_seq)){
                act_game_stat.setText(you_win);
                flag_new_counter=true;
                integer_to_enter ++;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
            // If fail, actualize the status, reset the game + display
            else{
                act_game_stat.setText(you_fail_try_again);
                integer_to_enter=1;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }

        }else {
            act_game_stat.setText(playing);
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

    public void Modify_string_to_one(View vieuw) {
        //When click to enter the first number (1), start the counter with the initial value 58s +2s= 60s (because he will give automatically a good answer for the first number)
        if((integer_to_enter == 1)){
            counter_value=28000;
            create_counter();
            Count.start();
        }

        // Int to binary for the next integer to enter
        String int_bin_to_compare= Integer.toBinaryString(integer_to_enter);

        // Take the content of the actual entered binary sequence and convert to string type
        TextView bin_seq_entered = (TextView)findViewById(R.id.output);
        String bin_seq_entered_string = (String) bin_seq_entered.getText().toString();

        // Take the content of the actual game status and convert to string type
        TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
        String act_game_status_string = (String) act_game_stat.getText().toString();

        bin_seq_entered_string=Check_game_status(act_game_status_string,bin_seq_entered_string);

        // Add one to bin_seq_entered_string string (because it is the main purpose of the 1 button).
        String temp_seq_entered=bin_seq_entered_string+"1";

        actualize_game_status(temp_seq_entered,int_bin_to_compare,act_game_stat);

        bin_seq_entered.setText(temp_seq_entered);
    }

    public void Modify_string_to_zero(View vieuw) {

        // Int to binary for the next integer to enter
        String int_bin_to_compare= Integer.toBinaryString(integer_to_enter);

        // Take the content of the actual entered binary sequence and convert to string type
        TextView bin_seq_entered = (TextView)findViewById(R.id.output);
        String bin_seq_entered_string = (String) bin_seq_entered.getText().toString();

        // Take the content of the actual game status and convert to string type
        TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
        String act_game_status_string = (String) act_game_stat.getText().toString();

        bin_seq_entered_string=Check_game_status(act_game_status_string,bin_seq_entered_string);

        // Add one to bin_seq_entered_string string (because it is the main purpose of the 0 button).
        String temp_seq_entered=bin_seq_entered_string+"0";

        actualize_game_status(temp_seq_entered,int_bin_to_compare,act_game_stat);

        bin_seq_entered.setText(temp_seq_entered);
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