package asterixorobelix.afrikaburn.about

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aboutModule = module {
    viewModel { AboutViewModel(androidContext()) }
}