package com.example.autologbook.ui.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.autologbook.ui.add_entry.AddNewEntry

class MyActivityContract : ActivityResultContract<String, Int?>() {
    override fun createIntent(context: Context, input: String): Intent {
        return Intent(context, AddNewEntry::class.java)
            .putExtra("my_input_key", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int? {
        if (resultCode == Activity.RESULT_OK ){
            return Activity.RESULT_OK
        }
        else {
            return null
        }
    }
}