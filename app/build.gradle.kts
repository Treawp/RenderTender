plugins {
    id("com.android.application")
}

// ============================================================
//  ENV-VAR FPS — bagian yang beneran ngatur performa renderer.
//  Format: KEY=VAL dipisah koma. Diisi ke manifest saat build.
//  Ubah-ubah di sini kalau mau tuning, gak perlu sentuh kode lain.
// ============================================================
val fpsEnv = listOf(
    "LIBGL_ES=2",            // target GLES2 (paling kompatibel & ngebut di Adreno/Mali)
    "LIBGL_MIPMAP=3",        // mipmap di-handle GL4ES, ngurangin beban + anti shimmer
    "LIBGL_NOERROR=1",       // skip glGetError tiap call -> hemat overhead besar
    "LIBGL_NOINTOVLHACK=1",  // matiin overlay hack yg bikin lag di sebagian device
    "LIBGL_NORMALIZE=1",     // normalisasi di GPU, bukan CPU
    "LIBGL_VSYNC=0",         // lepas VSync -> FPS gak dikunci refresh layar
    "LIBGL_FASTMATH=1",      // math cepat (sedikit kurang presisi, gak kerasa di game)
    "LIBGL_SILENTSTUB=1",    // jangan spam log buat fungsi stub -> hemat I/O
    "LIBGL_GL=21",           // expose profil GL 2.1 ke Minecraft
    "LIBGL_FB=2",            // framebuffer cepat (FBO langsung, skip blit ekstra)
    "LIBGL_SHRINK=2",        // shrink tekstur gede -> hemat VRAM di HP RAM kecil
    "LIBGL_BATCH=1"          // gabung draw call kecil -> ngurangin overhead
).joinToString(",")

android {
    namespace = "com.bzlzhh.ngg.fps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bzlzhh.ngg.fps"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-fps-ega"

        // Placeholder ini diisi ke AndroidManifest.xml.
        // Format renderer = "NAMA:lib_renderer.so:lib_egl.so"
        manifestPlaceholders["renderer"] = "NGGL4ES:libng_gl4es.so:libEGL.so"
        manifestPlaceholders["des"] = "NG-GL4ES (Krypton) FPS-tuned by Ega"
        manifestPlaceholders["boatEnv"] = fpsEnv   // env utk Boat/Zalith
        manifestPlaceholders["pojavEnv"] = fpsEnv   // env utk PojavGlowWorm
    }

    buildTypes {
        // debug = gampang, gak butuh signing key. Cukup buat dipasang manual.
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    // Plugin ini gak punya kode Java/Kotlin sama sekali (cuma .so + manifest),
    // jadi gak perlu compileOptions/kotlin block.
    androidComponents {
        // biarin default
    }
}
