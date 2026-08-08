package teknik;

import javax.swing.*;
import java.awt.*;

public class CihazEkleEkrani extends JFrame {

    JTextField txtTelefon;
    JTextField txtTur;
    JTextField txtMarka;
    JTextField txtModel;
    JTextField txtAriza;

    JButton btnKaydet;
    JButton btnKapat;

    public CihazEkleEkrani() {

        setTitle("CİHAZ EKLE");
        setSize(550, 500);
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
                BorderFactory.createEmptyBorder(
                        18, 10, 18, 10
                )
        );

        JLabel baslik = new JLabel(
                "CİHAZ EKLE",
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

     

        JPanel formPanel = new JPanel(
                new GridLayout(5, 2, 12, 12)
        );

        formPanel.setBackground(arkaPlan);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 35, 20, 35
                )
        );

        txtTelefon = new JTextField();
        txtTur = new JTextField();
        txtMarka = new JTextField();
        txtModel = new JTextField();
        txtAriza = new JTextField();

        alanAyarla(txtTelefon);
        alanAyarla(txtTur);
        alanAyarla(txtMarka);
        alanAyarla(txtModel);
        alanAyarla(txtAriza);

        JLabel lblTelefon =
                new JLabel("MÜŞTERİ TELEFONU");

        JLabel lblTur =
                new JLabel("CİHAZ TÜRÜ");

        JLabel lblMarka =
                new JLabel("MARKA");

        JLabel lblModel =
                new JLabel("MODEL");

        JLabel lblAriza =
                new JLabel("ARIZA");

        etiketAyarla(lblTelefon);
        etiketAyarla(lblTur);
        etiketAyarla(lblMarka);
        etiketAyarla(lblModel);
        etiketAyarla(lblAriza);

        formPanel.add(lblTelefon);
        formPanel.add(txtTelefon);

        formPanel.add(lblTur);
        formPanel.add(txtTur);

        formPanel.add(lblMarka);
        formPanel.add(txtMarka);

        formPanel.add(lblModel);
        formPanel.add(txtModel);

        formPanel.add(lblAriza);
        formPanel.add(txtAriza);

       

        JPanel altPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        10
                )
        );

        altPanel.setBackground(arkaPlan);

        btnKaydet = new JButton("KAYDET");
        btnKapat = new JButton("KAPAT");

        butonAyarla(btnKaydet, lacivert);
        butonAyarla(btnKapat, kirmizi);

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

            String telefon =
                    txtTelefon.getText().trim();

            String tur =
                    txtTur.getText().trim();

            String marka =
                    txtMarka.getText().trim();

            String model =
                    txtModel.getText().trim();

            String ariza =
                    txtAriza.getText().trim();

            if (telefon.isEmpty()
                    || tur.isEmpty()
                    || marka.isEmpty()
                    || model.isEmpty()
                    || ariza.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "LÜTFEN TÜM ALANLARI DOLDURUN."
                );

                return;
            }

            if (!VeriIslemleri.telefonVarMi(telefon)) {

                JOptionPane.showMessageDialog(
                        this,
                        "BU TELEFON NUMARASINA AİT MÜŞTERİ BULUNAMADI."
                );

                return;
            }

            VeriIslemleri.cihazEkle(
                    telefon,
                    tur,
                    marka,
                    model,
                    ariza,
                    "BEKLİYOR"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "CİHAZ BAŞARIYLA EKLENDİ."
            );

            txtTelefon.setText("");
            txtTur.setText("");
            txtMarka.setText("");
            txtModel.setText("");
            txtAriza.setText("");

        });

       

        btnKapat.addActionListener(e -> dispose());

        setVisible(true);
    }

    

    private void alanAyarla(JTextField alan) {

        alan.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        alan.setPreferredSize(
                new Dimension(200, 32)
        );
    }

    
    private void etiketAyarla(JLabel etiket) {

        etiket.setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        etiket.setForeground(
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
