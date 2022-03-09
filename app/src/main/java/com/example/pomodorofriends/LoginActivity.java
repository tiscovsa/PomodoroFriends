package com.example.pomodorofriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class LoginActivity extends AppCompatActivity {

    Button register,login;
    String Appid = "attendanceapp-sknlf";
    App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.btn_goto_register);
        register.setOnClickListener(v-> goToRegister());
        login.setOnClickListener(v-> login());
    }

    private void login() {
        EditText email_edt = findViewById(R.id.et_email);
        String email = email_edt.getText().toString();
        EditText password_edt = findViewById(R.id.et_password);
        String password = email_edt.getText().toString();
        app = new App(new AppConfiguration.Builder(Appid).build());
        Credentials credentials = Credentials.emailPassword(email,password);
        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess())
                {
                    Log.i("1234","Logged in Successfully");
                    goToMain();
                }
                else
                {
                    Log.i("1234","Log in failed");
                    email_edt.setError( "Email or password incorrect!" );
                    password_edt.setError( "Email or password incorrect!" );
                }
            }
        });
        //this.recreate();
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