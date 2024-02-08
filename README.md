# Proyek Todo List Menggunakan Java Native dengan Struktur Clean Code Architecture

Proyek ini adalah aplikasi Todo List yang menggunakan Java Native yang mengimplementasikan pola arsitektur Model-View-Controller (MVC) dengan tambahan service layer dan repository layer untuk memisahkan tanggung jawab dan meningkatkan pemeliharaan serta skalabilitas kode.

## Deskripsi Proyek

Proyek ini dirancang untuk memenuhi kebutuhan aplikasi Java Native yang membutuhkan organisasi kode yang terstruktur dan mudah dipelihara. Dengan menggunakan pola arsitektur MVC, service layer, dan repository layer, proyek ini memisahkan peran antara model, tampilan, logika bisnis, dan akses data.

### Struktur Proyek

Berikut adalah struktur direktori utama proyek ini:

- `models`: Berisi definisi dari model-model yang mewakili entitas-entitas dalam aplikasi.
- `services`: Berisi implementasi layanan-layanan yang menyediakan logika bisnis tambahan dan memfasilitasi interaksi antara kontroler dan repository.
- `repositories`: Berisi implementasi repository-repository yang bertanggung jawab untuk berinteraksi dengan penyimpanan data.
- `controllers`: Berisi kontroler-kontroler yang menangani alur kerja aplikasi dan berinteraksi dengan tampilan serta layanan.
- `views`: Berisi file-file tampilan atau antarmuka pengguna yang akan ditampilkan kepada pengguna akhir.

## Penggunaan

Berikut adalah langkah-langkah untuk menjalankan dan menggunakan proyek ini:

1. **Persiapan Lingkungan**
   - Pastikan Anda memiliki JDK terinstal di sistem Anda.

2. **Clone Repositori**
   - Clone repositori ini ke mesin lokal Anda.

3. **Menjalankan Aplikasi**
   - Compile dan jalankan aplikasi Anda menggunakan IDE favorit Anda atau perintah `javac` dan `java`.

## Kontribusi
Kontribusi selalu dipersilakan! Jika Anda ingin meningkatkan proyek ini, silakan buka *issue* untuk mendiskusikan perubahan yang ingin Anda usulkan atau kirimkan *pull request*.

## Lisensi
Proyek ini dilisensikan di bawah Lisensi MIT. Silakan lihat [LICENSE](LICENSE) untuk detail lebih lanjut.
