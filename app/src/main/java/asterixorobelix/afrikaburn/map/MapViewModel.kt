package asterixorobelix.afrikaburn.map

import androidx.navigation.NavController
import asterixorobelix.offlinemap.OfflineMapConfiguration
import asterixorobelix.offlinemap.OfflineMapDataProvider
import asterixorobelix.offlinemap.OfflineMapViewModel
import com.google.gson.Gson
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.CircleManager

class MapViewModel(gson: Gson, private val mapRepository: MapRepository) :
    OfflineMapViewModel(gson) {

    override val offlineMapConfiguration: OfflineMapConfiguration = MapConfiguration()
    override val offlineMapDataProvider: OfflineMapDataProvider = mapRepository

    override suspend fun initializeUserMapMarkers(
        mapView: MapView,
        mapboxMap: MapboxMap,
        mapStyle: Style,
        navController: NavController
    ) {
        //todo
        mapRepository.provideUserMapMarkerLocations {
            //setUserMapMarkers(it, navController, CircleManager(mapView, mapboxMap, mapStyle))
        }
    }

    override suspend fun initializeMapMarkers(
        mapView: MapView,
        mapboxMap: MapboxMap,
        mapStyle: Style,
        navController: NavController
    ) {
        mapRepository.provideMapMarkerLocations {
            setMapMarkers(it, navController, mapView, mapboxMap, mapStyle)
        }
    }
}