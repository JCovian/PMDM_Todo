package es.covian.pmdm.productos

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.covian.pmdm.productos.BBDDHelper.BBDDHelper

class MainActivity : AppCompatActivity() {
    // Instancia la base de datos
    private val conexion = BBDDHelper(this,"productos.db",null,1)
    // Declaración de variables para su futura inicialización
    private lateinit var etCodigo: EditText
    private lateinit var spinner: Spinner
    private lateinit var etDescripcion: EditText
    private lateinit var etPrecio: EditText
    private lateinit var btnAlta: Button
    private lateinit var btnConsultaCodigo: Button
    private lateinit var btnConsultaDescripcion: Button
    private lateinit var btnBaja: Button
    private lateinit var btnModificacion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Declaracion los diferentes elemenos del layout
        spinner = findViewById(R.id.spCategoria)
        val categorias = resources.getStringArray(R.array.categorias_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        etCodigo = findViewById(R.id.etCodigoProducto)
        etDescripcion = findViewById(R.id.etDescripcion)
        etPrecio = findViewById(R.id.etPrecio)
        btnAlta = findViewById(R.id.btnAlta)
        btnConsultaCodigo = findViewById(R.id.btnConsultaCodigo)
        btnConsultaDescripcion = findViewById(R.id.btnConsultaDescripcion)
        btnBaja = findViewById(R.id.btnBaja)
        btnModificacion = findViewById(R.id.btnModificacion)

        // Botón alta de producto
        btnAlta.setOnClickListener {
            val codigo = etCodigo.text.toString()

            if(codigo.isBlank()) {
                mostrarDialogo("El código es obligatorio")
            } else{
                val existe = conexion.existeProducto(codigo.toInt())
                if (existe) {
                    Toast.makeText(this,"No se puede añadir el producto " +
                            "con codigo $codigo porque ya existe", Toast.LENGTH_SHORT).show()
                } else {
                    val descripcion = etDescripcion.text.toString()
                    val categoria = spinner.selectedItem.toString()
                    val precio = etPrecio.text.toString().toDoubleOrNull() ?: 0.0
                    // Envío de datos a la base de datos
                    val exito = conexion.altaProducto(codigo.toInt(), categoria, descripcion, precio)
                    if (exito) {
                        Toast.makeText(this,"Se añadió el producto " +
                                "correctamente", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"No se pudo añadir el producto " +
                                "en la base de datos", Toast.LENGTH_LONG).show()
                    }
                }
                limpiarCampos()
            }
        }

        //Botón consulta por código
        btnConsultaCodigo.setOnClickListener {
            val codigo = etCodigo.text.toString()

            if(codigo.isBlank()) {
                mostrarDialogo("El codigo es obligatorio")
            }else {
                val intent = Intent(this, ListadoActivity::class.java)
                intent.putExtra("tipoConsulta", "codigo")
                intent.putExtra("valorConsulta", codigo)
                limpiarCampos()
                startActivity(intent)
            }
        }

        // Botón consulta por descripción
        btnConsultaDescripcion.setOnClickListener {
            val descripcion = etDescripcion.text.toString()
            val intent = Intent(this,ListadoActivity::class.java)
            intent.putExtra("tipoConsulta", "descripcion")
            intent.putExtra("valorConsulta", descripcion)
            limpiarCampos()
            startActivity(intent)
        }

        // Botón baja por código
        btnBaja.setOnClickListener {
            val codigo = etCodigo.text.toString()

            if(codigo.isBlank()) {
                mostrarDialogo("El codigo es obligatorio")
            } else {
                if(conexion.borrar(codigo) != 0) {
                    Toast.makeText(this, "Se eliminó el producto " +
                            "correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    mostrarDialogo("No se puede eliminar el producto con" +
                            "codigo $codigo porque no existe")
                }
                limpiarCampos()
            }
        }

        //Botón modificación
        btnModificacion.setOnClickListener {
            val codigo = etCodigo.text.toString()

            if(codigo.isBlank()) {
                mostrarDialogo("El código es obligatorio")
            } else {
                // Captura los datos de los diferentes campos en pantalla
                val categoria = spinner.selectedItem.toString()
                val descripcion = etDescripcion.text.toString()
                val precio = etPrecio.text.toString().toDoubleOrNull() ?: 0.0
                // Envío de datos a la base de datos
                val resultado = conexion.modificar(codigo.toInt(),categoria,descripcion, precio)
                if (resultado == 0) {
                    mostrarDialogo("No se puede modificar el producto con " +
                            "código $codigo porque no existe")
                } else {
                    Toast.makeText(this, "Se modificó el producto " +
                            "correctamente", Toast.LENGTH_SHORT).show()
                }
                limpiarCampos()
            }
        }

        conexion.close()
    }

    // Mostrar dialogos
    private fun mostrarDialogo(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar",null)
            .show()
    }

    // Limpiar campos
    private fun limpiarCampos() {
        etCodigo.text.clear()
        spinner.setSelection(0)
        etDescripcion.text.clear()
        etPrecio.text.clear()
    }

}