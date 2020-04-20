package com.itis.template.ui

interface MainView {

    fun showError(throwable: Throwable)

    fun showLoading()

    fun hideLoading()

    fun setResult(main: String)

    fun checkPermissions()

    fun requestPermissions()

    fun showNotAvailablePermissions()

    // region Navigation
    fun navigateToProfile()
    // endregion
}
