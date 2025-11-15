package com.example.practica7noticias

data class Categoria(
    val nombre: String,
    val valor: String,
    val emoji: String  // Cambiamos iconoResId por emoji
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
                    emoji = "💰"  // Icono de dinero
                ),
                Categoria(
                    nombre = "Entretenimiento",
                    valor = "entertainment",
                    emoji = "📺"  // Icono de televisión
                ),
                Categoria(
                    nombre = "General",
                    valor = "general",
                    emoji = "📰"  // Icono de periódico
                ),
                Categoria(
                    nombre = "Salud",
                    valor = "health",
                    emoji = "🏥"  // Icono de hospital
                ),
                Categoria(
                    nombre = "Ciencia",
                    valor = "science",
                    emoji = "🔬"  // Icono de microscopio
                ),
                Categoria(
                    nombre = "Deportes",
                    valor = "sports",
                    emoji = "⚽"  // Icono de balón de fútbol
                ),
                Categoria(
                    nombre = "Tecnología",
                    valor = "technology",
                    emoji = "🤖"  // Icono de robot
                )
            )
    }
}