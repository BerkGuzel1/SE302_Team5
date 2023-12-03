package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class DersArayuzu extends JFrame {
        private Ders ders;

        private JTextField dersAdiField;
        private JTextField dersKoduField;
        private JComboBox<String> donemComboBox;

        // Diğer alanlar için gerekli bileşenleri tanımla

        public DersArayuzu(Ders ders) {
            this.ders = ders;

            setTitle("Ders Bilgi Girişi");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

            // Dersin Adı
            JLabel dersAdiLabel = new JLabel("Dersin Adı:");
            dersAdiField = new JTextField(ders.getDersAdi());
            add(dersAdiLabel);
            add(dersAdiField);

            // Dersin Kodu
            JLabel dersKoduLabel = new JLabel("Dersin Kodu:");
            dersKoduField = new JTextField(ders.getDersKodu());
            add(dersKoduLabel);
            add(dersKoduField);

            // Güz veya Bahar
            JLabel donemLabel = new JLabel("Dönem:");
            String[] donemler = {"Güz", "Bahar"};
            donemComboBox = new JComboBox<>(donemler);
            donemComboBox.setSelectedItem(ders.getDonem());
            add(donemLabel);
            add(donemComboBox);

            // Diğer alanlar da var onlar için de benzer şeyleri ekleyecepğim

            // Save et
            JButton saveButton = new JButton("Save");
            add(saveButton);
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    kaydet();
                }
            });

            setVisible(true);
        }

        private void kaydet() {
            ders.setDersAdi(dersAdiField.getText());
            ders.setDersKodu(dersKoduField.getText());
            ders.setDonem((String) donemComboBox.getSelectedItem());

            // Diğer alanlardan gelen verileri de Ders nesnesine kaydettim ama Arraylist lazım bana

            // Verileri veritabanına yazabileceğim kısım da burda olursa iyi olur ama
        }

        public static void main(String[] args) {
            Ders ders = new Ders();
            DersArayuzu arayuz = new DersArayuzu(ders);
        }
    }


