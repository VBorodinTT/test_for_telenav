package test.telenav

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var locListener: LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val locMan = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                Log.w("test", "${location.latitude} ${location.longitude}");
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}
        }
        try {
            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0f, locListener!!)
        } catch (e: SecurityException) {
            Log.e("test", "", e)
        }
    }

    override fun onDestroy() {
        val locMan = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locMan.removeUpdates(locListener!!);

        super.onDestroy()
    }
}