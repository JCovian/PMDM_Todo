package es.covian.pmdm.multimedia

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var  sp:SoundPool
    private var spLoaded: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_opciones, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.reproducirSonido -> {
               // Configuración del SoundPool
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
                this.sp = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build()
                // --- Fin Configuración ---
                // Carga el sonido
                val soundId = this.sp.load(this, R.raw.golpe, 1)
                sp.setOnLoadCompleteListener { _, _, status ->
                    if (status == 0) {
                        this.spLoaded = true
                    } else {
                        Toast.makeText(this, "Carga fallida!", Toast.LENGTH_SHORT).show()
                    }
                }
                // --- Fin Carga ---
                // Reproducir el sonido
                if(this.spLoaded) {
                    this.sp.play(soundId, 1f,1f, 0,0 , 1f)
                    Toast.makeText(this, "Sonando audio", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.reproducirAudio -> {
                val intent = Intent(this, AudioActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.reproducirVideo -> {
                val intent = Intent(this, VideoActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.salir -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setTitle("Salir")
                    .setMessage("Se va a cerrar la aplicación, ¿está seguro de querer salir?")
                    .setPositiveButton("Sí") { dialog,which ->
                        dialog.dismiss()
                        finish()
                    }
                    .setNegativeButton("No") { dialog, which ->
                        dialog.dismiss()
                    }
                builder.create().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuración del SoundPool
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        this.sp = SoundPool.Builder().setMaxStreams(1).setAudioAttributes(audioAttributes).build()
        // --- Fin Configuración ---
        // Carga el sonido
         val soundId = this.sp.load(this, R.raw.golpe, 1)
         sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) {
                this.spLoaded = true
            } else {
                Toast.makeText(this, "Carga fallida!", Toast.LENGTH_SHORT).show()
            }
         }
        // --- Fin Carga ---

        val btnRepSonido = findViewById<Button>(R.id.btnRepSonido)
        val btnRepAudio = findViewById<Button>(R.id.btnRepAudio)
        val btnRepVideo = findViewById<Button>(R.id.btnRepVideo)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        // Función para salir de la App
        fun salirApp() {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setTitle("Salir")
                .setMessage("Se va a cerrar la aplicación, ¿está seguro de querer salir?")
                .setPositiveButton("Sí") { dialog,which ->
                    dialog.dismiss()
                    finish()
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
            builder.create().show()
        }

        // Botón para reproducir el sonido
        btnRepSonido.setOnClickListener{
            // Reproducir el sonido
            if(this.spLoaded) {
                this.sp.play(soundId, 1f,1f, 0,0 , 1f)
            }
        }

        // Botón actividad Audio
        btnRepAudio.setOnClickListener {
            val intent = Intent(this, AudioActivity::class.java)
            startActivity(intent)
        }

        // Botón actividad video
        btnRepVideo.setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }

        // Botón salir de la app
        btnSalir.setOnClickListener {
            salirApp()
        }
    }

    // Liberacion recursos de audio
    override fun onDestroy() {
        super.onDestroy()
        this.sp.release()
    }
}