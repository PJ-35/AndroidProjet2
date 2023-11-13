package com.example.a23_tp3_depart.ui.details

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a23_tp3_depart.data.LocDatabase
import com.example.a23_tp3_depart.model.Locat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Console

class DetailsViewModel : ViewModel() {

    private lateinit var mDb: LocDatabase
    // todo : déclaration correcte du point retourné
    private val locationLiveData = MutableLiveData<Locat>()

    // todo : Passer contexte et instancier DB et Dao

    fun setContext(context: Context) {
        mDb = LocDatabase.getInstance(context.applicationContext)

    }


    // todo : 1 méthode pour retourner le point à id défini
    // accédé par le fragment
    // 1 méthode pour retourner le point à id défini
     /*fun getLocationById(itemId: Int): LiveData<Locat?> {

        viewModelScope.launch {
            try {
                // Utilisation du DAO pour obtenir le point à partir de la base de données en fonction de l'ID
                val location = mDb?.locDao()?.getLocation(itemId)

                // Mettez à jour la LiveData avec le point trouvé sur le thread principal
                location?.value?.let { locationLiveData.postValue(it) }

                Log.d("DetailsFragment", "Location: ${location?.value}")
            } catch (e: Exception) {
                // Gestion d'erreur : une exception s'est produite lors de l'accès à la base de données
                // Vous pouvez gérer cela de manière appropriée, par exemple en lançant une exception
                Log.e("DetailsFragment", "Error getting location: $e")
            }
        }*/

    fun getLocationById(itemId: Int): LiveData<Locat?> {

                // Utilisation du DAO pour obtenir le point à partir de la base de données en fonction de l'ID
                val location = mDb?.locDao()?.getLocation(itemId)

        return location!!
    }
}


