package grp02.brg_app.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;

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
        cv.put("RecipeName",dto_recipe.get);
    }
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
}
