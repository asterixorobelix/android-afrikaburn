package asterixorobelix.afrikaburn.location

import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import asterixorobelix.afrikaburn.Constants
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.LocationFragmentBinding
import asterixorobelix.utilities.base.BaseViewModelFragment
import asterixorobelix.utilities.ui.items.Buttonable
import asterixorobelix.utilities.ui.items.ClickableItem
import asterixorobelix.utilities.ui.items.Iconable
import asterixorobelix.utilities.ui.items.card.CardAdapter
import asterixorobelix.utilities.ui.items.card.Cardable
import asterixorobelix.utilities.ui.launchMapIntent
import asterixorobelix.utilities.ui.obtainStringFromResourceId
import org.koin.android.ext.android.inject

class LocationFragment : BaseViewModelFragment<LocationFragmentBinding, LocationViewModel>() {
    override val layout: Int = R.layout.location_fragment
    override val viewModel: LocationViewModel by inject()
    override val optionsMenuId: Int? = null

    override fun loadFragment() {
        setTitleAndRecycler()
    }

    override fun setTitleAndRecycler() {
        toolbarTitle = obtainStringFromResourceId(R.string.menu_location)
        recyclerView = binding?.locationRecycler

        val card = object : Cardable {
            override val actionIcon1: Iconable? = null
            override val actionIcon2: Iconable? = null
            override val body: String = obtainStringFromResourceId(R.string.location_body)
            override val bodyMaxLineLength: Int? = null
            override val button1: Buttonable? = object : Buttonable {
                override val buttonText: String = obtainStringFromResourceId(R.string.location_map)
                override val clickableItem: ClickableItem = object : ClickableItem {
                    override val clickListener: View.OnClickListener? = View.OnClickListener {
                        this@LocationFragment.launchMapIntent("${MAP_NAVIGATION}${Constants.AFRIKABURN_LATITUDE},${Constants.AFRIKABURN_LONGITUDE}")
                    }
                    override val isEnabled: Boolean? = true
                }
            }
            override val button2: Buttonable? = null
            override val cardImageID: Int? = R.drawable.location
            override val cardImageURL: String? = null
            override val headline: String = obtainStringFromResourceId(R.string.location_title)
            override val subtitle: String? = obtainStringFromResourceId(R.string.location_subtitle)
        }

        recyclerView?.adapter = ConcatAdapter(CardAdapter(listOf(card)))
    }

    companion object {
        const val MAP_NAVIGATION = "google.navigation:q="
    }
}
