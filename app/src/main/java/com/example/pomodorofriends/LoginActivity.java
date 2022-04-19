package com.example.pomodorofriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


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

        final String email,password;
        email = String.valueOf(inputEmail.getText());
        password = String.valueOf(inputPassword.getText());

        if(!email.equals("") && !password.equals("")) {

            progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "email";
                    field[1] = "password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = password;
                    PutData putData = new PutData("http://192.168.0.123/LoginRegister/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            if (result.equals("Login Success")) {
                                goToMain();
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            }
                            //End ProgressBar (Set visibility to GONE)
                            Log.i("PutData", result);
                        }
                    }
                    //End Write and Read data with URL
                }
            });
        }
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