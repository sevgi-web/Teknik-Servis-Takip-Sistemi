package teknik;

import java.awt.*;
import javax.swing.*;

public class CihazEkrani extends JFrame {

    public CihazEkrani() {

        setTitle("CİHAZ İŞLEMLERİ");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color kirmizi = new Color(190, 65, 65);

        getContentPane().setBackground(arkaPlan);

        JPanel baslikPanel = new JPanel(new BorderLayout());
        baslikPanel.setBackground(lacivert);
        baslikPanel.setBorder(
                BorderFactory.createEmptyBorder(18, 10, 18, 10)
        );

        JLabel baslik = new JLabel(
                "CİHAZ İŞLEMLERİ",
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

        JPanel panel = new JPanel(
                new GridLayout(3, 1, 15, 15)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 80, 30, 80
                )
        );

        panel.setBackground(arkaPlan);

        JButton btnEkle =
                new JButton("CİHAZ EKLE");

        JButton btnListele =
                new JButton("CİHAZLARI LİSTELE");

        JButton btnKapat =
                new JButton("KAPAT");

        butonAyarla(btnEkle, lacivert);
        butonAyarla(btnListele, lacivert);
        butonAyarla(btnKapat, kirmizi);

        btnEkle.addActionListener(e -> {
            new CihazEkleEkrani();
        });

        btnListele.addActionListener(e -> {
            new CihazListeleEkrani();
        });

        btnKapat.addActionListener(e -> {
            dispose();
        });

        panel.add(btnEkle);
        panel.add(btnListele);
        panel.add(btnKapat);

        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        add(
                panel,
                BorderLayout.CENTER
        );

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
