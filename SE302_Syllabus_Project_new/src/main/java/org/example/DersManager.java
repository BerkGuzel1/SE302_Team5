package org.example;
import java.util.ArrayList;

public class DersManager {

    private ArrayList<Ders> dersListesi;

    public DersManager() {
        dersListesi = new ArrayList<>();
    }

    public void dersEkle(Ders ders) {
        dersListesi.add(ders);
    }

    public void dersOlusturVeEkle(String dersAdi, String kodu, String mevsim, int teori, int uygulama, int yerelKredi, int AKTS,
                                  String onKosullar, String dersDili, String dersTuru, String dersDuzeyi,
                                  String dersKoordinatoru, String ogretimElemanlari, String yardimcilar,
                                  String dersAmaci, String ogrenmeCiktilari, String dersTanimi, String dersKategorisi) {
        Ders ders = new Ders();
        ders.setDersAdi(dersAdi);
        ders.setKodu(kodu);

        dersListesi.add(ders);
    }

    public ArrayList<Ders> getDersListesi() {
        return dersListesi;
    }
}

