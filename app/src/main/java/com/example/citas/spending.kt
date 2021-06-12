package com.example.citas

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_spending.*


class spending : AppCompatActivity() {
    val listSpeding:MutableList<Gasto> = ArrayList()
    var presupuesto:Int = 0
    var restante:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spending)

        presupuesto = intent.getStringExtra("com.example.citas.Gasto").toInt()

        restante = presupuesto

        val tvPresupuesto = findViewById<TextView>(R.id.tvPresupuesto)
        tvPresupuesto.setText("Restante: $ ${presupuesto}")

        val tvRestante = findViewById<TextView>(R.id.tvRestante)
        tvRestante.setText("Restante: $ ${restante}")

        val bGasto = findViewById<Button>(R.id.bAgregar)
        bGasto.setOnClickListener { setSpending() }
    }


    @SuppressLint("ResourceAsColor")
    private fun setSpending() {

        val etNombre = findViewById<EditText>(R.id.etNombreGasto)
        val etGasto = findViewById<EditText>(R.id.etGasto)

        if(etNombre.text.toString() == "" || etGasto.text.toString() == "") {
            Toast.makeText(this, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show()
        } else if(etGasto.text.toString().toInt() <= 0) {
            Toast.makeText(this, "El gasto debe ser mayor a 0.", Toast.LENGTH_SHORT).show()
        } else {

            val gasto = Gasto(etNombre.text.toString(), etGasto.text.toString().toInt())
            listSpeding.add(gasto)

            val adapter = ProductoAdapter(this, listSpeding)

            lvTablero.adapter = adapter

            restante -= etGasto.text.toString().toInt()

            if(restante <= presupuesto/2) {
                tvRestante.background = ContextCompat.getDrawable(this, R.color.warningBackground)
                tvRestante.setTextColor(Color.parseColor("#856404"))
            }

            if (restante <= presupuesto*0.25) {
                tvRestante.background = ContextCompat.getDrawable(this, R.color.dangerBackground)
                tvRestante.setTextColor(Color.parseColor("#721c24"))
            }

            val tvRestante = findViewById<TextView>(R.id.tvRestante)
            tvRestante.setText("Restante: $ ${restante}")

            etNombre.setText("")
            etGasto.setText("")

            hideKeyboard()

        }

    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}