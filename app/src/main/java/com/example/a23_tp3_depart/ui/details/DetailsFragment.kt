package com.example.a23_tp3_depart.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a23_tp3_depart.R
import com.example.a23_tp3_depart.databinding.FragmentDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class DetailsFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        galleryViewModel.setContext(requireContext())
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo : titre de fragment dans l'ActionBar
        requireActivity().title = getString(R.string.menu_details)

        // TODO : Instanciation correcte de l'id d'élément détaillé
        val itemId = arguments?.getInt("id") ?: -1
        assert(itemId != -1)
        // todo : instanciation correcte de l'id d'élément détaillé
        assert(arguments != null)

        // todo : régler le comportement de l'observe sur le point retourné par le view model
        // --> méthode onChanged de l'Observer : passer les valeurs du point courant à la View
        val detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        detailsViewModel.getLocationById(itemId).observe(viewLifecycleOwner) { location ->
            binding.tvNomDetails.text = location.nom
            binding.tvAdresseDetails.text = location.adresse
            //binding.ivLocationBottom.setImageDrawable(location.)
            // todo : get mapFragment
            val markerOptions = MarkerOptions()
                .position(LatLng(location.latitude, location.longitude))
                .title(location.nom)
            mMap.addMarker(markerOptions)

            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(location.latitude, location.longitude))
                .zoom(15f)
                .build()
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap!!
        // todo : régler le comportement de l'observe sur élément Location retourné par le view model
        // --> méthode onChanged de l'Observer : affichage correct du marqueur pour ce point
    }
}