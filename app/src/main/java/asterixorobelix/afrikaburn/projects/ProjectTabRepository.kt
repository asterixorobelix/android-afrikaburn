package asterixorobelix.afrikaburn.projects

import asterixorobelix.afrikaburn.models.ProjectTypes
import asterixorobelix.afrikaburn.repository.DataProvider

class ProjectTabRepository(private val dataProvider: DataProvider) {

    fun obtainProjectCount(projectType: ProjectTypes, onCountReceived: (List<TabProjectable>) -> Unit) {
        dataProvider.getProjectCount(projectType, onCountReceived)
    }
}