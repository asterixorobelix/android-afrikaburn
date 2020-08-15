package asterixorobelix.afrikaburn.projects

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val projectsModule = module {
    viewModel { ProjectsViewModel() }

    viewModel { ProjectTabViewModel(get(), androidContext()) }

    single { ProjectTabRepository(get()) }
}