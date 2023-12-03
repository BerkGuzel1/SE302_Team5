package org.example;
import java.util.Scanner;

public class DersDegerlendirmeOlcutleri {
    private String[][] aktiviteler;
    private int[][] loDegerleri;
    private int[] toplam;

        public DersDegerlendirmeOlcutleri() {
            this.aktiviteler = new String[12][3]; // Her aktivite için 3 verim
            this.loDegerleri = new int[12][7]; // Her aktivite için 7 LO değerif
            this.toplam = new int[3]; // Toplam, Yarıyıl İçi, Yarıyıl Sonusd
        }

/*
    public String[] getAktiviteler() {
        return aktiviteler;
    }

    public void setAktiviteler(String[] aktiviteler) {
        this.aktiviteler = aktiviteler;
    }

    public int[] getSayi() {
        return sayi;
    }

    public void setSayi(int[] sayi) {
        this.sayi = sayi;
    }

    public int[] getKatkiPayi() {
        return katkiPayi;
    }

    public void setKatkiPayi(int[] katkiPayi) {
        this.katkiPayi = katkiPayi;
    }

    public int[] getLo1() {
        return lo1;
    }

    public void setLo1(int[] lo1) {
        this.lo1 = lo1;
    }

    public int[] getLo2() {
        return lo2;
    }

    public void setLo2(int[] lo2) {
        this.lo2 = lo2;
    }

    public int[] getLo3() {
        return lo3;
    }

    public void setLo3(int[] lo3) {
        this.lo3 = lo3;
    }

    public int[] getLo4() {
        return lo4;
    }

    public void setLo4(int[] lo4) {
        this.lo4 = lo4;
    }

    public int[] getLo5() {
        return lo5;
    }

    public void setLo5(int[] lo5) {
        this.lo5 = lo5;
    }

    public int[] getLo6() {
        return lo6;
    }

    public void setLo6(int[] lo6) {
        this.lo6 = lo6;
    }

    public int[] getLo7() {
        return lo7;
    }

    public void setLo7(int[] lo7) {
        this.lo7 = lo7;
    }

    public int[] getToplam() {
        return toplam;
    }

    public void setToplam(int[] toplam) {
        this.toplam = toplam;
    }

 */

    public String[][] getAktiviteler() {
        return aktiviteler;
    }

    public void setAktiviteler(String[][] aktiviteler) {
        this.aktiviteler = aktiviteler;
    }

    public int[][] getLoDegerleri() {
        return loDegerleri;
    }

    public void setLoDegerleri(int[][] loDegerleri) {
        this.loDegerleri = loDegerleri;
    }

    public int[] getToplam() {
        return toplam;
    }

    public void setToplam(int[] toplam) {
        this.toplam = toplam;
    }

    public void veriGirisi() {
            Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 12; i++) {
            System.out.println((i + 1) + ". Aktivite İçin Bilgiler:");

            System.out.println("Aktivite adını girin: ");
            this.aktiviteler[i][0] = scanner.nextLine();

            System.out.println("Sayıyı girin: ");
            this.aktiviteler[i][1] = scanner.nextLine();

            System.out.println("Katkı Payı % değerini girin: ");
            this.aktiviteler[i][2] = scanner.nextLine();

            System.out.println("LO değerlerini girin: ");
            for (int j = 0; j < 7; j++) {
                System.out.println("LO" + (j + 1) + " değerini girin: ");
                this.loDegerleri[i][j] = scanner.nextInt();
            }

            scanner.nextLine(); // Buffer temizlemem
        }
        }

    // Toplamı hesaplayan metotmbe
    public void toplamHesapla() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 7; j++) {
                this.toplam[j] += this.loDegerleri[i][j];
            }
        }
    }

    // Toplamı gösteren metotm
    public void toplamGoster() {
        for (int j = 0; j < 3; j++) {
            System.out.println("Toplam " + (j + 1) + ": " + this.toplam[j]);
        }
    }

    public static void main(String[] args) {
        DersDegerlendirmeOlcutleri degerlendirme = new DersDegerlendirmeOlcutleri();
        degerlendirme.veriGirisi();
        degerlendirme.toplamHesapla();
        degerlendirme.toplamGoster();
    }
}

