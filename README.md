# Teknik Servis Takip Sistemi

Java ve Swing kullanılarak geliştirilmiş masaüstü **Teknik Servis Takip Sistemi**.

Bu proje, teknik servise gelen müşterilerin ve cihazların kayıtlarının tutulması, servis durumlarının takip edilmesi ve verilerin dosya tabanlı olarak yönetilmesi amacıyla geliştirilmiştir.

## Özellikler

* Müşteri ekleme
* Müşteri listeleme
* Müşteri arama
* Müşteri bilgilerini güncelleme
* Müşteri silme
* Müşteriye cihaz kaydı oluşturma
* Cihazları listeleme
* Cihaz servis durumunu güncelleme
* Cihaz silme
* Müşteriye ait cihazları görüntüleme
* Servis istatistiklerini görüntüleme
* Müşteri ve cihaz verilerini dosyada saklama
* Veri yedekleme
* Yedekleri geri yükleme
* Eski yedekleri otomatik temizleme
* Silme ve geri yükleme işlemlerinde onay pencereleri

## Kullanılan Teknolojiler

* Java
* Java Swing
* Java I/O
* Dosya tabanlı veri yönetimi
* Eclipse IDE

## Veri Yönetimi

Sistemde müşteri ve cihaz bilgileri `.txt` dosyalarında saklanmaktadır.

Kullanılan temel veri dosyaları:

* `musteriler.txt`
* `cihazlar.txt`

Yedekleme işlemleri `yedekler` klasörü içerisinde tarih ve saat bilgisiyle oluşturulmaktadır.

## Proje Yapısı

Projede kullanıcı arayüzleri ve veri işlemleri ayrı sınıflar halinde yapılandırılmıştır.

Önemli sınıflar:

* `MusteriEkrani` – Müşteri işlemleri
* `MusteriEkleEkrani` – Müşteri ekleme
* `MusteriListeleEkrani` – Müşteri listeleme ve silme
* `MusteriAraEkrani` – Müşteri arama
* `MusteriGuncelleEkrani` – Müşteri güncelleme
* `CihazEkrani` – Cihaz işlemleri
* `CihazEkleEkrani` – Cihaz ekleme
* `CihazListeleEkrani` – Cihaz listeleme, silme ve durum güncelleme
* `IstatistikEkrani` – Servis istatistikleri
* `Yedekleme` – Veri yedekleme işlemleri
* `YedeklemeEkrani` – Yedekleri yönetme
* `VeriIslemleri` – Dosya tabanlı veri işlemleri
* `OnayPenceresi` – İşlem onay pencereleri

## Geliştirme Amacı

Bu proje, Java programlama dili ve nesne yönelimli programlama yaklaşımı kullanılarak masaüstü uygulama geliştirme, dosya işlemleri, kullanıcı arayüzü tasarımı ve veri yönetimi konularında pratik kazanmak amacıyla geliştirilmiştir.
