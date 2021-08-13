项目介绍
1.minsdk , gradle , kotlin版本都和主客一致
2.编译需要android 29的sdk ,获取地址\\HYSBSH-L5252\share\share  , 打开分享的地址拷贝即可
3.项目采用纯kotln编写,基于MVVM模式编写
4.MVVM模式编写参照官方推荐架构 https://developer.android.google.cn/jetpack/docs/guide
5.三方库: LiveData , ViewModel ,BaseRecyclerViewAdapterHelper , Room ,gson 为避免臃肿添加三方库较少
6.项目官方支持库,全面升级androidX
7.module介绍
base:主架构,包含BaseViewModel , BaseRepository,BaseApplication BaseActivity ,BaseFragment,BAseDialog
    BaseActivity:统筹UI变更和view加载 , BaseFragment相同
    BaseViewModel:统筹子viewmodel ,监听的数据以及数据处理类,需要UI的反应到UI,需要获取数据的采用Repository
    BaseRepository:数据来源控制,来源有网络和数据库,封装网络请求来源
    Room:数据库的使用,封装了键值对的存储
bussiness:相关业务层的代码封装,比如网络实现,打点,等一些经常变更的代码
    DiplomacyNetImp网络相关的实现,继承base中的DiplomacyNet
    DiplomacyBusnissImp 业务相关的实现,继承base中的DiplomacyBusniss
# ggmvvm
