package asterixorobelix.afrikaburn.map

import asterixorobelix.afrikaburn.models.ProjectTypes
import asterixorobelix.afrikaburn.repository.DataProvider
import asterixorobelix.offlinemap.MarkerLocation
import asterixorobelix.offlinemap.OfflineMapDataProvider

class MapRepository(private val dataProvider: DataProvider) : OfflineMapDataProvider {
    override suspend fun provideMapMarkerLocations(onMarkerLocationsReceived: (List<MarkerLocation>) -> Unit) {
        dataProvider.getMarkerLocations(ProjectTypes.All, onMarkerLocationsReceived)
    }

    override suspend fun provideUserMapMarkerLocations(onMarkerLocationsReceived: (List<MarkerLocation>) -> Unit) {
        //todo
    }
}