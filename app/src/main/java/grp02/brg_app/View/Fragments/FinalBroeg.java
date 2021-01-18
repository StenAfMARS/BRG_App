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
import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;
import grp02.brg_app.View.Fragments.OnPressedBryg;
import grp02.brg_app.View.Fragments.OnSaveBryg;

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

        textViewFinal.setText("DIN BRYG");
        name.setText(recipeFactory.getDTO_recipe().getRecipeName());
        groundCoffee.setText(recipeFactory.getDTO_recipe().getGroundCoffee() + "g. kaffe");
        grindSize.setText(recipeFactory.getDTO_recipe().getGrindSize() + " grind size");
        waterCoffeeRatio.setText(recipeFactory.getDTO_recipe().getWaterAmount() + " ml. vand pr. gram kaffe");
        brewingTemp.setText(recipeFactory.getDTO_recipe().getBrewingTemperature() + " grader Celcius");
        bloomWater.setText(recipeFactory.getDTO_recipe().getBloomWater() + " ml. vand til bloom");
        bloomTime.setText(recipeFactory.getDTO_recipe().getBloomTime() + " sekunder til bloom vand distribuering");

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