package asterixorobelix.afrikaburn.projects.detail

import io.asterixorobelix.databasemodels.ProjectTypes
import java.io.Serializable

class DetailNavArgument(val projectType: ProjectTypes, val projectID: String?) : Serializable