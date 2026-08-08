package teknik;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class VeriIslemleri {

    public static void musteriEkle(String ad, String telefon, String adres) {

        try {

            FileWriter yaz = new FileWriter("musteriler.txt", true);

            yaz.write(ad + ";" + telefon + ";" + adres + "\n");

            yaz.close();

        }
        catch(IOException e) {

            System.out.println("DOSYA KAYDEDİLEMEDİ.");

        }

    }

    public static boolean telefonVarMi(String telefon) {

        try {

            File dosya = new File("musteriler.txt");
            Scanner oku = new Scanner(dosya);

            while(oku.hasNextLine()) {

                String satir = oku.nextLine();
                String[] bilgiler = satir.split(";");

                if(bilgiler.length == 3) {

                    if(bilgiler[1].equals(telefon)) {

                        oku.close();
                        return true;

                    }

                }

            }

            oku.close();

        }
        catch(Exception e) {

        }

        return false;

    }

    public static String[] musteriBul(String telefon) {

        try {

            File dosya = new File("musteriler.txt");
            Scanner oku = new Scanner(dosya);

            while (oku.hasNextLine()) {

                String[] bilgiler = oku.nextLine().split(";");

                if (bilgiler.length == 3) {

                    if (bilgiler[1].equals(telefon)) {

                        oku.close();
                        return bilgiler;

                    }

                }

            }

            oku.close();

        }
        catch (Exception e) {

        }

        return null;

    }
    public static void musteriGuncelle(String eskiTelefon,
            String yeniAd,
            String yeniTelefon,
            String yeniAdres) {

try {

File dosya = new File("musteriler.txt");
Scanner oku = new Scanner(dosya);

StringBuilder yeniDosya = new StringBuilder();

while (oku.hasNextLine()) {

String[] bilgiler = oku.nextLine().split(";");

if (bilgiler.length == 3) {

if (bilgiler[1].equals(eskiTelefon)) {

yeniDosya.append(
     yeniAd + ";" +
     yeniTelefon + ";" +
     yeniAdres + "\n");

}
else {

yeniDosya.append(
     bilgiler[0] + ";" +
     bilgiler[1] + ";" +
     bilgiler[2] + "\n");

}

}

}

oku.close();

FileWriter yaz = new FileWriter("musteriler.txt");

yaz.write(yeniDosya.toString());

yaz.close();

}
catch (Exception e) {

JOptionPane.showMessageDialog(null,
"GÜNCELLEME SIRASINDA HATA OLUŞTU.");

}

}
    public static void cihazEkle(String telefon,
            String tur,
            String marka,
            String model,
            String ariza,
            String durum) {

try {

FileWriter yaz = new FileWriter("cihazlar.txt", true);

String tarih = LocalDate.now().format(
        DateTimeFormatter.ofPattern("dd.MM.yyyy"));

yaz.write(
telefon + ";" +
tur + ";" +
marka + ";" +
model + ";" +
ariza + ";" +
durum + ";" +
tarih + "\n"
);

yaz.close();

}
catch(IOException e) {

JOptionPane.showMessageDialog(null,
"CİHAZ KAYDEDİLEMEDİ.");

}

}
    public static boolean durumGuncelle(String telefon,
            String tur,
            String marka,
            String model,
            String ariza,
            String eskiDurum,
            String yeniDurum) {

			try {
			
			File dosya = new File("cihazlar.txt");
			Scanner oku = new Scanner(dosya);
			
			StringBuilder veri = new StringBuilder();
			
			boolean bulundu = false;
			
			while(oku.hasNextLine()) {
			
			String[] bilgiler = oku.nextLine().split(";");
			
			if(bilgiler.length >= 6) {
			
			if(bilgiler[0].equals(telefon)
			&& bilgiler[1].equals(tur)
			&& bilgiler[2].equals(marka)
			&& bilgiler[3].equals(model)
			&& bilgiler[4].equals(ariza)
			&& bilgiler[5].equals(eskiDurum)) {
			
			bilgiler[5] = yeniDurum;
			bulundu = true;
			
			}
			
			veri.append(
					bilgiler[0] + ";" +
					bilgiler[1] + ";" +
					bilgiler[2] + ";" +
					bilgiler[3] + ";" +
					bilgiler[4] + ";" +
					bilgiler[5]);

					if(bilgiler.length == 7) {
					    veri.append(";" + bilgiler[6]);
					}

					veri.append("\n");
			
			}
			
			}
			
			oku.close();
			
			FileWriter yaz = new FileWriter("cihazlar.txt");
			
			yaz.write(veri.toString());
			
			yaz.close();
			
			return bulundu;

}
			catch(Exception e) {
			
			return false;
			
			}

}
    public static boolean musteriSil(String telefon) {

        try {

            File musteriDosya = new File("musteriler.txt");
            Scanner oku = new Scanner(musteriDosya);

            StringBuilder yeniMusteriler = new StringBuilder();

            boolean bulundu = false;

            while (oku.hasNextLine()) {

                String[] bilgiler = oku.nextLine().split(";");

                if (bilgiler.length == 3) {

                    if (bilgiler[1].equals(telefon)) {

                        bulundu = true;

                    }
                    else {

                        yeniMusteriler.append(
                                bilgiler[0] + ";" +
                                bilgiler[1] + ";" +
                                bilgiler[2] + "\n");

                    }

                }

            }

            oku.close();

            FileWriter yaz = new FileWriter("musteriler.txt");
            yaz.write(yeniMusteriler.toString());
            yaz.close();


            File cihazDosya = new File("cihazlar.txt");
            oku = new Scanner(cihazDosya);

            StringBuilder yeniCihazlar = new StringBuilder();

            while (oku.hasNextLine()) {

                String[] bilgiler = oku.nextLine().split(";");

                if (bilgiler.length >= 6) {
                    if (!bilgiler[0].equals(telefon)) {

                    	yeniCihazlar.append(
                    	        bilgiler[0] + ";" +
                    	        bilgiler[1] + ";" +
                    	        bilgiler[2] + ";" +
                    	        bilgiler[3] + ";" +
                    	        bilgiler[4] + ";" +
                    	        bilgiler[5]);

                    	if(bilgiler.length == 7) {
                    	    yeniCihazlar.append(";" + bilgiler[6]);
                    	}

                    	yeniCihazlar.append("\n");

                    }

                }

            }

            oku.close();

            yaz = new FileWriter("cihazlar.txt");
            yaz.write(yeniCihazlar.toString());
            yaz.close();

            return bulundu;

        }
        catch (Exception e) {

            return false;

        }

    }
    public static void cihazSil(String telefon,
            String tur,
            String marka,
            String model,
            String ariza,
            String durum,
            String tarih) {

try {

File dosya = new File("cihazlar.txt");
Scanner oku = new Scanner(dosya);

StringBuilder yeniDosya = new StringBuilder();

while(oku.hasNextLine()) {

String satir = oku.nextLine();

String[] bilgiler = satir.split(";");

if(bilgiler.length == 7) {
if(bilgiler[0].equals(telefon)
        && bilgiler[1].equals(tur)
        && bilgiler[2].equals(marka)
        && bilgiler[3].equals(model)
        && bilgiler[4].equals(ariza)
        && bilgiler[5].equals(durum)
        && bilgiler[6].equals(tarih)) {

    continue;

}

yeniDosya.append(satir).append("\n");

}

}

oku.close();

FileWriter yaz = new FileWriter("cihazlar.txt");

yaz.write(yeniDosya.toString());

yaz.close();

}
catch(Exception e) {

JOptionPane.showMessageDialog(null,
"CİHAZ SİLİNEMEDİ.");

}

}
}