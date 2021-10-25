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

public class IncomeActivity extends AppCompatActivity {

    protected Cursor cursor;
    EditText incomedate;
    private int curYear, curMonth, curDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        DataHelper dbHelper = new DataHelper(this);
        incomedate = findViewById(R.id.incomeDate);
        ImageButton incomedatebtn = findViewById(R.id.incomeDateBtn);

        EditText incomenominal = findViewById(R.id.incomeNominal);
        EditText incomedesc = findViewById(R.id.incomeDesc);

        Button incomesave = findViewById(R.id.incomeSaveBtn);
        Button incomereturn = findViewById(R.id.incomeReturnBtn);

        incomereturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IncomeActivity.this, MainActivity.class));
            }
        });

        incomedatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar myCalendar = Calendar.getInstance();
                curYear = myCalendar.get(Calendar.YEAR);
                curMonth = myCalendar.get(Calendar.MONTH);
                curDay = myCalendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePick = new DatePickerDialog(IncomeActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        incomedate.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                }, curYear, curMonth, curDay);
                datePick.show();
            }
        });

        incomesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO income_cashflow_table(nominal, description, date, cashflow_type) " +
                        "VALUES('" + incomenominal.getText().toString() + "','"
                        + incomedesc.getText().toString() + "','" + incomedate.getText().toString() + "','+');");
                finish();
            }
        });
    }

//    public void onDateSet(DatePickerDialogue view, int year, int month, int day){
//        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(myCalendar.getTime());
//        incomedate.setText(selectedDate);
//    }
}
