package asterixorobelix.afrikaburn.event

import android.content.Context
import android.view.View
import asterixorobelix.afrikaburn.R
import asterixorobelix.utilities.base.BaseBusyIndicatorViewModel
import asterixorobelix.utilities.ui.items.Buttonable
import asterixorobelix.utilities.ui.items.ClickableItem
import asterixorobelix.utilities.ui.items.Iconable
import asterixorobelix.utilities.ui.items.card.Cardable

class EventTabViewModel(context: Context) : BaseBusyIndicatorViewModel() {
    private val theme = object : Cardable {
        override val actionIcon1: Iconable? = null
        override val actionIcon2: Iconable? = null
        override val body: String = context.getString(R.string.about_theme_body)
        override val bodyMaxLineLength: Int? = null
        override val button1: Buttonable? = object : Buttonable {
            override val buttonText: String = context.getString(R.string.about_theme_button_text)
            override val clickableItem: ClickableItem = object : ClickableItem {
                override val clickListener: View.OnClickListener? = View.OnClickListener {
                    //todo
                }
                override val isEnabled: Boolean? = true
            }
        }
        override val button2: Buttonable? = null
        override val cardImageID: Int? = R.drawable.event_theme
        override val cardImageURL: String? = null
        override val headline: String = context.getString(R.string.about_theme_title)
        override val subtitle: String? = null
    }

    private val afrikaburn = object : Cardable {
        override val actionIcon1: Iconable? = null
        override val actionIcon2: Iconable? = null
        override val body: String = context.getString(R.string.about_ab_body)
        override val bodyMaxLineLength: Int? = null
        override val button1: Buttonable? = object : Buttonable {
            override val buttonText: String = context.getString(R.string.about_website_button_text)
            override val clickableItem: ClickableItem = object : ClickableItem {
                override val clickListener: View.OnClickListener? = View.OnClickListener {
                    //todo
                }
                override val isEnabled: Boolean? = true
            }
        }
        override val button2: Buttonable? = null
        override val cardImageID: Int? = R.drawable.event_image_about_afrikaburn
        override val cardImageURL: String? = null
        override val headline: String = context.getString(R.string.about_ab_title)
        override val subtitle: String? = null
    }

    fun determineTabContents(tabPosition: Int?): Cardable {
        return when (tabPosition) {
            1 -> theme
            else -> afrikaburn
        }
    }

    companion object {
        internal const val AB_WEBSITE = "www.afrikaburn.com"
        internal const val THEME_WEBSITE = "https://www.afrikaburn.com/the-event/2019-theme"

    }
}
