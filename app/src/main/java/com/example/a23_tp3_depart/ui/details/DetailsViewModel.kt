package com.example.a23_tp3_depart.ui.details

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a23_tp3_depart.data.LocDatabase
import com.example.a23_tp3_depart.model.Locat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private var mDb: LocDatabase? = null
    // todo : déclaration correcte du point retourné
    private val locationLiveData = MutableLiveData<Locat>()

    // todo : Passer contexte et instancier DB et Dao

    fun setContext(context: Context) {
        mDb = LocDatabase.getInstance(context!!.applicationContext)
    }


    // todo : 1 méthode pour retourner le point à id défini
    // accédé par le fragment
    // 1 méthode pour retourner le point à id défini
    fun getLocationById(itemId: Int): LiveData<Locat> {

        // Utilisez viewModelScope pour effectuer l'opération de base de données de manière asynchrone
        viewModelScope.launch(Dispatchers.IO) {
            // Utilisez le DAO pour obtenir le point à partir de la base de données en fonction de l'ID
            val location = mDb?.locDao()?.getLocation(itemId)

            // Mettez à jour la LiveData avec le point trouvé
            location?.value?.let { locationLiveData.postValue(it) }
        }
        return locationLiveData
    }
}


