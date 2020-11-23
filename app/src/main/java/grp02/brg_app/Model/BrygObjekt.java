package grp02.brg_app.Model;

public class BrygObjekt {
    String navn;
    double gramKaffe;
    double vandPrGramKaffe;
    int hvorHurtigtSkalVandetFordeles;
    double hvorMegetVandSkalDuBrugeTilBLoom;
    int hvorHurtigtSkalBloomvandetDistribueres;


    public BrygObjekt(String navn, double gramKaffe, double vandPrGramKaffe, int hvorHurtigtSkalVandetFordeles, double hvorMegetVandSkalDuBrugeTilBLoom, int hvorHurtigtSkalBloomvandetDistribueres) {
        this.navn = navn;
        this.gramKaffe = gramKaffe;
        this.vandPrGramKaffe = vandPrGramKaffe;
        this.hvorHurtigtSkalVandetFordeles = hvorHurtigtSkalVandetFordeles;
        this.hvorMegetVandSkalDuBrugeTilBLoom = hvorMegetVandSkalDuBrugeTilBLoom;
        this.hvorHurtigtSkalBloomvandetDistribueres = hvorHurtigtSkalBloomvandetDistribueres;
    }
}
