package com.example.xyz.kisileruygulamasi_0.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xyz.kisileruygulamasi_0.R
import com.example.xyz.kisileruygulamasi_0.data.entity.Kisiler
import com.example.xyz.kisileruygulamasi_0.databinding.FragmentAnaSayfaBinding
import com.example.xyz.kisileruygulamasi_0.ui.adapter.KisilerAdapter
import com.example.xyz.kisileruygulamasi_0.ui.viewmodel.AnaSayfaViewModel
import com.example.xyz.kisileruygulamasi_0.ui.viewmodel.KisiDetayViewModel
import com.example.xyz.kisileruygulamasi_0.ui.viewmodel.KisiKayitViewModel
import com.example.xyz.kisileruygulamasi_0.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnaSayfaFragment : Fragment() , SearchView.OnQueryTextListener {
    private lateinit var tasarim : FragmentAnaSayfaBinding
    private lateinit var viewModel: AnaSayfaViewModel

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_ana_sayfa, container, false)

         tasarim.anaSayfaFragment = this

         tasarim.anaSayfaToolbarBaslik = "Kişiler"
         (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnaSayfa)

        viewModel.kisilerListe.observe(viewLifecycleOwner){
            val adapter = KisilerAdapter(requireContext(),it,viewModel)
            tasarim.kisilerAdapter = adapter
        }

         requireActivity().addMenuProvider(object : MenuProvider{
             override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                 menuInflater.inflate(R.menu.toolbar_menu,menu)

                 val item = menu.findItem(R.id.action_ara)
                 val searchView = item.actionView as SearchView
                 searchView.setOnQueryTextListener(this@AnaSayfaFragment)
             }

             override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                 return false
             }
         },viewLifecycleOwner,Lifecycle.State.RESUMED)

         return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnaSayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun fabTikla(it:View){
        Navigation.gecisYap(it,R.id.kisiKayitGecis) // dikkat kisi KayitGecis
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.ara(newText)
        return true
    }

}