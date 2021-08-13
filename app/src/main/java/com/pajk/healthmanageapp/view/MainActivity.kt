package com.pajk.healthmanageapp.view

import android.content.Intent
import android.telecom.Call
import androidx.lifecycle.*
import com.pajk.healthmanageapp.R
import com.pajk.healthmanageapp.base.exception.ContextUseException
import com.pajk.healthmanageapp.base.extend.*
import com.pajk.healthmanageapp.base.model.ActionData
import com.pajk.healthmanageapp.base.model.CallAction
import com.pajk.healthmanageapp.base.model.EventData
import com.pajk.healthmanageapp.base.repository.preference.DataStoreServer
import com.pajk.healthmanageapp.base.repository.preference.KeyValueStore
import com.pajk.healthmanageapp.base.view.BaseActivity
import com.pajk.healthmanageapp.model.User
import com.pajk.healthmanageapp.viewmodel.MusicViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay

class MainActivity : BaseActivity<MusicViewModel>() {

    override fun getLayoutId(): Int =
        R.layout.activity_main

    override fun initViewOrData(intent: Intent) {

        testbind.setOnClickListener{
            mViewModel.testBind.bindUI(this){
                //数据改变做的操作
                testbindView.text = it
            }
        }



        changeTestbind.setOnClickListener{
            mViewModel.testBind.value = "更改后的值 ${System.currentTimeMillis().toString().formatData("yyyy-mm-dd hh:mm:ss:sss")}"
        }

        showloading.setOnClickListener {
            mViewModel.changeActionData(ActionData(CallAction.ACTION_SHOWLOADING , null))
        }

        hideloading.setOnClickListener {
            mViewModel.changeActionData(ActionData(CallAction.ACTION_HIDELOADING , null))
        }

        storeValue.setOnClickListener {
            var user = User("soar" , 18)
            DataStoreServer.INSTANCE.putValue("user" , user)
        }

        getValue.setOnClickListener {
            var user = DataStoreServer.INSTANCE.getValue("user" , User::class.java)
            getValueView.text = "user {${user?.name} , ${user?.age}}"
        }

        testErrorView.setOnClickListener {
            mViewModel.changeActionData(ActionData(CallAction.ACTION_EXCEPTION , ContextUseException("测试Activity exception")))
        }

        threadTest.setOnClickListener {
            runThreadIOToMain(500 ,{
                //子线程操作
                runThreadUI {
                    threadTestView.text = ""
                    threadTestView.append("子线程运行3s ,输入参数$it +20后传递给UI线程")
                }
               Thread.sleep(3000)
                it+20
            },{
                //主线程操作
                threadTestView.append("----主线程运行,结果是 $it")
            })
        }

        toOther.setOnClickListener {
            FragmentTestActivity.startMe(this)
        }


    }


    override fun redo() {
        super.redo()
        showToast("你点击了Activity重试操作")
    }


    override fun onEvent(t: Any) {
        super.onEvent(t)
        //接收后面一个页面发过来的更新
        when(t){
            is EventData<*> ->{
                mViewModel.testBind.value = t.eventT.toString()
            }
        }
    }

}
