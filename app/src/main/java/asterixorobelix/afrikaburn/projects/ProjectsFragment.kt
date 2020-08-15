package asterixorobelix.afrikaburn.projects

import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.FragmentProjectsBinding
import asterixorobelix.afrikaburn.models.ProjectTypes
import asterixorobelix.utilities.base.BaseViewPagerFragment
import asterixorobelix.utilities.listeners.HideImageNestedScrollListener
import asterixorobelix.utilities.ui.loadImageFromIDSetVisibility
import asterixorobelix.utilities.ui.obtainStringFromResourceId
import asterixorobelix.utilities.ui.toggleMainActivityBottomNavBar
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class ProjectsFragment : BaseViewPagerFragment<FragmentProjectsBinding, ProjectsViewModel>() {
    override val layout: Int = R.layout.fragment_projects
    override val optionsMenuId: Int? = null
    override val viewModel: ProjectsViewModel? by inject()

    override var tabMediator: TabLayoutMediator? = null
    override var viewPagerAdapter: FragmentStateAdapter? = null

    override fun setTitleAndRecycler() {
        toolbarTitle = obtainStringFromResourceId(R.string.menu_projects)
    }

    override fun loadFragment() {
        setTitleAndRecycler()
        binding?.apply {
            viewPagerAdapter = ProjectsAdapter(this@ProjectsFragment)
            projectViewPager.adapter = viewPagerAdapter
            tabMediator = TabLayoutMediator(projectTabs, projectViewPager) { tab, position ->
                tab.text = ProjectTypes.values()[position].name
            }
            tabMediator?.attach()
        }
        toggleMainActivityBottomNavBar(true)
    }

    override fun onPause() {
        toggleMainActivityBottomNavBar(false)
        super.onPause()
    }
}