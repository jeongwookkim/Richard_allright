package com.example.richardallright;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class Agree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        final TextView textView = (TextView)findViewById(R.id.textView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month,

                                            int dayOfMonth) {

                // TODO Auto-generated method stu
                textView.setText(String.valueOf(year) +"년"+ String.valueOf(month)+"월" + String.valueOf(dayOfMonth)+"일");

            }

        });
    }


}