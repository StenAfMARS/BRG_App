package grp02.brg_app.View.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.Control.StorageController;
import grp02.brg_app.Control.TextController;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;

public class FinalBroeg extends Fragment implements View.OnClickListener {

    private Button btnBrewAction, btnSaveBrew;
    private StorageController storageController;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_final_broeg, container, false);

        TextController textController = TextController.getInstance();
        RecipeFactoryController recipeFactory = RecipeFactoryController.getInstance();
        storageController = new StorageController(BroegActivity1.context);
        DTO_recipe recipe = recipeFactory.getDTO_recipe();

        TextView name = rod.findViewById(R.id.navn);
        TextView groundCoffee = rod.findViewById(R.id.gramKaffe1);
        TextView grindSize = rod.findViewById(R.id.grindSize2);
        TextView waterCoffeeRatio = rod.findViewById(R.id.waterCoffeeRatio);
        TextView brewingTemp = rod.findViewById(R.id.brewingTemperature);
        TextView bloomWater = rod.findViewById(R.id.bloomWater);
        TextView bloomTime = rod.findViewById(R.id.bloomTime);
        TextView textViewFinal = rod.findViewById(R.id.textViewFinal);

        textViewFinal.setText(R.string.dinBryg);
        name.setText(recipe.getRecipeName());
        groundCoffee.setText(textController.getContextStrings(R.string.kaffeGram, recipe.getGroundCoffee()));
        grindSize.setText(textController.getContextStrings(R.string.grindSize, recipe.getGrindSize()));
        waterCoffeeRatio.setText(textController.getContextStrings(R.string.waterCoffeeRatio, recipe.getWaterAmount()));
        brewingTemp.setText(textController.getContextStrings(R.string.brewTemp, recipe.getBrewingTemperature()));
        bloomWater.setText(textController.getContextStrings(R.string.bloomWater, recipe.getBloomWater()));
        bloomTime.setText(textController.getContextStrings(R.string.bloomTime, recipe.getBloomTime()));

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
            RecipeFactoryController recipeFactoryController = RecipeFactoryController.getInstance();
            String getDateTime = LogicController.getInstance().getCurrentDateTime();
            storageController.saveRecipe(recipeFactoryController.getDTO_recipe());
            storageController.addRow(
                    "History",
                    0,
                    true,
                    getDateTime);
            RecipeFactoryController.getInstance().clearRecipe();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new OnPressedBryg(BroegActivity1.context))
                    .addToBackStack(null)
                    .commit();

        } else if (v == btnSaveBrew) {
            RecipeFactoryController recipeFactoryController = RecipeFactoryController.getInstance();
            String getDateTime = LogicController.getInstance().getCurrentDateTime();
            storageController.saveRecipe(recipeFactoryController.getDTO_recipe());
            storageController.addRow(
                    "History",
                    0,
                    true,
                    getDateTime);

            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.broegFragmentetIActivity, new OnSaveBryg())
                    .addToBackStack(null)
                    .commit();
        } else if(v != btnBrewAction || v != btnSaveBrew){
            storageController.deleteRecipes();
        }
    }

}