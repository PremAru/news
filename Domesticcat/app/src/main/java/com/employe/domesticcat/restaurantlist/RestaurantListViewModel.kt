package com.employe.domesticcat.zomato

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.employe.domesticcat.base.ReactiveViewModel
import com.employe.domesticcat.model.UserInfo
import com.employe.domesticcat.service.UserInfoService
import com.employe.domesticcat.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ZomatoListViewModel @Inject constructor(private val userInfoService: UserInfoService): ReactiveViewModel() {

    private val users = MutableLiveData<Resource<List<UserInfo>>>()

    fun fetchUserInfoList(){
        users.postValue(Resource.loading(null))
        userInfoService.getUserInfosList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<List<UserInfo>>(){

                override fun onComplete() {
                    Timber.d("User Info list complete block executed")
                }

                override fun onNext(userInfoList: List<UserInfo>) {
                    Timber.i("User Info list success response received")
                    users.postValue(Resource.success(userInfoList))
                }

                override fun onError(e: Throwable?) {
                    Timber.e("User Info list error response received $e")
                    users.postValue(Resource.error("${e?.localizedMessage}", null))
                }
            })
    }

    fun getUsers(): LiveData<Resource<List<UserInfo>>> {
        return users;
    }


}