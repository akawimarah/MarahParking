package com.akawi.marah.marahparking;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class payActivity extends AppCompatActivity {
private Button btnStart , btnEnd;
    private TextView tvStart, tvEnd;
    private ImageButton btnSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnEnd=(Button)findViewById(R.id.btnEnd);
        tvStart=(TextView)findViewById(R.id.tvStart);
        tvEnd=(TextView)findViewById(R.id.tvEnd);
        btnSong =(ImageButton)findViewById(R.id.song);
         eventHandler();

    }
   private void eventHandler(){
       btnStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar mcurrentTime = Calendar.getInstance();
               int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
               int minute = mcurrentTime.get(Calendar.MINUTE);
               TimePickerDialog mTimePicker;
               mTimePicker = new TimePickerDialog(payActivity.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                      tvStart.setText( selectedHour + ":" + selectedMinute);
                   }
               }, hour, minute, true);//Yes 24 hour time
               mTimePicker.setTitle("Select Time");
               mTimePicker.show();

           }
       });
      btnEnd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar mcurrentTime = Calendar.getInstance();
               int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
               int minute = mcurrentTime.get(Calendar.MINUTE);
               TimePickerDialog mTimePicker;
               mTimePicker = new TimePickerDialog(payActivity.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                      tvEnd.setText( selectedHour + ":" + selectedMinute);
                   }
               }, hour, minute, true);//Yes 24 hour time
               mTimePicker.setTitle("Select Time");
               mTimePicker.show();

           }
       });
       btnSong.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

   }



}
