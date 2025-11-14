package com.example.practica7noticias


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NoticiasAdapter(
    private var noticias: List<Articles>,
    private val onItemClick: (Articles) -> Unit
) : RecyclerView.Adapter<NoticiasAdapter.NoticiaViewHolder>() {

    inner class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imgNoticia)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(noticias[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]

        // Cargar imagen con Picasso
        if (!noticia.urlToImage.isNullOrEmpty()) {
            Picasso.get()
                .load(noticia.urlToImage)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.imagen)
        } else {
            holder.imagen.setImageResource(R.mipmap.ic_launcher_round)
        }

        // Mostrar descripción
        holder.descripcion.text = noticia.description ?: "Descripción no disponible"
    }

    override fun getItemCount(): Int {
        return noticias.size
    }

    fun actualizarNoticias(nuevasNoticias: List<Articles>) {
        noticias = nuevasNoticias
        notifyDataSetChanged()
    }
}