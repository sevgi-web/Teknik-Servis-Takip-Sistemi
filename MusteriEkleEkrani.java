package teknik;

import javax.swing.*;
import java.awt.*;

public class MusteriEkleEkrani extends JFrame {

    JTextField txtAd;
    JTextField txtTelefon;
    JTextField txtAdres;

    JButton btnKaydet;
    JButton btnKapat;

    public MusteriEkleEkrani() {

        setTitle("MÜŞTERİ EKLE");
        setSize(500, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color yesil = new Color(45, 125, 85);
        Color kirmizi = new Color(190, 65, 65);

        getContentPane().setBackground(arkaPlan);

        JPanel baslikPanel =
                new JPanel(new BorderLayout());

        baslikPanel.setBackground(lacivert);

        baslikPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 10, 20, 10
                )
        );

        JLabel baslik =
                new JLabel(
                        "MÜŞTERİ EKLE",
                        SwingConstants.CENTER
                );

        baslik.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        baslik.setForeground(Color.WHITE);

        baslikPanel.add(
                baslik,
                BorderLayout.CENTER
        );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                12,
                                15
                        )
                );

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        20,
                        50
                )
        );

        formPanel.setBackground(arkaPlan);

        JLabel lblAd =
                new JLabel("AD SOYAD");

        JLabel lblTelefon =
                new JLabel("TELEFON");

        JLabel lblAdres =
                new JLabel("ADRES");

        lblAd.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        lblTelefon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        lblAdres.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        lblAd.setForeground(lacivert);
        lblTelefon.setForeground(lacivert);
        lblAdres.setForeground(lacivert);

        txtAd = new JTextField();
        txtTelefon = new JTextField();
        txtAdres = new JTextField();

        txtAd.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        txtTelefon.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        txtAdres.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        formPanel.add(lblAd);
        formPanel.add(txtAd);

        formPanel.add(lblTelefon);
        formPanel.add(txtTelefon);

        formPanel.add(lblAdres);
        formPanel.add(txtAdres);

        JPanel altPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        altPanel.setBackground(arkaPlan);

        btnKaydet =
                new JButton("KAYDET");

        btnKapat =
                new JButton("KAPAT");

        butonAyarla(
                btnKaydet,
                yesil
        );

        butonAyarla(
                btnKapat,
                kirmizi
        );

        altPanel.add(btnKaydet);
        altPanel.add(btnKapat);

        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        add(
                formPanel,
                BorderLayout.CENTER
        );

        add(
                altPanel,
                BorderLayout.SOUTH
        );

        btnKaydet.addActionListener(e -> {

            String ad =
                    txtAd.getText();

            String telefon =
                    txtTelefon.getText();

            String adres =
                    txtAdres.getText();

            if(ad.isEmpty()
                    || telefon.isEmpty()
                    || adres.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "LÜTFEN TÜM ALANLARI DOLDURUN."
                );

                return;
            }

            if(VeriIslemleri.telefonVarMi(
                    telefon)) {

                JOptionPane.showMessageDialog(
                        this,
                        "BU TELEFON NUMARASI ZATEN KAYITLI."
                );

            }
            else {

                VeriIslemleri.musteriEkle(
                        ad,
                        telefon,
                        adres
                );

                JOptionPane.showMessageDialog(
                        this,
                        "MÜŞTERİ BAŞARIYLA EKLENDİ."
                );

                txtAd.setText("");
                txtTelefon.setText("");
                txtAdres.setText("");

            }

        });

        btnKapat.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
    }

    private void butonAyarla(
            JButton buton,
            Color yaziRengi) {

        buton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        buton.setForeground(
                yaziRengi
        );

        buton.setBackground(
                Color.WHITE
        );

        buton.setFocusPainted(
                false
        );

        buton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        200,
                                        205,
                                        210
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                20,
                                10,
                                20
                        )
                )
        );

        buton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );
    }
}