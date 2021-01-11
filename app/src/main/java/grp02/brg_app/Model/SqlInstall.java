package grp02.brg_app.Model;
import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;

/*this file is only used first time the app is ever launch*/
public class SqlInstall{
    /*call following functions first time*/

    private final HashMap<Integer, String> createQueries = new HashMap<>();

    public void storageConstructors(SQLiteDatabase db){
        createQueryStrings();
        db.execSQL(getTableQueries(1));
        //db.execSQL(getTableQueries(2));
        //  db.execSQL(getTableQueries(3));
    }

    public String getTableQueries(int queryId) {
        return createQueries.get(queryId);
    }

    private void createQueryStrings() {
        createQueries.put(1, "CREATE TABLE Recipes (RecipeID INTEGER PRIMARY KEY AUTOINCREMENT, RecipeName TEXT NOT NULL, GroundCoffee INTEGER NOT NULL, GrindSize TEXT NOT NULL, WaterToCoffee INTEGER NOT NULL, BrewingTemperature INTEGER NOT NULL, BloomWater INTEGER NOT NULL, BloomTime INTEGER NOT NULL)");

        createQueries.put(2, "CREATE TABLE History (fk_RecipeID INTEGER PRIMARY, timeOfBrew TEXT NOT NULL)");

        createQueries.put(3, "CREATE TABLE Preferences ( fk_RecipeID INTEGER PRIMARY)");
    }
    private void addPrecreatedRecipes()
    {

    }
}
