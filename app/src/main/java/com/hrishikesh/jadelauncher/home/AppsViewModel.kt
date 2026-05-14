package com.hrishikesh.jadelauncher.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hrishikesh.jadelauncher.models.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _apps =
        MutableStateFlow<List<AppInfo>>(emptyList())

    val apps: StateFlow<List<AppInfo>>
        = _apps

    init {
        loadApps()
    }

    private fun loadApps() {

        viewModelScope.launch(Dispatchers.IO) {

            val packageManager =
                getApplication<Application>()
                    .packageManager

            val installedApps =
                packageManager
                    .getInstalledApplications(0)

            val appList = installedApps.map {

                AppInfo(
                    appName = packageManager
                        .getApplicationLabel(it)
                        .toString(),

                    packageName = it.packageName,

                    icon = packageManager
                        .getApplicationIcon(it)
                )

            }.sortedBy {
                it.appName.lowercase()
            }

            _apps.value = appList
        }
    }
}
