package grp02.brg_app.Control;

import android.content.Context;

import org.junit.jupiter.api.Test;

import java.util.Random;

import grp02.brg_app.Model.DTO_recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonDatabaseTest {

    @Test
    void testSaveRecipe() {
        JsonDatabase db = new JsonDatabase();
        Random rand = new Random();

        DTO_recipe recipe = new DTO_recipe();
        recipe.setBloomTime(rand.nextInt());

        db.saveRecipe(recipe);

        assertEquals(db.getRecipe(0).getBloomTime(), recipe.getBloomTime());
    }

    @Test
    void testDeleteRecipe() {
    }

    @Test
    void testGetRecipe() {
    }

    @Test
    void testGetRecipes() {
    }
}
