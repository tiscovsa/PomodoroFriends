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

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

public class RegisterActivity extends AppCompatActivity {
    Button register,cancel;
    EditText inputFullName, inputUsername, inputPassword, inputEmail;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cancel = findViewById(R.id.btn_cancel);
        register = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progress);

        inputFullName = findViewById(R.id.et_name);
        inputUsername = findViewById(R.id.et_username);
        inputPassword = findViewById(R.id.et_password);
        inputEmail = findViewById(R.id.et_email);

        cancel.setOnClickListener(v-> goToLogin());
        register.setOnClickListener(v-> register());



    }

    private void register() {
        if( inputFullName.getText().toString().length() == 0 )
        {
            inputFullName.setError( "Full name is required!" );
            this.recreate();
        }

        if( inputUsername.getText().toString().length() == 0 )
        {
            inputUsername.setError( "Username is required!" );
            this.recreate();
        }

        if( inputEmail.getText().toString().length() == 0 )
        {
            inputEmail.setError( "Email is required!" );
            this.recreate();
        }

        if( inputPassword.getText().toString().length() == 0 )
        {
            inputPassword.setError( "Password is required!" );
            this.recreate();
        }


        EditText repassword_edt = findViewById(R.id.et_repassword);
        if( repassword_edt.getText().toString().equals(inputPassword.getText().toString()) == false)
        {
            repassword_edt.setError( "Passwords do not match" );
            this.recreate();
        }
        Log.i("1234","Added User");
        addUser(inputFullName.getText().toString(),inputUsername.getText().toString(),inputEmail.getText().toString(),repassword_edt.getText().toString() );
    }

    private void addUser(String name,String username, String email, String password) {




        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[4];
                field[0] = "fullname";
                field[1] = "username";
                field[2] = "password";
                field[3] = "email";
                //Creating array for data
                String[] data = new String[4];
                data[0] = name;
                data[1] = username;
                data[2] = password;
                data[3] = email;
                PutData putData = new PutData("http://192.168.0.123/LoginRegister/signup.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        progressBar.setVisibility(View.GONE);
                        String result = putData.getResult();
                        if(result.equals("Sign Up Success")){
                           goToLogin();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                        }
                        //End ProgressBar (Set visibility to GONE)
                        Log.i("PutData", result);
                    }
                }
                //End Write and Read data with URL
            }
        });

        Log.i("1234","Register Success");

        Log.i("1234","Register failed");
    }


    private void goToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}