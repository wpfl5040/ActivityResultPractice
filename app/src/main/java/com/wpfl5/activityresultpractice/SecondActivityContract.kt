package com.wpfl5.activityresultpractice

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class SecondActivityContract : ActivityResultContract<Int, String?>() {
    /**
     * 다른 액티비티를 호출하기 위한 Intent를 생성한다.
     * 제네릭 타입 I가 intent를 생성하기 위한 매개변수 타입으로 전달된다.
     * (startActivityForResult 메서드 호출을 대체한다.)
     */
    override fun createIntent(context: Context, input: Int): Intent {
        return SecondActivity.getIntent(context, input)
    }


    /**
     * 액티비티로 전달받은 결과 데이터를 제너릭 O타입으로 변환한다.
     * (onActivityResult 콜백 메서드 처리를 대체한다.)
     */
    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val data = intent?.getStringExtra(SecondActivity.TEST_RESULT)
        return if (resultCode == Activity.RESULT_OK && data != null) data
                else null
    }

}