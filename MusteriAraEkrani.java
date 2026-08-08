package teknik;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class MusteriAraEkrani extends JFrame {

    JTextField txtAra;
    JButton btnAra;

    JTextArea sonuc;

    public MusteriAraEkrani() {

        setTitle("MÜŞTERİ ARA");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);

        getContentPane().setBackground(arkaPlan);

        JPanel baslikPanel = new JPanel(new BorderLayout());

        baslikPanel.setBackground(lacivert);

        baslikPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 10, 20, 10
                )
        );

        JLabel baslik = new JLabel(
                "MÜŞTERİ ARA",
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

        JPanel ustPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        ustPanel.setBackground(arkaPlan);

        ustPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 15, 30
                )
        );

        JLabel etiket =
                new JLabel(
                        "TELEFON VEYA AD SOYAD"
                );

        etiket.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        etiket.setForeground(lacivert);

        txtAra = new JTextField();

        txtAra.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        btnAra = new JButton("ARA");

        butonAyarla(btnAra, lacivert);

        ustPanel.add(
                etiket,
                BorderLayout.NORTH
        );

        ustPanel.add(
                txtAra,
                BorderLayout.CENTER
        );

        ustPanel.add(
                btnAra,
                BorderLayout.EAST
        );

        sonuc = new JTextArea();

        sonuc.setEditable(false);

        sonuc.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        sonuc.setBackground(Color.WHITE);

        sonuc.setForeground(Color.BLACK);

        sonuc.setLineWrap(false);

        JScrollPane scroll =
                new JScrollPane(sonuc);

        scroll.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(
                                5, 30, 20, 30
                        ),
                        BorderFactory.createLineBorder(
                                new Color(200, 205, 210)
                        )
                )
        );

        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        JPanel ortaPanel =
                new JPanel(
                        new BorderLayout()
                );

        ortaPanel.setBackground(arkaPlan);

        ortaPanel.add(
                ustPanel,
                BorderLayout.NORTH
        );

        ortaPanel.add(
                scroll,
                BorderLayout.CENTER
        );

        add(
                ortaPanel,
                BorderLayout.CENTER
        );

        btnAra.addActionListener(
                e -> ara()
        );

        setVisible(true);

    }

    private void ara() {

        sonuc.setText("");

        String aranan =
                txtAra.getText().trim();

        if(aranan.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "LÜTFEN İSİM VEYA TELEFON GİRİNİZ."
            );

            return;

        }

        boolean bulundu = false;
        String telefon = "";

        try {

            File dosya =
                    new File("musteriler.txt");

            Scanner oku =
                    new Scanner(dosya);

            while(oku.hasNextLine()) {

                String[] bilgiler =
                        oku.nextLine().split(";");

                if(bilgiler.length == 3) {

                    if(bilgiler[0].equalsIgnoreCase(aranan)
                            || bilgiler[1].equals(aranan)) {

                        bulundu = true;
                        telefon = bilgiler[1];

                        sonuc.append(
                                "MÜŞTERİ BİLGİLERİ\n"
                        );

                        sonuc.append(
                                "-------------------------\n"
                        );

                        sonuc.append(
                                "AD SOYAD : "
                                + bilgiler[0]
                                + "\n"
                        );

                        sonuc.append(
                                "TELEFON  : "
                                + bilgiler[1]
                                + "\n"
                        );

                        sonuc.append(
                                "ADRES    : "
                                + bilgiler[2]
                                + "\n\n"
                        );

                        break;

                    }

                }

            }

            oku.close();

            if(!bulundu) {

                sonuc.setText(
                        "MÜŞTERİ BULUNAMADI."
                );

                return;

            }

            sonuc.append(
                    "CİHAZLARI\n"
            );

            sonuc.append(
                    "-------------------------\n"
            );

            File cihazDosya =
                    new File("cihazlar.txt");

            Scanner cihazOku =
                    new Scanner(cihazDosya);

            boolean cihazVar = false;

            while(cihazOku.hasNextLine()) {

                String[] bilgiler =
                        cihazOku.nextLine().split(";");

                if(bilgiler.length == 7 &&
                        bilgiler[0].equals(telefon)) {

                    cihazVar = true;

                    sonuc.append(
                            "TÜR    : "
                            + bilgiler[1]
                            + "\n"
                    );

                    sonuc.append(
                            "MARKA  : "
                            + bilgiler[2]
                            + "\n"
                    );

                    sonuc.append(
                            "MODEL  : "
                            + bilgiler[3]
                            + "\n"
                    );

                    sonuc.append(
                            "ARIZA  : "
                            + bilgiler[4]
                            + "\n"
                    );

                    sonuc.append(
                            "DURUM  : "
                            + bilgiler[5]
                            + "\n"
                    );

                    sonuc.append(
                            "GİRİŞ TARİHİ : "
                            + bilgiler[6]
                            + "\n"
                    );

                    sonuc.append(
                            "-------------------------\n"
                    );

                }

            }

            cihazOku.close();

            if(!cihazVar) {

                sonuc.append(
                        "BU MÜŞTERİYE AİT CİHAZ BULUNAMADI."
                );

            }

        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ARAMA SIRASINDA HATA OLUŞTU."
            );

        }

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
