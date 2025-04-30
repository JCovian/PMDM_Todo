package es.covian.pmdm.multimedia

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VideoActivity : AppCompatActivity() {

    private var iniciar = true // Variable para que al pausar el video no reinicie
    private lateinit var ruta: String
    private lateinit var selectedRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Variables para los diferentes componentes
        val radioGroup: RadioGroup = findViewById(R.id.rbVideos)
        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnPause: Button = findViewById(R.id.btnPause)
        val btnStop: Button = findViewById(R.id.btnStop)
        val video = findViewById<VideoView>(R.id.videoView)

        // Rutas de los videos
        val pathEdimburgo = "android.resource://" + packageName + "/" + R.raw.edimburgo
        val pathLondres = "android.resource://" + packageName + "/" + R.raw.londres
        val pathNuevaYork =  "android.resource://" + packageName + "/" + R.raw.nuevayork

        // Botón para reproducir video
        btnPlay.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId

            // Si hay un video seleccionado lo carga, sino te recuerda que selecciones uno
            if (selectedRadioButtonId != -1) {
                selectedRadioButton = findViewById(selectedRadioButtonId)

                if (iniciar){
                    // Carga el fichero de video
                    ruta = when (selectedRadioButton.id) {
                        R.id.rbOpcion1-> pathLondres
                        R.id.rbOpcion2 -> pathNuevaYork
                        R.id.rbOpcion3 -> pathEdimburgo
                        else -> ""
                    }
                    if (ruta.equals("")) {
                        Toast.makeText(this, "Error al cargar la selección", Toast.LENGTH_SHORT).show()
                    } else {
                        video.setVideoPath(ruta)
                        val mediaController = MediaController(this)
                        mediaController.setAnchorView(video)
                        video.setMediaController(mediaController)
                    }
                }
                video.start()
            } else {
                Toast.makeText(this, "Seleccione un video para reproducir", Toast.LENGTH_SHORT).show()
            }
        }
        // Botón para pausar video
        btnPause.setOnClickListener {
            iniciar = false
            video.pause()
        }
        // Botón para parar video
        btnStop.setOnClickListener {
            iniciar = true
            video.stopPlayback()
            video.seekTo(0)
        }
    }
}