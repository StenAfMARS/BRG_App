package grp02.brg_app.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.IDatabaseConnector;
import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.Control.TextController;
import grp02.brg_app.Model.RecipeAdapter;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.OnPressedBryg;
import grp02.brg_app.View.HistorikFragments.RecipeList;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    public static SharedPreferences sharedPref;
    public static MainActivity Instance;

    public static MainActivity getInstance() {
        if(Instance == null) {
            Instance = new MainActivity();
        }

        return Instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        TextController.getInstance().setResources(getResources());

        DatabaseController.getInstance().UseSQL(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = getIntent();
                if(intent.getBooleanExtra("Frag",false) == true){
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.ShowBrewAnimation, new OnPressedBryg(getApplicationContext()))
                            .commit();
                    TextView headerText = (TextView) findViewById(R.id.TVPreferencesTitle3);
                    headerText.setVisibility(View.GONE);

                }
                else if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.FLHistorikOpenCards, new RecipeList(DatabaseController.getInstance().getDB().getAllFavorites()))  // tom container i layout
                            .commit();
                }
                sharedPref = getPreferences(Context.MODE_PRIVATE);

                // Set DateTime
                RecipeFactoryController.getInstance().setDateTime(LogicController.getInstance().getCurrentDateTime());

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
            }

        }, 100);   //5 seconds


    }
    public void getFragment(){
        Intent intent = new Intent(MainActivity.context,MainActivity.class);
        intent.putExtra("Frag",true);
        context.startActivity(intent);
    }
}