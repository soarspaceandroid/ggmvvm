package com.soar.vm.base.extend

import android.app.Service
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.soar.vm.base.exception.ContextUseException
import com.soar.vm.base.view.BaseApplication
import java.lang.reflect.ParameterizedType

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 11:05
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.extend
 *----------------------------------------------------
 */
/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getViewModelClazz(obj: Any): VM  = (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM



/**
 * 在Context 的作用域下获取ViewModel
 */
inline  fun <reified VM:ViewModel , T> T.getAppViewModel(): VM {
    when(this){
        is AppCompatActivity ->{
            return convertApplication().getAppViewModelProvider().get(VM::class.java)
        }
        is Fragment ->{
            return requireContext().convertApplication().getAppViewModelProvider().get(VM::class.java)
        }
        is Service ->{
            return applicationContext.convertApplication().getAppViewModelProvider().get(VM::class.java)
        }
        is View ->{
            return context.convertApplication().getAppViewModelProvider().get(VM::class.java)
        }
        is Context ->{
            return convertApplication().getAppViewModelProvider().get(VM::class.java)
        }
        is BaseApplication ->{
            return getAppViewModelProvider().get(VM::class.java)
        }
        else -> throw ContextUseException("Pls user context class call")
    }
}


fun Context.convertApplication():BaseApplication {
    val back = applicationContext as BaseApplication
    if(back == null){
        throw ContextUseException("Application must extend BaseApplication")
    }else{
        return back
    }
}


/**
 * 创建UI的数据监听
 */
fun <T> LiveData<T>.bindUI(lifecycleOwner: LifecycleOwner, uiAction:(T)->Unit){
    observe(lifecycleOwner , Observer(uiAction))
}