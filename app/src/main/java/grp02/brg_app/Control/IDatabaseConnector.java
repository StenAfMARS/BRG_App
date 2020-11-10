package grp02.brg_app.Control;

import grp02.brg_app.Model.DTO_recipe;

public interface IDatabaseConnector {
    void saveRecipe(DTO_recipe recipe);
    void deleteRecipe(int id);
    DTO_recipe getRecipe(int id);
    DTO_recipe[] getRecipes();
}
