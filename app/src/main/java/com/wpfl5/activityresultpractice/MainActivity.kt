package com.wpfl5.activityresultpractice

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    it.key
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_startActivity.setOnClickListener {
            registerForActivityResult(SecondActivityContract()){ result ->
                if(!result.isNullOrBlank()) toast("Result $result")
                else toast("Not Result")
            }.launch(1)
        }

        btn_requestPermission.setOnClickListener {
            registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
                if(isGranted) toast("퍼미션 허용됨")
                else toast("퍼미션 거부됨")
            }.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }


    }


    fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}



