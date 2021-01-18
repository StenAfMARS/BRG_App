package grp02.brg_app.Control;

import android.content.Context;
import android.content.res.Resources;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

import grp02.brg_app.R;

public class TextController {
    RecipeFactoryController recipeFactoryController = RecipeFactoryController.getInstance();
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb, Locale.getDefault());
    private Resources resources;
    private static TextController Instance;

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public static TextController getInstance() {
        if(Instance == null) {
            Instance = new TextController();
        }

        return Instance;
    }

    public String getContextStrings(int StringID, Object... obj) {
        return formatter.format(resources.getString(StringID), obj).toString();
    }
}
