package com.employe.domesticcat.zomato

import com.employe.domesticcat.di.ActivityScope
import com.employe.domesticcat.di.ViewModelModule
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
@ActivityScope
interface UserInfoListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserInfoListComponent
    }

    fun inject(activity: ZomatoListActivity)

}