package asterixorobelix.afrikaburn.map

import androidx.lifecycle.Observer
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.FragmentMapBinding
import asterixorobelix.afrikaburn.map.MapConfiguration.Companion.AFRIKABURN_LAT_LONG
import asterixorobelix.afrikaburn.map.MapConfiguration.Companion.DEFAULT_ZOOM_LEVEL
import asterixorobelix.offlinemap.OfflineMapFragment
import asterixorobelix.utilities.ui.obtainStringFromResourceId
import asterixorobelix.utilities.ui.setVisibleOrGone
import asterixorobelix.utilities.ui.toggleMainActivityBottomNavBar
import com.mapbox.mapboxsdk.geometry.LatLng
import org.koin.android.ext.android.inject

class MapFragment : OfflineMapFragment<FragmentMapBinding, MapViewModel>() {
    // todo crash when leaving fragment before map markers have loaded.

    override val layout: Int = R.layout.fragment_map
    override val optionsMenuId: Int? = null
    override val viewModel: MapViewModel? by inject()

    override fun setTitleAndRecycler() {
        toolbarTitle = obtainStringFromResourceId(R.string.menu_map)
    }

    override fun loadFragment() {
        setTitleAndRecycler()
        toggleMainActivityBottomNavBar(true)
        binding?.apply {
            setupView(
                mapView,
                DEFAULT_ZOOM_LEVEL,
                AFRIKABURN_LAT_LONG,
                { d: Double, latLng: LatLng ->
                    onMapMovement(d, latLng)
                },
                locationReject,
                locationAccept,
                locationFab,
                locationPickerFab,
                locationPickerOverlay
            )
        }

        viewModel?.apply {
            isDataLoading.observe(viewLifecycleOwner, Observer {
                binding?.lottieView?.setVisibleOrGone(it)
            })
        }
    }

    override fun onPause() {
        toggleMainActivityBottomNavBar(visible = false)
        super.onPause()
    }

    private fun onMapMovement(asdf: Double, latLong: LatLng) {
        //todo
    }
}