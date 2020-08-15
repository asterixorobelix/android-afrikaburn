package asterixorobelix.afrikaburn.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { Firebase.firestore }

    single { DataProvider(get(),get(), androidContext(),get()) }

    single { Firebase.auth }
}