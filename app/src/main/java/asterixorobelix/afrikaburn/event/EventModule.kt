package asterixorobelix.afrikaburn.event

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val eventModule = module {
    viewModel { EventViewModel() }

    viewModel { EventTabViewModel(androidContext()) }
}