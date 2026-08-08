package teknik;

import java.awt.*;
import javax.swing.*;

public class MusteriSilEkrani extends JFrame {

    JTextField txtTelefon;
    JButton btnSil;

    public MusteriSilEkrani() {

        setTitle("MÜŞTERİ SİL");
        setSize(400, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color kirmizi = new Color(190, 65, 65);

        getContentPane().setBackground(arkaPlan);

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2, 2, 10, 10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        panel.setBackground(arkaPlan);

        JLabel lblTelefon =
                new JLabel("TELEFON NUMARASI");

        lblTelefon.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        lblTelefon.setForeground(lacivert);

        panel.add(lblTelefon);

        txtTelefon = new JTextField();

        txtTelefon.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        panel.add(txtTelefon);

        btnSil = new JButton("SİL");

        butonAyarla(btnSil, kirmizi);

        panel.add(new JLabel(""));
        panel.add(btnSil);

        add(panel);

        btnSil.addActionListener(
                e -> sil()
        );

        setVisible(true);
    }

    private void sil() {

        String telefon =
                txtTelefon.getText();

        if(telefon.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "TELEFON NUMARASINI GİRİNİZ."
            );

            return;

        }

        boolean silindi =
                VeriIslemleri.musteriSil(
                        telefon
                );

        if(silindi) {

            JOptionPane.showMessageDialog(
                    this,
                    "MÜŞTERİ VE TÜM CİHAZ KAYITLARI SİLİNDİ."
            );

            dispose();

        }
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "BU TELEFON NUMARASINA AİT MÜŞTERİ BULUNAMADI."
            );

        }

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
