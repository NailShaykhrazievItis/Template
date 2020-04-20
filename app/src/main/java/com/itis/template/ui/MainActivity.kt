package com.itis.template.ui

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.itis.template.App
import com.itis.template.R
import com.itis.template.repository.WeatherRepository
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var repository: WeatherRepository
    lateinit var presenter: MainPresenter

    private var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, repository)
        presenter.fetchWeather()

        initListeners()
    }

    override fun onDestroy() {
        presenter.clearData()
        super.onDestroy()
    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(
            findViewById(android.R.id.content),
            throwable.message ?: "Some error message",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showLoading() {
        progress = ProgressDialog.show(this, "Loading", "Loading")
    }

    override fun hideLoading() {
        progress?.hide()
    }

    override fun setResult(main: String) {
        Snackbar.make(
            findViewById(android.R.id.content), main, Snackbar.LENGTH_INDEFINITE
        ).show()
    }

    override fun checkPermissions() {
        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE).let {
            presenter.onPermissionCheck(it == PackageManager.PERMISSION_GRANTED)
        }
    }

    override fun requestPermissions() {
    }

    override fun showNotAvailablePermissions() {
    }

    override fun navigateToProfile() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun initListeners() {
        btn_hello.setOnClickListener { presenter.onHelloClick() }
    }
}
