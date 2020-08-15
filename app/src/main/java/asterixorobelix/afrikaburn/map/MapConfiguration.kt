package asterixorobelix.afrikaburn.map

import android.os.Bundle
import androidx.navigation.NavController
import asterixorobelix.afrikaburn.Constants
import asterixorobelix.afrikaburn.MainActivity
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.projects.detail.ProjectDetailFragment
import asterixorobelix.offlinemap.OfflineMapConfiguration
import asterixorobelix.offlinemap.OfflineMapStyle
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.mapbox.mapboxsdk.geometry.LatLng

class MapConfiguration : OfflineMapConfiguration {

    companion object {
        val DEFAULT_ZOOM_LEVEL = 15.toDouble()

        val AFRIKABURN_LAT_LONG =
            LatLng(Constants.AFRIKABURN_LATITUDE, Constants.AFRIKABURN_LONGITUDE)
        const val MARKER_ADDITIONAL_INFO_KEY = "markerAfrikaburnProjectInfo"
    }

    override val allowUserLocationSelection: Boolean = true
    override val mapMinZoom: Double = 10.0
    override val mapMaxZoom: Double = 20.0
    override val boundingMinLat: Double = -32.324203
    override val boundingMaxLat: Double = -32.335009
    override val boundingMinLong: Double = 19.740243
    override val defaultZoomLevel: Double = DEFAULT_ZOOM_LEVEL
    override val boundingMaxLong: Double = 19.754577
    override var mapStyle: OfflineMapStyle = OfflineMapStyle.Dark
    override val markerCircleRadius: Float = 12f
    override val metadataRegionName: String = "Afrikaburn"
    override val returnActivityName: String = MainActivity::class.qualifiedName!!
    override val shouldAllowOfflineDownload: Boolean = true
    override val showUserLocation: Boolean = true
    override val targetLatLong: LatLng = AFRIKABURN_LAT_LONG

    override fun onMarkerClick(navController: NavController, markerData: JsonElement?) {
        val obj = JsonParser().parse(markerData.toString())
        val bundle = Bundle()
        bundle.putString(
            ProjectDetailFragment.BUNDLE_NAV_KEY,
            Gson().toJson(obj.asJsonObject.get(MARKER_ADDITIONAL_INFO_KEY))
        )
        navController.navigate(R.id.projectDetailFragment, bundle)
    }

    override fun onUserMarkerClick(navController: NavController, markerData: JsonElement?) {
        TODO("Not yet implemented")
    }
}