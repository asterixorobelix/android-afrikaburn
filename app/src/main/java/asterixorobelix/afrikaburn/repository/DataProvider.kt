package asterixorobelix.afrikaburn.repository

import android.content.Context
import androidx.core.content.ContextCompat
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.map.MapConfiguration
import asterixorobelix.afrikaburn.map.MapProject
import asterixorobelix.afrikaburn.models.ProjectTypes
import asterixorobelix.afrikaburn.projects.TabProject
import asterixorobelix.afrikaburn.projects.TabProjectable
import asterixorobelix.afrikaburn.projects.detail.DetailNavArgument
import asterixorobelix.afrikaburn.projects.detail.ProjectDetailModel
import asterixorobelix.afrikaburn.projects.detail.ProjectDetailable
import asterixorobelix.offlinemap.MarkerLocation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class DataProvider(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val context: Context, private val gson: Gson
) {

    fun getProject(
        detailNavArgument: DetailNavArgument,
        onProjectReceived: (ProjectDetailable?) -> Unit
    ) {
        firebaseAuth.signInAnonymously().apply {
            addOnSuccessListener {
                getCollection(detailNavArgument.projectType).whereEqualTo(
                    PROJECT_ID_KEY,
                    detailNavArgument.projectID
                ).get().addOnSuccessListener {
                    onProjectReceived(it.toObjects(ProjectDetailModel::class.java).singleOrNull())
                }
            }
        }
    }

    fun getProjectCount(
        projectType: ProjectTypes,
        onCountReceived: (List<TabProjectable>) -> Unit
    ) {
        firebaseAuth.signInAnonymously().apply {
            addOnSuccessListener {
                getCollection(projectType).get().addOnSuccessListener {
                    onCountReceived(it.toObjects(TabProject::class.java))
                }
            }
        }
    }

    fun getMarkerLocations(
        projectType: ProjectTypes,
        onMarkerLocationsReceived: (List<MarkerLocation>) -> Unit
    ) {
        firebaseAuth.signInAnonymously().apply {
            addOnSuccessListener {
                val collection = getCollection(projectType)

                collection.get().addOnSuccessListener {
                    val markerLocationsList: MutableList<MarkerLocation> = mutableListOf()

                    for (dbProject in it.toObjects(MapProject::class.java)) {
                        val markerLocation = object : MarkerLocation {
                            override val additionalInformation: JsonObject = JsonObject().apply {
                                add(
                                    MapConfiguration.MARKER_ADDITIONAL_INFO_KEY,
                                    JsonParser.parseString(
                                        gson.toJson(
                                            DetailNavArgument(
                                                ProjectTypes.valueOf(dbProject.type!!),
                                                dbProject.nid
                                            )
                                        )
                                    )
                                )
                            }
                            override val markerColorInt: Int =
                                ContextCompat.getColor(context, R.color.colorAccent)
                            override val markerLatitude: Double? =
                                dbProject.fieldPrjAdmLatitude?.toDoubleOrNull()
                            override val markerLongitude: Double? =
                                dbProject.fieldPrjAdmLongitude?.toDoubleOrNull()
                        }
                        markerLocationsList.add(markerLocation)
                    }
                    onMarkerLocationsReceived(markerLocationsList)
                }
                return@addOnSuccessListener
            }
        }
    }

    private fun getCollection(projectType: ProjectTypes): CollectionReference {
        return when (projectType) {
            ProjectTypes.MutantVehicle -> firestore.collection("Mutant Vehicle")
            ProjectTypes.ThemeCamp -> firestore.collection("Theme Camp")
            ProjectTypes.BinnekringEvent -> firestore.collection("Binnekring Event")
            ProjectTypes.Artwork -> firestore.collection("Artwork")
            ProjectTypes.All -> firestore.collection("projects")
        }
    }

    companion object {
        const val PROJECT_ID_KEY = "nid"
    }
}