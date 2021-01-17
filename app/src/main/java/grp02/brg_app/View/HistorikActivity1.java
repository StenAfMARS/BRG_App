package grp02.brg_app.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.IDatabaseConnector;
import grp02.brg_app.Model.HistoryAdapter;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.NameStart;
import grp02.brg_app.View.HistorikFragments.HistorikList;

public class HistorikActivity1 extends AppCompatActivity {

    public static HistorikActivity1 historikActivity1;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historik1);

        context = this;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.FLHistorikOpenCards, new HistorikList())  // tom container i layout
                    .commit();
        }

        // Navigation
        // ##########################################################
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        // Set nav highlighted button
        bottomNav.setSelectedItemId(R.id.nav_HistorikBtn);
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
                        startActivity(new Intent(getApplicationContext(), BroegActivity1.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_HistorikBtn:
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

    public static HistorikActivity1 getInstance() {
        if(historikActivity1 == null) {
            historikActivity1 = new HistorikActivity1();
        }
        return historikActivity1;
    }

}