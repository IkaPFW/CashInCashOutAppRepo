package com.example.cashincashoutapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ListView detaillist;
    Button returnbtn;
    DataHelper db;
    ArrayList<Model> detailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detaillist = findViewById(R.id.detailList);
        returnbtn = findViewById(R.id.detailReturnBtn);
        db = new DataHelper(this);
        detailList = new ArrayList<>();

//        incomeDetailList = db.GetIncomeDetails();
//        outcomeDetailList = db.GetOutcomeDetails();

        Cursor cur = db.getReadableDatabase().rawQuery(
                "SELECT date, nominal, description, cashflow_type FROM income_cashflow_table",
                null);
        cur.moveToFirst();
        for (int i = 0; i < cur.getCount(); i++) {
            cur.moveToPosition(i);
            @SuppressLint("Range") String nominal = cur.getString(cur.getColumnIndex("nominal"));
            detailList.add(new Model("","","",1,R.drawable.ic_launcher_background));
        }

//        for (int i = 0; 0 <= countOutcome(); i++){
//            Cursor cur = db.getReadableDatabase().rawQuery(
//                    "SELECT date, nominal, description, cashflow_type FROM outcome_cashflow_table",
//                    null);
//            String field2Add = null;
//            field2Add = cur.getString(i);
//            detailList .add(field2Add);
//            cur.close();
//        }

//        incomeList();
//        outcomeList();


        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this, MainActivity.class));
            }
        });
    }

    public long countIncome(){
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(sqlDB, "income_cashflow_table");
    }

    public long countOutcome(){
        SQLiteDatabase sqlDB = db.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(sqlDB, "outcome_cashflow_table");
    }

//    public void detailAdapter(){
//        ListAdapter incomeAdapter = new SimpleAdapter(DetailActivity.this, detailList, R.layout.detail_item_income,
//                new String[]{"date", "type", "nominal", "description"}, new int[]{R.id.detailIncomeItemFooter,
//                R.id.detailIncomeItemHeader, R.id.detailIncomeItemHeader, R.id.detailIncomeItem});
//        detaillist.setAdapter(incomeAdapter);
//    }

//    public void incomeList(){
//        ListAdapter incomeAdapter = new SimpleAdapter(DetailActivity.this, incomeDetailList, R.layout.detail_item_income,
//                new String[]{"date", "type", "nominal", "description"}, new int[]{R.id.detailIncomeItemFooter,
//                R.id.detailIncomeItemHeader, R.id.detailIncomeItemHeader, R.id.detailIncomeItem});
//        detaillist.setAdapter(incomeAdapter);
//    }
//
//    public void outcomeList(){
//        ListAdapter outcomeAdapter = new SimpleAdapter(DetailActivity.this, outcomeDetailList, R.layout.detail_item_outcome,
//                new String[]{"date", "type", "nominal", "description"}, new int[]{R.id.detailOutcomeItemFooter,
//                R.id.detailOutcomeItemHeader, R.id.detailOutcomeItemHeader, R.id.detailOutcomeItem});
//        detaillist.setAdapter(outcomeAdapter);
//    }
}
