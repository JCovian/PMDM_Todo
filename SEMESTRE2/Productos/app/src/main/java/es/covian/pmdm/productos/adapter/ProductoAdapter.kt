package es.covian.pmdm.productos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import es.covian.pmdm.productos.R

class ProductoAdapter(private val context: Context, private val productos: List<Producto>): BaseAdapter() {
    override fun getCount(): Int = productos.size
    override fun getItem(posicion: Int): Any = productos[posicion]
    override fun getItemId(posicion: Int): Long = productos[posicion].codigo.toLong()

    override fun getView(posicion: Int, convertView: View?, parent: ViewGroup?): View {
        val producto = productos[posicion]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.articulo_layout, parent, false)

        // Declaración de variables para trabajar con los diferentes textView y el imageView del Layout
        val tvCodigoCategoria = view.findViewById<TextView>(R.id.tvCodigoCategoria)
        val tvDescripcion = view.findViewById<TextView>(R.id.tvDescripcion)
        val tvPrecio = view.findViewById<TextView>(R.id.tvPrecio)
        val imgCategoria = view.findViewById<ImageView>(R.id.ivCategoria)

        // Da valor al texto de los diferentes textView
        tvCodigoCategoria.text = "${producto.codigo} - ${producto.categoria}"
        tvDescripcion.text = producto.descripcion
        tvPrecio.text = "${producto.precio} €"
        // Asigna la imagen de acuerdo al nombre de la categoria
        val imgId = context.resources.getIdentifier(producto.categoria.lowercase(),"drawable",context.packageName)
        if(imgId != 0) {
            imgCategoria.setImageResource(imgId)
        } else {
            // Si la categoría no tiene imagen muestra la imagen "producto"
            imgCategoria.setImageResource(R.drawable.producto)
        }
        return view
    }
}