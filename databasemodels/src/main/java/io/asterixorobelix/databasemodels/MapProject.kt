package io.asterixorobelix.databasemodels

class MapProject : MapProjectable {
    override var nid: String? = ""
    override var type: String? = ""
        get() {
            return field?.replace(" ", "")
        }
    override var fieldPrjAdmLatitude: String? = ""
    override var fieldPrjAdmLongitude: String? = ""
}