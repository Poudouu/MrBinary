package com.arlauunlimited.mrbinary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;

/**
 * Created by i7-3930 on 17/08/2015.
 */
public class Player_1 extends ActionBarActivity {

    int integer_to_enter=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_1_layout);
        Intent intent = getIntent();
        TextView output = (TextView)findViewById(R.id.output);
        output.setText("");

        String integer_to_display = ""+integer_to_enter;
        TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
        int_to_disp.setText(integer_to_display);

    }

    public void Modify_string_to_one(View vieuw) {

        String test_to_compare= Integer.toBinaryString(integer_to_enter);

        TextView output = (TextView)findViewById(R.id.output);
        String temp_1 = (String) output.getText().toString();

        if (((temp_1).equals("You win!!!!")) ||((temp_1).equals("You fail!!!!")) ){
            temp_1="";
        }

        String temp_2=temp_1+"1";
        int length_temp_2 = temp_2.length();
        int length_test = test_to_compare.length();
        if (length_temp_2 == length_test)
        {
            if ((test_to_compare).equals(temp_2)){
                output.setText("You win!!!!");

                integer_to_enter ++;

                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
            else{
                output.setText("You fail!!!!");

                integer_to_enter=1;

                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
        }else {
            output.setText(temp_2);
        }
    }

    public void Modify_string_to_zero(View vieuw) {

        String test_to_compare= Integer.toBinaryString(integer_to_enter);

        TextView output = (TextView)findViewById(R.id.output);
        String temp_1 = (String) output.getText().toString();

        if (((temp_1).equals("You win!!!!")) ||((temp_1).equals("You fail!!!!")) ){
            temp_1="";
        }

        String temp_2=temp_1+"0";
        int length_temp_2 = temp_2.length();
        int length_test = test_to_compare.length();
        if (length_temp_2 == length_test)
        {
            if ((test_to_compare).equals(temp_2)){
                output.setText("You win!!!!");

                integer_to_enter ++;

                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
            else{
                output.setText("You fail!!!!");

                integer_to_enter=1;

                String integer_to_display = ""+integer_to_enter;
                TextView int_to_disp = (TextView)findViewById(R.id.int_to_disp);
                int_to_disp.setText(integer_to_display);
            }
        }else {
            output.setText(temp_2);
        }
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