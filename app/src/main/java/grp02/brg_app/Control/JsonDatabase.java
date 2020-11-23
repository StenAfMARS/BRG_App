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
import grp02.brg_app.View.MainActivity;

public class JsonDatabase implements IDatabaseConnector {
    private Gson gson = new Gson();
    private SharedPreferences sharedPrefs;

    public JsonDatabase(Context context) {
        sharedPrefs = context.getSharedPreferences("Json", Context.MODE_PRIVATE);
    }

    @Override
    public void saveRecipe(DTO_recipe recipe) {
        List<DTO_recipe> recipes = getRecipeList();
        recipes.add(0, recipe);

        sharedPrefs.edit()
                .putString("JsonRecipes", gson.toJson(recipes))
                .apply();
    }

    @Override
    public void deleteRecipe(int id) {
        List<DTO_recipe> recipes = getRecipeList();
        recipes.remove(id);

        sharedPrefs.edit()
                .putString("JsonRecipes", gson.toJson(recipes))
                .apply();
    }

    @Override
    public DTO_recipe getRecipe(int id) {
        List<DTO_recipe> recipes = getRecipeList();
        return recipes.get(id);
    }

    @Override
    public DTO_recipe[] getRecipes() {
        return getRecipeList().toArray(new DTO_recipe[0]);
    }

    private List<DTO_recipe> getRecipeList()
    {
        String jsonString = sharedPrefs.getString("JsonRecipes", "[]");

        Type listType = new TypeToken<List<DTO_recipe>>() {}.getType();

        return gson.fromJson(jsonString, listType);
    }
}
