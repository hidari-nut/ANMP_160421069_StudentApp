package com.viswa.studentapp.view

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.viswa.studentapp.R
import android.Manifest
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.viswa.studentapp.util.createNotifChannel

class MainActivity : AppCompatActivity() {
    init{
        instance = this
    }
    companion object{
        private var instance:MainActivity ?= null

        fun showNotif(title:String, content:String, icon:Int){
            val channelID = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelID).apply{
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }
            val manager = NotificationManagerCompat.from(instance!!.applicationContext)

            if(ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                    instance!!, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                return
            }
            manager.notify (42069, builder.build())
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1->{
                if(grantResults.isNotEmpty()
                    && grantResults[0]==
                    PackageManager.PERMISSION_GRANTED){
                        Log.d("permission", "granted")
                    createNotifChannel(this,NotificationManagerCompat.IMPORTANCE_DEFAULT, false, "Notification channel for "
                            + "creating new student")
                    }
                else{
                    Log.d("permission", "denied")
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotifChannel(this,NotificationManagerCompat.IMPORTANCE_DEFAULT, false, "Notification channel for "
        + "creating new student")
    }


}