package asterixorobelix.afrikaburn.map

import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapModule = module {
    viewModel { MapViewModel(get(), get()) }

    single { Gson() }

    single { MapRepository(get()) }
}