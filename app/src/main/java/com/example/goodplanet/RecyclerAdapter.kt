package com.example.goodplanet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goodplanet.base.BaseViewHolder
import com.example.goodplanet.modelo.Plastico
import kotlinx.android.synthetic.main.plastico_row.view.*


class RecyclerAdapter(
    private val context: Context,
    private val listaPlastico:List<Plastico>,
    private val itemClickListener:OnPlasticoClickListener
    ): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnPlasticoClickListener{
        fun onImagenClick(imagen: String)
        fun onItemClick(nombre: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return PasticoViewHolder(LayoutInflater.from(context).inflate(R.layout.plastico_row, parent,false))
    }

    override fun getItemCount(): Int {
        return listaPlastico.size
    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is PasticoViewHolder -> holder.bind(listaPlastico[position],position)
            else -> throw IllegalArgumentException("Se olvido de pasar el viewholder en el bind")
        }
    }

    inner class PasticoViewHolder(itemView: View):BaseViewHolder<Plastico>(itemView){
        override fun bind(item: Plastico, position: Int) {
            itemView.setOnClickListener{itemClickListener.onItemClick(item.nombre)}
            itemView.Image_View.setOnClickListener{itemClickListener.onImagenClick(item.imagen)}
            Glide.with(context).load(item.imagen).into(itemView.Image_View)
            itemView.txt_nombre_ViewText.text = item.nombre

        }

    }


}