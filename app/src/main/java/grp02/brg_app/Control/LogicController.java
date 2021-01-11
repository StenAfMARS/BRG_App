package grp02.brg_app.Control;

import java.text.DecimalFormat;
import java.util.ArrayList;

import grp02.brg_app.Model.GrindSize;

public class LogicController {

    // Singleton class
    private static LogicController Instance;

    private LogicController() {}

    public static LogicController getInstance() {
        if (Instance == null) {
            Instance = new LogicController();
        }

        return Instance;
    }

    public float convertStringsToFloats(String str1, String str2) {
        float val1 = Float.parseFloat(str1);
        float val2 = Float.parseFloat(str2);
        float result = val1 + (val2 / 10);
        System.out.println("This is the converted result: " + result);

        return result;
    }

    public ArrayList<String> loadMængderIGram(){

        ArrayList<Integer> gramValues = new ArrayList<>();

        for(int i = 50; i <= 75; i++) {
            gramValues.add(i);
        }

        return convertToStringList(gramValues);
    }

    public ArrayList<String> loadMængderIMilliGram(){

        ArrayList<Integer> milliGramValues = new ArrayList<>();

        for(int i = 0; i <= 9; i++) {
            milliGramValues.add(i);
        }

        return convertToStringList(milliGramValues);
    }

    public ArrayList<String> convertToStringList(ArrayList<Integer> list) {
        ArrayList<String> convertedResults = new ArrayList<>();
        int n = list.size();
        if(n == 0) {
            System.out.println("no items in list");
            return null;
        }

        for(int item : list) {
            convertedResults.add(String.valueOf(item));
        }

        return convertedResults;
    }
}
