package grp02.brg_app.View.BrygFragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.Control.StorageController;
import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;

public class FinalBroeg extends Fragment implements View.OnClickListener {

    private TextView name, groundCoffee, grindSize, waterCoffeeRatio, brewingTemp, bloomTime, bloomWater, textViewFinal;
    private Button btnBrewAction, btnSaveBrew;
    private RecipeFactoryController recipeFactory = RecipeFactoryController.getInstance();
    private StorageController storageController;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_final_broeg, container, false);

        storageController = new StorageController(BroegActivity1.context);

        name = rod.findViewById(R.id.navn);
        groundCoffee = rod.findViewById(R.id.gramKaffe1);
        grindSize = rod.findViewById(R.id.grindSize2);
        waterCoffeeRatio = rod.findViewById(R.id.waterCoffeeRatio);
        brewingTemp = rod.findViewById(R.id.brewingTemperature);
        bloomWater = rod.findViewById(R.id.bloomWater);
        bloomTime = rod.findViewById(R.id.bloomTime);
        textViewFinal = rod.findViewById(R.id.textViewFinal);

        name.setText(recipeFactory.getDto_recipe().getRecipeName());
        groundCoffee.setText(recipeFactory.getDto_recipe().getGroundCoffee() + "g. kaffe");
        grindSize.setText(recipeFactory.getDto_recipe().getGrindSize() + " grind size");
        waterCoffeeRatio.setText(recipeFactory.getDto_recipe().getWaterAmount() + " ml. vand pr. gram kaffe");
        brewingTemp.setText(recipeFactory.getDto_recipe().getBrewingTemperature() + " grader Celcius");
        bloomWater.setText(recipeFactory.getDto_recipe().getBloomWater() + " ml. vand til bloom");
        bloomTime.setText(recipeFactory.getDto_recipe().getBloomTime() + " sekunder til bloom vand distribuering");
        textViewFinal.setText("DIN BRYG");

        btnBrewAction = rod.findViewById(R.id.FB_brewActionBtn);
        btnBrewAction.setOnClickListener(this);

        btnSaveBrew = rod.findViewById(R.id.FB_saveBrewBtn);
        btnSaveBrew.setOnClickListener(this);

        return rod;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if (v == btnBrewAction) {
            storageController.saveRecipe(RecipeFactoryController.getInstance().getDto_recipe());
            storageController.addRow("History",0,true);
            RecipeFactoryController.getInstance().clearRecipe();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new OnPressedBryg())
                    .addToBackStack(null)
                    .commit();

        } else if (v == btnSaveBrew) {
            storageController.saveRecipe(RecipeFactoryController.getInstance().getDto_recipe());
            storageController.addRow("History",0,true);
            testRecipeFactory();

            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new OnSaveBryg())
                    .addToBackStack(null)
                    .commit();
        }
        else if(v != btnBrewAction || v != btnSaveBrew){
            storageController.deleteRecipies();
        }
    }

    private void testRecipeFactory() {
        System.out.println("DET HER ER TAGET FRA DATABASEN: ");
        System.out.println(storageController.getRecipes().get(storageController.getRecipes().size() - 1).getRecipeName());
        System.out.println(storageController.getRecipes().get(storageController.getRecipes().size() - 1).getGroundCoffee());
        System.out.println(storageController.getRecipes().get(storageController.getRecipes().size() - 1).getGrindSize());
        System.out.println(storageController.getRecipes().get(storageController.getRecipes().size() - 1).getWaterToCoffee());
        System.out.println(storageController.getRecipes().get(storageController.getRecipes().size() - 1).getBrewingTemperature());
        System.out.println(storageController.getRecipes().get(storageController.getRecipes().size() - 1).getBloomWater());
        System.out.println(storageController.getRecipes().get(storageController.getRecipes().size() - 1).getBloomTime());
        System.out.println("______________________________________________________________________________________________________");

        System.out.println("DET HER ER TAGER FRA DEN INSTANS DER IKKE ER SLETTE ENDNU: ");
        System.out.println(RecipeFactoryController.getInstance().getDto_recipe().getRecipeName());
        System.out.println(RecipeFactoryController.getInstance().getDto_recipe().getGroundCoffee());
        System.out.println(RecipeFactoryController.getInstance().getDto_recipe().getGrindSize());
        System.out.println(RecipeFactoryController.getInstance().getDto_recipe().getWaterToCoffee());
        System.out.println(RecipeFactoryController.getInstance().getDto_recipe().getBrewingTemperature());
        System.out.println(RecipeFactoryController.getInstance().getDto_recipe().getBloomWater());
        System.out.println(RecipeFactoryController.getInstance().getDto_recipe().getBloomTime());
        System.out.println("______________________________________________________________________________________________________");
        //Matching completely.
    }

}