package es.covian.pmdm.multimedia

import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AudioActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var selectedRadioButton: RadioButton
    private var changeId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_audio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Declaracion de variables para los radio button y los botones
        val radioGroup: RadioGroup = findViewById(R.id.radioCanciones)
        val btnPlay: Button = findViewById(R.id.btnPlay)
        val btnPause: Button = findViewById(R.id.btnPause)
        val btnStop: Button = findViewById(R.id.btnStop)

        // Botón que ejecuta la música
        btnPlay.setOnClickListener {
            // Obtiene el ID del radio button seleccionado
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId

            // Si hay una canción seleccionada la carga, sino te recuerda que lo hagas
            if(selectedRadioButtonId !=-1) {
                selectedRadioButton = findViewById(selectedRadioButtonId)

                // Comprueba si se cambió a una nueva canción, de esta manera consigo
                // que funcione el pause
                if (changeId != selectedRadioButtonId) {
                    // Cancela cualquier reproducción anterior si no sonarían dos a la vez
                    // si el usuario inicia nueva reproducción sin pulsar stop
                    mediaPlayer?.stop()
                    mediaPlayer?.release()

                    // Cargar fichero de música según la selección
                    mediaPlayer = when (selectedRadioButton.id) {
                        R.id.rbCancion1 -> MediaPlayer.create(this, R.raw.ichwill)
                        R.id.rbCancion2 -> MediaPlayer.create(this, R.raw.los)
                        R.id.rbCancion3 -> MediaPlayer.create(this, R.raw.liese)
                        else -> null
                    }
                    changeId = selectedRadioButtonId
                }

                // Listener para liberar recursos cuando termine la canción
                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer?.release()
                }

                // Reproducir canción seleccionada
                mediaPlayer?.start()
            } else {
                Toast.makeText(this, "Seleccione una canción para reproducir", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón que pausa la música
        btnPause.setOnClickListener {
            if(mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        // Botón que para la música
        btnStop.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.stop()
                mediaPlayer?.prepare()
                mediaPlayer?.seekTo(0)
            }
        }
    }

    // Libera los recursos
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}