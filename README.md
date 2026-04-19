# 🏎️ Blueba Pro Control (BBGAMING)

**Blueba Pro Control**, Arduino ve Deneyap Kart tabanlı robotik projeler için geliştirilmiş, yüksek performanslı bir Android Bluetooth kontrol uygulamasıdır. Özellikle **Teknofest Robolig** gibi yarışmalarda akıcı ve güvenli kontrol sağlamak amacıyla tasarlanmıştır.

## 📱 Uygulama Özellikleri
- 🎮 **Yatay Gamepad Arayüzü:** Tam ekran landscape modu ile profesyonel sürüş deneyimi.
- 🛡️ **Güvenli Veri Protokolü:** Herhangi bir butona basılmadığında otomatik olarak **"0"** verisi göndererek robotun kontrolsüz hareket etmesini engeller.
- 🏗️ **Gelişmiş Kontrol:** OnTouchListener yapısı sayesinde bas-çek mantığıyla milisaniyelik tepki süresi.
- 🛠️ **Çok Yönlü Kullanım:** 4 yönlü hareket oklarına ek olarak Kıskaç, Lamba veya Korna gibi ek donanımlar için 4 adet aksiyon butonu (Δ, X, □, O).
- 🔗 **Dinamik Bağlantı:** Uygulama içinden Bluetooth cihaz seçme ve yönetme ekranı.

## 🛰️ Veri Protokolü (Buton Haritası)

Uygulama üzerinden gönderilen karakterler aşağıdadır:

| Buton | Gönderilen Veri | Fonksiyon |
| :--- | :---: | :--- |
| **İleri (↑)** | `F` | İleri Hareket |
| **Geri (↓)** | `B` | Geri Hareket |
| **Sol (←)** | `L` | Sola Dönüş |
| **Sağ (→)** | `R` | Sağa Dönüş |
| **Üst (Δ)** | `1` | Özel Aksiyon 1 (Örn: Kıskaç Aç) |
| **Alt (X)** | `2` | Özel Aksiyon 2 (Örn: Kıskaç Kapat) |
| **Sol (□)** | `3` | Özel Aksiyon 3 |
| **Sağ (O)** | `4` | Özel Aksiyon 4 |
| **BOŞTA** | `0` | Dur / Güvenli Mod |

## 🛠️ Kurulum ve Geliştirme
Uygulama **AIDE** ortamında Java diliyle geliştirilmiştir.

- **Paket Adı:** `com.bbgaming.blueba.tr`
- **Minimum Android Sürümü:** 5.0 (API 21)
- **Hedef Sürüm:** Android 12+ (Bluetooth Connect/Scan İzinleri Dahil)

## 👤 Geliştirici
Bu proje **Ahmet Mirza Yalçın ve Nuri İbrahim Akın** tarafından geliştirilmiştir.

---
⭐ Bu projeyi beğendiyseniz yıldız (star) vermeyi unutmayın!
