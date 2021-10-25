package com.example.cashincashoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    DataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton incomebtn = findViewById(R.id.incomeBtn);
        ImageButton outcomebtn = findViewById(R.id.outcomeBtn);
        ImageButton detailbtn = findViewById(R.id.detailBtn);
        ImageButton settingbtn = findViewById(R.id.settingBtn);

        TextView incometxt = findViewById(R.id.income);
        TextView outcometxt = findViewById(R.id.outcome);

        GraphView graph = findViewById(R.id.graph);

        db = new DataHelper(this);
        SQLiteDatabase database = db.getReadableDatabase();

        incometxt.setText(String.valueOf(incomeSum()));
        outcometxt.setText(String.valueOf(outcomeSum()));

        LineGraphSeries<DataPoint> dataseries = new LineGraphSeries<>(new DataPoint[0]);

        getGraphData();
        graph.addSeries(dataseries);
        dataseries.setColor(Color.GREEN);
        dataseries.setThickness(8);
        dataseries.setDataPointsRadius(10);

        incomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IncomeActivity.class));
            }
        });

        outcomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OutcomeActivity.class));
            }
        });

        detailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }

    public int incomeSum(){
        int total = 0;
        Cursor sumQuery = db.getReadableDatabase().rawQuery("SELECT SUM(nominal) FROM income_cashflow_table", null);
        if (sumQuery.moveToFirst()){
            total = sumQuery.getInt(0);
        }
        return total;
    }

    public int outcomeSum(){
        int total = 0;
        Cursor sumQuery = db.getReadableDatabase().rawQuery("SELECT SUM(nominal) FROM outcome_cashflow_table", null);
        if (sumQuery.moveToFirst()){
            total = sumQuery.getInt(0);
        }
        return total;
    }

    private DataPoint[] getGraphData(){
        Cursor cur = db.getReadableDatabase().rawQuery("SELECT nominal FROM income_cashflow_table",
                null);
        DataPoint[] dp = new DataPoint[cur.getCount()];

        for (int i = 0; i < cur.getCount(); i++){
            cur.moveToNext();
            dp[i] = new DataPoint(i, cur.getInt(0));
        }

        return dp;
    }
}