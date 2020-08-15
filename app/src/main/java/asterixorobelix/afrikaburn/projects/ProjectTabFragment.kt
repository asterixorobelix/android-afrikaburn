package asterixorobelix.afrikaburn.projects

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.ProjectTabFragmentBinding
import asterixorobelix.afrikaburn.models.ProjectTypes
import asterixorobelix.afrikaburn.projects.detail.DetailNavArgument
import asterixorobelix.afrikaburn.projects.detail.ProjectDetailFragment
import asterixorobelix.utilities.base.BaseViewModelFragment
import asterixorobelix.utilities.ui.items.Buttonable
import asterixorobelix.utilities.ui.items.ClickableItem
import asterixorobelix.utilities.ui.items.Iconable
import asterixorobelix.utilities.ui.items.card.Cardable
import asterixorobelix.utilities.ui.items.subtitledescription.SubtitleDescribable
import asterixorobelix.utilities.ui.items.subtitledescription.SubtitleDescriptionAdapter
import asterixorobelix.utilities.ui.items.thumbnail.ThumbnailCardAdapter
import asterixorobelix.utilities.ui.obtainStringFromResourceId
import asterixorobelix.utilities.ui.setAnimatedVisibility
import org.koin.android.ext.android.inject

class ProjectTabFragment : BaseViewModelFragment<ProjectTabFragmentBinding, ProjectTabViewModel>() {
    override val layout: Int = R.layout.project_tab_fragment
    override val viewModel: ProjectTabViewModel by inject()
    override val optionsMenuId: Int? = null

    override fun setTitleAndRecycler() {
        recyclerView = binding?.projectsRecycler
    }

    override fun loadFragment() {
        viewModel.projectType = arguments?.getString(PROJECT_TAB_BUNDLE_KEY)
        setTitleAndRecycler()

        viewModel.apply {
            tabProjectables.observe(viewLifecycleOwner, Observer {

                val countDescription = object : SubtitleDescribable {
                    override val body: String = determineBodyText()
                    override val headline: String = viewModel.getCountText()
                    override val subtitle: String? = null
                    override val subtitleImage: Int? =
                        if (viewModel.projectType?.isNotEmpty() == true) determineImageForType(
                            ProjectTypes.valueOf(viewModel.projectType!!)
                        ) else null
                    override val subtitleImageURL: String? = null
                }

                val tabProjectsAsThumbnailCards = mutableListOf<Cardable>()

                for (tabProject in it) {
                    val projectCard = object : Cardable {
                        override val actionIcon1: Iconable? = null
                        override val actionIcon2: Iconable? = null
                        override val body: String = tabProject.fieldPrjWtfLong!!
                        override val bodyMaxLineLength: Int? = 10
                        override val button1: Buttonable? = object : Buttonable {
                            override val buttonText: String =
                                obtainStringFromResourceId(R.string.view_project_button_text)
                            override val clickableItem: ClickableItem = object : ClickableItem {
                                override val clickListener: View.OnClickListener? =
                                    View.OnClickListener {
                                        val detailNavArgument = DetailNavArgument(
                                            ProjectTypes.valueOf(tabProject.type!!),
                                            tabProject.nid
                                        )
                                        val bundle = Bundle().apply {
                                            putSerializable(
                                                ProjectDetailFragment.BUNDLE_NAV_KEY,
                                                detailNavArgument
                                            )
                                        }
                                        findNavController().navigate(
                                            R.id.projectDetailFragment,
                                            bundle
                                        )
                                    }
                                override val isEnabled: Boolean? = true
                            }
                        }
                        override val button2: Buttonable? = null
                        override val cardImageID: Int? = null
                        override val cardImageURL: String? = tabProject.getImageUrl()
                        override val headline: String = tabProject.title!!
                        override val subtitle: String? = "${obtainStringFromResourceId(R.string.collective)}: ${tabProject.fieldCollective}"
                    }
                    tabProjectsAsThumbnailCards.add(projectCard)
                }

                recyclerView?.adapter =
                    ConcatAdapter(
                        SubtitleDescriptionAdapter(listOf(countDescription)),
                        ThumbnailCardAdapter(tabProjectsAsThumbnailCards)
                    )
            })
            busy.observe(viewLifecycleOwner, Observer {
                binding?.lottieView?.setAnimatedVisibility(it)
            })
        }
    }

    private fun determineBodyText(): String {
        return when (viewModel.projectType) {
            ProjectTypes.BinnekringEvent.name -> obtainStringFromResourceId(R.string.event_explanation)
            ProjectTypes.ThemeCamp.name -> obtainStringFromResourceId(R.string.theme_camp_explanation)
            ProjectTypes.Artwork.name -> obtainStringFromResourceId(R.string.artwork_explanation)
            ProjectTypes.MutantVehicle.name -> obtainStringFromResourceId(R.string.mutant_vehicle_explanation)
            else -> obtainStringFromResourceId(R.string.projects_count_body)
        }
    }

    private fun determineImageForType(projectType: ProjectTypes): Int {
        return when (projectType) {
            ProjectTypes.MutantVehicle -> R.drawable.mutant_vehicle
            ProjectTypes.ThemeCamp -> R.drawable.theme_camp
            ProjectTypes.BinnekringEvent -> R.drawable.event
            ProjectTypes.Artwork -> R.drawable.artwork
            else -> R.drawable.ic_launcher_foreground
        }
    }

    companion object {
        const val PROJECT_TAB_BUNDLE_KEY = "afrikaburnProjectTabKey"
    }
}
