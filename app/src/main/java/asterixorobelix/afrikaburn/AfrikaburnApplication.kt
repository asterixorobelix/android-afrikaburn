package asterixorobelix.afrikaburn

import asterixorobelix.afrikaburn.about.aboutModule
import asterixorobelix.afrikaburn.event.eventModule
import asterixorobelix.afrikaburn.location.locationModule
import asterixorobelix.afrikaburn.map.mapModule
import asterixorobelix.afrikaburn.projects.detail.detailModule
import asterixorobelix.afrikaburn.projects.projectsModule
import asterixorobelix.afrikaburn.repository.repositoryModule
import asterixorobelix.utilities.KoinApplication
import org.koin.core.module.Module

class AfrikaburnApplication : KoinApplication() {
    override val koinModules: List<Module> =
        listOf(
            aboutModule,
            projectsModule,
            mapModule,
            repositoryModule,
            detailModule,
            eventModule,
            locationModule
        )
}