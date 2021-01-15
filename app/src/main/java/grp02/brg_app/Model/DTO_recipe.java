package grp02.brg_app.Model;

public class DTO_recipe {
    private int recipeID;
    private String recipeName;
    private float groundCoffee;
    private float waterToCoffee;
    private int brewingTemperature;
    private int bloomWater;
    private int bloomTime;
    private int waterAmount;
    private String grindSize;
    private String dateTime;
    private String onAppStartUpDateTime;

    public int getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }

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

    public float getWaterToCoffee() {
        return waterToCoffee;
    }

    public void setWaterToCoffee(float waterToCoffee) {
        this.waterToCoffee = waterToCoffee;
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

    public float getGroundCoffee(){
        return groundCoffee;
    }

    public void setGroundCoffee(float groundCoffee){
        this.groundCoffee = groundCoffee;
    }

    public String getGrindSize() {
        return grindSize;
    }

    public void setGrindSize(String grindSize) {
        this.grindSize = grindSize;
    }

    public void setDateTime(String date) {
        dateTime = date;
    }

    public String setBrewDateTime(String onAppStartupDateTime) {
        this.onAppStartUpDateTime = onAppStartupDateTime;
        return onAppStartUpDateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

}