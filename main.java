package teknik;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class main { 
	public static void musterileriKaydet(ArrayList<String> adlar,
            ArrayList<String> telefonlar,
            ArrayList<String> adresler) {

				try {
				
				java.io.FileWriter yaz = new java.io.FileWriter("musteriler.txt");
				
				for(int i = 0; i < adlar.size(); i++) {
				
				yaz.write(adlar.get(i) + ";" +
				telefonlar.get(i) + ";" +
				adresler.get(i) + "\n");
				
				}
				
				yaz.close();
				
				}
				
				catch(Exception e) {
				
				System.out.println("DOSYA  KAYDEDİLEMEDİ.");
				
				}

}
	public static void cihazlariKaydet(ArrayList<String> cihazTelefonlari,
	        ArrayList<String> cihazTurleri,
	        ArrayList<String> markalar,
	        ArrayList<String> modeller,
	        ArrayList<String> arizalar,
	        ArrayList<String> durumlar) {

	    try {

	        FileWriter yaz = new FileWriter("cihazlar.txt");

	        for(int i = 0; i < cihazTelefonlari.size(); i++) {

	            yaz.write(
	                cihazTelefonlari.get(i) + ";" +
	                cihazTurleri.get(i) + ";" +
	                markalar.get(i) + ";" +
	                modeller.get(i) + ";" +
	                arizalar.get(i) + ";" +
	                durumlar.get(i) + "\n"
	            );

	        }

	        yaz.close();

	    }
	    catch(Exception e) {

	        System.out.println("CİHAZ DOSYASI KAYDEDİLEMEDİ.");

	    }

	}
	public static void main(String [] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> adlar = new ArrayList<>();
		ArrayList<String> telefonlar = new ArrayList<>();
		ArrayList<String> adresler = new ArrayList<>();
		ArrayList<String> cihazTurleri = new ArrayList<>();
		ArrayList<String> markalar = new ArrayList<>();
		ArrayList<String> modeller = new ArrayList<>();
		ArrayList<String> arizalar = new ArrayList<>();
		ArrayList<String> durumlar = new ArrayList<>();
		ArrayList<String> cihazTelefonlari = new ArrayList<>();
		boolean devam = true;
		try {

		    File dosya = new File("musteriler.txt");

		    Scanner dosyaOku = new Scanner(dosya);

		    while(dosyaOku.hasNextLine()) {

		        String satir = dosyaOku.nextLine();

		        String[] bilgiler = satir.split(";");

		        if(bilgiler.length == 3) {

		            adlar.add(bilgiler[0]);
		            telefonlar.add(bilgiler[1]);
		            adresler.add(bilgiler[2]);

		        }

		    }

		    dosyaOku.close();

		}
		catch(FileNotFoundException e) {

		    System.out.println("MÜŞTERİ DOSYASI BULUNAMADI.");

		}
		try {

		    File dosya = new File("cihazlar.txt");

		    Scanner dosyaOku = new Scanner(dosya);

		    while(dosyaOku.hasNextLine()) {

		        String satir = dosyaOku.nextLine();

		        String[] bilgiler = satir.split(";");

		        if(bilgiler.length == 6) {

		            cihazTelefonlari.add(bilgiler[0]);
		            cihazTurleri.add(bilgiler[1]);
		            markalar.add(bilgiler[2]);
		            modeller.add(bilgiler[3]);
		            arizalar.add(bilgiler[4]);
		            durumlar.add(bilgiler[5]);

		        }

		    }

		    dosyaOku.close();

		}
		catch(FileNotFoundException e) {

		    System.out.println("CİHAZ DOSYASI BULUNAMADI.");

		}

		while(devam) {
			System.out.println("==============================================");
			System.out.println("          TEKNİK SERVİS TAKİP SİSTEMİ         ");
			System.out.println("==============================================");
			System.out.println("1- MÜŞTERİ EKLE");
			System.out.println("2- MÜŞTERİLERİ LİSTELE");
			System.out.println("3- MÜŞTERİ GÜNCELLE");
			System.out.println("4- MÜŞTERİ SİL");
			System.out.println("----------------------------------------------");
			System.out.println("5- CİHAZ EKLE");
			System.out.println("6- CİHAZLARI LİSTELE");
			System.out.println("7- SERVİS DURUMU GÜNCELLE");
			System.out.println("8- MÜŞTERİ ARA (İSİM / TELEFON)");
			System.out.println("9- İSTATİSTİKLER");
			System.out.println("----------------------------------------------");
			System.out.println("0- ÇIKIŞ");
			
		System.out.print("\n SEÇİMİNİZ: ");
		int secim=sc.nextInt();
		
		
		if(secim==1) {
			sc.nextLine();
			System.out.print("AD SOYAD: ");
			String ad = sc.nextLine();

			System.out.print("TELEFON NUMARASI: ");
			String no = sc.nextLine();

			if(telefonlar.contains(no)) {

			    System.out.println("\nBU TELEFON NUMARASI ZATEN KAYITLIDIR.");

			}
			else {

			    System.out.print("ADRES: ");
			    String adres = sc.nextLine();

			    adlar.add(ad);
			    telefonlar.add(no);
			    adresler.add(adres);
			    musterileriKaydet(adlar, telefonlar, adresler);
			    
			    
			    System.out.println("\nMÜŞTERİ BAŞARIYLA EKLENDİ.");
			}
			
		}
		else if(secim==2) {

		    if (adlar.isEmpty()) {
		        System.out.println("\n HENÜZ KAYITLI MÜŞTERİ YOK.");
		    } else {

		        System.out.println("\n------ MÜŞTERİ LİSTESİ ------ \n");

		        for (int i = 0; i < adlar.size(); i++) {

		            System.out.println("AD SOYAD : " + adlar.get(i));
		            System.out.println("TELEFON  : " + telefonlar.get(i));
		            System.out.println("ADRES    : " + adresler.get(i));
		            System.out.println("\n ---------------------------- \n");

		        }
		    }
		}
		else if(secim==3) {

		    sc.nextLine();

		    System.out.print("\n GÜNCELLEMEK İSTEDİĞİNİZ MÜŞTERİ TELEFONU: ");
		    String guncellenecekTelefon = sc.nextLine();

		    boolean bulundu = false;

		    for(int i = 0; i < telefonlar.size(); i++) {

		        if(telefonlar.get(i).equals(guncellenecekTelefon)) {

		            System.out.print("YENİ AD SOYAD: ");
		            String yeniAd = sc.nextLine();

		            System.out.print("YENİ TELEFON NUMARASI: ");
		            String yeniTelefon = sc.nextLine();

		            System.out.print("YENİ ADRES: ");
		            String yeniAdres = sc.nextLine();

		            adlar.set(i, yeniAd);
		            telefonlar.set(i, yeniTelefon);
		            adresler.set(i, yeniAdres);
		            musterileriKaydet(adlar, telefonlar, adresler);
		            
		            System.out.println("\n MÜŞTERİ BAŞARIYLA GÜNCELLENDİ.\n");

		            bulundu = true;
		            break;
		        }

		    }

		    if(!bulundu) {
		        System.out.println("\n BU TELEFON NUMARASINA AİT MÜŞTERİ BULUNAMADI.\n");
		    }

		}
		else if(secim==4) {
			sc.nextLine();
			System.out.print("\n SİLMEK İSTEDİĞİNİZ MÜŞTERİ TELEFONU: ");
			String silinecekTelefon=sc.nextLine();
			boolean bulundu = false;

			for(int i = 0; i < telefonlar.size(); i++) {

			    if(telefonlar.get(i).equals(silinecekTelefon)) {

			        adlar.remove(i);
			        telefonlar.remove(i);
			        adresler.remove(i);
			        musterileriKaydet(adlar, telefonlar, adresler);
			        for(int j = cihazTelefonlari.size() - 1; j >= 0; j--) {

			            if(cihazTelefonlari.get(j).equals(silinecekTelefon)) {

			                cihazTelefonlari.remove(j);
			                cihazTurleri.remove(j);
			                markalar.remove(j);
			                modeller.remove(j);
			                arizalar.remove(j);
			                durumlar.remove(j);

			            }

			        }

			        cihazlariKaydet(cihazTelefonlari, cihazTurleri,
			                markalar, modeller, arizalar, durumlar);

			        bulundu = true;

			        System.out.println("\n MÜŞTERİ BAŞARIYLA SİLİNDİ. \n");

			        break;
			    }

			}
			if(!bulundu) {

			    System.out.println("\n BU TELEFON NUMARASINA AİT MÜŞTERİ BULUNAMADI. \n");
 
			}
			
			
		}
		else if(secim==5) {

		    sc.nextLine();

		    System.out.print("\n MÜŞTERİ TELEFON NUMARASI: ");
		    String telefon = sc.nextLine();

		    if(!telefonlar.contains(telefon)) {

		        System.out.println("\n BU TELEFON NUMARASINA AİT MÜŞTERİ BULUNAMADI. \n");

		    }
		    else {

		        System.out.println(" \n MÜŞTERİ BULUNDU.");

		        System.out.print("CİHAZ TÜRÜ: ");
		        String cihazTuru = sc.nextLine();

		        System.out.print("MARKA: ");
		        String marka = sc.nextLine();

		        System.out.print("MODEL: ");
		        String model = sc.nextLine();

		        System.out.print("ARIZA AÇIKLAMASI: ");
		        String ariza = sc.nextLine();
		        
		        cihazTelefonlari.add(telefon);
		        cihazTurleri.add(cihazTuru);
		        markalar.add(marka);
		        modeller.add(model);
		        arizalar.add(ariza);
		        durumlar.add("BEKLİYOR");
		        cihazlariKaydet(cihazTelefonlari, cihazTurleri, markalar,
		        		modeller, arizalar, durumlar);


		        System.out.println("\n CİHAZ BAŞARIYŞA KAYDEDİLDİ.");

		    }
		    

		}
		else if(secim==6) {

		    if(cihazTelefonlari.isEmpty()) {

		        System.out.println("\n KAYITLI CİHAZ BULUNAMADI.\n");

		    }
		    else {

		        System.out.println("\n------ CİHAZ LİSTESİ ------");

		        for(int i = 0; i < cihazTelefonlari.size(); i++) {
		        	int musteriIndex = telefonlar.indexOf(cihazTelefonlari.get(i));

		        	if(musteriIndex != -1) {
		        	    System.out.println("MÜŞTERİ      : " + adlar.get(musteriIndex));
		        	}

		            System.out.println("TELEFON      : " + cihazTelefonlari.get(i));
		            System.out.println("CİHAZ TÜRÜ   : " + cihazTurleri.get(i));
		            System.out.println("MARKA        : " + markalar.get(i));
		            System.out.println("MODEL        : " + modeller.get(i));
		            System.out.println("ARIZA        : " + arizalar.get(i));
		            System.out.println("DURUM        : " + durumlar.get(i));
		            System.out.println("------------------------------------");

		        }

		    }

		}
		else if(secim==7) {

		    sc.nextLine();

		    System.out.print("\n DURUMUNU GÜNCELLEMEK İSTEDİĞİNİZ CİHAZIN TELEFON NUMARASI: ");
		    String telefon = sc.nextLine();

		    boolean bulundu = false;

		    for(int i = 0; i < cihazTelefonlari.size(); i++) {

		    	if(cihazTelefonlari.get(i).equals(telefon)) {

		    	    bulundu = true;

		    	    System.out.println("\nCİHAZ BULUNDU .");
		    	    System.out.println("MEVCUT DURUM: " + durumlar.get(i));

		    	    System.out.print("YENİ DURUM: ");
		    	    String yeniDurum = sc.nextLine();

		    	    durumlar.set(i, yeniDurum);
		    	    cihazlariKaydet(cihazTelefonlari, cihazTurleri,
		    	            markalar, modeller, arizalar, durumlar);

		    	    System.out.println("\nSERVİS DURUMU BAŞARIYLA GÜNCELLENDİ.");

		    	    break;

		    	}

		    }

		    if(!bulundu) {

		        System.out.println("BU TELEFON NUMARASINA AİT CİHAZ BULUNAMADI.");

		    }

		}
		else if(secim==8) {

		    sc.nextLine();

		    System.out.print("\nARAMAK İSTEDİĞİNİZ İSİM VEYA TELEFON: ");
		    String arama = sc.nextLine();

		    boolean bulundu = false;

		    for(int i = 0; i < adlar.size(); i++) {

		    	
		        if(adlar.get(i).equalsIgnoreCase(arama)
		                || telefonlar.get(i).equals(arama)) {

		            bulundu = true;

		            System.out.println("\n------ MÜŞTERİ BİLGİLERİ ------");
		            System.out.println("AD SOYAD : " + adlar.get(i));
		            System.out.println("TELEFON  : " + telefonlar.get(i));
		            System.out.println("ADRES    : " + adresler.get(i));

		            System.out.println("\n--- CİHAZLARI ---");

		            boolean cihazVar = false;

		            for(int j = 0; j < cihazTelefonlari.size(); j++) {
		            	

		                if(cihazTelefonlari.get(j).equals(telefonlar.get(i))) {

		                    cihazVar = true;

		                    System.out.println("CİHAZ TÜRÜ : " + cihazTurleri.get(j));
		                    System.out.println("MARKA      : " + markalar.get(j));
		                    System.out.println("MODEL      : " + modeller.get(j));
		                    System.out.println("ARIZA      : " + arizalar.get(j));
		                    System.out.println("DURUM      : " + durumlar.get(j));
		                    System.out.println("-----------------------------");

		                }

		            }

		            if(!cihazVar) {
		                System.out.println("BU MÜŞTERİYE AİT KAYITLI CİHAZ YOK.");
		            }

		            break;
		        }

		    }

		    if(!bulundu) {

		        System.out.println("\nMÜŞTERİ BULUNAMADI .");

		    }

		}
		else if(secim==9) {

		    int bekleyen = 0;

		    for(int i = 0; i < durumlar.size(); i++) {

		        if(durumlar.get(i).equalsIgnoreCase("BEKLİYOR")) {
		            bekleyen++;
		        }

		    }

		    System.out.println("\n------ İSTATİSTİKLER ------");
		    System.out.println("TOPLAM MÜŞTERİ : " + adlar.size());
		    System.out.println("TOPLAM CİHAZ   : " + cihazTelefonlari.size());
		    System.out.println("BEKLEYEN CİHAZ : " + bekleyen);

		}
		else if(secim==0){
			musterileriKaydet(adlar, telefonlar, adresler);

			cihazlariKaydet(cihazTelefonlari, cihazTurleri,
			        markalar, modeller, arizalar, durumlar);

			System.out.println("\nPROGRAMDAN ÇIKILIYOR...");
			devam = false;
		}
		else {

		    System.out.println("\nGEÇERSİZ SEÇİM YAPTINIZ.");

		}
		}	
		sc.close();
	}
}
