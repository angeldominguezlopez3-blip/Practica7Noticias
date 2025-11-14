package com.example.practica7noticias

data class Categoria(
    val nombre: String,
    val valor: String,
    val iconoResId: Int
) {
    override fun toString(): String {
        return nombre
    }

    companion object {
        val datos
            get() = arrayListOf(
                Categoria(
                    nombre = "Negocios",
                    valor = "business",
                    iconoResId = R.drawable.business_icon
                ),
                Categoria(
                    nombre = "Entretenimiento",
                    valor = "entertainment",
                    iconoResId = R.drawable.entertainment_icon
                ),
                Categoria(
                    nombre = "General",
                    valor = "general",
                    iconoResId = R.drawable.general_icon
                ),
                Categoria(
                    nombre = "Salud",
                    valor = "health",
                    iconoResId = R.drawable.health_icon
                ),
                Categoria(
                    nombre = "Ciencia",
                    valor = "science",
                    iconoResId = R.drawable.science_icon
                ),
                Categoria(
                    nombre = "Deportes",
                    valor = "sports",
                    iconoResId = R.drawable.sports_icon
                ),
                Categoria(
                    nombre = "Tecnología",
                    valor = "technology",
                    iconoResId = R.drawable.technology_icon
                )
            )
    }
}