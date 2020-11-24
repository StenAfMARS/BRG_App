package grp02.brg_app.Model;

import java.util.ArrayList;

public class BrygObjekt {
    public String navn;
    public double gramKaffe;
    public int vandPrGramKaffe;
    public int hvorHurtigtSkalVandetFordeles;
    public int hvorMegetVandSkalDuBrugeTilBLoom;
    public int hvorHurtigtSkalBloomvandetDistribueres;


    public BrygObjekt(String navn, double gramKaffe, int vandPrGramKaffe, int hvorHurtigtSkalVandetFordeles, int hvorMegetVandSkalDuBrugeTilBLoom, int hvorHurtigtSkalBloomvandetDistribueres) {
        this.navn = navn;
        this.gramKaffe = gramKaffe;
        this.vandPrGramKaffe = vandPrGramKaffe;
        this.hvorHurtigtSkalVandetFordeles = hvorHurtigtSkalVandetFordeles;
        this.hvorMegetVandSkalDuBrugeTilBLoom = hvorMegetVandSkalDuBrugeTilBLoom;
        this.hvorHurtigtSkalBloomvandetDistribueres = hvorHurtigtSkalBloomvandetDistribueres;
    }


    public void setNavn(String navn){
        this.navn = navn; }
    public String getNavn(){
        return this.navn;
    }


    public void setGramKaffe(Double gramKaffe){
        this.gramKaffe = gramKaffe; }
    public double getGramKaffe(){
        return this.gramKaffe;
    }


    public void setVandPrGramKaffe(int vandPrGramKaffe){
        this.vandPrGramKaffe = vandPrGramKaffe; }
    public int getVandPrGramKaffe(){
        return this.vandPrGramKaffe;
    }


    public void setHvorHurtigtSkalVandetFordeles(int hvorHurtigtSkalVandetFordeles)
    {
        this.hvorHurtigtSkalVandetFordeles = hvorHurtigtSkalVandetFordeles; }
    public int getHvorHurtigtSkalVandetFordeles(){
        return this.hvorHurtigtSkalVandetFordeles;
    }


    public void setHvorMegetVandSkalDuBrugeTilBLoom(int hvorMegetVandSkalDuBrugeTilBLoom){
        this.hvorMegetVandSkalDuBrugeTilBLoom = hvorMegetVandSkalDuBrugeTilBLoom; }
    public int getHvorMegetVandSkalDuBrugeTilBLoom(){
        return this.hvorMegetVandSkalDuBrugeTilBLoom;
    }


    public void setHvorHurtigtSkalBloomvandetDistribueres(int hvorHurtigtSkalBloomvandetDistribueres){
        this.hvorHurtigtSkalBloomvandetDistribueres = hvorHurtigtSkalBloomvandetDistribueres; }
    public int getHvorHurtigtSkalBloomvandetDistribueres(){
        return this.hvorHurtigtSkalBloomvandetDistribueres;
    }
}
