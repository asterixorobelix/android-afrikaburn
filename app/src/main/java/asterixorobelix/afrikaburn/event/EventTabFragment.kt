package asterixorobelix.afrikaburn.event

import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.EventTabFragmentBinding
import asterixorobelix.utilities.base.BaseViewModelFragment
import asterixorobelix.utilities.ui.items.card.CardAdapter
import org.koin.android.ext.android.inject

class EventTabFragment : BaseViewModelFragment<EventTabFragmentBinding, EventTabViewModel>() {
    override val layout: Int = R.layout.event_tab_fragment
    override val viewModel: EventTabViewModel by inject()
    override val optionsMenuId: Int? = null

    override fun loadFragment() {
        setTitleAndRecycler()

        viewModel.apply {
            recyclerView?.adapter = CardAdapter(
                listOf(
                    viewModel.determineTabContents(
                        arguments?.getInt(EVENT_TAB_BUNDLE_KEY)
                    )
                )
            )
        }
    }

    override fun setTitleAndRecycler() {
        recyclerView = binding?.eventTabRecycler
    }

    companion object {
        const val EVENT_TAB_BUNDLE_KEY = "afrikaburnEventTabKey"
    }
}
