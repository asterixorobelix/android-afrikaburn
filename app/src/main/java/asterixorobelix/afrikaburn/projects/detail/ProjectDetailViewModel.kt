package asterixorobelix.afrikaburn.projects.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import asterixorobelix.utilities.base.BaseBusyIndicatorViewModel
import io.asterixorobelix.databasemodels.ProjectDetailable

class ProjectDetailViewModel(private val projectDetailRepository: ProjectDetailRepository) :
    BaseBusyIndicatorViewModel() {
    var detailNavArgument: DetailNavArgument? = null
        set(value) {
            field = value
            value?.let {
                toggleBusy()
                projectDetailRepository.getProject(it) { projectDetail ->
                    onProjectDetailReceived(projectDetail)
                }
            }
        }

    private val projectDetail: MutableLiveData<ProjectDetailable> = MutableLiveData()
    val projectDetails: LiveData<ProjectDetailable>
        get() = projectDetail

    private fun onProjectDetailReceived(projectDetailable: ProjectDetailable?) {
        projectDetailable?.let {
            projectDetail.postValue(it)
            toggleBusy()
        }
    }
}
