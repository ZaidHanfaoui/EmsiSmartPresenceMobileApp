package com.example.emsismartpresence;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView toregister; // Add this TextView

    private final String validEmail = "user@example.com";
    private final String validPassword = "123456";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        toregister = findViewById(R.id.tv_register); // Initialize the TextView
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });

        // Set OnClickListener for the "toregister" TextView
        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to the RegisterActivity
                Intent intent = new Intent(SignInActivity.this, RegisterActivty.class); // Replace RegisterActivity.class with the actual class name of your register layout
                startActivity(intent); // Start the RegisterActivity
            }
        });
    }

    private void authenticateUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.equals(validEmail) && password.equals(validPassword)) {
            Toast.makeText(this, "Authentification réussie !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Connexion reussie", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignInActivity.this, DashboardActivity.class);
                        startActivity(i); // Start the Home activity upon successful login
                        finish(); // Optional: Close the Signin activity so the user can't go back without logging out
                    } else
                        Toast.makeText(this, "Connexion non réeussie" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
    }

