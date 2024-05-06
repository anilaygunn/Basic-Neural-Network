import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;


public class Main {
    static ArrayList<Double> mse_list = new ArrayList<>();
    public static void main(String[] args) throws Exception
    {
        // pass the path to the file as a parameter
        File file = new File("C:\\Users\\Emre\\Desktop\\ogrenciler_veri_seti.txt");
        //File file = new File("besli_veri_seti.txt");

        Scanner sc = new Scanner(file);
        List<Ogrenci> ogrenciListesi = new ArrayList<>();
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] data_set = data.split("\t");
            double sure = Double.parseDouble(data_set[0]);
            int derse_devam = Integer.parseInt(data_set[1]);
            int not = Integer.parseInt(data_set[2]);
            Ogrenci ogrenci = new Ogrenci(sure,derse_devam,not);
            ogrenciListesi.add(ogrenci);


        }
        ArrayList<Double> sonuclar = new ArrayList<>();
        ArrayList<Double> targets = new ArrayList<>();
        ArrayList<Noron> noronlar = new ArrayList<>();






        // 10 defa döndürmek ve 0.05 katsayı için (b şıkkı)
        egitim_0(ogrenciListesi,sonuclar,targets,noronlar,0.05);
        for(int i=0;i<100;i++){
            egitim(ogrenciListesi,sonuclar,targets,noronlar);
        }
        yazdir(ogrenciListesi,sonuclar,targets);


      /*
        // 10 defa döndürmek ve 0.01 katsayı için
        egitim_0(ogrenciListesi,sonuclar,targets,noronlar,0.01);
        for(int i=0;i<10;i++){
            egitim(ogrenciListesi,sonuclar,targets,noronlar);
        }
         yazdir(ogrenciListesi,sonuclar,targets);

        // 50 defa döndürmek için
        egitim_0(ogrenciListesi,sonuclar,targets,noronlar,0.01);
        for(int i=0;i<50;i++){
            egitim(ogrenciListesi,sonuclar,targets,noronlar);

        }
        yazdir(ogrenciListesi,sonuclar,targets);
        // 100 defa döndürmek ve 0.01 katsayısı için
        egitim_0(ogrenciListesi,sonuclar,targets,noronlar,0.01);
        for(int i=0;i<100;i++){
            egitim(ogrenciListesi,sonuclar,targets,noronlar);

        }
        yazdir(ogrenciListesi,sonuclar,targets);

        // 10 defa döndürmek ve 0.025 katsayı için
        egitim_0(ogrenciListesi,sonuclar,targets,noronlar,0.025);
        for(int i=0;i<10;i++){
            egitim(ogrenciListesi,sonuclar,targets,noronlar);
        }
        yazdir(ogrenciListesi,sonuclar,targets);

        // 50 defa döndürmek için ve 0.025 katsayısı için
        egitim_0(ogrenciListesi,sonuclar,targets,noronlar,0.025);
        for(int i=0;i<50;i++){
            egitim(ogrenciListesi,sonuclar,targets,noronlar);

        }
        yazdir(ogrenciListesi,sonuclar,targets);

        // 100 defa döndürmek için ve 0.025 katsayısı için
        egitim_0(ogrenciListesi,sonuclar,targets,noronlar,0.025);
        for(int i=0;i<100;i++){
            egitim(ogrenciListesi,sonuclar,targets,noronlar);
        }
        yazdir(ogrenciListesi,sonuclar,targets);

       */

    }

    public static void yazdir(List<Ogrenci> ogrenciListesi,ArrayList<Double> sonuclar,ArrayList<Double> targets ){
        System.out.format("%-15s%-15s%-15s%-15s%-15s%n", "Öğrenci", "Süre", "Devam", "Tahmin", "Hedef");

        double last_mse=mse_list.get(mse_list.size()-1) ;
        for(int i=0; i< ogrenciListesi.size();i++){
            double sure=ogrenciListesi.get(i).getSure();
            double derse_devam = ogrenciListesi.get(i).getDerse_devam();
            double sonuc = sonuclar.get(i)*100;
            double target = targets.get(i)*100;
            System.out.format("%-15s%-15.2f%-15.2f%-15.2f%-15.2f%n", "Öğrenci " + (i + 1), sure, derse_devam, sonuc, target);
        }
        System.out.println("Mean Square Error:"+last_mse);
    }

    public static void egitim_0(List<Ogrenci>ogrenciListesi,ArrayList<Double> sonuclar
            ,ArrayList<Double> targets,ArrayList<Noron> noronlar,double kat_sayi){


        for(int i =0; i< ogrenciListesi.size();i++){
            Ogrenci yeni_ogrenci = ogrenciListesi.get(i);
            Noron noron = new Noron(yeni_ogrenci.getSure(),yeni_ogrenci.getDerse_devam());
            noron.setKATSAYI(kat_sayi);
            double tahmini_sinav_sonucu =noron.tahmini_sinav_sonucu() ;
            sonuclar.add(tahmini_sinav_sonucu);
            targets.add(yeni_ogrenci.getNot()/100);
            noronlar.add(noron);

        }


    }
    public static void egitim(List<Ogrenci>ogrenciListesi,ArrayList<Double> sonuclar,ArrayList<Double> targets,ArrayList<Noron> noronlar){
        double mse =0;
        for(int i=0;i<ogrenciListesi.size();i++){
            double sonuc =noronlar.get(i).egit(targets,sonuclar,i);
            sonuclar.set(i,sonuc) ;
            double fark = ogrenciListesi.get(i).getNot()/100- sonuc;
            mse += Math.pow(fark,2);
        }
        mse *= (1.0 / ogrenciListesi.size());
        mse_list.add(mse);

    }
}






