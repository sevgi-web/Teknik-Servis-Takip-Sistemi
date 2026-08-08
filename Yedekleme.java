package teknik;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Yedekleme {

    public static void yedekle() {

        try {

            File anaKlasor = new File("yedekler");

            if (!anaKlasor.exists()) {

                anaKlasor.mkdir();

            }

            String tarih = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern(
                            "dd-MM-yyyy_HH-mm-ss"
                    )
            );

            File yedekKlasoru = new File(
                    anaKlasor,
                    "Yedek_" + tarih
            );

            yedekKlasoru.mkdir();

            File musteriDosyasi =
                    new File("musteriler.txt");

            if (musteriDosyasi.exists()) {

                dosyaKopyala(
                        musteriDosyasi,
                        new File(
                                yedekKlasoru,
                                "musteriler.txt"
                        )
                );

            }

            File cihazDosyasi =
                    new File("cihazlar.txt");

            if (cihazDosyasi.exists()) {

                dosyaKopyala(
                        cihazDosyasi,
                        new File(
                                yedekKlasoru,
                                "cihazlar.txt"
                        )
                );

            }

            eskiYedekleriSil(anaKlasor);

        }
        catch (Exception e) {

            System.out.println(
                    "YEDEKLEME SIRASINDA HATA OLUŞTU."
            );

        }

    }


    private static void dosyaKopyala(
            File kaynak,
            File hedef) throws Exception {

        FileInputStream giris =
                new FileInputStream(kaynak);

        FileOutputStream cikis =
                new FileOutputStream(hedef);

        byte[] veri = new byte[1024];

        int uzunluk;

        while ((uzunluk = giris.read(veri)) > 0) {

            cikis.write(veri, 0, uzunluk);

        }

        giris.close();
        cikis.close();

    }


    private static void eskiYedekleriSil(
            File anaKlasor) {

        File[] yedekler =
                anaKlasor.listFiles();

        if (yedekler == null) {

            return;

        }

        if (yedekler.length <= 5) {

            return;

        }

        java.util.Arrays.sort(
                yedekler,
                (a, b) -> Long.compare(
                        a.lastModified(),
                        b.lastModified()
                )
        );

        for (int i = 0;
             i < yedekler.length - 5;
             i++) {

            klasoruSil(yedekler[i]);

        }

    }


    private static void klasoruSil(File klasor) {

        if (klasor.isDirectory()) {

            File[] dosyalar =
                    klasor.listFiles();

            if (dosyalar != null) {

                for (File dosya : dosyalar) {

                    klasoruSil(dosya);

                }

            }

        }

        klasor.delete();

    }

}