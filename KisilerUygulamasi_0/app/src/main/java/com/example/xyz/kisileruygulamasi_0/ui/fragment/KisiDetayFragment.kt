package com.example.xyz.kisileruygulamasi_0.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.xyz.kisileruygulamasi_0.R
import com.example.xyz.kisileruygulamasi_0.databinding.FragmentAnaSayfaBinding
import com.example.xyz.kisileruygulamasi_0.databinding.FragmentKisiDetayBinding
import com.example.xyz.kisileruygulamasi_0.ui.viewmodel.KisiDetayViewModel
import com.example.xyz.kisileruygulamasi_0.ui.viewmodel.KisiKayitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KisiDetayFragment : Fragment() {
    private lateinit var tasarim : FragmentKisiDetayBinding
    private lateinit var viewModel: KisiDetayViewModel

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_kisi_detay, container, false)

         tasarim.kisiDetayFragment = this

         tasarim.kisiDetayToolbarBaslik = "Kisi Detay"

         val bundle: KisiDetayFragmentArgs by navArgs()
         val gelenKisi = bundle.kisi

         tasarim.kisiNesnesi = gelenKisi

             return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : KisiDetayViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun buttonGuncelle(kisi_id:String,kisi_ad:String,kisi_tel:String){
       viewModel.Guncelle(kisi_id,kisi_ad,kisi_tel)
    }

}