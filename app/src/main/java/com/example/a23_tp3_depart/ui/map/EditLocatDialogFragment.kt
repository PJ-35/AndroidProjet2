package com.example.a23_tp3_depart.ui.map

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.a23_tp3_depart.R
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.a23_tp3_depart.MainActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.SupportMapFragment

/**
 * Exemple de boîte de dialogue personnalisée pour modifier le nom d'une personne
 */
class EditLocatDialogFragment() : DialogFragment() {
    private lateinit var spinner: Spinner
    private lateinit var champNom: EditText
    private var name: String? =null
    private var latitude: Double =0.0
    private var longitude: Double = 0.0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = activity?.let { AlertDialog.Builder(it) }
        // Layout Inflater : Responsable de l'affichage du layout
        // requireActivity() : Servi par l'activité appelante (ici, MainActivity)
        val inflater = requireActivity().layoutInflater

        val view = inflater.inflate(R.layout.set_location_dialog, null)

        champNom = (view).findViewById<EditText>(R.id.txtNom)!!

        spinner = (view).findViewById<Spinner>(R.id.spinner)!!

        val spinnerArray = ArrayList<String>()
        spinnerArray.add("Un")
        spinnerArray.add("Deux")
        spinnerArray.add("Trois")

        val spinnerArrayAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerArray)
        spinner!!.adapter = spinnerArrayAdapter

        // Récupère les arguments passés à la boîte de dialogue depuis l'activité appelante
        arguments?.let {
            name = it.getString("name")
            latitude = it.getDouble("latitude")
            longitude = it.getDouble("longitude")
            builder?.setTitle("Création d'un nouveau point d'intérêts")
        }
        // Importe le layout de la boîte de dialogue
        // Le paramètre null est nécessaire car le layout est directement lié à la boîte de dialogue et non ancré dans un parent
        builder?.setView(view)
            // Gestion des boutons Ok et Annuler
            ?.setPositiveButton("OK") { dialog, id ->


                val nom = champNom.text.toString()
                val categorie = spinner.getSelectedItem().toString()
                if(nom.isNullOrEmpty()||categorie.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Le point d'intérêts n'a pas été créé. Informations insuffisantes ou mal entrées", Toast.LENGTH_SHORT).show()
                }else{
                    (activity as MainActivity).onCreatePointInteret(nom,categorie,name!!,latitude,longitude)
                }
                // Retourne le nom modifié à l'activité
                Log.i("TAG CLIC DIALOG", "$nom $categorie")
            }
            ?.setNegativeButton("Annuler") { dialog, id ->
                getDialog()?.cancel()
            }



        if (builder != null) {
            return builder.create()
        }
        return super.onCreateDialog(savedInstanceState)
    }

}