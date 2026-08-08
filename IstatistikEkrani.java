package teknik;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class IstatistikEkrani extends JFrame {

    public IstatistikEkrani() {

        setTitle("İSTATİSTİKLER");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color kirmizi = new Color(190, 65, 65);

        getContentPane().setBackground(arkaPlan);

        int toplamMusteri = 0;
        int toplamCihaz = 0;
        int bekliyor = 0;
        int tamirde = 0;
        int hazir = 0;
        int teslim = 0;

        try {

            File musteriDosya =
                    new File("musteriler.txt");

            if (musteriDosya.exists()) {

                Scanner oku =
                        new Scanner(musteriDosya);

                while (oku.hasNextLine()) {

                    String[] bilgiler =
                            oku.nextLine().split(";");

                    if (bilgiler.length == 3) {
                        toplamMusteri++;
                    }
                }

                oku.close();
            }

            File cihazDosya =
                    new File("cihazlar.txt");

            if (cihazDosya.exists()) {

                Scanner oku =
                        new Scanner(cihazDosya);

                while (oku.hasNextLine()) {

                    String[] bilgiler =
                            oku.nextLine().split(";");

                    if (bilgiler.length == 7) {

                        toplamCihaz++;

                        if (bilgiler[5].equalsIgnoreCase("BEKLİYOR")) {
                            bekliyor++;
                        }
                        else if (bilgiler[5].equalsIgnoreCase("TAMİRDE")) {
                            tamirde++;
                        }
                        else if (bilgiler[5].equalsIgnoreCase("HAZIR")) {
                            hazir++;
                        }
                        else if (bilgiler[5].equalsIgnoreCase("TESLİM EDİLDİ")) {
                            teslim++;
                        }
                    }
                }

                oku.close();
            }

        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "İSTATİSTİKLER OKUNAMADI."
            );
        }

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
                        "İSTATİSTİKLER",
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

        JPanel bilgiPanel =
                new JPanel(
                        new GridLayout(6, 1, 10, 10)
                );

        bilgiPanel.setBackground(arkaPlan);

        bilgiPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 35, 15, 35
                )
        );

        JLabel lblToplamMusteri =
                new JLabel(
                        "TOPLAM MÜŞTERİ : " + toplamMusteri
                );

        JLabel lblToplamCihaz =
                new JLabel(
                        "TOPLAM CİHAZ : " + toplamCihaz
                );

        JLabel lblBekliyor =
                new JLabel(
                        "BEKLEYEN : " + bekliyor
                );

        JLabel lblTamirde =
                new JLabel(
                        "TAMİRDE : " + tamirde
                );

        JLabel lblHazir =
                new JLabel(
                        "HAZIR : " + hazir
                );

        JLabel lblTeslim =
                new JLabel(
                        "TESLİM EDİLDİ : " + teslim
                );

        JLabel[] etiketler = {
                lblToplamMusteri,
                lblToplamCihaz,
                lblBekliyor,
                lblTamirde,
                lblHazir,
                lblTeslim
        };

        for (JLabel etiket : etiketler) {

            etiket.setFont(
                    new Font("Arial", Font.BOLD, 15)
            );

            etiket.setForeground(lacivert);

            etiket.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(
                                    new Color(220, 225, 230)
                            ),
                            BorderFactory.createEmptyBorder(
                                    8, 15, 8, 15
                            )
                    ));

            bilgiPanel.add(etiket);
        }

        JPanel altPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        altPanel.setBackground(arkaPlan);

        JButton btnKapat =
                new JButton("KAPAT");

        butonAyarla(btnKapat, kirmizi);

        btnKapat.addActionListener(
                e -> dispose()
        );

        altPanel.add(btnKapat);

        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        add(
                bilgiPanel,
                BorderLayout.CENTER
        );

        add(
                altPanel,
                BorderLayout.SOUTH
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
