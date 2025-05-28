package com.example.emsismartpresence;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MarkAbsenceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_absence);

        AutoCompleteTextView group = findViewById(R.id.group);
        AutoCompleteTextView site = findViewById(R.id.site);
        EditText studentNameEditText = findViewById(R.id.studentNameEditText);
        EditText remarksEditText = findViewById(R.id.remarksEditText);
        FloatingActionButton saveButton = findViewById(R.id.saveButton);

        ImageButton dateButton = findViewById(R.id.dateButton);
        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);

        dateButton.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        selectedDateTextView.setText("📅 " + date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        // TODO : Ajouter l’adaptateur pour les AutoCompleteTextView si besoin
        // TODO : Ajouter la logique d’enregistrement sur saveButton
    }
}