package hr.algebra.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity( ) {

    private lateinit var tvDateTime : TextView
    private lateinit var bDateTime  : Button
    private lateinit var myService  : TimeService
    private var mBound = false

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        tvDateTime = findViewById( R.id.tvDateAndTime )
        bDateTime = findViewById( R.id.bDateAndTime )

        bDateTime.setOnClickListener {
            if( mBound )
                tvDateTime.text = myService.getCurrentTime( )
            else
                tvDateTime.text = "Not connected to the service..."
        }

    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected( name: ComponentName?, service: IBinder? ) {
            val binder = service as TimeService.LocalBinder
            myService = binder.getService( )
            mBound    = true
        }

        override fun onServiceDisconnected( name: ComponentName? ) {
            mBound = false
        }
    }


    override fun onStart( ) {
        super.onStart( )
        val intent = Intent( this, TimeService::class.java )
        bindService( intent, connection, Context.BIND_AUTO_CREATE )
    }

    override fun onStop( ) {
        super.onStop( )
        unbindService( connection )
    }
}