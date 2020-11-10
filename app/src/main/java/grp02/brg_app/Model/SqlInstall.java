package grp02.brg_app.Model;
import android.database.sqlite.SQLiteDatabase;
/*this file is only used first time the app is ever launch*/
public class SqlInstall{
    /*call following functions first time*/
    public void storageConstructors(SQLiteDatabase db){
        db.execSQL(createRecipies());
        db.execSQL(createHistory());
        db.execSQL(createPrefferences());
    }
    public String createRecipies(){


        String createRecipies =
                "CREATE TABLE Reciepies (\n" +
                        "    ReciepiesID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    ReciepiesName TEXT NOT NULL,\n" +
                        "    GrindSize TEXT NOT NULL,\n" +
                        "    Coffee/water INTEGER NOT NULL,\n" +
                        "    BrewingTempreture INTEGER NOT NULL,\n" +
                        "    BloomWater INTEGER NOT NULL,\n" +
                        "    BloomTime INTEGER NOT NULL\n" +
                        ")";
        return createRecipies;
        }
    public String createHistory(){
        String createHistory =
                "CREATE TABLE History (\n" +
                    "    fk_ReciepiesID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    timeOfBrew TEXT NOT NULL\n" +
                ")";
        return createHistory;
    }
    public String createPrefferences(){
        String createPrefferences =
                "CREATE TABLE Prefferences (\n" +
                        "    fk_ReciepiesID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        ")";
        return createPrefferences;
    }
}
