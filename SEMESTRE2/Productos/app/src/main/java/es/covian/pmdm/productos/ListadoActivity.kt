package es.covian.pmdm.productos

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.covian.pmdm.productos.BBDDHelper.BBDDHelper
import es.covian.pmdm.productos.adapter.ProductoAdapter

class ListadoActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private val conexion = BBDDHelper(this,"productos.db",null,1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView =findViewById(R.id.lvProductos)

        // Recogemos los datos del botón consulta por codigo o por descripcion
        // Esta variable recoge el tipo de consulta que realizamos
        val tipoConsulta = intent.getStringExtra("tipoConsulta")
        // En esta el valor del texto del editText que contiene el codigo o la descripción
        val valorConsulta = intent.getStringExtra("valorConsulta") ?: ""

        // Llamada a la consulta correspondiente a cada tipo de botón
        val productos = when (tipoConsulta) {
            "codigo" -> conexion.consultaCodigo(valorConsulta)
            "descripcion" -> conexion.consultaDescripcion(valorConsulta)
            else -> emptyList()
        }

        if(productos.isEmpty()) {
            Toast.makeText(this,"No existen productos", Toast.LENGTH_SHORT).show()
        }

        listView.adapter = ProductoAdapter(this,productos)
        conexion.close()

    }
}