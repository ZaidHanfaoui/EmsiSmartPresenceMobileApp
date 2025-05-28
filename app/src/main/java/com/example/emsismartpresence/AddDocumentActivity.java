package com.example.emsismartpresence;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddDocumentActivity extends AppCompatActivity {

    private long selectedDateMillis = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_document);


        EditText titreEditText = findViewById(R.id.titreEditText);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        EditText urlEditText = findViewById(R.id.urlEditText);
        TextView dateTextView = findViewById(R.id.dateTextView);
        ImageButton dateButton = findViewById(R.id.dateButton);
        Button saveButton = findViewById(R.id.saveButton);
        // Affiche la date du jour par défaut
        Calendar calendar = Calendar.getInstance();
        updateDateTextView(dateTextView, calendar);

        dateButton.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        calendar.set(selectedYear, selectedMonth, selectedDay);
                        selectedDateMillis = calendar.getTimeInMillis();
                        updateDateTextView(dateTextView, calendar);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        saveButton.setOnClickListener(v -> {
            String titre = titreEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String url = urlEditText.getText().toString();
            long dateAjout = selectedDateMillis;

            // TODO : Sauvegarder le document (ex : en base de données ou envoyer à un serveur)
            // Exemple :
            // Document doc = new Document("id", titre, description, url, dateAjout);
            // ...
            finish();
        });
    }

    private void updateDateTextView(TextView textView, Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        textView.setText("📅 " + day + "/" + month + "/" + year);
    }
}