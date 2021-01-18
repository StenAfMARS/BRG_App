package grp02.brg_app.Control;

import grp02.brg_app.Model.DTO_recipe;

public class RecipeFactoryController {
    // Singleton class
    private static RecipeFactoryController Instance;
    private DTO_recipe dto_recipe;

    public DTO_recipe getDTO_recipe() {
        return dto_recipe;
    }

    public int getRecipeID() {
        return dto_recipe.getRecipeID();
    }

    public void setRecipeID(int recipeID) {
        dto_recipe.setRecipeID(recipeID);
    }

    public void setWaterAmount(int waterAmount) {
        dto_recipe.setWaterAmount(waterAmount);
    }

    private RecipeFactoryController() {}

    public static RecipeFactoryController getInstance() {
        if (Instance == null) {
            Instance = new RecipeFactoryController();
            Instance.clearRecipe();
        }

        return Instance;
    }

    public void clearRecipe() {
        dto_recipe = new DTO_recipe();
    }

    public void setRecipeName(String recipeName) {
        dto_recipe.setRecipeName(recipeName);
    }

    public void setGrindSize(String grindSize) {
        dto_recipe.setGrindSize(grindSize);
    }

    public void setBrewingTemperature(int brewingTemperature) {
        dto_recipe.setBrewingTemperature(brewingTemperature);
    }

    public void setBloomWater(int bloomWater) {
        dto_recipe.setBloomWater(bloomWater);
    }

    public void setBloomTime(int bloomTime) {
        dto_recipe.setBloomTime(bloomTime);
    }

    public void setGroundCoffee(float groundCoffee) {
        dto_recipe.setGroundCoffee(groundCoffee);
    }

    public void setDateTime(String date) { dto_recipe.setDateTime(date); }

}
