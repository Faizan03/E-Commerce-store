package com.example.myecommercestore.usercredentialActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myecommercestore.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        mAuth=FirebaseAuth.getInstance();
    }
}