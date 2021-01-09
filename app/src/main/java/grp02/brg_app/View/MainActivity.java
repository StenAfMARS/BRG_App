package grp02.brg_app.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import grp02.brg_app.Control.storageController;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.R;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grp02.brg_app.Control.storageController storageController = new storageController(MainActivity.this);
        if(storageController.getAllRecipes().size() == 0){
            storageController.addPrecreatedRecipes();
        }
        List<DTO_recipe> list = storageController.getAllRecipes();
        for (int i = 0; list.size()>i;i++){
            System.out.println(list.get(i).getRecipeName());
        }

        System.out.println(storageController.getAllRecipes());
        sharedPref = getPreferences(Context.MODE_PRIVATE);

        // Navigation
        // ##########################################################
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        // Set nav highlighted button
        bottomNav.setSelectedItemId(R.id.nav_HomeBtn);
        // Perform ItemSelectedListener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_HomeBtn:
                        return true;
                    case R.id.nav_BroegBtn:
                        startActivity(new Intent(getApplicationContext(), BroegActivity1.class));
                        overridePendingTransition(0, 0);
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