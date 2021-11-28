package com.example.myecommercestore.usercredentialActivity.fragmentsUserCredentials;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myecommercestore.MainActivity;
import com.example.myecommercestore.R;
import com.example.myecommercestore.usercredentialActivity.UserModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private EditText email,password,username;
    private Button signUpBtn;
    private Activity activity;
    private ProgressDialog progressDialog;
    public SignUpFragment(Activity activity) {
      this.activity=activity;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(activity);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        email=view.findViewById(R.id.SignUpEmailAddress);
        password=view.findViewById(R.id.SignUpPassword);
        username=view.findViewById(R.id.userName);
        signUpBtn=view.findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).
                            addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        UserModelClass user = new UserModelClass(username.getText().toString(), email.getText().toString(), password.getText().toString());
                                        String id = task.getResult().getUser().getUid();
                                        firebaseDatabase.getReference().child("Users").child(id).setValue(user);

                                        Toast.makeText(activity, "User created succesfully", Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(activity, MainActivity.class);
                                        startActivity(i);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(activity, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(activity,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            });

        return view;
    }
}