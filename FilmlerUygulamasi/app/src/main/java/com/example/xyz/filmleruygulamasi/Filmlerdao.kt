 package com.example.xyz.filmleruygulamasi

class Filmlerdao {

    fun tumFilmlerByKaregoriId(vt:VeritabaniYardimcisi,kategori_id:Int) : ArrayList<Filmler> {
        val db = vt.writableDatabase

        val filmlerListe = ArrayList<Filmler>()
        val c = db.rawQuery("SELECT * FROM filmler,kategoriler,yonetmenler WHERE filmler.kategori_id=kategoriler.kategori_id AND filmler.yonetmen_id=yonetmenler.yonetmen_id AND filmler.kategori_id = $kategori_id",null)

        while (c.moveToNext()){
            val kategori = Kategoriler(c.getInt(c.getColumnIndex("kategori_id"))
                ,c.getString(c.getColumnIndex("kategori_ad")))

            val yonetmen = Yonetmenler(c.getInt(c.getColumnIndex("yonetmen_id"))
                ,c.getString(c.getColumnIndex("yonetmen_ad")))

            val film = Filmler(c.getInt(c.getColumnIndex("film_id"))
            ,c.getString(c.getColumnIndex("film_ad"))
            ,c.getInt(c.getColumnIndex("film_yil"))
            ,c.getString(c.getColumnIndex("film_resim")),kategori,yonetmen)

            filmlerListe.add(film)
        }
        return filmlerListe
    }
}