package asterixorobelix.afrikaburn.projects.detail

import asterixorobelix.afrikaburn.repository.DataProvider
import io.asterixorobelix.databasemodels.ProjectDetailable

class ProjectDetailRepository(private val dataProvider: DataProvider) {
    fun getProject(
        detailNavArgument: DetailNavArgument,
        onProjectReceived: (ProjectDetailable?) -> Unit
    ) {
        dataProvider.getProject(detailNavArgument, onProjectReceived)
    }
}