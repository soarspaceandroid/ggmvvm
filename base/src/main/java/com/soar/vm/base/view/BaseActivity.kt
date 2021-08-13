package com.soar.vm.base.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.soar.vm.base.BuildConfig
import com.soar.vm.base.R
import com.soar.vm.base.datatype.NetState
import com.soar.vm.base.datatype.CommonData
import com.soar.vm.base.extend.*
import com.soar.vm.base.interfaces.CallBridge
import com.soar.vm.base.interfaces.ErrorInter
import com.soar.vm.base.model.ActionData
import com.soar.vm.base.model.BaseViewModel
import com.soar.vm.base.model.CallAction
import com.soar.vm.base.utils.DisplayUtil
import com.soar.vm.base.widget.StubStatusBar
import kotlinx.android.synthetic.main.layout_base_view.*
import kotlinx.android.synthetic.main.layout_debug_view.*
import kotlinx.android.synthetic.main.layout_error.view.*
import java.lang.Exception


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/21
 *※ Time : 13:52
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.view
 *----------------------------------------------------
 */
abstract class BaseActivity<VM:BaseViewModel>: AppCompatActivity() , View.OnClickListener ,
    CallBridge<ActionData<*>> , ErrorInter{
    /**
     * 获取viewmodel 对象
     */
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_base_view)
        initParentViewOrDate()
        initViewOrData(intent)
    }

    /**
     * base中布局初始化
     */
    private fun initParentViewOrDate(){
        configStatusbar(stubStatusBar)
        //add child
        layoutInflater.inflate(getLayoutId() , childContainer)
        //init debug
        configDebugView()
        //viewmodel实列
        initViewModel()
        //数据注册
        registerObserver()
    }


    /**
     * Baseviewmodel 初始化
     */
    private fun initViewModel(){
        mViewModel = ViewModelProvider(this).get(getViewModelClazz(this))
        mViewModel.callBridge = this
    }

    /**
     * 配置占位statusbar , 如果需要更改请重写
     */
    open fun configStatusbar(stubStatusBar: StubStatusBar){
        stubStatusBar.visible()
        stubStatusBar.setBackgroundColor(Color.GRAY)
    }

    /**
     * debug版本配置debug的view
     */
    private fun configDebugView(){
        if(BuildConfig.DEBUG){
            debugView.visible()
            debugText.layoutParams?.apply {
                val sw= DisplayUtil.getScreenWidth(this@BaseActivity)
                val sh= DisplayUtil.getScreenHeight(this@BaseActivity)
                width = 2 * sw
                height = 2 * sh
                debugText.layoutParams = this
                debugText.translationX = - (sw *2/3).toFloat()

            }
        }
    }


    /**
     * 网络发生了改变
     */
    open fun onNetChange(netState: NetState){
        showToast("网络发生了变化--${netState.name}")
    }

    /**
     * 所有点击事件,后面添加多次点击去重
     */
    override fun onClick(v: View?) {
        //
    }

    /**
     * 必须重写返回子布局ID
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 必须重写初始化
     */
    abstract fun initViewOrData(intent: Intent)


    /**
     * 数据注册 , 子类可重写
     */
    open fun registerObserver(){
        CommonData.INSTANCE.netState.bindUI(this){
            onNetChange(it)
        }

    }

    /**
     * 如需修改物理返回功能请重写
     */
    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_no_anim, R.anim.activity_slide_right_out)
    }





    /**
     * 改变actiondata 的值
     */
    override fun obtain(t: ActionData<*>) {
        when(t.action) {
            CallAction.ACTION_SHOWLOADING -> {
                showLoading()
            }
            CallAction.ACTION_HIDELOADING -> {
                hideLoading()
            }
            CallAction.ACTION_EVENT->{
                //发送过来的消息处理,数据更新类消息当前类处理,UI更新类消息UI处理,都重写onEvent
                t.t?.let {
                    onEvent(it)
                }
            }
            CallAction.ACTION_EXCEPTION -> {
                //各种异常情况,后续分类型
                childContainer.replaceView(
                    getErrorView(
                        this,
                        (t.t as Exception).message ?: "UNKWON EXCEPTION"
                    ) { view ->
                        view.replaceView(childContainer)
                        redo()
                    })
            }
        }
    }


    /**
     * 出现错误页面,点击后重新做的操作
     */
    open fun redo(){

    }

    /**
     * 消息处理重写
     */
    open fun onEvent(t:Any){}

}