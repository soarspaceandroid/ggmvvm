package com.soar.vm.view

import android.content.Context
import android.content.Intent
import com.soar.vm.R
import com.soar.vm.base.model.BaseViewModel
import com.soar.vm.base.view.BaseActivity

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/22
 *※ Time : 15:41
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp
 *----------------------------------------------------
 */
class FragmentTestActivity:BaseActivity<BaseViewModel>() {

    companion object{
        fun startMe(context: Context){
            Intent(context , FragmentTestActivity::class.java).let {
                context.startActivity(it)
            }
        }
    }

    override fun getLayoutId(): Int =
        R.layout.activity_fragment_test

    override fun initViewOrData(intent: Intent) {
        MainFragment.newInstance("12345678").let {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, it).commit()
        }

    }



}