import java.util.ArrayList;
import java.util.Random;

public class Noron {
    private double KATSAYI ;
    private double calisma_saati;
    private double derse_devam;
    private double agirlik_1;
    private double agirlik_2;

    public void setAgirlik_1(double agirlik_1) {
        this.agirlik_1 = agirlik_1;
    }

    public double getAgirlik_1() {
        return agirlik_1;
    }

    public double getAgirlik_2() {
        return agirlik_2;
    }

    public void setAgirlik_2(double agirlik_2) {
        this.agirlik_2 = agirlik_2;
    }

    public Noron(double calisma_saati, double derse_devam) {
        this.calisma_saati = calisma_saati;
        this.derse_devam = derse_devam;
        Random r = new Random();
        this.agirlik_1 = r.nextDouble(0,1);
        this.agirlik_2 = r.nextDouble(0,1);
    }

    public void setKATSAYI(double KATSAYI) {
        this.KATSAYI = KATSAYI;
    }

    public double tahmini_sinav_sonucu( ){

        return this.agirlik_1*this.calisma_saati/10 + this.agirlik_2*this.derse_devam/15;
    }

    public double egit(ArrayList<Double> targets,ArrayList<Double> sonuclar,int index){
        double x_1 = this.calisma_saati/10;
        double x_2 = this.derse_devam/15;
        double yeni_agirlik1 = getAgirlik_1() +KATSAYI*(targets.get(index)-sonuclar.get(index))*x_1;
        double yeni_agirlik2=getAgirlik_2() +KATSAYI*(targets.get(index)-sonuclar.get(index))*x_2;;

        setAgirlik_1(yeni_agirlik1);
        setAgirlik_2(yeni_agirlik2);

        return tahmini_sinav_sonucu();

    }





}


