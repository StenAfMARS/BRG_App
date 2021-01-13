package grp02.brg_app.Model;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    public String setBrewDateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        int YY = now.getYear();
        int MM = now.getMonthValue();
        int dd = now.getDayOfMonth();
        int hh = now.getHour();
        int mm = now.getMinute();
        String dash = "-";

        dateTime = dd + dash + MM + dash + YY + dash + hh + dash + mm;

//        String pattern = "dd-MM-yy-kk:mm";
//        String timeZoneId = TimeZone.getDefault().getID();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
//        dateTime = simpleDateFormat.format(new Date());
        System.out.println("Date: " + dateTime);

        return dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

}