package com.example.cashincashoutapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHelper extends SQLiteOpenHelper {

    private static final String database_name = "cashApp.db";
    private static final int database_version = 2;

    public static final String table_income = "income_cashflow_table";
    public static final String table_outcome = "outcome_cashflow_table";
    public static final String cashflow_id = "id";
    public static final String cashflow_nominal = "nominal";
    public static final String cashflow_desc = "description";
    public static final String cashflow_date = "date";
    public static final String cashflow_type = "cashflow_type";

    private String TBL_CREATE_INCOME_CASHFLOW = "CREATE TABLE " + table_income +
            " (" + cashflow_id + " INTEGER PRIMARY KEY, " + cashflow_date +
            " TEXT, " + cashflow_type + " TEXT, " + cashflow_nominal +
            " INTEGER, " + cashflow_desc + " TEXT)";

    private String TBL_CREATE_OUTCOME_CASHFLOW = "CREATE TABLE " + table_outcome +
            " (" + cashflow_id + " INTEGER PRIMARY KEY, " + cashflow_date +
            " TEXT, " + cashflow_type + " TEXT, " + cashflow_nominal +
            " INTEGER, " + cashflow_desc + " TEXT)";

    public DataHelper(Context context){
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TBL_CREATE_INCOME_CASHFLOW);
        Log.d("income", "Table " + TBL_CREATE_INCOME_CASHFLOW + " created");
        db.execSQL(TBL_CREATE_OUTCOME_CASHFLOW);
        Log.d("outcome", "Table " + TBL_CREATE_OUTCOME_CASHFLOW + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_income);
        db.execSQL("DROP TABLE IF EXISTS " + table_outcome);
        onCreate(db);
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetIncomeDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> detailList = new ArrayList<>();
        String query = "SELECT date, cashflow_type, nominal, description FROM " + table_income;
        Cursor cur = db.rawQuery(query, null);
        while (cur.moveToNext()){
            HashMap<String, String> details = new HashMap<>();
            details.put("date", cur.getString(cur.getColumnIndex(cashflow_date)));
            details.put("cashflow_type", cur.getString(cur.getColumnIndex(cashflow_type)));
            details.put("nominal", cur.getString(cur.getColumnIndex(cashflow_nominal)));
            details.put("description", cur.getString(cur.getColumnIndex(cashflow_desc)));
            detailList.add(details);
        }
        return detailList;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetOutcomeDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> detailList = new ArrayList<>();
        String query = "SELECT date, cashflow_type, nominal, description FROM " + table_outcome;
        Cursor cur = db.rawQuery(query, null);
        while (cur.moveToNext()){
            HashMap<String, String> details = new HashMap<>();
            details.put("date", cur.getString(cur.getColumnIndex(cashflow_date)));
            details.put("cashflow_type", cur.getString(cur.getColumnIndex(cashflow_type)));
            details.put("nominal", cur.getString(cur.getColumnIndex(cashflow_nominal)));
            details.put("description", cur.getString(cur.getColumnIndex(cashflow_desc)));
            detailList.add(details);
        }
        return detailList;
    }
}
