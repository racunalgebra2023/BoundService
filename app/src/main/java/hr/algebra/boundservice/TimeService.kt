package hr.algebra.boundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.google.android.material.tabs.TabLayout.TabGravity
import java.text.SimpleDateFormat
import java.util.Date

class TimeService : Service( ) {

    val TAG = "TimeService"

    // poziva se kad netko kreira servis metodom startService( )
    override fun onStartCommand( intent: Intent?, flags: Int, startId: Int ): Int {
        Log.e( TAG, "onStartCommand called..." )
        return super.onStartCommand(intent, flags, startId)
    }

    private val binder = LocalBinder( )

    override fun onBind( intent: Intent? ): IBinder? {
        return binder
    }

    inner class LocalBinder : Binder( ) {
        fun getService( ) : TimeService = this@TimeService
    }

    fun getCurrentTime( ) : String {
        val dateFormat = SimpleDateFormat( "HH:mm:ss dd.MM.yyyy." )
        return dateFormat.format( Date( ) )
    }

    override fun onDestroy() {
        Log.e( TAG, "Service destroyed..." )
        super.onDestroy()
    }
}