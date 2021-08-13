package com.soar.vm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.soar.vm.base.model.BaseViewModel
import com.soar.vm.base.repository.BaseRepository
import com.soar.vm.model.Music
import com.soar.vm.repository.MusicRepository

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/26
 *※ Time : 14:56
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.viewmodel
 *----------------------------------------------------
 */
class MusicViewModel:BaseViewModel() {

    override fun createRepository(): BaseRepository = MusicRepository()

    /**
     * 网络数据
     */
    var responseData: MutableLiveData<Music>? = MutableLiveData()

    /**
     * 测试绑定的数据
     */
    var testBind = MutableLiveData<String>()

    fun loadDataFromWeb(){
        repository.postRequest(true , Music::class.java , HashMap())
    }

    fun loadMusicFromDB(){
        responseData?.value = (repository as MusicRepository)?.loadData()
    }

    fun delMusic(){
        (repository as MusicRepository)?.delDataFromDB(responseData?.value!!)
    }


    fun insertMusic(){
        (repository as MusicRepository)?.insertDataToDB(responseData?.value)
    }


    override fun onRequestSuccess(t: Any) {
        super.onRequestSuccess(t)
        when(t){
            is Music ->{
                responseData?.value = t
            }
        }
    }

}