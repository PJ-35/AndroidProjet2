package com.example.a23_tp3_depart.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a23_tp3_depart.R
import com.example.a23_tp3_depart.model.Locat

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.NoteHolder>() {
    private var locations: List<Locat> = ArrayList<Locat>()
    private var context: Context? = null

    interface OnItemClickListenerInterface {
        fun onItemClick(itemView: View?, position: Int)
//        fun onClickEdit(itemView: View, position: Int)
//        fun onClickDelete(position: Int)
    }

    // Objet qui instancie l'interface OnItemClickListener
    lateinit var listener: OnItemClickListenerInterface

    // Cette méthode permet de passer l'implémentation de l'interface OnItemClickListener à cet adapter
    // Elle est appelée dans la classe MainActivity
    fun setOnItemClickListener(listener: OnItemClickListenerInterface) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.location_row, parent, false)
        context = parent.context
        return NoteHolder(itemView)
    }


    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentLocation: Locat = locations[position]
        val categorie: String = currentLocation.categorie
        holder.tvCategorie.text = categorie
        holder.tvNom.setText(currentLocation.nom)
        holder.tvAdresse.setText(currentLocation.adresse)
        holder.ivLocation.setImageResource(R.drawable.pleinair)
        holder.itemView.setOnClickListener { v ->
            //todo : clic sur rangée : Navigation vers le fragment Détail avec Id du point détaillé
            val itemId = currentLocation.id // L'ID de l'élément sélectionné
            val action = HomeFragmentDirections.actionNavHomeToNavDetails(itemId!!)
            findNavController(v).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    fun setLocations(locations: List<Locat>) {
        //todo : instancier la liste
        this.locations = locations
        //todo : notifier l'adapteur
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategorie: TextView
        val tvNom: TextView
        val tvAdresse: TextView
        val ivLocation: ImageView

        init {
            tvCategorie = itemView.findViewById<TextView>(R.id.tv_categorie)
            tvNom = itemView.findViewById<TextView>(R.id.tv_nom)
            tvAdresse = itemView.findViewById<TextView>(R.id.tv_adresse)
            ivLocation = itemView.findViewById<ImageView>(R.id.iv_loc_cat)
        }
    }
}