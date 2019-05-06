package com.ramijemli.klarna.presentation.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ramijemli.klarna.R
import com.ramijemli.klarna.presentation.presenter.WeatherPresenter
import com.ramijemli.klarna.presentation.view.IWeatherView
import com.ramijemli.klarna.presentation.viewmodel.Weather
import kotlinx.android.synthetic.main.activity_weather.*
import permissions.dispatcher.*

@RuntimePermissions
class WeatherActivity : AppCompatActivity(), IWeatherView, LocationListener {

    private var presenter: WeatherPresenter? = null
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        presenter = WeatherPresenter(this)
        getLocationWithPermissionCheck()
    }

    override fun onDestroy() {
        presenter?.destroy()
        presenter = null
        super.onDestroy()
    }

    //################################################################################################################  CALLBACKS
    override fun onLocationChanged(location: Location?) {
        presenter?.getWeatherData(location?.latitude!!, location.longitude)
        locationManager.removeUpdates(this)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //This should be treated.
    }

    override fun onProviderEnabled(provider: String?) {
        //This should be treated.
    }

    override fun onProviderDisabled(provider: String?) {
        //This should be treated.
    }

    override fun renderWeather(data: Weather) {
        status.text = "It's ${data.details.summary.toLowerCase()} where you are!"
        Toast.makeText(baseContext, "We have a response!", Toast.LENGTH_LONG).show()
    }

    override fun showError(msg: String) {
        //TOAST AS THIS IS JUST A TECHNICAL TEST
        Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
    }

    override fun showNetworkError() {
        //TOAST AS THIS IS JUST A TECHNICAL TEST
        Toast.makeText(baseContext, "No internet. OMG, it's over!", Toast.LENGTH_LONG).show()
    }

    //################################################################################################################  RUNTIME PERMISSIONS
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, this)
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    fun showRationaleForLocation(request: PermissionRequest) {
        //HARDCODED TEXT BELONGS TO STRINGS FILE
        showRationaleDialog("Be a good boy and grant me location powers", request)
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onLocationDenied() {
        //HARDCODED TEXT BELONGS TO STRINGS FILE
        Toast.makeText(baseContext, "You will pay for this", Toast.LENGTH_LONG).show()
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    fun onLocationNeverAskAgain() {
        //HARDCODED TEXT BELONGS TO STRINGS FILE
        Toast.makeText(baseContext, "Okey, whatever!", Toast.LENGTH_LONG).show()
    }

    private fun showRationaleDialog(message: String, request: PermissionRequest) {
        //HARDCODED TEXT BELONGS TO STRINGS FILE
        AlertDialog.Builder(this@WeatherActivity)
            .setPositiveButton("Yes") { _, _ -> request.proceed() }
            .setNegativeButton("No") { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage(message)
            .show()
    }

}
