package teknik;

import javax.swing.*;
import java.awt.*;

public class MusteriGuncelleEkrani extends JFrame {

    JTextField txtAraTelefon;
    JTextField txtAd;
    JTextField txtTelefon;
    JTextField txtAdres;

    JButton btnBul;
    JButton btnGuncelle;
    JButton btnKapat;

    public MusteriGuncelleEkrani() {

        setTitle("MÜŞTERİ GÜNCELLE");
        setSize(500, 420);
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
                        18, 10, 18, 10
                )
        );

        JLabel baslik =
                new JLabel(
                        "MÜŞTERİ GÜNCELLE",
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

       
        JPanel panel =
                new JPanel(
                        new GridLayout(
                                5, 2, 12, 15
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 15, 40
                )
        );

        panel.setBackground(arkaPlan);

        JLabel lblAra =
                new JLabel("ARANACAK TELEFON");

        JLabel lblAd =
                new JLabel("AD SOYAD");

        JLabel lblTelefon =
                new JLabel("TELEFON");

        JLabel lblAdres =
                new JLabel("ADRES");

        lblAra.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        lblAd.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        lblTelefon.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        lblAdres.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        lblAra.setForeground(lacivert);
        lblAd.setForeground(lacivert);
        lblTelefon.setForeground(lacivert);
        lblAdres.setForeground(lacivert);

        txtAraTelefon = new JTextField();
        txtAd = new JTextField();
        txtTelefon = new JTextField();
        txtAdres = new JTextField();

        txtAraTelefon.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        txtAd.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        txtTelefon.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        txtAdres.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        panel.add(lblAra);
        panel.add(txtAraTelefon);

        // BUL BUTONU

        btnBul = new JButton("BUL");

        butonAyarla(btnBul, lacivert);

        panel.add(btnBul);
        panel.add(new JLabel(""));

        panel.add(lblAd);
        panel.add(txtAd);

        panel.add(lblTelefon);
        panel.add(txtTelefon);

        panel.add(lblAdres);
        panel.add(txtAdres);

       

        JPanel altPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        altPanel.setBackground(arkaPlan);

        btnGuncelle =
                new JButton("GÜNCELLE");

        btnKapat =
                new JButton("KAPAT");

        butonAyarla(btnGuncelle, yesil);
        butonAyarla(btnKapat, kirmizi);

        altPanel.add(btnGuncelle);
        altPanel.add(btnKapat);

     
        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        add(
                panel,
                BorderLayout.CENTER
        );

        add(
                altPanel,
                BorderLayout.SOUTH
        );

     
        btnBul.addActionListener(e -> {

            String[] bilgiler =
                    VeriIslemleri.musteriBul(
                            txtAraTelefon.getText()
                    );

            if (bilgiler == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "MÜŞTERİ BULUNAMADI."
                );

            }
            else {

                txtAd.setText(bilgiler[0]);
                txtTelefon.setText(bilgiler[1]);
                txtAdres.setText(bilgiler[2]);

            }

        });


        btnGuncelle.addActionListener(e -> {

            if (txtAraTelefon.getText().trim().isEmpty()
                    || txtAd.getText().trim().isEmpty()
                    || txtTelefon.getText().trim().isEmpty()
                    || txtAdres.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "LÜTFEN TÜM ALANLARI DOLDURUN."
                );

                return;
            }

            VeriIslemleri.musteriGuncelle(
                    txtAraTelefon.getText(),
                    txtAd.getText(),
                    txtTelefon.getText(),
                    txtAdres.getText()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "MÜŞTERİ BAŞARIYLA GÜNCELLENDİ."
            );

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
                new Font("Arial", Font.BOLD, 13)
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
                                10, 20, 10, 20
                        )
                )
        );

        buton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }
}
