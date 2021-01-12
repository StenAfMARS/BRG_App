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

public class storageController extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String DATABASE = "database.db";

    public storageController(Context context) {
        super(context, DATABASE, null,VERSION);
    }

    private SQLiteDatabase db  =  this.getWritableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Recipes (RecipeID INTEGER PRIMARY KEY AUTOINCREMENT, RecipeName TEXT NOT NULL, GroundCoffee INTEGER NOT NULL, GrindSize TEXT NOT NULL, WaterToCoffee INTEGER NOT NULL, BrewingTemperature INTEGER NOT NULL, BloomWater INTEGER NOT NULL, BloomTime INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE History (fk_RecipeID INTEGER PRIMARY KEY, timeOfBrew TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Preferences ( fk_RecipeID INTEGER PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addPrecreatedRecipes()
    {
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

    public void addRowRecipes(DTO_recipe dto_recipe){
        ContentValues cv  = new ContentValues();
        cv.put("RecipeName",dto_recipe.getRecipeName());
        cv.put("GroundCoffee",dto_recipe.getGroundCoffee());
        cv.put("GrindSize",dto_recipe.getGrindSize());
        cv.put("WaterToCoffee",dto_recipe.getWaterToCoffee());
        cv.put("BrewingTemperature",dto_recipe.getBrewingTemperature());
        cv.put("BloomWater",dto_recipe.getBloomWater());
        cv.put("BloomTime",dto_recipe.getBloomTime());
        db.insert("Recipes",null,cv);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addRow(String tableName, int recipieID,boolean fromNewRecipe){
        ContentValues cv  = new ContentValues();
        switch (tableName){
            case"History":
                if(fromNewRecipe == true) {
                    String selectQuery = "SELECT * FROM Recipes WHERE   ID = (SELECT MAX(RecipeID)  FROM Recipes);";

                    Cursor cursor = db.rawQuery(selectQuery, null);

                    // looping through all rows and adding to list
                    if (cursor.moveToFirst()) {
                        do {
                            recipieID = Integer.parseInt(cursor.getString(0));
                        } while (cursor.moveToNext());
                    }
                }
                LocalDateTime now = LocalDateTime.now();
                cv.put("fk_RecipeID",recipieID);
                cv.put("timeOfBrew",now.toString());
                db.insert(tableName,null,cv);
                break;
            case"Preferences":
                cv.put("fk_RecipeID",recipieID);
                db.insert(tableName,null,cv);
                break;
        }
    }

    public void deleteRow(String tableName, String tableRow,int ID){
        db.delete(tableName, tableRow + "=" + ID, null);
    }
    public void deleteRecipies(){
        String tableRow = "RecipeID";
        String selectQuery = "SELECT * FROM Recipes WHERE   ID = (SELECT MAX(RecipeID)  FROM Recipes);";

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

    // code comes from https://www.javatpoint.com/android-sqlite-tutorial
    public List<DTO_recipe> getAllRecipes() {
        List<DTO_recipe> recipeList = new ArrayList<DTO_recipe>();

        String selectQuery = "SELECT  * FROM Recipes";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                RecipeFactory.getInstance().setRecipeID(Integer.parseInt(cursor.getString(0)));
                RecipeFactory.getInstance().setGrindSize(cursor.getString(2));
                RecipeFactory.getInstance().setRecipeName(cursor.getString(1));
                RecipeFactory.getInstance().setWaterToCoffee(cursor.getFloat(3));
                RecipeFactory.getInstance().setBrewingTemperature(cursor.getInt(4));
                RecipeFactory.getInstance().setBloomWater(cursor.getInt(5));
                RecipeFactory.getInstance().setBloomTime(cursor.getInt(6));
                RecipeFactory.getInstance().setGroundCoffee(cursor.getInt(7));
                recipeList.add(RecipeFactory.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        return recipeList;
    }
    public List<DTO_recipe> getHistory() {
        List<DTO_recipe> recipeList = new ArrayList<DTO_recipe>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Recipes INNER JOIN History ON History.fk_RecipeID = Recipes.RecipeID;";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeFactory.getInstance().setRecipeID(Integer.parseInt(cursor.getString(0)));
                RecipeFactory.getInstance().setGroundCoffee(cursor.getInt(1));
                RecipeFactory.getInstance().setGrindSize(cursor.getString(2));
                RecipeFactory.getInstance().setRecipeName(cursor.getString(3));
                RecipeFactory.getInstance().setWaterToCoffee(cursor.getFloat(4));
                RecipeFactory.getInstance().setBrewingTemperature(cursor.getInt(5));
                RecipeFactory.getInstance().setBloomWater(cursor.getInt(6));
                RecipeFactory.getInstance().setBloomTime(cursor.getInt(7));
                // Adding contact to list
                recipeList.add(RecipeFactory.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }

    public List<DTO_recipe> getAllFavorites() {
        List<DTO_recipe> recipeList = new ArrayList<DTO_recipe>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Recipes INNER JOIN Preferences ON Preferences.fk_RecipeID = Recipes.RecipeID;";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeFactory.getInstance().setRecipeID(Integer.parseInt(cursor.getString(0)));
                RecipeFactory.getInstance().setGroundCoffee(cursor.getInt(1));
                RecipeFactory.getInstance().setGrindSize(cursor.getString(2));
                RecipeFactory.getInstance().setRecipeName(cursor.getString(3));
                RecipeFactory.getInstance().setWaterToCoffee(cursor.getFloat(4));
                RecipeFactory.getInstance().setBrewingTemperature(cursor.getInt(5));
                RecipeFactory.getInstance().setBloomWater(cursor.getInt(6));
                RecipeFactory.getInstance().setBloomTime(cursor.getInt(7));
                recipeList.add(RecipeFactory.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }

}
