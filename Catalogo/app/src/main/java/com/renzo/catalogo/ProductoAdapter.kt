package com.renzo.catalogo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class ProductoAdapter(
    private val lista: List<Producto>
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtCantidad: TextView = view.findViewById(R.id.txtCantidad)
        val txtPrecio: TextView = view.findViewById(R.id.txtPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]
        holder.txtNombre.text = producto.nombre
        holder.txtCantidad.text = "Cantidad: ${producto.cantidad}"
        holder.txtPrecio.text = "Precio: S/. ${producto.precio}"
    }

    override fun getItemCount(): Int = lista.size
}