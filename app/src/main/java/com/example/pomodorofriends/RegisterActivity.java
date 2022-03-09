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

public class RegisterActivity extends AppCompatActivity {
    Button register,cancel;
    String Appid = "attendanceapp-sknlf";
    private App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cancel = findViewById(R.id.btn_cancel);
        register = findViewById(R.id.btn_register);

        cancel.setOnClickListener(v-> goToLogin());
        register.setOnClickListener(v-> register());
    }

    private void register() {
        EditText name_edt = findViewById(R.id.et_name);
        if( name_edt.getText().toString().length() == 0 )
        {
            name_edt.setError( "Full name is required!" );
            this.recreate();
        }

        EditText email_edt = findViewById(R.id.et_email);
        if( email_edt.getText().toString().length() == 0 )
        {
            email_edt.setError( "Email is required!" );
            this.recreate();
        }


        EditText password_edt = findViewById(R.id.et_password);
        if( password_edt.getText().toString().length() == 0 )
        {
            password_edt.setError( "Password is required!" );
            this.recreate();
        }


        EditText repassword_edt = findViewById(R.id.et_repassword);
        if( repassword_edt.getText().toString().equals(password_edt.getText().toString()) == false)
        {
            repassword_edt.setError( "Passwords do not match" );
            this.recreate();
        }
        Log.i("1234","Added User");
        addUser(name_edt.getText().toString(),email_edt.getText().toString(),repassword_edt.getText().toString() );
        register.setOnClickListener(v-> goToLogin());
    }

    private void addUser(String name, String email, String password) {
        app = new App(new AppConfiguration.Builder(Appid).build());
        app.getEmailPassword().registerUserAsync(email,password,it ->{
            if(it.isSuccess())
            {
                Log.i("1234","Register Success");
            }
            else
            {
                Log.i("1234","Register failed");
            }
        });
    }


    private void goToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}