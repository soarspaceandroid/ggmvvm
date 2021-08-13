package com.soar.vm.base.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.soar.vm.base.extend.runThreadUI
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 *
 * 此类是从主客挪移
 * 设置状态栏颜色
 *
 */
object PajkStatusBar {
    /**
     *
     * 设置状态栏文字与icon为深色
     *
     * @param window
     */
    fun setStatusBarTextIconThemeWithDark(window: Window?) {
        if (window == null) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } catch (e: Throwable) {
            }
        }
    }

    /**
     *
     * 设置状态栏颜色
     *
     * @param window
     * @param statusBarBackgroundColor
     */
    fun setStatusBarBackgroundColor(
        window: Window?,
        statusBarBackgroundColor: Int
    ) {
        if (window == null) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (checkIsMiuiRom()) {
                setMiuiStatusBarDarkMode(window)
            }
            try { //设置FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS属性才能调用setStatusBarColor方法来设置状态栏颜色
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                //设置FLAG_TRANSLUCENT_STATUS透明状态栏
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                //需要将状态栏设置为透明
                window.statusBarColor = statusBarBackgroundColor
            } catch (e: Throwable) {
            }
        }
    }

    /**
     *
     * 小米手机
     * @param window
     * @return
     */
    private fun setMiuiStatusBarDarkMode(window: Window): Boolean {
        val clazz: Class<out Window> = window.javaClass
        try {
            var darkModeFlag = 0
            val layoutParams =
                Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field =
                layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            //状态栏透明且黑色字体
            extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    private fun checkIsMiuiRom(): Boolean {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"))
    }

    private fun getSystemProperty(propName: String): String? {
        var input: BufferedReader? = null
        val var4: Any?
        try {
            val p = Runtime.getRuntime().exec("getprop $propName")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            val line = input.readLine()
            input.close()
            return line
        } catch (var14: IOException) {
            var4 = null
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (var13: IOException) {
                }
            }
        }
        return var4 as String?
    }

    /**
     * get StatusBar height
     * @param context
     * @return
     */
    fun getStatusbarHeight(context: Context): Int {
        val resourceId =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 沉浸式状态的深色/浅色模式切换
     *
     * @param activity
     * @param isDarkMode
     */
    fun setImmersiveStatusBarDarkMode(
        activity: Activity?,
        isDarkMode: Boolean,
        android5StatusColorResId: Int
    ) {

        runThreadUI{
            setImmersiveStatusBarDarkModeImpl(
                activity,
                isDarkMode,
                android5StatusColorResId
            )
        }
    }

    private fun setImmersiveStatusBarDarkModeImpl(
        activity: Activity?,
        isDarkMode: Boolean,
        android5StatusColorResId: Int
    ) {
        if (activity == null) {
            return
        }
        val window = activity.window ?: return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isDarkMode) {
                    window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
                setStatusBarBackgroundColor(
                    window,
                    Color.TRANSPARENT
                )
            } else { //android 6.0以下的手机不支持深色模式，只能浅色模式
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                setStatusBarBackgroundColor(
                    window,
                    if (android5StatusColorResId == 0) 0x55888888 else activity.resources.getColor(
                        android5StatusColorResId
                    )
                )
            }
        }
    }
}