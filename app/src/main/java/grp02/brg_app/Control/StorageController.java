package grp02.brg_app.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Model.DTO_recipe;

public class StorageController extends SQLiteOpenHelper implements IDatabaseConnector {

    static final int VERSION = 1;
    static final String DATABASE = "database.db";

    public StorageController(Context context) {
        super(context, DATABASE, null,VERSION);
    }

    private SQLiteDatabase db = this.getWritableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Recipes (RecipeID INTEGER PRIMARY KEY AUTOINCREMENT, RecipeName TEXT NOT NULL, GroundCoffee INTEGER NOT NULL, GrindSize TEXT NOT NULL, WaterToCoffee INTEGER NOT NULL, BrewingTemperature INTEGER NOT NULL, BloomWater INTEGER NOT NULL, BloomTime INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE History (fk_RecipeID INTEGER PRIMARY KEY, timeOfBrew TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Preferences ( fk_RecipeID INTEGER PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPrecreatedRecipes() {
        ContentValues  cv = new ContentValues();
        cv.put("RecipeName","test");
        cv.put("GroundCoffee", 1);
        cv.put("GrindSize","fine");
        cv.put("WaterToCoffee",1);
        cv.put("BrewingTemperature",1);
        cv.put("BloomWater",1);
        cv.put("BloomTime",1);
        System.out.println("test 2");
        db.insert("Recipes","",cv);
        System.out.println("test 3");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addRow(String tableName, int recipeID, boolean fromNewRecipe){
        ContentValues cv  = new ContentValues();
        switch (tableName){
            case"History":
                if(fromNewRecipe == true) {
                    String selectQuery = "SELECT * FROM Recipes WHERE   RecipeID = (SELECT MAX(RecipeID)  FROM Recipes);";

                    Cursor cursor = db.rawQuery(selectQuery, null);

                    // looping through all rows and adding to list
                    if (cursor.moveToFirst()) {
                        do {
                            recipeID = Integer.parseInt(cursor.getString(0));
                        } while (cursor.moveToNext());
                    }
                }
                LocalDateTime now = LocalDateTime.now();
                cv.put("fk_RecipeID",recipeID);
                cv.put("timeOfBrew",now.toString());
                db.insert(tableName,null,cv);
                break;
            case"Preferences":
                cv.put("fk_RecipeID",recipeID);
                db.insert(tableName,null,cv);
                break;
        }
    }

    @Override
    public List<DTO_recipe> getHistory() {
        List<DTO_recipe> recipeList = new ArrayList<DTO_recipe>();
        // Select All Query
        String selectQuery = "SELECT  *, History.timeOfBrew FROM Recipes INNER JOIN History ON History.fk_RecipeID = Recipes.RecipeID;";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeFactoryController.getInstance().setRecipeID(Integer.parseInt(cursor.getString(0)));
                RecipeFactoryController.getInstance().setGroundCoffee(cursor.getInt(1));
                RecipeFactoryController.getInstance().setGrindSize(cursor.getString(2));
                RecipeFactoryController.getInstance().setRecipeName(cursor.getString(3));
                RecipeFactoryController.getInstance().setWaterToCoffee(cursor.getFloat(4));
                RecipeFactoryController.getInstance().setBrewingTemperature(cursor.getInt(5));
                RecipeFactoryController.getInstance().setBloomWater(cursor.getInt(6));
                RecipeFactoryController.getInstance().setBloomTime(cursor.getInt(7));
                RecipeFactoryController.getInstance().setDateTime(cursor.getString(8));
                // Adding contact to list
                recipeList.add(RecipeFactoryController.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }

    @Override
    public List<DTO_recipe> getAllFavorites() {
        List<DTO_recipe> recipeList = new ArrayList<DTO_recipe>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Recipes INNER JOIN Preferences ON Preferences.fk_RecipeID = Recipes.RecipeID;";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeFactoryController.getInstance().setRecipeID(Integer.parseInt(cursor.getString(0)));
                RecipeFactoryController.getInstance().setGroundCoffee(cursor.getInt(1));
                RecipeFactoryController.getInstance().setGrindSize(cursor.getString(2));
                RecipeFactoryController.getInstance().setRecipeName(cursor.getString(3));
                RecipeFactoryController.getInstance().setWaterToCoffee(cursor.getFloat(4));
                RecipeFactoryController.getInstance().setBrewingTemperature(cursor.getInt(5));
                RecipeFactoryController.getInstance().setBloomWater(cursor.getInt(6));
                RecipeFactoryController.getInstance().setBloomTime(cursor.getInt(7));
                recipeList.add(RecipeFactoryController.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }

    @Override
    public void saveRecipe(DTO_recipe recipe) {
        ContentValues cv  = new ContentValues();
        cv.put("RecipeName", RecipeFactoryController.getInstance().getDto_recipe().getRecipeName());
        cv.put("GroundCoffee", RecipeFactoryController.getInstance().getDto_recipe().getGroundCoffee());
        cv.put("GrindSize", RecipeFactoryController.getInstance().getDto_recipe().getGrindSize());
        cv.put("WaterToCoffee", RecipeFactoryController.getInstance().getDto_recipe().getWaterToCoffee());
        cv.put("BrewingTemperature", RecipeFactoryController.getInstance().getDto_recipe().getBrewingTemperature());
        cv.put("BloomWater", RecipeFactoryController.getInstance().getDto_recipe().getBloomWater());
        cv.put("BloomTime", RecipeFactoryController.getInstance().getDto_recipe().getBloomTime());
        db.insert("Recipes",null,cv);
    }

    @Override
    public void deleteRecipe(String tableName, String tableRow,int ID) {
        db.delete(tableName, tableRow + "=" + ID, null);
    }
    public void deleteRecipies(){
        String tableRow = "RecipeID";
        String selectQuery = "SELECT * FROM Recipes WHERE   RecipeID = (SELECT MAX(RecipeID)  FROM Recipes);";

        Cursor cursor = db.rawQuery(selectQuery, null);
        int recipieID = 0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                recipieID = Integer.parseInt(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.delete("Recipes", tableRow + "=" + recipieID, null);
    }

    @Override
    public DTO_recipe getRecipe(int id) {
        return null;
    }

    // code comes from https://www.javatpoint.com/android-sqlite-tutorial
    @Override
    public List<DTO_recipe> getRecipes() {
        List<DTO_recipe> recipeList = new ArrayList<DTO_recipe>();

        String selectQuery = "SELECT  * FROM Recipes";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                RecipeFactoryController.getInstance().setRecipeID(Integer.parseInt(cursor.getString(0)));
                RecipeFactoryController.getInstance().setGrindSize(cursor.getString(2));
                RecipeFactoryController.getInstance().setRecipeName(cursor.getString(1));
                RecipeFactoryController.getInstance().setWaterToCoffee(cursor.getFloat(3));
                RecipeFactoryController.getInstance().setBrewingTemperature(cursor.getInt(4));
                RecipeFactoryController.getInstance().setBloomWater(cursor.getInt(5));
                RecipeFactoryController.getInstance().setBloomTime(cursor.getInt(6));
                RecipeFactoryController.getInstance().setGroundCoffee(cursor.getInt(7));
                recipeList.add(RecipeFactoryController.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        return recipeList;
    }
}