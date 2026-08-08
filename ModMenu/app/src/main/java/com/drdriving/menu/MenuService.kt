package com.drdriving.menu

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MenuService : Service() {

    private lateinit var wm: WindowManager
    private lateinit var panel: LinearLayout
    private lateinit var panelParams: WindowManager.LayoutParams

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        wm = getSystemService(WINDOW_SERVICE) as WindowManager
        buildPanel()
        showFloatingButton()
    }

    private fun showFloatingButton() {
        val fab = TextView(this).apply {
            text = "🛠"
            textSize = 22f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#E64A19"))
        }
        val fabParams = WindowManager.LayoutParams(
            120, 120,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 40
            y = 400
        }
        wm.addView(fab, fabParams)

        // إظهار / إخفاء اللوحة عند الضغط على الزر
        var visible = false
        fab.setOnClickListener {
            visible = !visible
            if (visible) wm.addView(panel, panelParams)
            else wm.removeView(panel)
        }
    }

    private fun buildPanel() {
        panelParams = WindowManager.LayoutParams(
            620, WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 170
            y = 350
        }

        panel = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.parseColor("#CC121212"))
            setPadding(48, 48, 48, 48)
        }

        val title = TextView(this).apply {
            text = "🚗 Dr. Driving MOD"
            setTextColor(Color.WHITE)
            textSize = 18f
        }
        panel.addView(title)

        // ⭐ زر الأونلاين — يفتح الخريطة على سيرفر Render
        val onlineBtn = Button(this).apply {
            text = "🗺️ دخول السيرفر أونلاين"
            setOnClickListener {
                startActivity(Intent(this@MenuService, OnlineActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
        panel.addView(onlineBtn)

        // أزرار الهكرز — تُربط بكود اللعبة (smali) في مرحلة لاحقة
        val moneyBtn = Button(this).apply {
            text = "💰 فلوس لا نهائية (قريباً)"
            isEnabled = false
        }
        val carsBtn = Button(this).apply {
            text = "🚗 فتح كل السيارات (قريباً)"
            isEnabled = false
        }
        panel.addView(moneyBtn)
        panel.addView(carsBtn)
    }
}
