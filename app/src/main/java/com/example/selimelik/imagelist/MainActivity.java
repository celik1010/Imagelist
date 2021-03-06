package com.example.selimelik.imagelist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mEmailSignInButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Intent intentSignUp = new Intent(this, ContainerActivity.class);
            startActivity(intentSignUp);
        }


    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void signIn(View view) {
        mAuth.signInWithEmailAndPassword(mEmailView.getText().toString(), mPasswordView.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Intent intent = new Intent(getApplicationContext(), ContainerActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signUp(View view) {
        Intent intentSignUp = new Intent(this, SignUpActivity.class);
        startActivity(intentSignUp);
    }


}

