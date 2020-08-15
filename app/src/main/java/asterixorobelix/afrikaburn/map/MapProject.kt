package asterixorobelix.afrikaburn.map

class MapProject : MapProjectable {
    override var nid: String? = ""
    override var type: String? = ""
        get() {
            return field?.replace(" ", "")
        }
    override var fieldPrjAdmLatitude: String? = ""
    override var fieldPrjAdmLongitude: String? = ""
}