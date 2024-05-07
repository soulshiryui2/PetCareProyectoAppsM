package mx.edu.potros.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MascotasAdapter : RecyclerView.Adapter<MascotasAdapter.MascotaViewHolder>() {

    private var mascotasList: List<Mascota> = emptyList()

    inner class MascotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)

        fun bind(mascota: Mascota) {
            nombreTextView.text = mascota.nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_mascota, parent, false)
        return MascotaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = mascotasList[position]
        holder.bind(mascota)
    }

    override fun getItemCount(): Int {
        return mascotasList.size
    }

    fun submitList(list: List<Mascota>) {
        mascotasList = list
        notifyDataSetChanged()
    }
}
