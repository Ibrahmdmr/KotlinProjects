package com.example.xyz.notlaruygulamasi

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.xyz.notlaruygulamasi.databinding.ActivityDetayBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DetayActivity : AppCompatActivity() {

    private lateinit var not: Notlar
 //   private lateinit var vt:VeritabaniYardimcisi
      private lateinit var refNotlar: DatabaseReference

    private lateinit var tasarim : ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(tasarim.root)


        tasarim.toolbarDetay.title = "Not Detay"
        setSupportActionBar(tasarim.toolbarDetay)

      //  vt = VeritabaniYardimcisi(this)

        val db = FirebaseDatabase.getInstance() // izin alma
        refNotlar = db.getReference("notlar")

        not = intent.getSerializableExtra("nesne") as Notlar

        tasarim.editTextTextDetayDers.setText(not.ders_adi)
        tasarim.editTextDetayNot1.setText((not.not1).toString())           // sorun burda
        tasarim.editTextDetayNot2.setText((not.not2).toString())     // parantezlere dikkat!!

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {

        when(item.itemId){
            R.id.action_sil -> {
                Snackbar.make(tasarim.toolbarDetay,"Silinsin mi?",Snackbar.LENGTH_LONG)
                    .setAction("Evet") {

                        refNotlar.child(not.not_id!!).removeValue()

                        val intent = Intent(this@DetayActivity, MainActivity::class.java)
                        finish()
                        startActivity(intent)
                    }.show()
                return true
            }

            R.id.action_duzenle -> {
                val ders_adi = tasarim.editTextTextDetayDers.text.toString().trim()
                val not1 = tasarim.editTextDetayNot1.text.toString().trim()
                val not2 = tasarim.editTextDetayNot2.text.toString().trim()

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(tasarim.toolbarDetay,"Ders adını giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(tasarim.toolbarDetay,"1. Notu giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(tasarim.toolbarDetay,"2. Notu giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                val bilgiler = HashMap<String,Any>()

                bilgiler.put("ders_adi",ders_adi)
                bilgiler.put("not1",not1.toInt())
                bilgiler.put("not2",not2.toInt())

                refNotlar.child(not.not_id!!).updateChildren(bilgiler)


                val intent = Intent(this@DetayActivity,MainActivity::class.java)
                finish()
                startActivity(intent)
                return true
            }
            else -> return false
        }
    }
}