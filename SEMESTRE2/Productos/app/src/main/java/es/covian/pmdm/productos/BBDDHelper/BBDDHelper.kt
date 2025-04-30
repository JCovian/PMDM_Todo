package es.covian.pmdm.productos.BBDDHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import es.covian.pmdm.productos.adapter.Producto

class BBDDHelper(context: Context, name: String, factory: CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {
    private val sqlCreateEntries = "CREATE TABLE producto(codigo INT PRIMARY KEY, categoria VARCHAR(50), descripcion VARCHAR(50), precio REAL)"
    private val sqlDeleteEntries = "DROP TABLE IF EXISTS producto"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(this.sqlCreateEntries)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(this.sqlDeleteEntries)
        this.onCreate(db)
    }

    // Insertar producto en la base de datos
    fun altaProducto(codigo: Int, categoria: String, descripcion: String, precio: Double): Boolean {
        var exito = false
        // Obtiene base de datos en modo escritura
        val db = this.writableDatabase
        // Crea un mapa de valores donde los nombres de las columnas son las claves
        val valores = ContentValues()
        valores.put("codigo", codigo)
        valores.put("categoria", categoria)
        valores.put("descripcion", descripcion)
        valores.put("precio",precio)
        // Inserta una nueva fila, retornando la clave primaria de la nueva fila
        val nuevoId = db.insert("producto", null, valores)
        if (nuevoId != -1L) {
            exito = true
        }
        db.close()
        return exito
    }

    // Comprueba si existe producto
    fun existeProducto(codigo: Int): Boolean {
        var existe = false
        // Obtiene base de datos en modo lectura
        val db = this.readableDatabase
        val consulta = db.rawQuery("SELECT * FROM producto WHERE codigo = $codigo", null)
        if(consulta.count > 0) {
            existe = true
        }
        db.close()
        return existe
    }

    // Consulta producto por código
    fun consultaCodigo(codigo: String): ArrayList<Producto> {
        val db = this.readableDatabase
        val productos = ArrayList<Producto>()
        // Definir una proyeccion con las columnas de la tabla que se desean recuperar
        val consulta = db.rawQuery("SELECT * FROM producto WHERE codigo = $codigo", null)
        while (consulta.moveToNext()) {
            productos.add(
                Producto(
                    consulta.getInt(0),
                    consulta.getString(1),
                    consulta.getString(2),
                    consulta.getDouble(3)
                )
            )
        }
        db.close()
        return productos
    }

    // Consulta de productos por descripción
    fun consultaDescripcion(descripcion: String): ArrayList<Producto> {
        val db = this.readableDatabase
        val productos = ArrayList<Producto>()
        val consulta: Cursor
        // Definir una proyeccion con las columnas de la tabla que se desean recuperar
        if (descripcion.isBlank()) {
            consulta = db.rawQuery("SELECT * FROM producto", null)
        } else {
            val sql = "SELECT * FROM productos WHERE descripcion LIKE '%${descripcion}%'"
            consulta = db.rawQuery(sql,null)
        }

        while (consulta.moveToNext()) {
            productos.add(
                Producto(
                    consulta.getInt(0),
                    consulta.getString(1),
                    consulta.getString(2),
                    consulta.getDouble(3)
                )
            )
        }
        db.close()
        return productos
    }

    // Borrar producto
    fun borrar(codigo: String): Int{
        val db = this.writableDatabase
        val consulta = db.delete("producto", "codigo like ?", arrayOf(codigo))
        db.close()
        return consulta
    }

    // Modificar producto
    fun modificar(codigo: Int, categoria: String, descripcion: String, precio: Double): Int{
        val db = this.writableDatabase
        val valores = ContentValues().apply {
            put("categoria", categoria)
            put("descripcion", descripcion)
            put("precio",precio)
        }
        val consulta = db.update("producto", valores, "codigo = ?", arrayOf(codigo.toString()))
        db.close()
        return consulta
    }
}