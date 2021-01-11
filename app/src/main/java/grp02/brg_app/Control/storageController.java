package grp02.brg_app.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SymbolTable;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Model.BrygObjekt;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.Model.GrindSize;
import grp02.brg_app.Model.SqlInstall;

public class storageController extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String DATABASE = "database.db";

    public storageController(Context context) {
        super(context, DATABASE, null,VERSION);
    }

    private SQLiteDatabase db  =  this.getWritableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Recipes (RecipeID INTEGER PRIMARY KEY AUTOINCREMENT, RecipeName TEXT NOT NULL, GrindSize TEXT NOT NULL, CoffeeWater INTEGER NOT NULL, BrewingTemperature INTEGER NOT NULL, BloomWater INTEGER NOT NULL, BloomTime INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE History (fk_RecipeID INTEGER PRIMARY KEY, timeOfBrew TEXT NOT NULL)");
        db.execSQL("CREATE TABLE Preferences ( fk_RecipeID INTEGER PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addPrecreatedRecipes()
    {
        ContentValues  cv = new ContentValues();
        cv.put("RecipeName","teset");
        cv.put("GrindSize","fine");
        cv.put("CoffeeWater",1);
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
        cv.put("GrindSize",dto_recipe.getGrindSize().toString());
        cv.put("CoffeeWater",dto_recipe.getCoffeeToWater());
        cv.put("BrewingTemperature",dto_recipe.getBrewingTemperature());
        cv.put("BloomWater",dto_recipe.getBloomWater());
        cv.put("BloomTime",dto_recipe.getBloomTime());
        db.insert("Recipes",null,cv);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addRow(String tableName, int recipieID){
        ContentValues cv  = new ContentValues();
        switch (tableName){
            case"History":
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

    // code comes from https://www.javatpoint.com/android-sqlite-tutorial
    public List<DTO_recipe> getAllRecipes() {
        List<DTO_recipe> recipeList = new ArrayList<DTO_recipe>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Recipes";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecipeFactory.getInstance().setRecipeID(Integer.parseInt(cursor.getString(0)));
                RecipeFactory.getInstance().setGrindSize(LogicController.getInstance().stringToGrindSizeObject(cursor.getString(2)));
                RecipeFactory.getInstance().setRecipeName(cursor.getString(1));
                RecipeFactory.getInstance().setCoffeeToWater(cursor.getFloat(3));
                RecipeFactory.getInstance().setBrewingTemperature(cursor.getInt(4));
                RecipeFactory.getInstance().setBloomWater(cursor.getInt(5));
                RecipeFactory.getInstance().setBloomTime(cursor.getInt(6));
                // Adding contact to list
                recipeList.add(RecipeFactory.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        // return contact list
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
                RecipeFactory.getInstance().setGrindSize(LogicController.getInstance().stringToGrindSizeObject(cursor.getString(2)));
                RecipeFactory.getInstance().setRecipeName(cursor.getString(1));
                RecipeFactory.getInstance().setCoffeeToWater(cursor.getFloat(3));
                RecipeFactory.getInstance().setBrewingTemperature(cursor.getInt(4));
                RecipeFactory.getInstance().setBloomWater(cursor.getInt(5));
                RecipeFactory.getInstance().setBloomTime(cursor.getInt(6));
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
                RecipeFactory.getInstance().setGrindSize(LogicController.getInstance().stringToGrindSizeObject(cursor.getString(2)));
                RecipeFactory.getInstance().setRecipeName(cursor.getString(1));
                RecipeFactory.getInstance().setCoffeeToWater(cursor.getFloat(3));
                RecipeFactory.getInstance().setBrewingTemperature(cursor.getInt(4));
                RecipeFactory.getInstance().setBloomWater(cursor.getInt(5));
                RecipeFactory.getInstance().setBloomTime(cursor.getInt(6));
                // Adding contact to list
                recipeList.add(RecipeFactory.getInstance().getDto_recipe());
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }
}
