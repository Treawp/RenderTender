# NGG FPS Renderer

Renderer plugin **NG-GL4ES (Krypton Wrapper)** untuk **Zalith Launcher / Fold Craft Launcher / PojavGlowWorm**, dengan **env-var FPS yang sudah di-tuning** dari pabrik.

> Plugin ini **gak compile NG-GL4ES dari nol**. Saat build, CI nyomot `.so` jadi dari
> release resmi [NGG-FCLRendererPlugin](https://github.com/BZLZHH/NGG-FCLRendererPlugin),
> lalu membungkusnya ulang dengan env-var FPS di `app/build.gradle.kts`.
> Jadi renderer-nya identik dengan aslinya, bedanya cuma default setelan performa.

---

## Cara dapetin APK-nya (tanpa install toolchain apa-apa)

1. Bikin repo baru di GitHub (boleh private).
2. Push semua isi folder `ngg-fps-renderer/` ini ke repo itu.
3. Buka tab **Actions** → workflow **Build NGG FPS Renderer APK** jalan otomatis.
   (Kalau belum jalan, klik **Run workflow**.)
4. Tunggu ±3–5 menit (centang ijo).
5. Scroll ke bawah di halaman run → bagian **Artifacts** → download **`ngg-fps-renderer-apk`**.
6. Unzip → ada file `.apk`. **Itu yang diinstall di HP.**

### Push lewat command (contoh)
```bash
cd ngg-fps-renderer
git init
git add .
git commit -m "NGG FPS renderer plugin"
git branch -M main
git remote add origin https://github.com/USERNAME/REPO.git
git push -u origin main
```

---

## Cara pakai di HP

1. Install APK `ngg-fps-renderer-*.apk`.
2. Buka **Zalith Launcher** → **Settings → Renderer**.
3. Pilih renderer **NGG FPS Renderer** (atau "NGGL4ES" / sesuai deskripsi).
4. Masuk game. Env FPS otomatis kepasang.

---

## Tuning FPS (opsional)

Semua setelan performa ada di satu tempat: **`app/build.gradle.kts`**, variabel `fpsEnv`.
Ubah, commit, push → Actions build APK baru.

| Env | Fungsi | Catatan |
|-----|--------|---------|
| `LIBGL_ES=2` | Target GLES2 | Ganti `3` kalau GPU baru, kadang lebih kenceng |
| `LIBGL_VSYNC=0` | Lepas kunci VSync | FPS bisa di atas refresh rate |
| `LIBGL_MIPMAP=3` | Mipmap di GL4ES | Anti-shimmer, ringan |
| `LIBGL_NOERROR=1` | Skip cek error tiap call | Hemat overhead besar |
| `LIBGL_FB=2` | Framebuffer cepat | |
| `LIBGL_SHRINK=2` | Perkecil tekstur gede | Buat HP RAM kecil |
| `LIBGL_BATCH=1` | Gabung draw call | Kurangi overhead |

> **Jujur soal FPS:** renderer cuma penerjemah; FPS akhir tetap nempel ke GPU + driver HP.
> Penambah FPS paling kerasa, urut: turunin **resolution scale** di launcher → pasang **Sodium**
> di Minecraft → baru tuning env ini. Kombinasiin ya.

---

## Catatan teknis / kalau ada error

- Kalau build gagal di step "Ambil & ekstrak .so" → kemungkinan nama asset release NGG berubah.
  Cek [releases NGG](https://github.com/BZLZHH/NGG-FCLRendererPlugin/releases), sesuaikan filter di
  `.github/workflows/build.yml`.
- Format `boatEnv`/`pojavEnv` di sini pakai pemisah **koma**. Kalau launcher versi tertentu
  gak baca env-nya, coba ganti pemisah jadi spasi di `fpsEnv` (`app/build.gradle.kts`).
- Nama meta-data (`fclPlugin`, `renderer`, `boatEnv`, `pojavEnv`, `des`) ngikut format
  plugin resmi NGG. Kalau Zalith versi baru pakai nama lain, tinggal sesuaikan di
  `app/src/main/AndroidManifest.xml`.

---

## Lisensi

Turunan dari NG-GL4ES & template renderer Zalith yang **GPL-3.0**.
Jadi project ini juga **GPL-3.0** — wajib open-source kalau didistribusi.
