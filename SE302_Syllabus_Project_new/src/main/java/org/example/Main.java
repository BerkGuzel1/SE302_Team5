package org.example;

import java.util.ArrayList;
import java.util.Scanner;


class HaftaBilgisi {

    // Hafta bilgilerini içeren sınıfım

        private int hafta;
        private String konular;
        private String onHazirlik;

        public HaftaBilgisi(int hafta, String konular, String onHazirlik) {
            this.hafta = hafta;
            this.konular = konular;
            this.onHazirlik = onHazirlik;
        }

        public int getHafta() {
            return hafta;
        }

        public String getKonular() {
            return konular;
        }

        public String getOnHazirlik() {
            return onHazirlik;
        }
    }

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<HaftaBilgisi> haftalikBilgiler = new ArrayList<>();

        for (int i = 1; i <= 16; i++) {
            System.out.println("Hafta " + i + " Konuları:");
            String konu = scanner.nextLine();

            System.out.println("Hafta " + i + " Ön Hazırlık Bilgileri:");
            String onHazirlik = scanner.nextLine();

            HaftaBilgisi bilgi = new HaftaBilgisi(i, konu, onHazirlik);
            haftalikBilgiler.add(bilgi);
        }

        for (HaftaBilgisi bilgi : haftalikBilgiler) {
            System.out.println("Hafta: " + bilgi.getHafta() +
                    ", Konular: " + bilgi.getKonular() +
                    ", Ön Hazırlık: " + bilgi.getOnHazirlik());
        }

        scanner.close();
    }

}


