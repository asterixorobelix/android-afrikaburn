package io.asterixorobelix.databasemodels

import io.asterixorobelix.databasemodels.DatabaseProjectable

interface TabProjectable : DatabaseProjectable {
    var title: String?
    var fieldCollective: String?
    var fieldPrjWtfShortCopy: String?
    var fieldPrjWtfLong: String?
    var fieldPrjWtfPlanned: String?
    var fieldPrjWtfCategories: String?
    var fieldPrjWtfScheduled: String?
    var fieldPrjWtfImage: String?
    var fieldPrjBrnBurning: String?
    var fieldPrjBrnTimeAdm: String?
    var fieldPrjSndSound: String?
    var fieldPrjSndLevel: String?

    fun getImageUrl(): String
}