package com.example.emsismartpresence;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_settings) {
            Toast.makeText(this, "Paramètres", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.item_height) {
            Toast.makeText(this, "Valeur", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.item_about) {
            Toast.makeText(this, "À propos", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.item_logout) {
            // Action pour déconnexion
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

