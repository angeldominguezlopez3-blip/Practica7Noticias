package com.example.practica7noticias

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.android.volley.Request

class MainActivity : AppCompatActivity() {
    lateinit var spn1: Spinner
    lateinit var recyclerView: RecyclerView
    lateinit var adaptador1: ArrayAdapter<Categoria>
    lateinit var noticiasAdapter: NoticiasAdapter
    lateinit var requestQueue: RequestQueue
    var listaArticulos: List<Articles> = emptyList()

    private val newsApiUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=eb0f53bd3d2f40de8542f5238fe803ad"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarVistas()
        configurarSpinner()
        configurarRecyclerView()
    }

    private fun inicializarVistas() {
        spn1 = findViewById(R.id.spinner1)
        recyclerView = findViewById(R.id.recyclerViewNoticias)
    }

    private fun configurarSpinner() {
        adaptador1 = ArrayAdapter<Categoria>(this, android.R.layout.simple_spinner_item, Categoria.datos)
        adaptador1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spn1.adapter = adaptador1

        spn1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val categoriaSeleccionada = parent?.getItemAtPosition(position) as Categoria
                cargarNoticiasPorCategoria(categoriaSeleccionada.valor)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                cargarNoticiasPorCategoria("general")
            }
        }
    }

    private fun configurarRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        noticiasAdapter = NoticiasAdapter(listaArticulos) { noticia ->
            mostrarDialogNoticia(noticia)
        }
        recyclerView.adapter = noticiasAdapter
    }

    private fun cargarNoticiasPorCategoria(categoria: String) {
        val url = "https://newsapi.org/v2/top-headlines?country=us&category=$categoria&apiKey=eb0f53bd3d2f40de8542f5238fe803ad"

        requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                try {
                    val objetoJSON = JSONObject(response)
                    val tipo = object : TypeToken<List<Articles>>() {}.type
                    listaArticulos = Gson().fromJson(objetoJSON.getJSONArray("articles").toString(), tipo)

                    if (listaArticulos.isNotEmpty()) {
                        noticiasAdapter.actualizarNoticias(listaArticulos)
                    } else {
                        Toast.makeText(this@MainActivity, "No hay noticias disponibles", Toast.LENGTH_SHORT).show()
                        noticiasAdapter.actualizarNoticias(emptyList())
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Error al procesar los datos", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this@MainActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun mostrarDialogNoticia(noticia: Articles) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_noticia, null)

        val imageView = dialogView.findViewById<ImageView>(R.id.dialogImage)
        val titulo = dialogView.findViewById<TextView>(R.id.dialogTitulo)
        val contenido = dialogView.findViewById<TextView>(R.id.dialogContenido)

        // Cargar imagen
        if (!noticia.urlToImage.isNullOrEmpty()) {
            Picasso.get()
                .load(noticia.urlToImage)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView)
        }

        // Configurar título y contenido
        titulo.text = noticia.title ?: "Título no disponible"
        contenido.text = noticia.content ?: noticia.description ?: "Contenido no disponible"

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alertDialog.show()
    }
}