package com.example.britishamericantobacco;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView input_date , output_result;
    EditText  input_blender , input_op_number ;
    Spinner color ;
    Button input_submit;

    private int clickedDay, clickedMonth, clickedYear, currentday, currentMonth, currentYear ;
    private String selected_date;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_date = findViewById(R.id.date);
        input_blender = findViewById(R.id.blender);
        input_op_number = findViewById(R.id.op_number);

        color = findViewById(R.id.spinner1);

        input_submit = findViewById(R.id.submit);
        output_result = findViewById(R.id.result);

        currentday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        currentYear = Calendar.getInstance().get(Calendar.YEAR);



        input_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog();

            }
        });

        input_submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
               // Toast.makeText(MainActivity.this , ""+)
                output_result.setText("Date : "+selected_date+", Bender : "+input_blender.getText().toString()+", Operation no. : "+input_op_number.getText().toString()+", Color : "+color.getSelectedItem().toString());
            }
        });


    }


    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        this.clickedDay = dayOfMonth;
        this.clickedMonth = month;
        this.clickedYear = year;

        String conv_day, conv_month;

        if(month < 10){
            conv_month = "0" + month;
        } else {
            conv_month = String.valueOf(month);
        }

        if(dayOfMonth < 10){
            conv_day = "0" + dayOfMonth;
        } else {
            conv_day = String.valueOf(dayOfMonth);
        }

//        if (month < 10 && dayOfMonth >= 10) {
//            selected_date = "" + year + "-0" + month + "-" + dayOfMonth;
//        } else if (dayOfMonth < 10 && month > 10) {
//            selected_date = "" + year + "-" + month + "-0" + dayOfMonth;
//        } else if (month < 10 && dayOfMonth < 10) {
//            selected_date = "" + year + "-0" + month + "-0" + dayOfMonth;
//        } else {
//            selected_date = "" + year + "-" + month + "-" + dayOfMonth;
//        }

        selected_date = "" + year + "-" + conv_month + "-" + conv_day;

        verifySelectedDate();

        if (flag) {

//            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
//            alertDialogBuilder.setTitle("Appointment Date");
//            alertDialogBuilder.setMessage("You requested appointment date -"+selected_date+ " ,your request has been sent to the authority . For confirmation you will be called soon");
//            alertDialogBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                }
//            });
//            alertDialogBuilder.setCancelable(false);
//            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
            input_date.setText(selected_date);

        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Message")
                    .setMessage("Please select an Future date . Past date selection is not allowed.")
                    .setPositiveButton("Select Date", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showDatePickerDialog();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            alertDialog.setCancelable(false);
            alertDialog.show();

        }

    }

    public void verifySelectedDate() {
        if (clickedYear > currentYear) {
            flag = true;
        } else if (clickedYear == currentYear) {
            if (clickedMonth > currentMonth) {
                flag = true;
            } else if (clickedMonth == currentMonth) {
                flag = clickedDay >= currentday;
            }
        } else {
            flag = false;
        }
    }
}
