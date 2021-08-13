package com.soar.vm.base.extend

import kotlinx.coroutines.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/10/23
 *※ Time : 10:03
 *※ Project : hmapp-android
 *※ Package : com.pajk.healthmanageapp.base.extend
 *----------------------------------------------------
 */

/**
 * 无参数的UI线程执行可取消
 */
fun runThreadUI(body:()->Unit):Job = GlobalScope.launch(Dispatchers.Main) {
        body()
    }


/**
 * 无参数的IO线程执行任务可取消
 */
fun runThreadIO(body:()->Unit): Job =  GlobalScope.launch(Dispatchers.IO) {
        body()
    }


/**
 * 带有返回值的io线程执行
 */
suspend fun <T> runThreadIO(body:()-> T?): T? =  GlobalScope.async(Dispatchers.IO) {
    body()
}.await()

/**
 * 无参数的IO 转 Main可取消
 */
fun <T> runThreadIOToMain(doOnIO: () -> T , doOnMain: (T) -> Unit):Job = GlobalScope.launch {
        var t  = doOnIO()
        withContext(Dispatchers.Main){
            doOnMain(t)
        }
    }


/**
 * 带有参数返回的线程,主线程的操作返回值
 */
suspend fun <T , R> runThreadIOToMain(doOnIO: () -> T , doOnMain: (T) -> R):R = GlobalScope.async(Dispatchers.IO) {
    var t = doOnIO()
    withContext(Dispatchers.Main) {
        doOnMain(t)
    }

}.await()


/**
 * 有参数的IO线程执行任务 可取消
 */
 fun <I,R>  runThreadIO(param:I ,doOnIO: (I)->R):Job = GlobalScope.launch(Dispatchers.IO){
        doOnIO(param)
    }


/**
 * 带有参数的线程转换可取消
 */
fun <I,R> runThreadIOToMain(param:I ,doOnIO: (I)->R , doOnMain: (R) -> Unit):Job = GlobalScope.launch {
        var back = doOnIO(param)
        withContext(Dispatchers.Main){
            doOnMain(back)
        }
}