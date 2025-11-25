package com.example.myim.ui.chat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myim.ui.base.BaseActivity

class GroupProfileActivity : BaseActivity() {

    companion object {

        private const val KEY_GROUP_ID = "keyGroupId"

        fun navTo(context: Context, groupId: String) {
            val intent = Intent(context, GroupProfileActivity::class.java)
            intent.putExtra(KEY_GROUP_ID, groupId)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}