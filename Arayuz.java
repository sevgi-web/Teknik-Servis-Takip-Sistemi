package teknik;

import java.awt.*;
import javax.swing.*;

public class Arayuz {

    public static void main(String[] args) {

        Yedekleme.yedekle();

        JFrame pencere = new JFrame("Teknik Servis Takip Sistemi");

        pencere.setSize(750, 650);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setLocationRelativeTo(null);
        pencere.setLayout(new BorderLayout());

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color koyuLacivert = new Color(24, 43, 63);
        Color acikGri = new Color(225, 230, 235);
        Color yesil = new Color(45, 125, 85);
        Color kirmizi = new Color(190, 65, 65);

        pencere.getContentPane().setBackground(arkaPlan);

        JPanel baslikPanel = new JPanel(new BorderLayout());
        baslikPanel.setBackground(lacivert);
        baslikPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 10, 25, 10)
        );

        JLabel baslik = new JLabel(
                "TEKNİK SERVİS TAKİP SİSTEMİ",
                SwingConstants.CENTER
        );

        baslik.setFont(new Font("Arial", Font.BOLD, 26));
        baslik.setForeground(Color.WHITE);

        JLabel altBaslik = new JLabel(
                "Müşteri ve Cihaz Yönetim Sistemi",
                SwingConstants.CENTER
        );

        altBaslik.setFont(new Font("Arial", Font.PLAIN, 14));
        altBaslik.setForeground(new Color(220, 225, 230));

        baslikPanel.add(baslik, BorderLayout.CENTER);
        baslikPanel.add(altBaslik, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 15, 15));
        panel.setBorder(
                BorderFactory.createEmptyBorder(30, 140, 35, 140)
        );
        panel.setBackground(arkaPlan);

        JButton btnMusteri = new JButton("MÜŞTERİ İŞLEMLERİ");
        JButton btnCihaz = new JButton("CİHAZ İŞLEMLERİ");
        JButton btnAra = new JButton("MÜŞTERİ ARA");
        JButton btnIstatistik = new JButton("İSTATİSTİKLER");
        JButton btnYedekleme = new JButton("YEDEKLEME");
        JButton btnCikis = new JButton("ÇIKIŞ");

        btnMusteri.addActionListener(e -> {
            new MusteriEkrani();
        });

        btnCihaz.addActionListener(e -> {
            new CihazEkrani();
        });

        btnAra.addActionListener(e -> {
            new MusteriAraEkrani();
        });

        btnIstatistik.addActionListener(e -> {
            new IstatistikEkrani();
        });

        btnYedekleme.addActionListener(e -> {
            new YedeklemeEkrani();
        });

        btnCikis.addActionListener(e -> {

            int cevap = JOptionPane.showConfirmDialog(
                    pencere,
                    "Programdan çıkmak istediğinize emin misiniz?",
                    "Çıkış",
                    JOptionPane.YES_NO_OPTION
            );

            if (cevap == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        });

        butonAyarla(btnMusteri, Color.WHITE, lacivert);
        butonAyarla(btnCihaz, Color.WHITE, lacivert);
        butonAyarla(btnAra, Color.WHITE, lacivert);
        butonAyarla(btnIstatistik, Color.WHITE, lacivert);
        butonAyarla(btnYedekleme, Color.WHITE, lacivert);
        butonAyarla(btnCikis, new Color(250, 245, 245), kirmizi);

        panel.add(btnMusteri);
        panel.add(btnCihaz);
        panel.add(btnAra);
        panel.add(btnIstatistik);
        panel.add(btnYedekleme);
        panel.add(btnCikis);

        pencere.add(baslikPanel, BorderLayout.NORTH);
        pencere.add(panel, BorderLayout.CENTER);

        setVisible(pencere);
    }

    private static void butonAyarla(
            JButton buton,
            Color arkaPlan,
            Color yaziRengi) {

        buton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        buton.setForeground(yaziRengi);
        buton.setBackground(arkaPlan);
        buton.setFocusPainted(false);

        buton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 205, 210)
                        ),
                        BorderFactory.createEmptyBorder(
                                12, 10, 12, 10
                        )
                )
        );

        buton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }

    private static void setVisible(JFrame pencere) {
        pencere.setVisible(true);
    }
}