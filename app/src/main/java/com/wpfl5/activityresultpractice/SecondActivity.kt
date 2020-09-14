package com.wpfl5.activityresultpractice

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    companion object{
        const val TEST_RESULT = "SUCCESS"
        const val ID = "res"
        fun getIntent(context: Context, id: Int): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(ID, id)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_ok.setOnClickListener {
            val intent = Intent().apply {
                putExtra(TEST_RESULT, "Success2")
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}