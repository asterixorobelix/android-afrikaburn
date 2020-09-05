package io.asterixorobelix.databasemodels

import com.google.gson.annotations.SerializedName

data class AfrikaburnProject (
    @SerializedName("nid") val nid : String,
    @SerializedName("type") val type : String,
    @SerializedName("title") val title : String,

    @SerializedName( "field_collective")
    val fieldCollective: String,

    @SerializedName("field_prj_wtf_short_copy")
    val fieldPrjWtfShortCopy: String,

    @SerializedName("field_prj_wtf_long")
    val fieldPrjWtfLong: String,

    @SerializedName("field_prj_wtf_planned")
    val fieldPrjWtfPlanned: String,

    @SerializedName("field_prj_wtf_categories")
    val fieldPrjWtfCategories: String,

    @SerializedName("field_prj_wtf_scheduled")
    val fieldPrjWtfScheduled: String,

    @SerializedName("field_prj_wtf_image")
    val fieldPrjWtfImage: String,

    @SerializedName("field_prj_gen_history")
    val fieldPrjGenHistory: String,

    @SerializedName("field_prj_wtf_website")
    val fieldPrjWtfWebsite: String,

    @SerializedName("field_prj_oth_relationship")
    val fieldPrjOthRelationship: String,

    @SerializedName("field_prj_oth_associated")
    val fieldPrjOthAssociated: String,

    @SerializedName("field_prj_brn_burning")
    val fieldPrjBrnBurning: String,

    @SerializedName("field_prj_brn_time_adm")
    val fieldPrjBrnTimeAdm: String,

    @SerializedName("field_prj_snd_sound")
    val fieldPrjSndSound: String,

    @SerializedName("field_prj_snd_level")
    val fieldPrjSndLevel: String,

    @SerializedName("field_prj_adm_latitude")
    val fieldPrjAdmLatitude: String,

    @SerializedName("field_prj_adm_longitude")
    val fieldPrjAdmLongitude: String,

    @SerializedName("field_api_content_type")
    val fieldAPIContentType: String
)