package com.example.cashincashoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    EditText oldpass, newpass;
    LoginActivity pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        oldpass = findViewById(R.id.passwordCurrent);
        newpass = findViewById(R.id.passwordNew);
        pass = new LoginActivity();

        Button settingsave = findViewById(R.id.passwordSaveBtn);
        Button settingreturn = findViewById(R.id.passwordReturnBtn);

        settingreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, MainActivity.class));
            }
        });

        settingsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getPass().equals(oldpass.getText().toString())){
                    oldpass = newpass;
                    pass.setPass(newpass.getText().toString());
                }
            }
        });
    }
}
