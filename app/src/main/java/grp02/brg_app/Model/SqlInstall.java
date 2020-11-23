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
        db.execSQL(getTableQueries(2));
        db.execSQL(getTableQueries(3));
    }

    public String getTableQueries(int queryId) {
        return createQueries.get(queryId);
    }

    public void createQueryStrings() {
        createQueries.put(1, "CREATE TABLE Recipes (\n" +
                "RecipeID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "RecipeName TEXT NOT NULL,\n" +
                "GrindSize TEXT NOT NULL,\n" +
                "Coffee/water INTEGER NOT NULL,\n" +
                "BrewingTemperature INTEGER NOT NULL,\n" +
                "BloomWater INTEGER NOT NULL,\n" +
                "BloomTime INTEGER NOT NULL\n" +
                ")");

        createQueries.put(2, "CREATE TABLE History (\n" +
                "fk_RecipeID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "timeOfBrew TEXT NOT NULL\n" +
                ")");

        createQueries.put(3, "CREATE TABLE Preferences (\n" +
                "fk_RecipeID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                ")");
    }
}
