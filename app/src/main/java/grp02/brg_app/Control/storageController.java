package grp02.brg_app.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import grp02.brg_app.Model.BrygObjekt;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.Model.SqlInstall;

public class storageController extends SQLiteOpenHelper {

    public storageController(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, "broeg.db", factory,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SqlInstall sqlInstall = new SqlInstall();
        sqlInstall.storageConstructors(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addRowReciepies(DTO_recipe dto_recipe){
        SQLiteDatabase db  =  this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put("RecipeName",dto_recipe.getRecipeName());
        cv.put("GrindSize",dto_recipe.getGrindSize().toString());
        cv.put("CoffeeWater",dto_recipe.getCoffeeToWater());
        cv.put("BrewingTemperature",dto_recipe.getBrewingTemperature());
        cv.put("BloomWater",dto_recipe.getBloomWater());
        cv.put("BloomTime",dto_recipe.getBloomTime());
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addRow(String tableName, int recipieID){
        SQLiteDatabase db  =  this.getWritableDatabase();
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
        SQLiteDatabase db  =  this.getWritableDatabase();
        db.delete(tableName, tableRow + "=" + ID, null);
    }
    // code comes from https://www.javatpoint.com/android-sqlite-tutorial
    public List<BrygObjekt> getAllRecepies() {
        List<BrygObjekt> BrygObjektList = new ArrayList<BrygObjekt>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Recipes";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BrygObjekt brygObjekt = new BrygObjekt();
                brygObjekt.setID(Integer.parseInt(cursor.getString(0)));
                brygObjekt.setNavn(cursor.getString(1));
                brygObjekt.s(cursor.getString(1));
                brygObjekt.setNavn(cursor.getString(1));
                brygObjekt.setNavn(cursor.getString(1));

                brygObjekt.set(cursor.getString(2));
                // Adding contact to list
                BrygObjektList.add(brygObjekt);
            } while (cursor.moveToNext());
        }

        // return contact list
        return BrygObjektList;
    }

    public List<BrygObjekt> getAllFavorites() {
        List<BrygObjekt> BrygObjektList = new ArrayList<BrygObjekt>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Recipes INNER JOIN Preferences ON Preferences.fk_RecipeID = Recipes.RecipeID;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BrygObjekt brygObjekt = new BrygObjekt();
                brygObjekt.setID(Integer.parseInt(cursor.getString(0)));
                brygObjekt.setNavn(cursor.getString(1));
                brygObjekt.setNavn(cursor.getString(1));
                brygObjekt.setNavn(cursor.getString(1));
                brygObjekt.setNavn(cursor.getString(1));

                brygObjekt.setHvorMegetVandSkalDuBrugeTilBLoom(cursor.getString(2));
                // Adding contact to list
                BrygObjektList.add(brygObjekt);
            } while (cursor.moveToNext());
        }

        // return contact list
        return BrygObjektList;
    }
}
