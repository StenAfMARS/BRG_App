package grp02.brg_app.Control;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import grp02.brg_app.Model.DTO_recipe;

public class JsonDBController implements IDatabaseConnector {
    private Gson gson = new Gson();
    private SharedPreferences sharedPrefs;

    public JsonDBController(Context context) {
        sharedPrefs = context.getSharedPreferences("Json", Context.MODE_PRIVATE);
    }

    @Override
    public void saveRecipe(DTO_recipe recipe) {
        List<DTO_recipe> recipes = getRecipes();
        recipes.add(0, recipe);

        sharedPrefs.edit()
                .putString("JsonRecipes", gson.toJson(recipes))
                .apply();
    }

    @Override
    public void deleteRecipe(String tableName, String tableRow, int ID) {
        List<DTO_recipe> recipes = getRecipes();
        recipes.remove(ID);

        sharedPrefs.edit()
                .putString("JsonRecipes", gson.toJson(recipes))
                .apply();
    }

    @Override
    public DTO_recipe getRecipe(int id) {
        List<DTO_recipe> recipes = getRecipes();
        return recipes.get(id);
    }

    @Override
    public List<DTO_recipe> getAllFavorites() {
        return null;
    }

    @Override
    public List<DTO_recipe> getHistory(){
        String jsonString = sharedPrefs.getString("JsonRecipes", "[]");

        Type listType = new TypeToken<List<DTO_recipe>>() {}.getType();

        return gson.fromJson(jsonString, listType);
    }

    @Override
    public void addRow(String tableName, int recipeID, boolean fromNewRecipe, String time) {

    }

    @Override
    public void addPrecreatedRecipes() {

    }

    @Override
    public List<DTO_recipe> getRecipes()
    {
        String jsonString = sharedPrefs.getString("JsonRecipes", "[]");

        Type listType = new TypeToken<List<DTO_recipe>>() {}.getType();

        return gson.fromJson(jsonString, listType);
    }
}
