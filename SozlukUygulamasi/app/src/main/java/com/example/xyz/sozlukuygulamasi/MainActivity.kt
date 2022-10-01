package com.example.xyz.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.xyz.sozlukuygulamasi.databinding.ActivityMainBinding
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() ,SearchView.OnQueryTextListener{

    private lateinit var kelimelerListe:ArrayList<Kelimeler>
    private lateinit var adapter: KelimelerAdapter
    // private lateinit var vt:VeritabaniYardimcisi
    private lateinit var refKelimeler:DatabaseReference


    private lateinit var tasarim :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root )


        //veritabaniCopy()

        tasarim.toolbar.title = "Sözlük Uygulaması"
        setSupportActionBar(tasarim.toolbar)


        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager =LinearLayoutManager(this)

       // vt = VeritabaniYardimcisi(this)

       // kelimelerListe = Kelimelerdao().tumKelimeler(vt)

        val db = FirebaseDatabase.getInstance()
        refKelimeler = db.getReference("kelimeler")

        kelimelerListe = ArrayList()

        adapter = KelimelerAdapter(this,kelimelerListe)

        tasarim.rv.adapter = adapter

        tumKisiler()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu.findItem(R.id.action_arama)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        aramaYap(query)
        Log.e("Gönderilen arama",query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        aramaYap(newText)
        Log.e("Her girdikçe",newText)
        return true
    }

   /* fun veritabaniCopy() {
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun arama(aramaYap:String){
        kelimelerListe = Kelimelerdao().aramaYap(vt,aramaYap)

        adapter = KelimelerAdapter(this,kelimelerListe)

        tasarim.rv.adapter = adapter

    }*/

    fun tumKisiler(){
        refKelimeler.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(ds: DataSnapshot) {

                kelimelerListe.clear()

                for (c in ds.children){
                    val kelime = c.getValue(Kelimeler::class.java)

                    if (kelime != null){
                        kelime.kelime_id = c.key
                        kelimelerListe.add(kelime)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun aramaYap(aramaKelime:String){
        refKelimeler.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(ds: DataSnapshot) {

                kelimelerListe.clear()

                for (c in ds.children){
                    val kelime = c.getValue(Kelimeler::class.java)

                    if (kelime != null){

                        if (kelime.ingilizce!!.contains(aramaKelime)) {  // contains
                            kelime.kelime_id = c.key
                            kelimelerListe.add(kelime)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}