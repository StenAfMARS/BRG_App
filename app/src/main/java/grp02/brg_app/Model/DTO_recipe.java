package grp02.brg_app.Model;

public class DTO_recipe {
    int recipeID;
    String recipeName;

    GrindSize grindSize;
    float coffeeToWater;
    int brewingTemperature;
    int bloomWater;
    int bloomTime;

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public GrindSize getGrindSize() {
        return grindSize;
    }

    public void setGrindSize(GrindSize grindSize) {
        this.grindSize = grindSize;
    }

    public float getCoffeeToWater() {
        return coffeeToWater;
    }

    public void setCoffeeToWater(float coffeeToWater) {
        this.coffeeToWater = coffeeToWater;
    }

    public int getBrewingTemperature() {
        return brewingTemperature;
    }

    public void setBrewingTemperature(int brewingTemperature) {
        this.brewingTemperature = brewingTemperature;
    }

    public int getBloomWater() {
        return bloomWater;
    }

    public void setBloomWater(int bloomWater) {
        this.bloomWater = bloomWater;
    }

    public int getBloomTime() {
        return bloomTime;
    }

    public void setBloomTime(int bloomTime) {
        this.bloomTime = bloomTime;
    }
}