package com.example.emsismartpresence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivty extends AppCompatActivity {
    private EditText etEmail, etPassword, confpassword;
    private Button btnRegister;
    private  EditText reg_name ;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_register);
        confpassword=findViewById(R.id.conf_password);
        mAuth = FirebaseAuth.getInstance();
        reg_name = findViewById(R.id.et_name);
        db=FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
    }



    private void store_user_firestore(String uid,String email,String name) {
        Map<String,Object> user =new HashMap<>() ;
        user.put("user_email",etEmail) ;
        user.put("date d'inscription ", new Timestamp(new Date())) ;
        user.put("name",name);
        db.collection("users").document(uid).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText( RegisterActivty.this, "Created user", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText( RegisterActivty.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerUser() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String compassionated = confpassword.getText().toString().trim();
        String name = reg_name.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        if (compassionated.equals(password)) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Connexion resussie", Toast.LENGTH_SHORT).show();
                            String userId = mAuth.getCurrentUser().getUid();
                            store_user_firestore(userId,email,name);
                            Intent i = new Intent(RegisterActivty.this, SignInActivity.class);
                            i.putExtra("name", name);
                            startActivity(i);
                        } else
                            Toast.makeText(this, "Connexion non réeussie" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    });
        }
    }

}

