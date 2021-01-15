package grp02.brg_app.View.HistorikFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import grp02.brg_app.Control.StorageController;
import grp02.brg_app.Model.DTO_recipe;
import grp02.brg_app.R;
import grp02.brg_app.View.Fragments.GroundCoffee;
import grp02.brg_app.View.Fragments.OnSaveBryg;
import grp02.brg_app.View.HistorikActivity1;

public class CardInfo extends Fragment implements View.OnClickListener {

    private HistorikActivity1 historikActivity1 = HistorikActivity1.getInstance();
    private Button backToHistoryBtn, beginBrewBtn;
    private StorageController storageController;
    private TextView gramKaffe, bloomWater, brewTemp, grindSize, name, WCRatio, bloomTime;
    private int recipeId;
    DTO_recipe recipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        storageController = new StorageController(HistorikActivity1.context);

        View cardInfoView = inflater.inflate(R.layout.fragment_card_info, container, false);

        gramKaffe = cardInfoView.findViewById(R.id.FCI_gramKaffe1);
        bloomWater = cardInfoView.findViewById(R.id.FCI_bloomWater);
        bloomTime = cardInfoView.findViewById(R.id.FCI_bloomTime);
        brewTemp = cardInfoView.findViewById(R.id.FCI_brewingTemperature);
        grindSize = cardInfoView.findViewById(R.id.FCI_grindSize2);
        name = cardInfoView.findViewById(R.id.FCI_navn);
        WCRatio = cardInfoView.findViewById(R.id.FCI_waterCoffeeRatio);

        recipeId = getArguments().getInt("recipeId");

        backToHistoryBtn = cardInfoView.findViewById(R.id.FCI_backToHistoryBtn);
        beginBrewBtn = cardInfoView.findViewById(R.id.FCI_BeginBrewBtn);
        backToHistoryBtn.setOnClickListener(this);
        beginBrewBtn.setOnClickListener(this);

        getInfoFromDatabase();

        return cardInfoView;
    }

    public void getInfoFromDatabase() {
        recipe = storageController.getRecipe(recipeId);
        gramKaffe.setText(String.valueOf(recipe.getGroundCoffee()));
        bloomWater.setText(String.valueOf(recipe.getBloomWater()));
        bloomTime.setText(String.valueOf(recipe.getBloomTime()));
        brewTemp.setText(String.valueOf(recipe.getBrewingTemperature()));
        grindSize.setText(recipe.getGrindSize());
        name.setText(recipe.getRecipeName());
        WCRatio.setText(String.valueOf(recipe.getWaterToCoffee()));
    }

    @Override
    public void onClick(View view) {
        if(view == backToHistoryBtn) {
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
                    .replace(R.id.FLHistorikOpenCards, new HistorikList())
                    .addToBackStack(null)
                    .commit();
        }

        if(view == beginBrewBtn) {

            // Get info from Database
                // Brew from storageController.getRecipe(id)

            // Show new Fragment and Start Brew
            getFragmentManager().beginTransaction()
                    //  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .replace(R.id.FLHistorikOpenCards, new OnSaveBryg())
                    .addToBackStack(null)
                    .commit();

        }
    }
}