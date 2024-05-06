public class Ogrenci {
    private double sure;
    private int derse_devam;
    private int not;

    public Ogrenci(double sure, int derse_devam, int not) {
        this.sure = sure;
        this.derse_devam = derse_devam;
        this.not= not;
    }
    public Ogrenci(){

    }

    public double getSure() {
        return sure;
    }

    public int getDerse_devam() {
        return derse_devam;
    }

    public double getNot() {
        return not;
    }
}
