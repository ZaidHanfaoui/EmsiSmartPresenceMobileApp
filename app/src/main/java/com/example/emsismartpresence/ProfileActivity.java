package com.example.emsismartpresence;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        Button updateButton = findViewById(R.id.updateButton);

        // Afficher les informations actuelles
        if (currentUser != null) {
            editName.setText(currentUser.getDisplayName());
            editEmail.setText(currentUser.getEmail());
        }

        updateButton.setOnClickListener(v -> updateProfile());
    }

    private void updateProfile() {
        String newName = editName.getText().toString();
        String newEmail = editEmail.getText().toString();
        String newPassword = editPassword.getText().toString();

        if (currentUser != null) {
            // Mise à jour du nom
            if (!newName.isEmpty()) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newName)
                        .build();

                currentUser.updateProfile(profileUpdates)
                        .addOnSuccessListener(unused -> Toast.makeText(ProfileActivity.this,
                                "Nom mis à jour", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,
                                "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            // Mise à jour de l'email
            if (!newEmail.isEmpty()) {
                currentUser.updateEmail(newEmail)
                        .addOnSuccessListener(unused -> Toast.makeText(ProfileActivity.this,
                                "Email mis à jour", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,
                                "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            // Mise à jour du mot de passe
            if (!newPassword.isEmpty()) {
                currentUser.updatePassword(newPassword)
                        .addOnSuccessListener(unused -> Toast.makeText(ProfileActivity.this,
                                "Mot de passe mis à jour", Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,
                                "Erreur: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }
    }
}