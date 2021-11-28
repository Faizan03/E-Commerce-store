package com.example.myecommercestore.usercredentialActivity.fragmentsUserCredentials;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myecommercestore.MainActivity;
import com.example.myecommercestore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {
   private FirebaseAuth mAuth;
   private Activity activity;
    private EditText logInemail,logInpassword;
    private Button signInBtn;
    private ProgressDialog progressDialog;

    public LoginFragment(){}
    public LoginFragment(Activity activity) {
     this.activity=activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(activity);
        progressDialog.setMessage("Logging in");
        if(mAuth.getCurrentUser()!=null){
            Intent i=new Intent(activity,MainActivity.class);
            startActivity(i);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_login, container, false);
          signInBtn=view.findViewById(R.id.logInBtn);
          logInemail=view.findViewById(R.id.LogInEmailAddress);
          logInpassword=view.findViewById(R.id.LogInPassword);

          signInBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                try {
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(logInemail.getText().toString(), logInpassword.getText().toString()).
                            addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(activity, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(activity, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(activity, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            });
                }catch (Exception e){
                    progressDialog.dismiss();
                    Toast.makeText(activity,e.getMessage(),Toast.LENGTH_LONG).show();
                }
              }
          });


        return view;
    }
}