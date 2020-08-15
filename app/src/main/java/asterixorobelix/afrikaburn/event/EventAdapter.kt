package asterixorobelix.afrikaburn.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import asterixorobelix.utilities.ui.newInstance

class EventAdapter(eventFragment: EventFragment) : FragmentStateAdapter(eventFragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val bundleArgs = Bundle()
        bundleArgs.putInt(EventTabFragment.EVENT_TAB_BUNDLE_KEY, position)
        return EventTabFragment().newInstance(bundleArgs)
    }
}