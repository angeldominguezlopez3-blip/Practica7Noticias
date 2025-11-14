package com.example.practica7noticias

data class Categoria (
    val nombre: String,
    val valor: String
) {

    override fun toString(): String {
        return nombre
    }

    companion object {
        val datos
            get() = arrayListOf(
                Categoria(
                    nombre = "Negocios",
                    valor = "business" // CORRECCIÓN: "Bussines" → "business"
                ),
                Categoria(
                    nombre = "Entretenimiento",
                    valor = "entertainment" // CORRECCIÓN: "Entertainment" → "entertainment"
                ),
                Categoria(
                    nombre = "General",
                    valor = "general"
                ),
                Categoria(
                    nombre = "Salud",
                    valor = "health"
                ),
                Categoria(
                    nombre = "Ciencia",
                    valor = "science"
                ),
                Categoria(
                    nombre = "Deportes",
                    valor = "sports"
                ),
                Categoria(
                    nombre = "Tecnología",
                    valor = "technology"
                )
            )
    }
}