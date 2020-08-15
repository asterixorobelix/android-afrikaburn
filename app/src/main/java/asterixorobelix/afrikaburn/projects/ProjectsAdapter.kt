package asterixorobelix.afrikaburn.projects

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import asterixorobelix.afrikaburn.models.ProjectTypes
import asterixorobelix.utilities.ui.newInstance

class ProjectsAdapter(projectsFragment: ProjectsFragment) : FragmentStateAdapter(projectsFragment) {
    override fun getItemCount(): Int = ProjectTypes.values().count()

    override fun createFragment(position: Int): Fragment {
        val bundleArgs = Bundle()
        bundleArgs.putString(
            ProjectTabFragment.PROJECT_TAB_BUNDLE_KEY,
            ProjectTypes.values()[position].name
        )
        return ProjectTabFragment().newInstance(bundleArgs)
    }
}