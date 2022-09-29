package com.example.xyz.notlaruygulamasi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xyz.notlaruygulamasi.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var notlarListesi:ArrayList<Notlar>
    private lateinit var adapter: NotlarAdapter
    //private lateinit var vt:VeritabaniYardimcisi
    private lateinit var refNotlar:DatabaseReference


    private lateinit var tasarim :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)


        tasarim.toolbar.title = "Notlar Uygulaması"

        setSupportActionBar(tasarim.toolbar)

      //  vt = VeritabaniYardimcisi(this)

        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager = LinearLayoutManager(this)

     //  notlarListesi = Notlardao().tumNotlar(vt)

        val db = FirebaseDatabase.getInstance() // izin alma
        refNotlar = db.getReference("notlar")

        notlarListesi = ArrayList()

        adapter = NotlarAdapter(this,notlarListesi)
        tasarim.rv.adapter = adapter

        tumKisiler()

        tasarim.fab.setOnClickListener {

            val intent = Intent(this@MainActivity,NotKayitActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun tumKisiler(){
        refNotlar.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(ds: DataSnapshot) {

                notlarListesi.clear()
                var toplam = 0

                for (c in ds.children){
                    val not = c.getValue(Notlar::class.java)

                    if (not != null){
                        not.not_id = c.key
                        notlarListesi.add(not)
                        toplam = toplam + (not.not1!! + not.not2!!)/2
                    }

                }
                adapter.notifyDataSetChanged()
                if (toplam != 0) {
                    tasarim.toolbar.subtitle= "Ortalama : ${toplam/notlarListesi.size}"
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}