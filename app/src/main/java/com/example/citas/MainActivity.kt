package com.example.citas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    val TAG = "com.example.citas.Gasto"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bDefinir = findViewById<Button>(R.id.bDefinir)

        // Listening
        bDefinir.setOnClickListener {
            definirPresupuesto()
        }
    }

    private fun definirPresupuesto() {

        val etPresupuesto = findViewById<TextView>(R.id.etPresupuesto)
        var cantidad:Int = if (etPresupuesto.text.toString() == "") 0 else etPresupuesto.text.toString().toInt()


        if(cantidad <= 0) {
            Toast.makeText(this, "Debe colocar un numero mayor a 0", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Bienvenido a Gasto Semanal", Toast.LENGTH_LONG).show()
            val intent = Intent(this, spending::class.java)
            intent.putExtra(TAG, etPresupuesto.text.toString())
            startActivity(intent)
        }

    }

}