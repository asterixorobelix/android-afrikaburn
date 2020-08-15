package asterixorobelix.afrikaburn.projects.detail

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel { ProjectDetailViewModel(get()) }

    single { ProjectDetailRepository(get()) }
}