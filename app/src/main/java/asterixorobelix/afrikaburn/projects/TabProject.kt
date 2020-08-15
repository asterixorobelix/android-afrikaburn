package asterixorobelix.afrikaburn.projects

import asterixorobelix.afrikaburn.Constants.Companion.AFRIKABURN_BASE_URL

open class TabProject : TabProjectable {
    override var nid: String? = ""
    override var type: String? = ""
        get() {
            return field?.replace(" ", "")
        }
    override var title: String? = ""
    override var fieldCollective: String? = ""
    override var fieldPrjWtfShortCopy: String? = ""
    override var fieldPrjWtfLong: String? = ""
    override var fieldPrjWtfPlanned: String? = ""
    override var fieldPrjWtfCategories: String? = ""
    override var fieldPrjWtfScheduled: String? = ""
    override var fieldPrjWtfImage: String? = ""
    override var fieldPrjBrnBurning: String? = ""
    override var fieldPrjBrnTimeAdm: String? = ""
    override var fieldPrjSndSound: String? = ""
    override var fieldPrjSndLevel: String? = ""

    override fun getImageUrl(): String = AFRIKABURN_BASE_URL + fieldPrjWtfImage
}