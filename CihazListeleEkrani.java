package teknik;

import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CihazListeleEkrani extends JFrame {

    DefaultTableModel model;
    JTable tablo;

    JButton btnSil;
    JButton btnDurum;
    JButton btnKapat;

    public CihazListeleEkrani() {

        setTitle("CİHAZ LİSTESİ");
        setSize(1000, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color arkaPlan = new Color(245, 247, 250);
        Color lacivert = new Color(31, 55, 80);
        Color kirmizi = new Color(190, 65, 65);
        Color turuncu = new Color(190, 125, 45);

        getContentPane().setBackground(arkaPlan);

        JPanel baslikPanel = new JPanel(new BorderLayout());
        baslikPanel.setBackground(lacivert);
        baslikPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 10, 18, 10
                )
        );

        JLabel baslik = new JLabel(
                "CİHAZ LİSTESİ",
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

        model.addColumn("MÜŞTERİ");
        model.addColumn("TELEFON");
        model.addColumn("CİHAZ TÜRÜ");
        model.addColumn("MARKA");
        model.addColumn("MODEL");
        model.addColumn("ARIZA");
        model.addColumn("DURUM");
        model.addColumn("GİRİŞ TARİHİ");

        tablo = new JTable(model);

        tablo.setRowHeight(30);

        tablo.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        tablo.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        tablo.getTableHeader().setBackground(lacivert);
        tablo.getTableHeader().setForeground(Color.WHITE);

        tablo.setSelectionBackground(
                new Color(220, 228, 235)
        );

        tablo.setSelectionForeground(Color.BLACK);

        tablo.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scroll = new JScrollPane(tablo);

        scroll.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 10, 20
                )
        );

        btnSil = new JButton("CİHAZI SİL");
        btnDurum = new JButton("DURUM GÜNCELLE");
        btnKapat = new JButton("KAPAT");

        butonAyarla(btnSil, kirmizi);
        butonAyarla(btnDurum, turuncu);
        butonAyarla(btnKapat, kirmizi);

        JPanel altPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        10
                )
        );

        altPanel.setBackground(arkaPlan);

        altPanel.add(btnSil);
        altPanel.add(btnDurum);
        altPanel.add(btnKapat);

        btnKapat.addActionListener(e ->
                dispose()
        );

        btnSil.addActionListener(e ->
                cihazSil()
        );

        btnDurum.addActionListener(e ->
                durumGuncelle()
        );

        add(
                baslikPanel,
                BorderLayout.NORTH
        );

        add(
                scroll,
                BorderLayout.CENTER
        );

        add(
                altPanel,
                BorderLayout.SOUTH
        );

        cihazlariYukle();

        setVisible(true);
    }

    private void cihazlariYukle() {

        model.setRowCount(0);

        try {

            File dosya = new File("cihazlar.txt");

            Scanner oku = new Scanner(dosya);

            while (oku.hasNextLine()) {

                String[] bilgiler =
                        oku.nextLine().split(";");

                if (bilgiler.length == 7) {

                    String musteriAdi = "";

                    try {

                        File musteriDosya =
                                new File("musteriler.txt");

                        Scanner musteriOku =
                                new Scanner(musteriDosya);

                        while (musteriOku.hasNextLine()) {

                            String[] musteri =
                                    musteriOku.nextLine().split(";");

                            if (musteri.length == 3
                                    && musteri[1].equals(bilgiler[0])) {

                                musteriAdi = musteri[0];

                                break;
                            }
                        }

                        musteriOku.close();

                    }
                    catch (Exception e) {

                    }

                    model.addRow(
                            new Object[] {
                                    musteriAdi,
                                    bilgiler[0],
                                    bilgiler[1],
                                    bilgiler[2],
                                    bilgiler[3],
                                    bilgiler[4],
                                    bilgiler[5],
                                    bilgiler[6]
                            }
                    );
                }
            }

            oku.close();

        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "CİHAZ DOSYASI OKUNAMADI.",
                    "HATA",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cihazSil() {

        int satir = tablo.getSelectedRow();

        if (satir == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "LÜTFEN SİLMEK İSTEDİĞİNİZ CİHAZI SEÇİNİZ.",
                    "UYARI",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String telefon =
                model.getValueAt(satir, 1).toString();

        String tur =
                model.getValueAt(satir, 2).toString();

        String marka =
                model.getValueAt(satir, 3).toString();

        String modelAdi =
                model.getValueAt(satir, 4).toString();

        String ariza =
                model.getValueAt(satir, 5).toString();

        String durum =
                model.getValueAt(satir, 6).toString();

        String tarih =
                model.getValueAt(satir, 7).toString();

        String musteri =
                model.getValueAt(satir, 0).toString();

        dialogAyarlari();

        Object[] secenekler = {
                "EVET, SİL",
                "VAZGEÇ"
        };

        int cevap = JOptionPane.showOptionDialog(
                this,

                "SEÇİLEN CİHAZ SİLİNECEK.\n\n"
                + "MÜŞTERİ : " + musteri + "\n"
                + "CİHAZ   : " + marka + " " + modelAdi + "\n"
                + "TELEFON : " + telefon + "\n\n"
                + "BU İŞLEM GERİ ALINAMAZ.\n"
                + "DEVAM ETMEK İSTİYOR MUSUNUZ?",

                "CİHAZ SİLME ONAYI",

                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,

                null,
                secenekler,
                secenekler[1]
        );

        if (cevap != 0) {

            return;
        }

        VeriIslemleri.cihazSil(
                telefon,
                tur,
                marka,
                modelAdi,
                ariza,
                durum,
                tarih
        );

        cihazlariYukle();

        JOptionPane.showMessageDialog(
                this,
                "CİHAZ BAŞARIYLA SİLİNDİ.",
                "İŞLEM BAŞARILI",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void durumGuncelle() {

        int satir = tablo.getSelectedRow();

        if (satir == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "LÜTFEN DURUMUNU GÜNCELLEMEK İSTEDİĞİNİZ CİHAZI SEÇİNİZ.",
                    "UYARI",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String telefon =
                model.getValueAt(satir, 1).toString();

        String tur =
                model.getValueAt(satir, 2).toString();

        String marka =
                model.getValueAt(satir, 3).toString();

        String modelAdi =
                model.getValueAt(satir, 4).toString();

        String ariza =
                model.getValueAt(satir, 5).toString();

        String eskiDurum =
                model.getValueAt(satir, 6).toString();

        String[] secenekler = {
                "BEKLİYOR",
                "TAMİRDE",
                "HAZIR",
                "TESLİM EDİLDİ"
        };

        dialogAyarlari();

        String yeniDurum =
                (String) JOptionPane.showInputDialog(
                        this,
                        "CİHAZIN YENİ DURUMUNU SEÇİNİZ:\n\n"
                        + "MEVCUT DURUM : " + eskiDurum,

                        "DURUM GÜNCELLE",

                        JOptionPane.PLAIN_MESSAGE,

                        null,

                        secenekler,

                        eskiDurum
                );

        if (yeniDurum == null) {

            return;
        }

        if (yeniDurum.equals(eskiDurum)) {

            JOptionPane.showMessageDialog(
                    this,
                    "CİHAZIN DURUMU ZATEN \""
                    + eskiDurum
                    + "\" OLARAK AYARLANMIŞ.",
                    "BİLGİ",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        Object[] onaySecenekleri = {
                "EVET, GÜNCELLE",
                "VAZGEÇ"
        };

        int cevap = JOptionPane.showOptionDialog(
                this,

                "CİHAZ DURUMU DEĞİŞTİRİLECEK.\n\n"
                + "MEVCUT DURUM : " + eskiDurum + "\n"
                + "YENİ DURUM   : " + yeniDurum + "\n\n"
                + "GÜNCELLEMEYİ ONAYLIYOR MUSUNUZ?",

                "DURUM GÜNCELLEME ONAYI",

                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,

                null,

                onaySecenekleri,

                onaySecenekleri[1]
        );

        if (cevap != 0) {

            return;
        }

        boolean guncellendi =
                VeriIslemleri.durumGuncelle(
                        telefon,
                        tur,
                        marka,
                        modelAdi,
                        ariza,
                        eskiDurum,
                        yeniDurum
                );

        if (guncellendi) {

            cihazlariYukle();

            JOptionPane.showMessageDialog(
                    this,
                    "CİHAZ DURUMU BAŞARIYLA GÜNCELLENDİ.\n\n"
                    + "YENİ DURUM : " + yeniDurum,
                    "İŞLEM BAŞARILI",
                    JOptionPane.INFORMATION_MESSAGE
            );

        }
        else {

            JOptionPane.showMessageDialog(
                    this,
                    "DURUM GÜNCELLENEMEDİ.",
                    "HATA",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void dialogAyarlari() {

        UIManager.put(
                "OptionPane.background",
                new Color(245, 247, 250)
        );

        UIManager.put(
                "Panel.background",
                new Color(245, 247, 250)
        );

        UIManager.put(
                "OptionPane.messageFont",
                new Font("Arial", Font.BOLD, 13)
        );

        UIManager.put(
                "OptionPane.buttonFont",
                new Font("Arial", Font.BOLD, 12)
        );

        UIManager.put(
                "OptionPane.buttonForeground",
                new Color(31, 55, 80)
        );
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