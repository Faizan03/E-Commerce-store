package com.example.myecommercestore.usercredentialActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myecommercestore.R;
import com.example.myecommercestore.usercredentialActivity.fragmentsUserCredentials.LoginFragment;
import com.example.myecommercestore.usercredentialActivity.fragmentsUserCredentials.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity  {

    private TextView textView;
    private TextView question;
    private FragmentManager fragmentManager;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        textView=findViewById(R.id.textView);
        question=findViewById(R.id.QuestionAboutAccountExistence);
        fragmentManager=getSupportFragmentManager();

        if(findViewById(R.id.frameLayout)!=null){
            if(savedInstanceState!=null){
                return;
            }
        }

        fragmentManager.beginTransaction().replace(R.id.frameLayout,new LoginFragment(SignUpActivity.this)).commit();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textView.getText().toString().equals("Sign up")){

                    fragmentManager.beginTransaction().replace(R.id.frameLayout,new SignUpFragment(SignUpActivity.this)).commit();


                    textView.setText(R.string.log_in);
                    question.setText(R.string.LogInQuestion);
                }
               else if(textView.getText().toString().equals("Log in")){

                    fragmentManager.beginTransaction().replace(R.id.frameLayout,new LoginFragment(SignUpActivity.this)).commit();

                    textView.setText(R.string.sign_up);
                    question.setText(R.string.SignUpQuestion);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Fragment fragment=fragmentManager.findFragmentById(R.id.frameLayout);
        if(fragment instanceof LoginFragment){

            super.onBackPressed();
            finish();
        }
        else{
            textView.setText(R.string.sign_up);
            question.setText(R.string.SignUpQuestion);
            fragmentManager.beginTransaction().replace(R.id.frameLayout,new LoginFragment(this)).commit();
        }
    }
}