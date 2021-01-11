package grp02.brg_app.View.FragmenterBrygDetaljer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import grp02.brg_app.Control.LogicController;
import grp02.brg_app.Control.RecipeFactory;
import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;

public class FinalBroeg extends Fragment implements View.OnClickListener {

    TextView name, groundCoffee, grindSize, coffeeWaterRatio, brewingTemp, bloomTime, bloomWater;
    Button btnBrewAction, btnSaveBrew;
    RecipeFactory recipeFactory = RecipeFactory.getInstance();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_final_broeg, container, false);

        name = rod.findViewById(R.id.navn);
        groundCoffee = rod.findViewById(R.id.gramKaffe1);
        grindSize = rod.findViewById(R.id.grindSize2);
        coffeeWaterRatio = rod.findViewById(R.id.coffeeWaterRatio);
        brewingTemp = rod.findViewById(R.id.brewingTemperature);
        bloomWater = rod.findViewById(R.id.bloomWater);
        bloomTime = rod.findViewById(R.id.bloomTime);

        name.setText(recipeFactory.getDto_recipe().getRecipeName());
        coffeeWaterRatio.setText(recipeFactory.getDto_recipe().getWaterAmount() + " ml. vand pr. gram kaffe");
        bloomWater.setText(recipeFactory.getDto_recipe().getBloomWater() + " ml. vand til bloom");
        bloomTime.setText(recipeFactory.getDto_recipe().getBloomTime() + " sekunder til bloom vandsdistribuering");
        brewingTemp.setText(recipeFactory.getDto_recipe().getBrewingTemperature() + " grader Celcius");
        brewingTemp.setText(recipeFactory.getDto_recipe().getCoffeeToWater() + " ml. vand til bloom");


        btnBrewAction = rod.findViewById(R.id.FB_brewActionBtn);
        btnBrewAction.setOnClickListener(this);

        btnSaveBrew = rod.findViewById(R.id.FB_saveBrewBtn);
        btnSaveBrew.setOnClickListener(this);

        return rod;
    }


    @Override
    public void onClick(View v) {
        if (v == btnBrewAction) {
            BroegActivity1 broegActivity1 = new BroegActivity1();
            RecipeFactory.getInstance().clearRecipe();


            //TODO
            // Save Recipe
            // Brew

        } else if (v == btnSaveBrew) {
            BroegActivity1 broegActivity1 = new BroegActivity1();
          //  broegActivity1.list.add(new BrygObjekt(navn, gramKaffe, mlVand, antalSekunder, mlVandTilBloom, antalSekunderTilBloomVandDistribuering));

            //TODO
            // Save recipe
        }
    }
}