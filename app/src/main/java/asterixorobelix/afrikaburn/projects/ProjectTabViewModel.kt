package asterixorobelix.afrikaburn.projects

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import asterixorobelix.afrikaburn.R
import asterixorobelix.afrikaburn.models.ProjectTypes
import asterixorobelix.utilities.base.BaseBusyIndicatorViewModel

class ProjectTabViewModel(
    private val projectTabRepository: ProjectTabRepository,
    private val context: Context
) :
    BaseBusyIndicatorViewModel() {
    var projectType: String? = ""
        set(value) {
            field = value
            field?.let {
                toggleBusy()
                getProjects(ProjectTypes.valueOf(it))
            }
        }
    private val projects: MutableLiveData<List<TabProjectable>> = MutableLiveData()
    val tabProjectables: LiveData<List<TabProjectable>> = projects

    private fun getProjects(projectType: ProjectTypes) {
        projectTabRepository.obtainProjectCount(projectType) {
            projects.postValue(it)
            toggleBusy()
        }
    }

    fun getCountText(): String {
        return "${tabProjectables.value?.count()} ${if (projectType != ProjectTypes.All.name) projectType else context.getString(
            R.string.project
        )}s"
    }
}
