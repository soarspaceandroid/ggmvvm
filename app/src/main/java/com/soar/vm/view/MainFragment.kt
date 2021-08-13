package com.soar.vm.view

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.LinearLayoutManager
import com.soar.vm.R
import com.soar.vm.adapters.MusicAdapter
import com.soar.vm.base.datatype.CommonData
import com.soar.vm.base.exception.ContextUseException
import com.soar.vm.base.extend.bindUI
import com.soar.vm.base.extend.hmlog
import com.soar.vm.base.extend.showToast
import com.soar.vm.base.model.ActionData
import com.soar.vm.base.model.CallAction
import com.soar.vm.base.model.EventData
import com.soar.vm.base.view.BaseFragment
import com.soar.vm.model.Music
import com.soar.vm.viewmodel.MusicViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.testErrorView
import java.lang.Exception

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/22
 *※ Time : 15:47
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp
 *----------------------------------------------------
 */
class MainFragment private constructor():BaseFragment<MusicViewModel>() {

    companion object{
        /**
         * 传入参数key
         */
        const val PARAMS = "params"

        fun newInstance(name:String): MainFragment {
            return MainFragment().apply {
                arguments = Bundle()
                arguments?.putString(PARAMS, name)
            }
        }
    }

    /**
     * 重写layout加载
     */
    override fun getLayoutId(): Int =
        R.layout.fragment_main

    /**
     * 初始化view 和数据
     */
    override fun initViewOrData(arguments: Bundle?) {
        if(arguments?.getString(PARAMS) == null){
            fragmenttext.text = "未收到传入的参数"
        }else{
            fragmenttext.text = "传入到当前fragment的参数是 ${arguments?.getString(PARAMS)}"
        }

        testErrorView.setOnClickListener {
            mViewModel.changeActionData(ActionData(CallAction.ACTION_EXCEPTION , ContextUseException("测试Fragment exception")))
        }

        var adapter = MusicAdapter().apply {
            isUseEmpty = true
            setEmptyView(R.layout.layout_empty)
            musicRecyclerView.layoutManager  = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            musicRecyclerView.adapter = this
            setOnItemClickListener { adapter, view, position ->
                this@MainFragment.showToast("你点击了 ${(adapter.getItem(position) as Music.SongDetail).name}")
            }
        }

        //item单个数据监听对象
        var name:MutableLiveData<String> ? = null

        getValueFromWeb.setOnClickListener {
            //先绑定数据
            mViewModel.responseData?.bindUI(this){

                if(it == null){
                    return@bindUI
                }
                hmlog(it.toString())
                adapter?.setList(it?.result?.songs!!)

                //此处为下面的单个数据改变创建livedata
                name = mViewModel.responseData?.let { it1 ->
                    Transformations.map(it1){
                        it?.result?.songs!![1].name
                    }
                } as MutableLiveData<String>
                name?.bindUI(this){str->
                    try{
                        adapter.data[1].name = str
                        adapter.notifyItemChanged(1)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
            //请求网络数据
            mViewModel.loadDataFromWeb()
        }


        updateDataAdapter.setOnClickListener {
            try {
                adapter.data[1].name = "adapter方式更新了此处的name"
                adapter.notifyItemChanged(1)
            }catch (e:Exception){
                e.printStackTrace()
            }

        }


        updateData.setOnClickListener {
            name?.value = "LiveData方式更新了此处的name"
        }

        testEmpty.setOnClickListener {
            //先将已有数据存入数据库
            mViewModel.insertMusic()
            adapter.setList(arrayListOf())
        }

        testEvent.setOnClickListener {
            CommonData.INSTANCE.eventData.value = ActionData(CallAction.ACTION_EVENT ,EventData<String>().apply {
                eventFlag = "test"
                eventT = "这是后面一个页面要求更新的内容"
            })
        }

        testSql.setOnClickListener {
            mViewModel.loadMusicFromDB()
        }


        testDel.setOnClickListener {
            mViewModel.delMusic()
        }

    }



    override fun redo() {
        super.redo()
        showToast("你点击了fragment的错误重试")
    }
}

