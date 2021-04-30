package com.itis.template.presentation.main

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.itis.template.ApplicationDelegate
import com.itis.template.R
import com.itis.template.data.LocationRepositoryImpl
import com.itis.template.data.WeatherRepositoryImpl
import com.itis.template.data.api.ApiFactory
import com.itis.template.domain.FindCityUseCase
import com.itis.template.presentation.Screens
import com.itis.template.utils.getErrorMessage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = initPresenter()

    private val appNavigator = SupportAppNavigator(this, R.id.container)

    private val appCustomNavigator = object : SupportAppNavigator(this, R.id.container) {

        override fun applyCommand(command: Command?) {
//            hideKeyboard()
            super.applyCommand(command)
        }

        override fun setupFragmentTransaction(
            command: Command,
            currentFragment: Fragment,
            nextFragment: Fragment,
            fragmentTransaction: FragmentTransaction
        ) {
            super.setupFragmentTransaction(
                command,
                currentFragment,
                nextFragment,
                fragmentTransaction
            )
        }

        override fun createStartActivityOptions(
            command: Command?,
            activityIntent: Intent?
        ): Bundle {
            return super.createStartActivityOptions(command, activityIntent)
        }
    }

    private val navigator = object : Navigator {

        override fun applyCommands(commands: Array<out Command>) {
            commands.forEach {
                when (it) {
                    is Forward -> onForward(it.screen)
                    is BackTo -> {
                    }
                    else -> {
                    }
                }
            }
        }

        private fun onForward(screen: Screen) {
            when (screen as SupportAppScreen) {
                is Screens.LoginScreen, is Screens.ProfileScreen -> {
                    screen.getActivityIntent(this@MainActivity)?.let {
                        startActivity(it)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        ApplicationDelegate.INSTANCE.navigatorHolder.setNavigator(appNavigator)
    }

    override fun onPause() {
        ApplicationDelegate.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun checkLocationPermission() {
//        presenter.onLocationAccess()
//        presenter.onLocationDenied()
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun consumerError(throwable: Throwable) {
        Snackbar.make(
            findViewById(android.R.id.content),
            throwable.getErrorMessage(resources),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showWeather(temperature: String) {
        tv_result.text = "Шаhaр Температурасы: $temperature"
        tv_result.visibility = View.VISIBLE
    }

    override fun showUserLocation(location: Location) {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Lon: ${location.longitude}, Lat: ${location.latitude}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun navigateToLogin() {
    }

    private fun initPresenter() = MainPresenter(
        findCityUseCase = ApiFactory.weatherApi.let {
            WeatherRepositoryImpl(it).let {
                FindCityUseCase(it, Dispatchers.IO)
            }
        },
        locationRepository = LocationRepositoryImpl(
            client = LocationServices.getFusedLocationProviderClient(this)
        ),
        router = ApplicationDelegate.INSTANCE.router
    )

    private fun initListeners() {
        tv_hello.setOnClickListener { presenter.onHelloClick() }
        tv_location.setOnClickListener { presenter.onNextClick() }
    }
}
