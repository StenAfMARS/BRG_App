package grp02.brg_app.Model;

import java.util.ArrayList;

public class BrygObjekt {
    public String navn;
    public double gramKaffe;
    public double vandPrGramKaffe;
    public int hvorHurtigtSkalVandetFordeles;
    public double hvorMegetVandSkalDuBrugeTilBLoom;
    public int hvorHurtigtSkalBloomvandetDistribueres;


    public BrygObjekt(String navn, double gramKaffe, double vandPrGramKaffe, int hvorHurtigtSkalVandetFordeles, double hvorMegetVandSkalDuBrugeTilBLoom, int hvorHurtigtSkalBloomvandetDistribueres) {
        this.navn = navn;
        this.gramKaffe = gramKaffe;
        this.vandPrGramKaffe = vandPrGramKaffe;
        this.hvorHurtigtSkalVandetFordeles = hvorHurtigtSkalVandetFordeles;
        this.hvorMegetVandSkalDuBrugeTilBLoom = hvorMegetVandSkalDuBrugeTilBLoom;
        this.hvorHurtigtSkalBloomvandetDistribueres = hvorHurtigtSkalBloomvandetDistribueres;
    }
}
