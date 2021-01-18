package grp02.brg_app.View.HistorikFragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import grp02.brg_app.Control.DatabaseController;
import grp02.brg_app.Control.RecipeFactoryController;
import grp02.brg_app.Control.StorageController;
import grp02.brg_app.Control.TextController;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.GroundCoffee;
import grp02.brg_app.View.Fragments.OnPressedBryg;
import grp02.brg_app.View.Fragments.OnSaveBryg;
import grp02.brg_app.View.HistorikActivity1;
import grp02.brg_app.View.MainActivity;

import static grp02.brg_app.View.MainActivity.context;

public class CardInfo extends Fragment implements View.OnClickListener {

    private HistorikActivity1 historikActivity1 = HistorikActivity1.getInstance();
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
            if(prefEntry) {
                Intent intent = new Intent(((MainActivity)getContext()), MainActivity.class);
                startActivity(intent);
            } else {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                        .replace(R.id.FLHistorikOpenCards, new HistorikList())
                        .addToBackStack(null)
                        .commit();
            }
        }

        if(view == beginBrewBtn) {
            if(prefEntry) {
                // Show new Fragment and Start Brew
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.ShowBrewAnimation, new OnPressedBryg(MainActivity.context))
                        .addToBackStack(null)
                        .commit();
            } else {
                // Show new Fragment and Start Brew
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.FLHistorikOpenCards, new OnPressedBryg(HistorikActivity1.context))
                        .addToBackStack(null)
                        .commit();
            }

        }

        if(view == deleteBrewBtn) {
            // Delete btn then run animation
            DatabaseController.getInstance().getDB().deleteRecipe("History", "fk_RecipeID", recipeId);
            DatabaseController.getInstance().getDB().deleteRecipe("Preferences","fk_RecipeID", recipeId);

            if(prefEntry) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.ShowBrewAnimation, new OnItemDelete())
                        .addToBackStack(null)
                        .commit();
            } else {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.FLHistorikOpenCards, new OnItemDelete())
                        .addToBackStack(null)
                        .commit();
            }

        }
    }
}