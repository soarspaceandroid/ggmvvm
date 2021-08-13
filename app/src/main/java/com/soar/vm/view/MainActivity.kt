package com.soar.vm.view

import android.content.Intent
import com.soar.vm.R
import com.soar.vm.base.exception.ContextUseException
import com.soar.vm.base.extend.*
import com.soar.vm.base.model.ActionData
import com.soar.vm.base.model.CallAction
import com.soar.vm.base.model.EventData
import com.soar.vm.base.repository.preference.DataStoreServer
import com.soar.vm.base.view.BaseActivity
import com.soar.vm.model.User
import com.soar.vm.viewmodel.MusicViewModel
import kotlinx.android.synthetic.main.activity_main.*

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
