# 🤖 Blueba Pro - Bluetooth Robot Kontrol

**Blueba Pro**, Android cihazlar üzerinden HC-05/HC-06 gibi Bluetooth modüllerine sahip robotik sistemleri yönetmek için tasarlanmış bir kontrol arayüzüdür. 

---

## ⚠️ Önemli: Başlamadan Önce
Uygulamayı sorunsuz kullanabilmek için aşağıdaki adımları mutlaka tamamlamalısınız:

1.  **İzinler:** Uygulama ilk açıldığında veya telefon ayarlarından **Bluetooth** ve **Konum** (cihaz tarama için gereklidir) izinlerini el ile onaylamalısınız.
2.  **Eşleştirme:** Robotunuzun Bluetooth modülünü veya PC'nizi uygulamanın içinden önce telefonun kendi Bluetooth ayarlarından "Eşleşmiş Cihazlar" listesine eklemelisiniz.
3.  **Bağlantı:** Uygulama içindeki **BAĞLAN** butonu sadece daha önceden telefonla eşleşmiş cihazları listeler.

---

## 🚀 Özellikler
* **Geniş Kontrol Paneli:** Kolay kullanım için devasa buton tasarımları.
* **Hassas Hız Ayarı:** Robotun gücünü tek tıkla değiştirme imkanı.
* **Kıskaç Kontrolü:** Kıskaç mekanizmaları için özel "Aç/Kapa" desteği.
* **Güvenlik Modu:** Elinizi butondan çektiğiniz anda robotu durdurmak için otomatik "S" sinyali gönderimi.

---

## 📡 Bluetooth Veri Protokolü (Buton Haritası)

Robotunuzdaki yazılımı (Arduino/ESP32 vb.) aşağıdaki karakterlere göre hazırlamalısınız:

### 🏎 Hareket Kontrolü
| Hareket | Basınca Gönderilen | Bırakınca Gönderilen |
| :--- | :---: | :---: |
| **İleri** | `F` | `S` |
| **Geri** | `B` | `S` |
| **Sol** | `L` | `S` |
| **Sağ** | `R` | `S` |

### 🏗 Kıskaç Kontrolü
| İşlem | Basınca Gönderilen | Bırakınca Gönderilen |
| :--- | :---: | :---: |
| **Kıskaç AÇ** | `W` | `S` |
| **Kıskaç KAPAT** | `w` | `S` |

### ⚡ Hız Ayarları
| Mod | Karakter | Açıklama |
| :--- | :---: | :--- |
| **Yüksek Hız** | `q` | Maksimum güç modu |
| **Orta Hız** | `3` | Standart çalışma hızı |
| **Yavaş Hız** | `1` | Hassas park ve manevra hızı |

---

## 📲 Uygulama Kullanımı

1.  Robotunuzun Bluetooth modülüne güç verin.
2.  Uygulamada sağ üstteki **"BAĞLAN"** butonuna basın.
3.  Listeden robotunuzu seçin.
4.  Ekranda "Bağlandı!" uyarısını gördükten sonra butonları kullanmaya başlayın.

---

## 🛠 Teknik Bilgiler
* **Uyumlu Yazılımlar:** softarchx & apoman466 Bluetooth kütüphaneleriyle tam uyumludur.
* **Sistem Gereksinimi:** Android 4.2.2 ve üzeri sürümlerle çalışır.
* 
