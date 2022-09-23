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
import com.example.xyz.kisileruygulamasi_0.R
import com.example.xyz.kisileruygulamasi_0.databinding.FragmentAnaSayfaBinding
import com.example.xyz.kisileruygulamasi_0.databinding.FragmentKisiDetayBinding
import com.example.xyz.kisileruygulamasi_0.databinding.FragmentKisiKayitBinding
import com.example.xyz.kisileruygulamasi_0.ui.viewmodel.KisiKayitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KisiKayitFragment : Fragment() {
    private lateinit var tasarim : FragmentKisiKayitBinding
    private lateinit var viewModel: KisiKayitViewModel

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_kisi_kayit, container, false)

       tasarim.kisiKayitFragment = this

       tasarim.kisiKayitToolbarBaslik = "Kisi Kayıt"


       return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : KisiKayitViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun buttonKayit(kisi_ad:String,kisi_tel:String){
        viewModel.Kayit(kisi_ad,kisi_tel)
    }

}