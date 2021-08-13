package com.pajk.healthmanageapp.base.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pajk.healthmanageapp.base.R
import com.pajk.healthmanageapp.base.extend.*
import com.pajk.healthmanageapp.base.interfaces.CallBridge
import com.pajk.healthmanageapp.base.interfaces.ErrorInter
import com.pajk.healthmanageapp.base.model.ActionData
import com.pajk.healthmanageapp.base.model.BaseViewModel
import com.pajk.healthmanageapp.base.model.CallAction
import com.pajk.healthmanageapp.base.widget.StubStatusBar
import kotlinx.android.synthetic.main.layout_base_view.*
import java.lang.Exception

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/22
 *※ Time : 15:28
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.view
 *----------------------------------------------------
 */
abstract class BaseFragment<VM: BaseViewModel>: Fragment(),View.OnClickListener , CallBridge<ActionData<*>> ,
    ErrorInter {

    /**
     * 获取viewmodel 对象
     */
    lateinit var mViewModel: VM

    /**
     * oncreateview 父布局
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_base_view , null).apply {
            layoutInflater.inflate(getLayoutId() ,this.findViewById<FrameLayout>(R.id.childContainer))
            this.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT)
        }
    }

    /**
     * 初始操作
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initParentViewOrDate()
        initViewOrData(arguments)
    }


    /**
     * 父布局初始化
     */
    private fun initParentViewOrDate(){
        configStatusbar(stubStatusBar)
        initViewModel()
    }


    /**
     * Baseviewmodel 初始化
     */
    private fun initViewModel(){
        mViewModel = ViewModelProvider(this).get(getViewModelClazz(this))
        mViewModel.callBridge = this
    }

    /**
     * 如果需要修改statusbar 可重写
     */
    open fun configStatusbar(stubStatusBar: StubStatusBar){
        //fragment中默认隐藏
        stubStatusBar.gone()
        stubStatusBar.setBackgroundColor(Color.GRAY)
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
    abstract fun initViewOrData(arguments: Bundle?)


    /**
     * actiondata值继viewmodel后继续处理
     */
    override fun obtain(t: ActionData<*>) {
        when(t.action){
            CallAction.ACTION_EVENT->{
                //发送过来的消息处理,数据更新类消息当前类处理,UI更新类消息UI处理,都重写onEvent
                t.t?.let {
                    onEvent(it)
                }
            }
            CallAction.ACTION_EXCEPTION ->{
                //异常情况UI反馈
                childContainer.replaceView(getErrorView(requireContext() , (t.t as Exception).message ?: "UNKWON EXCEPTION") {view->
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
    open fun onEvent(t:Any){

    }
}