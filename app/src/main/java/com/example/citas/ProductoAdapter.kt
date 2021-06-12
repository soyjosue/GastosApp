package com.example.citas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_gasto.view.*

class ProductoAdapter(private val mContext: Context, private val listaGastos: List<Gasto>): ArrayAdapter<Gasto>(mContext, 0, listaGastos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_gasto, parent, false)

        val producto = listaGastos[position]

        layout.nombre.text = producto.nombre
        layout.cantidad.text = "$${producto.cantidad}"

        return layout
    }

}