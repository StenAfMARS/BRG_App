package grp02.brg_app.Control;

import java.util.List;

import grp02.brg_app.Model.DTO_recipe;

public interface IDatabaseConnector {
    void saveRecipe(DTO_recipe recipe);
    void deleteRecipe(String tableName, String tableRow,int ID);
    DTO_recipe getRecipe(int id);
    DTO_recipe getFavorite(int id);
    List<DTO_recipe> getRecipes();
    List<DTO_recipe> getAllFavorites();
    List<DTO_recipe> getHistory();
    void addRow(String tableName, int recipeID, boolean fromNewRecipe, String time);
    boolean checkPreferencesForItem(int id);
    void addPrecreatedRecipes();
}
