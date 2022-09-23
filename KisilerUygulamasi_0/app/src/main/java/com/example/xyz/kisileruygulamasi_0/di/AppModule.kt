package com.example.xyz.kisileruygulamasi_0.di


import com.example.xyz.kisileruygulamasi_0.data.entity.Kisiler
import com.example.xyz.kisileruygulamasi_0.data.repo.KisilerDaoRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideKisilerDaoRepository(refKisiler:DatabaseReference) : KisilerDaoRepository{
        return KisilerDaoRepository(refKisiler)
    }

    @Provides
    @Singleton
    fun provideDatabaseReference() : DatabaseReference{
        val db = FirebaseDatabase.getInstance()
        return db.getReference("kisiler")
    }

}