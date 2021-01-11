package grp02.brg_app.View.FragmenterBrygDetaljer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.controls.Control;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import grp02.brg_app.Control.RecipeFactory;
import grp02.brg_app.Control.storageController;
import grp02.brg_app.R;
import grp02.brg_app.View.BroegActivity1;
import grp02.brg_app.View.MainActivity;

public class FinalBroeg extends Fragment implements View.OnClickListener {

    TextView name, groundCoffee, grindSize, waterCoffeeRatio, brewingTemp, bloomTime, bloomWater;
    Button btnBrewAction, btnSaveBrew;
    RecipeFactory recipeFactory = RecipeFactory.getInstance();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container,
                             Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_final_broeg, container, false);

        name = rod.findViewById(R.id.navn);
        groundCoffee = rod.findViewById(R.id.gramKaffe1);
        grindSize = rod.findViewById(R.id.grindSize2);
        waterCoffeeRatio = rod.findViewById(R.id.waterCoffeeRatio);
        brewingTemp = rod.findViewById(R.id.brewingTemperature);
        bloomWater = rod.findViewById(R.id.bloomWater);
        bloomTime = rod.findViewById(R.id.bloomTime);

        name.setText(recipeFactory.getDto_recipe().getRecipeName());
        groundCoffee.setText(recipeFactory.getDto_recipe().getGroundCoffee()+ "g. kaffe");
        grindSize.setText(recipeFactory.getDto_recipe().getGrindSize()+ " grind size");
        waterCoffeeRatio.setText(recipeFactory.getDto_recipe().getWaterAmount() + " ml. vand pr. gram kaffe");
        brewingTemp.setText(recipeFactory.getDto_recipe().getBrewingTemperature() + " grader Celcius");
        bloomWater.setText(recipeFactory.getDto_recipe().getBloomWater() + " ml. vand til bloom");
        bloomTime.setText(recipeFactory.getDto_recipe().getBloomTime()+ " sekunder til bloom vand distribuering");


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

            grp02.brg_app.Control.storageController storageController = new storageController(getContext());
            storageController.addRowRecipes(RecipeFactory.getInstance().getDto_recipe());

            RecipeFactory.getInstance().clearRecipe();


        } else if (v == btnSaveBrew) {
            BroegActivity1 broegActivity1 = new BroegActivity1();
          //  broegActivity1.list.add(new BrygObjekt(navn, gramKaffe, mlVand, antalSekunder, mlVandTilBloom, antalSekunderTilBloomVandDistribuering));


            grp02.brg_app.Control.storageController storageController = new storageController(getContext());
            storageController.addRowRecipes(RecipeFactory.getInstance().getDto_recipe());

            System.out.println("DET HER ER TAGET FRA DATABASEN: ");
            System.out.println(storageController.getAllRecipes().get(storageController.getAllRecipes().size() - 1).getRecipeName());
            System.out.println(storageController.getAllRecipes().get(storageController.getAllRecipes().size() - 1).getGroundCoffee());
            System.out.println(storageController.getAllRecipes().get(storageController.getAllRecipes().size() - 1).getGrindSize());
            System.out.println(storageController.getAllRecipes().get(storageController.getAllRecipes().size() - 1).getWaterToCoffee());
            System.out.println(storageController.getAllRecipes().get(storageController.getAllRecipes().size() - 1).getBrewingTemperature());
            System.out.println(storageController.getAllRecipes().get(storageController.getAllRecipes().size() - 1).getBloomWater());
            System.out.println(storageController.getAllRecipes().get(storageController.getAllRecipes().size() - 1).getBloomTime());
            System.out.println("______________________________________________________________________________________________________");

            System.out.println("DET HER ER TAGER FRA DEN INSTANS DER IKKE ER SLETTE ENDNU: ");
            System.out.println(RecipeFactory.getInstance().getDto_recipe().getRecipeName());
            System.out.println(RecipeFactory.getInstance().getDto_recipe().getGroundCoffee());
            System.out.println(RecipeFactory.getInstance().getDto_recipe().getGrindSize());
            System.out.println(RecipeFactory.getInstance().getDto_recipe().getWaterToCoffee());
            System.out.println(RecipeFactory.getInstance().getDto_recipe().getBrewingTemperature());
            System.out.println(RecipeFactory.getInstance().getDto_recipe().getBloomWater());
            System.out.println(RecipeFactory.getInstance().getDto_recipe().getBloomTime());
            System.out.println("______________________________________________________________________________________________________");
            //Matching completely.
        }
    }
}