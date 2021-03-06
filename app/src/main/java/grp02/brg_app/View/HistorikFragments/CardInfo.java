package grp02.brg_app.View.HistorikFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.StorageController;
import grp02.brg_app.Control.TextController;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.OnPressedBryg;

import static grp02.brg_app.View.MainActivity.context;

public class CardInfo extends Fragment implements View.OnClickListener {

    private Button backToHistoryBtn, beginBrewBtn, deleteBrewBtn;
    private StorageController storageController;
    private TextView gramKaffe, bloomWater, brewTemp, grindSize, name, WCRatio, bloomTime;
    private int recipeId;
    private boolean prefEntry;
    DTO_recipe recipe;
    TextController textController = TextController.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        storageController = new StorageController(context);

        View cardInfoView = inflater.inflate(R.layout.fragment_card_info, container, false);

        gramKaffe = cardInfoView.findViewById(R.id.FCI_gramKaffe1);
        bloomWater = cardInfoView.findViewById(R.id.FCI_bloomWater);
        bloomTime = cardInfoView.findViewById(R.id.FCI_bloomTime);
        brewTemp = cardInfoView.findViewById(R.id.FCI_brewingTemperature);
        grindSize = cardInfoView.findViewById(R.id.FCI_grindSize2);
        name = cardInfoView.findViewById(R.id.FCI_navn);
        WCRatio = cardInfoView.findViewById(R.id.FCI_waterCoffeeRatio);

        recipeId = getArguments().getInt("id");
        prefEntry = getArguments().getBoolean("pref");

        backToHistoryBtn = cardInfoView.findViewById(R.id.FCI_backToHistoryBtn);
        beginBrewBtn = cardInfoView.findViewById(R.id.FCI_BeginBrewBtn);
        deleteBrewBtn = cardInfoView.findViewById(R.id.FCI_deletebrewBtn);
        backToHistoryBtn.setOnClickListener(this);
        beginBrewBtn.setOnClickListener(this);
        deleteBrewBtn.setOnClickListener(this);

        getInfoFromDatabase();

        return cardInfoView;
    }

    public void getInfoFromDatabase() {
        recipe = storageController.getRecipe(recipeId);

        gramKaffe.setText(textController.getContextStrings(R.string.kaffeGram, recipe.getGroundCoffee()));
        grindSize.setText(textController.getContextStrings(R.string.grindSize, recipe.getGrindSize()));
        WCRatio.setText(textController.getContextStrings(R.string.waterCoffeeRatio, recipe.getWaterAmount()));
        brewTemp.setText(textController.getContextStrings(R.string.brewTemp, recipe.getBrewingTemperature()));
        bloomWater.setText(textController.getContextStrings(R.string.bloomWater, recipe.getBloomWater()));
        bloomTime.setText(textController.getContextStrings(R.string.bloomTime, recipe.getBloomTime()));
    }

    @Override
    public void onClick(View view) {
        if(view == backToHistoryBtn) {
                getFragmentManager().popBackStackImmediate();
            }

        if(view == beginBrewBtn) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.FLHistorikOpenCards, new OnPressedBryg(getActivity()))
                        .commit();
        }

        if(view == deleteBrewBtn) {
            // Delete btn then run animation
            DatabaseController.getInstance().getDB().deleteRecipe("History", "fk_RecipeID", recipeId);
            DatabaseController.getInstance().getDB().deleteRecipe("Preferences","fk_RecipeID", recipeId);

            getFragmentManager().beginTransaction()
                    .replace(R.id.FLHistorikOpenCards, new OnItemDelete())
                    .commit();

        }
    }
}