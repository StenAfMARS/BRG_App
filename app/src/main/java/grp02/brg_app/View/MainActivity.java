package grp02.brg_app.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.IDatabaseConnector;
import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.Model.PreferencesAdapter;
import grp02.brg_app.Model.RecipesAdapter;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.OnPressedBryg;

public class MainActivity extends AppCompatActivity {
    public static Context context;
    public static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        System.out.println("nej");
        DatabaseController.getInstance().UseSQL(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = getIntent();
                if(intent.getBooleanExtra("Frag",false) == true){
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.ShowBrewAnimation, new OnPressedBryg(getApplicationContext()))  // tom container i layout
                            .commit();
                    TextView headerText = (TextView) findViewById(R.id.TVPreferencesTitle3);
                    headerText.setVisibility(View.GONE);

                }
                else{
                    InitPreferencesList(DatabaseController.getInstance().getDB());

                }
                sharedPref = getPreferences(Context.MODE_PRIVATE);

                // Set DateTime
                RecipeFactoryController.getInstance().setBrewDateTime(LogicController.getInstance().getCurrentDateTime());

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
                //InitRecipesList(DatabaseController.getInstance().getDB());
            }

        }, 10);   //5 seconds


    }
    public void getFragment(){
        Intent intent = new Intent(MainActivity.context,MainActivity.class);
        intent.putExtra("Frag",true);
        context.startActivity(intent);
    }
    private void InitPreferencesList(IDatabaseConnector db){
        ListView listView = findViewById(R.id.PreferencesCardList);

        PreferencesAdapter adapter = new PreferencesAdapter(this, db.getAllFavorites());
        listView.setAdapter(adapter);

    }
/*    private void InitRecipesList(IDatabaseConnector db){
        ListView listView = findViewById(R.id.RecipesCardList);

        RecipesAdapter adapter = new RecipesAdapter(this, db.getRecipes());
        listView.setAdapter(adapter);

    }

 */
}