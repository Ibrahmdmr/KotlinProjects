package com.example.xyz.kisileruygulamasi_0.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.xyz.kisileruygulamasi_0.R
import com.example.xyz.kisileruygulamasi_0.data.entity.Kisiler
import com.example.xyz.kisileruygulamasi_0.databinding.CardTasarimBinding
import com.example.xyz.kisileruygulamasi_0.ui.fragment.AnaSayfaFragment
import com.example.xyz.kisileruygulamasi_0.ui.fragment.AnaSayfaFragmentDirections
import com.example.xyz.kisileruygulamasi_0.ui.viewmodel.AnaSayfaViewModel
import com.example.xyz.kisileruygulamasi_0.util.gecisYap
import com.google.android.material.snackbar.Snackbar

class KisilerAdapter (var mContext: Context
                     ,var kisilerListe:List<Kisiler>
                     ,var viewModel: AnaSayfaViewModel)
     : RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>(){


     inner class CardTasarimTutucu(tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
          var tasarim:CardTasarimBinding
          init {
               this.tasarim = tasarim
          }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
          val layoutInflater = LayoutInflater.from(mContext)
          val tasarim:CardTasarimBinding = DataBindingUtil.inflate(layoutInflater,R.layout.card_tasarim,parent,false)

          return CardTasarimTutucu(tasarim)
     }

     override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
          val kisi = kisilerListe.get(position)
          val t = holder.tasarim

          t.kisiNesnesi = kisi

          t.satirCard.setOnClickListener {
               val gecis = AnaSayfaFragmentDirections.kisiDetayGecis(kisi = kisi)
              Navigation.gecisYap(it,gecis)
          }

          t.imageViewSil.setOnClickListener {
               Snackbar.make(it,"${kisi.kisi_ad} silinsin mi?",Snackbar.LENGTH_LONG)
                    .setAction("EVET"){
                        viewModel.sil(kisi.kisi_id!!)
                    }.show()
          }
     }

     override fun getItemCount(): Int {
          return kisilerListe.size
     }
}