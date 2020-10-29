package grp02.brg_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BroegActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broeg1);

        // Navigation
        // ##########################################################
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        // Set nav highlighted button
        bottomNav.setSelectedItemId(R.id.nav_BroegBtn);
        // Perform ItemSelectedListener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_HomeBtn:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_BroegBtn:
                        return true;
                    case R.id.nav_HistorikBtn:
                        startActivity(new Intent(getApplicationContext(), HistorikActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_RensBtn:
                        startActivity(new Intent(getApplicationContext(), RensActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_IndstillingerBtn:
                        startActivity(new Intent(getApplicationContext(), IndstillingerActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        // ##########################################################
    }
}