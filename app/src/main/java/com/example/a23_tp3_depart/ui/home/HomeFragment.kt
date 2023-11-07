package com.example.a23_tp3_depart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a23_tp3_depart.R
import com.example.a23_tp3_depart.data.LocDao
import com.example.a23_tp3_depart.data.LocDatabase
import com.example.a23_tp3_depart.databinding.FragmentHomeBinding
import com.example.a23_tp3_depart.model.Locat

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var rvHome: RecyclerView
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        // Créera l'instance de la BD dans le ViewModel
        homeViewModel.setContext(requireContext())

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // todo : déclaration et instanciation du RecyclerView
        rvHome = view.findViewById(R.id.rv_location)

        // todo : configuration
        rvHome.layoutManager = LinearLayoutManager(requireContext())
        rvHome.setHasFixedSize(true)
        // todo : adapteur et passage de l'adapteur au RecyclerView
        adapter = HomeAdapter()
        rvHome.adapter = adapter

        // todo : régler le comportement de l'observe sur la liste de points retourné par le view model
        // --> méthode onChanged de l'Observer : passer la liste à l'adapteur
        // Récupérez la liste de points depuis le ViewModel et observez les changements
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getAllLocations().observe(viewLifecycleOwner) { locations ->
            // Passer la liste de points à l'adaptateur
            adapter.setLocations(locations)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}