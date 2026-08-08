package com.drdriving.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // إذن الرسم فوق التطبيقات — ضروري للمود مينيو العائم
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "فعّل إذن الرسم فوق التطبيقات ثم ارجع", Toast.LENGTH_LONG).show()
            startActivity(
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            )
            finish()
            return
        }

        startService(Intent(this, MenuService::class.java))
        finish()
    }
}
