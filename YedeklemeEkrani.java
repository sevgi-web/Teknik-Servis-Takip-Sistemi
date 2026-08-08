package teknik;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.*;

public class YedeklemeEkrani extends JFrame {

    JList<String> liste;
    DefaultListModel<String> listeModel;

    public YedeklemeEkrani() {

        setTitle("YEDEKLEME İŞLEMLERİ");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color yesil = new Color(45, 125, 85);
        Color kirmizi = new Color(190, 65, 65);
        Color turuncu = new Color(190, 125, 45);

        getContentPane().setBackground(arkaPlan);

        setLayout(new BorderLayout());

        JPanel baslikPanel = new JPanel(new BorderLayout());

        baslikPanel.setBackground(lacivert);

        baslikPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 10, 18, 10
                )
        );

        JLabel baslik = new JLabel(
                "YEDEKLER",
                SwingConstants.CENTER
        );

        baslik.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        baslik.setForeground(Color.WHITE);

        baslikPanel.add(
                baslik,
                BorderLayout.CENTER
        );

        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        listeModel = new DefaultListModel<>();

        liste = new JList<>(listeModel);

        liste.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        liste.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        liste.setBackground(Color.WHITE);

        liste.setSelectionBackground(
                new Color(220, 228, 235)
        );

        liste.setSelectionForeground(Color.BLACK);

        JScrollPane scroll =
                new JScrollPane(liste);

        scroll.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 10, 20
                )
        );

        add(
                scroll,
                BorderLayout.CENTER
        );

        JButton btnYedekle =
                new JButton("YENİ YEDEK AL");

        JButton btnGeriYukle =
                new JButton("SEÇİLEN YEDEĞİ GERİ YÜKLE ");

        JButton btnSil =
                new JButton("SEÇİLEN YEDEĞİ SİL");

        JButton btnKapat =
                new JButton("KAPAT");

        butonAyarla(btnYedekle, yesil);
        butonAyarla(btnGeriYukle, turuncu);
        butonAyarla(btnSil, kirmizi);
        butonAyarla(btnKapat, lacivert);

        JPanel altPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        altPanel.setBackground(arkaPlan);

        altPanel.add(btnYedekle);
        altPanel.add(btnGeriYukle);
        altPanel.add(btnSil);
        altPanel.add(btnKapat);

        add(
                altPanel,
                BorderLayout.SOUTH
        );

        btnYedekle.addActionListener(e -> {

            Yedekleme.yedekle();

            yedekleriYukle();

            JOptionPane.showMessageDialog(
                    this,
                    "YENİ YEDEK BAŞARIYLA OLUŞTURULDU."
            );

        });

        btnGeriYukle.addActionListener(
                e -> geriYukle()
        );

        btnSil.addActionListener(
                e -> yedekSil()
        );

        btnKapat.addActionListener(
                e -> dispose()
        );

        yedekleriYukle();

        setVisible(true);
    }

    private void yedekleriYukle() {

        listeModel.clear();

        File klasor =
                new File("yedekler");

        if (!klasor.exists()) {

            klasor.mkdir();

        }

        File[] yedekler =
                klasor.listFiles();

        if (yedekler == null) {

            return;

        }

        for (File yedek : yedekler) {

            if (yedek.isDirectory()
                    && yedek.getName().startsWith("Yedek_")) {

                listeModel.addElement(
                        yedek.getName()
                );

            }

        }

    }

    private void geriYukle() {

        int secilen =
                liste.getSelectedIndex();

        if (secilen == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "LÜTFEN GERİ YÜKLEMEK İSTEDİĞİNİZ YEDEĞİ SEÇİNİZ."
            );

            return;

        }

        String yedekAdi =
                listeModel.getElementAt(secilen);

        File yedekKlasoru =
                new File(
                        "yedekler",
                        yedekAdi
                );

        int cevap =
                JOptionPane.showConfirmDialog(
                        this,
                        "SEÇİLEN YEDEKTEKİ MÜŞTERİ VE CİHAZ\n"
                        + "BİLGİLERİ MEVCUT VERİLEN ÜZERİNE YAZILACAK.\n\n"
                        + "DEVAM ETMEK İSTİYOR MUSUNUZ?",
                        "YEDEK GERİ YÜKLEME",
                        JOptionPane.YES_NO_OPTION
                );

        if (cevap != JOptionPane.YES_OPTION) {

            return;

        }

        try {

            File musteriYedek =
                    new File(
                            yedekKlasoru,
                            "musteriler.txt"
                    );

            File cihazYedek =
                    new File(
                            yedekKlasoru,
                            "cihazlar.txt"
                    );

            if (musteriYedek.exists()) {

                dosyaKopyala(
                        musteriYedek,
                        new File("musteriler.txt")
                );

            }

            if (cihazYedek.exists()) {

                dosyaKopyala(
                        cihazYedek,
                        new File("cihazlar.txt")
                );

            }

            JOptionPane.showMessageDialog(
                    this,
                    "YEDEK BAŞARIYLA GERİ YÜKLENDİ."
            );

        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "YEDEK GERİ YÜKLENİRKEN HATA OLUŞTU."
            );

        }

    }

    private void yedekSil() {

        int secilen =
                liste.getSelectedIndex();

        if (secilen == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "LÜTFEN SİLMEK İSTEDİĞİNİZ YEDEĞİ SEÇİNİZ."
            );

            return;

        }

        String yedekAdi =
                listeModel.getElementAt(secilen);

        int cevap =
                JOptionPane.showConfirmDialog(
                        this,
                        "SEÇİLEN YEDEK TAMAMEN SİLİNECEK.\n"
                        + "BU İŞLEM GERİ ALINAMAZ.\n\n"
                        + "DEVAM ETMEK İSTİYOR MUSUNUZ?",
                        "YEDEK SİL",
                        JOptionPane.YES_NO_OPTION
                );

        if (cevap != JOptionPane.YES_OPTION) {

            return;

        }

        File yedekKlasoru =
                new File(
                        "yedekler",
                        yedekAdi
                );

        if (klasoruSil(yedekKlasoru)) {

            yedekleriYukle();

            JOptionPane.showMessageDialog(
                    this,
                    "YEDEK BAŞARIYLA SİLİNDİ."
            );

        }
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "YEDEK SİLİNEMEDİ."
            );

        }

    }

    private void dosyaKopyala(
            File kaynak,
            File hedef) throws Exception {

        FileInputStream giris =
                new FileInputStream(kaynak);

        FileOutputStream cikis =
                new FileOutputStream(hedef);

        byte[] veri =
                new byte[1024];

        int uzunluk;

        while ((uzunluk =
                giris.read(veri)) > 0) {

            cikis.write(
                    veri,
                    0,
                    uzunluk
            );

        }

        giris.close();
        cikis.close();

    }

    private boolean klasoruSil(
            File klasor) {

        if (!klasor.exists()) {

            return false;

        }

        if (klasor.isDirectory()) {

            File[] dosyalar =
                    klasor.listFiles();

            if (dosyalar != null) {

                for (File dosya : dosyalar) {

                    klasoruSil(dosya);

                }

            }

        }

        return klasor.delete();

    }

    private void butonAyarla(
            JButton buton,
            Color yaziRengi) {

        buton.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        buton.setForeground(yaziRengi);

        buton.setBackground(Color.WHITE);

        buton.setFocusPainted(false);

        buton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(200, 205, 210)
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 15, 10, 15
                        )
                )
        );

        buton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }
}
