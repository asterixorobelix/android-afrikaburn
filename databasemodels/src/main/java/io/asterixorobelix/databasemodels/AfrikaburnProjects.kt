package databasemodels

import com.google.gson.annotations.SerializedName
import io.asterixorobelix.databasemodels.AfrikaburnProject

data class AfrikaburnProjects (
    @SerializedName("projects") val projects : List<AfrikaburnProject>
)