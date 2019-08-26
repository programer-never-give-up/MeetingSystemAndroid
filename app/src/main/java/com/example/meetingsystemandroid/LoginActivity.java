package com.example.meetingsystemandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private Button mRegisterButton;
    private TextInputEditText mUsernameEdit;
    private TextInputEditText mPasswordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginButton = findViewById(R.id.btn_login);
        mRegisterButton = findViewById(R.id.btn_register);
        mUsernameEdit = findViewById(R.id.edit_login_username);
        mPasswordEdit = findViewById(R.id.edit_login_password);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsernameEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                Toast.makeText(LoginActivity.this,username+" "+password,Toast.LENGTH_SHORT).show();
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
