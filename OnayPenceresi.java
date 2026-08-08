package teknik;

import java.awt.*;
import javax.swing.*;

public class OnayPenceresi {

    public static boolean goster(
            Component parent,
            String mesaj,
            String baslik) {

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color kirmizi = new Color(190, 65, 65);

        JPanel panel = new JPanel(new BorderLayout(10, 15));
        panel.setBackground(arkaPlan);
        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 10, 20
                )
        );

        JLabel lblMesaj = new JLabel(
                "<html>" +
                mesaj.replace("\n", "<br>") +
                "</html>"
        );

        lblMesaj.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        lblMesaj.setForeground(lacivert);

        panel.add(
                lblMesaj,
                BorderLayout.CENTER
        );

        JButton btnEvet =
                new JButton("EVET");

        JButton btnHayir =
                new JButton("VAZGEÇ");

        butonAyarla(btnEvet, lacivert);
        butonAyarla(btnHayir, kirmizi);

        JPanel butonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                0
                        )
                );

        butonPanel.setBackground(arkaPlan);

        butonPanel.add(btnEvet);
        butonPanel.add(btnHayir);

        panel.add(
                butonPanel,
                BorderLayout.SOUTH
        );

        final boolean[] sonuc = {false};

        JDialog pencere =
                new JDialog(
                        SwingUtilities.getWindowAncestor(parent),
                        baslik,
                        Dialog.ModalityType.APPLICATION_MODAL
                );

        pencere.setLayout(new BorderLayout());

        pencere.getContentPane()
                .setBackground(arkaPlan);

        pencere.add(
                panel,
                BorderLayout.CENTER
        );

        btnEvet.addActionListener(e -> {

            sonuc[0] = true;
            pencere.dispose();

        });

        btnHayir.addActionListener(e -> {

            sonuc[0] = false;
            pencere.dispose();

        });

        pencere.setSize(450, 210);
        pencere.setLocationRelativeTo(parent);
        pencere.setResizable(false);

        pencere.setVisible(true);

        return sonuc[0];
    }

    private static void butonAyarla(
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
                                9, 18, 9, 18
                        )
                )
        );

        buton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
    }
}
