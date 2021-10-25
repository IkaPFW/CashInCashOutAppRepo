package com.example.cashincashoutapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class OutcomeActivity extends AppCompatActivity {

    protected Cursor cursor;
    EditText outcomedate;
    private int curYear, curMonth, curDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);

        DataHelper dbHelper = new DataHelper(this);
        outcomedate = findViewById(R.id.outcomeDate);
        ImageButton outcomedatebtn = findViewById(R.id.outcomeDateBtn);

        EditText outcomenominal = findViewById(R.id.outcomeNominal);
        EditText outcomedesc = findViewById(R.id.outcomeDesc);

        Button outcomesave = findViewById(R.id.outcomeSaveBtn);
        Button outcomereturn = findViewById(R.id.outcomeReturnBtn);

        outcomereturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OutcomeActivity.this, MainActivity.class));
            }
        });

        outcomedatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                curYear = myCalendar.get(Calendar.YEAR);
                curMonth = myCalendar.get(Calendar.MONTH);
                curDay = myCalendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePick = new DatePickerDialog(OutcomeActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        outcomedate.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, curYear, curMonth, curDay);
                datePick.show();
            }
        });

        outcomesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO outcome_cashflow_table(nominal, description, date, cashflow_type) " +
                        "VALUES('" + outcomenominal.getText().toString() + "','"
                        + outcomedesc.getText().toString() + "','" + outcomedate.getText().toString() + "','-');");
                finish();
            }
        });
    }

//    public void onDateSet(DatePickerDialogue view, int year, int month, int day){
//        Calendar myCalendar = Calendar.getInstance();
//        myCalendar.set(Calendar.YEAR, year);
//        myCalendar.set(Calendar.MONTH, month);
//        myCalendar.set(Calendar.DAY_OF_MONTH, day);
//        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(myCalendar.getTime());
//        outcomedate.setText(selectedDate);
//    }
}
