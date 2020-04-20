package com.itis.template.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView: MvpView {

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
