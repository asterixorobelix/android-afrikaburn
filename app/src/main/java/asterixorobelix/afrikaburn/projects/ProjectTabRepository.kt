package asterixorobelix.afrikaburn.projects

import io.asterixorobelix.databasemodels.ProjectTypes
import asterixorobelix.afrikaburn.repository.DataProvider
import io.asterixorobelix.databasemodels.TabProjectable

class ProjectTabRepository(private val dataProvider: DataProvider) {

    fun obtainProjectCount(projectType: ProjectTypes, onCountReceived: (List<TabProjectable>) -> Unit) {
        dataProvider.getProjectCount(projectType, onCountReceived)
    }
}