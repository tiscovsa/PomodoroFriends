package com.example.pomodorofriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class LoginActivity extends AppCompatActivity {

    Button register,login;
    EditText inputEmail, inputPassword;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_goto_register);
        progressBar = findViewById(R.id.progress);

        inputEmail = findViewById(R.id.et_email);
        inputPassword = findViewById(R.id.et_password);

        register.setOnClickListener(v-> goToRegister());
        login.setOnClickListener(v-> login());
    }

    private void login() {
        EditText email_edt = findViewById(R.id.et_email);
        String email = email_edt.getText().toString();
        EditText password_edt = findViewById(R.id.et_password);
        String password = email_edt.getText().toString();
    }

    private void goToRegister() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    private void goToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}