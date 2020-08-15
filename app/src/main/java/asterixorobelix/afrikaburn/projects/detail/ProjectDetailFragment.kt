package asterixorobelix.afrikaburn.projects.detail

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.ProjectDetailFragmentBinding
import asterixorobelix.utilities.base.BaseViewModelFragment
import asterixorobelix.utilities.ui.items.Buttonable
import asterixorobelix.utilities.ui.items.ClickableItem
import asterixorobelix.utilities.ui.items.Iconable
import asterixorobelix.utilities.ui.items.card.CardAdapter
import asterixorobelix.utilities.ui.items.card.Cardable
import asterixorobelix.utilities.ui.launchBrowserIntent
import asterixorobelix.utilities.ui.obtainStringFromResourceId
import asterixorobelix.utilities.ui.setAnimatedVisibility
import org.koin.android.ext.android.inject

class ProjectDetailFragment :
    BaseViewModelFragment<ProjectDetailFragmentBinding, ProjectDetailViewModel>() {
    override val layout: Int = R.layout.project_detail_fragment
    override val viewModel: ProjectDetailViewModel by inject()
    override val optionsMenuId: Int? = null

    override fun loadFragment() {
        setTitleAndRecycler()
        binding?.apply {

        }

        viewModel.apply {
            detailNavArgument = arguments?.getSerializable(BUNDLE_NAV_KEY) as DetailNavArgument
            projectDetails.observe(viewLifecycleOwner, Observer {
                val card = object : Cardable {
                    override val actionIcon1: Iconable? =
                        determineActionIcon1(it.fieldPrjWtfWebsite)
                    override val actionIcon2: Iconable? = null
                    override val body: String = it.fieldPrjWtfLong!!
                    override val bodyMaxLineLength: Int? = null
                    override val button1: Buttonable? = null
                    override val button2: Buttonable? = null
                    override val cardImageID: Int? = null
                    override val cardImageURL: String? = it.getImageUrl()
                    override val headline: String = it.title!!
                    override val subtitle: String? =
                        "${obtainStringFromResourceId(R.string.collective)} ${it.fieldCollective}"
                }
                recyclerView?.adapter = ConcatAdapter(CardAdapter(listOf(card)))
            })

            busy.observe(viewLifecycleOwner, Observer {
                binding?.lottieView?.setAnimatedVisibility(it)
            })
        }
    }

    override fun setTitleAndRecycler() {
        toolbarTitle = obtainStringFromResourceId(R.string.app_name)
        recyclerView = binding?.projectDetailRecycler
    }

    private fun determineActionIcon1(websiteString: String?): Iconable? {
        websiteString?.let {webAddresses ->
            return object : Iconable {
                override val clickableItem: ClickableItem? = object : ClickableItem {
                    override val clickListener: View.OnClickListener? = View.OnClickListener {
                        launchBrowserIntent(webAddresses.split(" ").first())
                    }
                    override val isEnabled: Boolean? = true
                }
                override val iconImageID: Int = R.drawable.ic_web
            }
        }
        return null
    }

    companion object {
        const val BUNDLE_NAV_KEY = "asterixorobelix.afrikaburn.BUNDLE_NAV_KEY"
    }
}
