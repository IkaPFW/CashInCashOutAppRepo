package com.example.cashincashoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    Button login;
    DataHelper dbHelper;

    String passTxt = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.inputUser);
        pass = findViewById(R.id.inputPass);
        login = findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equals("user") && pass.getText().toString().equals(passTxt)){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }

    public String getPass(){
        return passTxt;
    }

    public void setPass(String password){this.passTxt = password;}
}
