package com.example.cashincashoutapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerDialogue extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        Calendar appCalendar = Calendar.getInstance();
        int year = appCalendar.get(Calendar.YEAR);
        int month = appCalendar.get(Calendar.MONTH);
        int day = appCalendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)
            getActivity(), year, month, day);
    }
}
