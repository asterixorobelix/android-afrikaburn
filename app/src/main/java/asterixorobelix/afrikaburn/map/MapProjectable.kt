package asterixorobelix.afrikaburn.map

import asterixorobelix.afrikaburn.models.DatabaseProjectable

interface MapProjectable : DatabaseProjectable {
    var fieldPrjAdmLatitude: String?
    var fieldPrjAdmLongitude: String?
}