package grp02.brg_app.Control;

import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.Model.GrindSize;

public class RecipeFactory {
    // Singleton class
    private static RecipeFactory Instance;
    private DTO_recipe dto_recipe;
    private String recipeName; // Navn på recipe
    private GrindSize grindSize;

    public DTO_recipe getDto_recipe() {
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

    private RecipeFactory() {}

    public static RecipeFactory getInstance() {
        if (Instance == null) {
            Instance = new RecipeFactory();
            Instance.clearRecipe();
        }

        return Instance;
    }

    public void calcGramKaffe(float kaffeGram) {

    }

    public void clearRecipe() {
        dto_recipe = new DTO_recipe();
    }

    public void setRecipeName(String recipeName) {
        dto_recipe.setRecipeName(recipeName);
    }

    public void setGroundCoffee(String recipeName) {
        dto_recipe.setRecipeName(recipeName);
    }

    public void setGrindSize(GrindSize grindSize) {
        dto_recipe.setGrindSize(grindSize);
    }

    public void setCoffeeToWater(float coffeeToWater) {
        dto_recipe.setCoffeeToWater(coffeeToWater);
    }

    public float getCoffeeToWater() {
        return dto_recipe.getCoffeeToWater();
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

    public void setGroundCoffe(int groundCoffe) {
        dto_recipe.setGroundCoffee(groundCoffe);
    }

}
