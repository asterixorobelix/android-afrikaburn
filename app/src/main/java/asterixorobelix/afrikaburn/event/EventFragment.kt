package asterixorobelix.afrikaburn.event

import androidx.viewpager2.adapter.FragmentStateAdapter
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.EventFragmentBinding
import asterixorobelix.utilities.base.BaseViewPagerFragment
import asterixorobelix.utilities.ui.obtainStringFromResourceId
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class EventFragment : BaseViewPagerFragment<EventFragmentBinding, EventViewModel>() {
    override val layout: Int = R.layout.event_fragment
    override val viewModel: EventViewModel by inject()
    override val optionsMenuId: Int? = null

    override var tabMediator: TabLayoutMediator? = null
    override var viewPagerAdapter: FragmentStateAdapter? = null

    override fun loadFragment() {
        setTitleAndRecycler()
        binding?.apply {
            viewPagerAdapter = EventAdapter(this@EventFragment)
            eventViewPager.adapter = viewPagerAdapter
            pageIndicator.setViewPager(eventViewPager)
        }

    }

    override fun setTitleAndRecycler() {
        toolbarTitle = obtainStringFromResourceId(R.string.menu_event)
    }
}
