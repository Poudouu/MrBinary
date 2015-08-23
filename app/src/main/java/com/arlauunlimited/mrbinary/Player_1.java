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

    int integer_to_enter=1;
    CountDownTimer Count;

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
        String game_status_temp = "Play now!";
        TextView Actual_game_status = (TextView)findViewById(R.id.Actual_game_status);
        Actual_game_status.setText(game_status_temp);

        TextView textic = (TextView) findViewById(R.id.counter);
        textic.setText("");

    }

    public void Modify_string_to_one(View vieuw) {
        //When click to enter the first number (1), start the timeout.
        if(integer_to_enter == 1){

            Count =  new CountDownTimer(60000, 500){

                // Action to check at every tic
                public void onTick(long millisUntilFinished) {
                    TextView textic = (TextView) findViewById(R.id.counter);
                    int seconds = (int) ((millisUntilFinished / 1000));

                    textic.setText(seconds + "s " + (millisUntilFinished - (seconds * 1000)) + "ms");

                    TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
                    String temp_4 = (String) act_game_stat.getText().toString();

                    //Stop the timer if the player entered a wrong binary sequence (reset game)
                    if ((temp_4).equals("You fail! Try again!")) {
                        Count.cancel();
                        textic.setText("");
                    }

                }

                // Reset of the game when the timeout goes to 0
                public void onFinish() {
                    TextView textic = (TextView) findViewById(R.id.counter);
                    textic.setText("Time over!");

                    TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
                    act_game_stat.setText("Timeout... try again!");
                    integer_to_enter=1;
                    String integer_to_display = ""+integer_to_enter;
                    TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                    int_to_disp.setText(integer_to_display);

                    TextView bin_seq_entered = (TextView)findViewById(R.id.output);
                    bin_seq_entered.setText("");

                }
            };
            Count.start();
        }

        // Int to binary for the next integer to enter
        String int_bin_to_compare= Integer.toBinaryString(integer_to_enter);

        // Take the content of the actual entered binary sequence and convert to string type
        TextView bin_seq_entered = (TextView)findViewById(R.id.output);
        String temp_1 = (String) bin_seq_entered.getText().toString();

        // Take the content of the actual game status and convert to string type
        TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
        String temp_3 = (String) act_game_stat.getText().toString();

        // Check the actual game status, and reset temp_1 if necessary (end of a binary chain)
        if (((temp_3).equals("You win!")) ||((temp_3).equals("You fail! Try again!"))|| ((temp_3).equals("Timeout... try again!")) ){
            temp_1="";
        }

        // Add one to temp_1 string (because it is the main purpose of the 1 button).
        String temp_2=temp_1+"1";

        // Find the length of the temp_2 string
        int length_temp_2 = temp_2.length();

        // Find the length of the int_bin_to_compare string
        int length_test = int_bin_to_compare.length();

        // If both string have the same length, it means that the game has to define if the player can continue or stop (game finished and reset)
        if (length_temp_2 == length_test)
        {
            // If it is won, actualize the status accordingly and increment the integer to enter + display
            if ((int_bin_to_compare).equals(temp_2)){
                act_game_stat.setText("You win!");
                integer_to_enter ++;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
            // If fail, actualize the status, reset the game + display
            else{
                act_game_stat.setText("You fail! Try again!");
                integer_to_enter=1;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }

        }else {
            act_game_stat.setText("Playing...");
        }
        bin_seq_entered.setText(temp_2);
    }

    public void Modify_string_to_zero(View vieuw) {

        // Int to binary for the next integer to enter
        String int_bin_to_compare= Integer.toBinaryString(integer_to_enter);

        // Take the content of the actual entered binary sequence and convert to string type
        TextView bin_seq_entered = (TextView)findViewById(R.id.output);
        String temp_1 = (String) bin_seq_entered.getText().toString();

        // Take the content of the actual game status and convert to string type
        TextView act_game_stat = (TextView)findViewById(R.id.Actual_game_status);
        String temp_3 = (String) act_game_stat.getText().toString();

        // Check the actual game status, and reset temp_1 if necessary (end of a binary chain)
        if (((temp_3).equals("You win!")) ||((temp_3).equals("You fail! Try again!")) ){
            temp_1="";
        }

        // Add one to temp_1 string (because it is the main purpose of the 0 button).
        String temp_2=temp_1+"0";

        // Find the length of the temp_2 string
        int length_temp_2 = temp_2.length();

        // Find the length of the int_bin_to_compare string
        int length_test = int_bin_to_compare.length();

        // If both string have the same length, it means that the game has to define if the player can continue or stop (game finished and reset)
        if (length_temp_2 == length_test)
        {
            // If it is won, actualize the status accordingly and increment the integer to enter + display
            if ((int_bin_to_compare).equals(temp_2)){
                act_game_stat.setText("You win!");
                integer_to_enter ++;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
            // If fail, actualize the status, reset the game + display
            else{
                act_game_stat.setText("You fail! Try again!");
                integer_to_enter=1;
                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
        }else {
            act_game_stat.setText("Playing...");
        }
        bin_seq_entered.setText(temp_2);
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