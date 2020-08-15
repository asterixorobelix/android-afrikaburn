package asterixorobelix.afrikaburn.about

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import asterixorobelix.afrikaburn.BuildConfig
import asterixorobelix.afrikaburn.R
import asterixorobelix.utilities.ui.createAppStoreListingIntent
import asterixorobelix.utilities.ui.items.Buttonable
import asterixorobelix.utilities.ui.items.ClickableItem
import asterixorobelix.utilities.ui.items.Iconable
import asterixorobelix.utilities.ui.items.card.Cardable
import asterixorobelix.utilities.ui.items.subtitledescription.SubtitleDescribable


class AboutViewModel(context: Context) : ViewModel() {
    val versionInfo = object : SubtitleDescribable {
        override val body: String =
            "${context.getString(R.string.version)}: ${BuildConfig.VERSION_CODE}"
        override val headline: String = context.getString(R.string.app_name)
        override val subtitle: String? = null
        override val subtitleImage: Int? = R.drawable.ic_gear
        override val subtitleImageURL: String? = null
    }
    val versionInfoList = listOf(versionInfo)

    val asterixorobelixInstagram = object : Cardable {
        override val actionIcon1: Iconable? = null
        override val actionIcon2: Iconable? = null
        override val body: String = context.getString(R.string.images_instagram)
        override val bodyMaxLineLength: Int? = null
        override val button1: Buttonable? = object : Buttonable {
            override val buttonText: String = context.getString(R.string.images_button)
            override val clickableItem: ClickableItem = object : ClickableItem {
                override val clickListener: View.OnClickListener? = View.OnClickListener {
                    val uri: Uri = Uri.parse(INSTAGRAM_URL)
                    val likeIng = Intent(Intent.ACTION_VIEW, uri).apply {
                        setPackage("com.instagram.android")
                        flags = FLAG_ACTIVITY_NEW_TASK
                    }
                    try {
                        startActivity(context, likeIng, null)
                    } catch (e: ActivityNotFoundException) {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(INSTAGRAM_URL)
                        )
                        intent.flags = FLAG_ACTIVITY_NEW_TASK
                        startActivity(
                            context,
                            intent, null
                        )
                    }
                }
                override val isEnabled: Boolean? = true
            }
        }
        override val button2: Buttonable? = null
        override val cardImageID: Int? = R.drawable.about_asterixorobelix
        override val cardImageURL: String? = null
        override val headline: String = context.getString(R.string.images_headline)
        override val subtitle: String? = null
    }

    val storeRating = object : Cardable {
        override val actionIcon1: Iconable? = object : Iconable {
            override val clickableItem: ClickableItem? = null
            override val iconImageID: Int = android.R.drawable.star_off
        }
        override val actionIcon2: Iconable? = null
        override val body: String = context.getString(R.string.rate_body)
        override val bodyMaxLineLength: Int? = null
        override val button1: Buttonable? = object : Buttonable {
            override val buttonText: String = context.getString(R.string.rate_button)
            override val clickableItem: ClickableItem = object : ClickableItem {
                override val clickListener: View.OnClickListener? = View.OnClickListener {
                    context.createAppStoreListingIntent(BuildConfig.APPLICATION_ID)
                }
                override val isEnabled: Boolean? = true
            }
        }
        override val button2: Buttonable? = null
        override val cardImageID: Int? = R.drawable.ic_icons8_google_play
        override val cardImageURL: String? = null
        override val headline: String = context.getString(R.string.rate_title)
        override val subtitle: String? = null
    }

    val cards = listOf(storeRating, asterixorobelixInstagram)

    companion object {
        private const val INSTAGRAM_URL = "https://www.instagram.com/asterixorobelix/"
    }
}