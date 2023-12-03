package org.example;

public class Ders {

        private String dersAdi;
        private String dersKodu;
        private String donem; // Güz veya Bahar için ayrı
        private int teoriSaat;
        private int uygulamaLabSaat;
        private int yerelKredi;
        private int AKTS;

        private String onKosullar;
        private String dersDili;
        private String dersTuru;
        private String dersDuzeyi;

        private String dersKoordinatoru;
        private String ogretimElemanlari;
        private String yardimcilari;

        private String dersAmaci;
        private String ogrenmeCiktilari;
        private String dersTanimi;

        private String dersKategorisi;


        public Ders(String dersAdi, String dersKodu, String donem, int teoriSaat, int uygulamaLabSaat, int yerelKredi, int AKTS,
                    String onKosullar, String dersDili, String dersTuru, String dersDuzeyi,
                    String dersKoordinatoru, String ogretimElemanlari, String yardimcilari,
                    String dersAmaci, String ogrenmeCiktilari, String dersTanimi, String dersKategorisi) {
            this.dersAdi = dersAdi;
            this.dersKodu = dersKodu;
            this.donem = donem;
            this.teoriSaat = teoriSaat;
            this.uygulamaLabSaat = uygulamaLabSaat;
            this.yerelKredi = yerelKredi;
            this.AKTS = AKTS;

            this.onKosullar = onKosullar;
            this.dersDili = dersDili;
            this.dersTuru = dersTuru;
            this.dersDuzeyi = dersDuzeyi;

            this.dersKoordinatoru = dersKoordinatoru;
            this.ogretimElemanlari = ogretimElemanlari;
            this.yardimcilari = yardimcilari;

            this.dersAmaci = dersAmaci;
            this.ogrenmeCiktilari = ogrenmeCiktilari;
            this.dersTanimi = dersTanimi;

            this.dersKategorisi = dersKategorisi;
        }

    public Ders() {

    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getDersKodu() {
        return dersKodu;
    }

    public void setDersKodu(String dersKodu) {
        this.dersKodu = dersKodu;
    }

    public String getDonem() {
        return donem;
    }

    public void setDonem(String donem) {
        this.donem = donem;
    }

    public int getTeoriSaat() {
        return teoriSaat;
    }

    public void setTeoriSaat(int teoriSaat) {
        this.teoriSaat = teoriSaat;
    }

    public int getUygulamaLabSaat() {
        return uygulamaLabSaat;
    }

    public void setUygulamaLabSaat(int uygulamaLabSaat) {
        this.uygulamaLabSaat = uygulamaLabSaat;
    }

    public int getYerelKredi() {
        return yerelKredi;
    }

    public void setYerelKredi(int yerelKredi) {
        this.yerelKredi = yerelKredi;
    }

    public int getAKTS() {
        return AKTS;
    }

    public void setAKTS(int AKTS) {
        this.AKTS = AKTS;
    }

    public String getOnKosullar() {
        return onKosullar;
    }

    public void setOnKosullar(String onKosullar) {
        this.onKosullar = onKosullar;
    }

    public String getDersDili() {
        return dersDili;
    }

    public void setDersDili(String dersDili) {
        this.dersDili = dersDili;
    }

    public String getDersTuru() {
        return dersTuru;
    }

    public void setDersTuru(String dersTuru) {
        this.dersTuru = dersTuru;
    }

    public String getDersDuzeyi() {
        return dersDuzeyi;
    }

    public void setDersDuzeyi(String dersDuzeyi) {
        this.dersDuzeyi = dersDuzeyi;
    }

    public String getDersKoordinatoru() {
        return dersKoordinatoru;
    }

    public void setDersKoordinatoru(String dersKoordinatoru) {
        this.dersKoordinatoru = dersKoordinatoru;
    }

    public String getOgretimElemanlari() {
        return ogretimElemanlari;
    }

    public void setOgretimElemanlari(String ogretimElemanlari) {
        this.ogretimElemanlari = ogretimElemanlari;
    }

    public String getYardimcilari() {
        return yardimcilari;
    }

    public void setYardimcilari(String yardimcilari) {
        this.yardimcilari = yardimcilari;
    }

    public String getDersAmaci() {
        return dersAmaci;
    }

    public void setDersAmaci(String dersAmaci) {
        this.dersAmaci = dersAmaci;
    }

    public String getOgrenmeCiktilari() {
        return ogrenmeCiktilari;
    }

    public void setOgrenmeCiktilari(String ogrenmeCiktilari) {
        this.ogrenmeCiktilari = ogrenmeCiktilari;
    }

    public String getDersTanimi() {
        return dersTanimi;
    }

    public void setDersTanimi(String dersTanimi) {
        this.dersTanimi = dersTanimi;
    }

    public String getDersKategorisi() {
        return dersKategorisi;
    }

    public void setDersKategorisi(String dersKategorisi) {
        this.dersKategorisi = dersKategorisi;
    }

    public void setKodu(String kodu) {
    }
}


