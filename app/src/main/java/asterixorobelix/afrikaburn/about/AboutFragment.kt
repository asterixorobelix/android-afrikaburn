package asterixorobelix.afrikaburn.about

import androidx.recyclerview.widget.ConcatAdapter
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.databinding.FragmentAboutBinding
import asterixorobelix.utilities.base.BaseViewModelFragment
import asterixorobelix.utilities.ui.items.subtitledescription.SubtitleDescriptionAdapter
import asterixorobelix.utilities.ui.items.thumbnail.ThumbnailCardAdapter
import asterixorobelix.utilities.ui.obtainStringFromResourceId
import org.koin.android.ext.android.inject

class AboutFragment : BaseViewModelFragment<FragmentAboutBinding, AboutViewModel>() {
    override val layout: Int = R.layout.fragment_about
    override val optionsMenuId: Int? = null
    override val viewModel: AboutViewModel? by inject()

    override fun setTitleAndRecycler() {
        toolbarTitle = obtainStringFromResourceId(R.string.menu_about)
        recyclerView = binding?.aboutRecycler
    }

    override fun loadFragment() {
        setTitleAndRecycler()
        viewModel?.apply {
            recyclerView?.adapter = ConcatAdapter(
                ThumbnailCardAdapter(cards),
                SubtitleDescriptionAdapter(versionInfoList)
            )
        }
    }
}