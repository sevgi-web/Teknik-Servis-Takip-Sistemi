package teknik;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MusteriListeleEkrani extends JFrame {

    private DefaultTableModel model;
    private JTable tablo;
    private JLabel lblToplam;

    public MusteriListeleEkrani() {

        setTitle("MÜŞTERİ LİSTESİ");
        setSize(700, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
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
                        "MÜŞTERİ LİSTESİ",
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

        model = new DefaultTableModel();

        model.addColumn("AD SOYAD");
        model.addColumn("TELEFON");
        model.addColumn("ADRES");

        tablo = new JTable(model);

        tableAyarla();

        JScrollPane scroll =
                new JScrollPane(tablo);

        scroll.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 10, 20
                )
        );

        JPanel bilgiPanel =
                new JPanel(new BorderLayout());

        bilgiPanel.setBackground(arkaPlan);

        bilgiPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 0, 20
                )
        );

        lblToplam =
                new JLabel("TOPLAM MÜŞTERİ : 0");

        lblToplam.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        lblToplam.setForeground(lacivert);

        bilgiPanel.add(
                lblToplam,
                BorderLayout.WEST
        );

        JButton btnSil =
                new JButton("MÜŞTERİYİ SİL");

        JButton btnKapat =
                new JButton("KAPAT");

        butonAyarla(btnSil, kirmizi);
        butonAyarla(btnKapat, lacivert);

        JPanel altPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        altPanel.setBackground(arkaPlan);

        altPanel.add(btnSil);
        altPanel.add(btnKapat);

        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        add(
                scroll,
                BorderLayout.CENTER
        );

        JPanel ortaPanel =
                new JPanel(new BorderLayout());

        ortaPanel.setBackground(arkaPlan);

        ortaPanel.add(
                bilgiPanel,
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

        add(
                altPanel,
                BorderLayout.SOUTH
        );

        btnSil.addActionListener(
                e -> musteriSil()
        );

        btnKapat.addActionListener(
                e -> dispose()
        );

        tablo.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        musterileriYukle();

        setVisible(true);
    }

    private void tableAyarla() {

        tablo.setRowHeight(30);

        tablo.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        tablo.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        tablo.getTableHeader().setBackground(
                new Color(31, 55, 80)
        );

        tablo.getTableHeader().setForeground(
                Color.WHITE
        );

        tablo.setSelectionBackground(
                new Color(220, 228, 235)
        );

        tablo.setSelectionForeground(
                Color.BLACK
        );
    }

    private void musterileriYukle() {

        model.setRowCount(0);

        int sayi = 0;

        try {

            File dosya =
                    new File("musteriler.txt");

            Scanner oku =
                    new Scanner(dosya);

            while (oku.hasNextLine()) {

                String[] bilgiler =
                        oku.nextLine().split(";");

                if (bilgiler.length == 3) {

                    model.addRow(
                            new Object[] {
                                    bilgiler[0],
                                    bilgiler[1],
                                    bilgiler[2]
                            }
                    );

                    sayi++;
                }
            }

            oku.close();

        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "MÜŞTERİ DOSYASI OKUNAMADI."
            );
        }

        lblToplam.setText(
                "TOPLAM MÜŞTERİ : " + sayi
        );
    }

    private void musteriSil() {

        int satir =
                tablo.getSelectedRow();

        if (satir == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "LÜTFEN SİLMEK İSTEDİĞİNİZ MÜŞTERİYİ SEÇİNİZ."
            );

            return;
        }

        String ad =
                model.getValueAt(
                        satir,
                        0
                ).toString();

        String telefon =
                model.getValueAt(
                        satir,
                        1
                ).toString();

        boolean onay =
                OnayPenceresi.goster(
                        this,
                        ad + " ADLI MÜŞTERİYİ SİLMEK İSTİYOR MUSUNUZ?\n\n"
                        + "TELEFON: " + telefon
                        + "\n\nBU MÜŞTERİYE AİT CİHAZLAR DA SİLİNECEKTİR.",
                        "MÜŞTERİ SİLME ONAYI"
                );

        if (!onay) {

            return;
        }

        boolean silindi =
                VeriIslemleri.musteriSil(
                        telefon
                );

        if (silindi) {

            musterileriYukle();

            JOptionPane.showMessageDialog(
                    this,
                    "MÜŞTERİ BAŞARIYLA SİLİNDİ."
            );

        }
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "MÜŞTERİ SİLİNEMEDİ."
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