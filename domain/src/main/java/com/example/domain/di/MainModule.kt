package com.example.domain.di

import com.example.domain.db.GetCachedData
import com.example.domain.db.GetCachedDataImpl
import com.example.domain.db.GetPhotoDetails
import com.example.domain.db.GetPhotoDetailsImpl
import com.example.domain.firstLaunch.CheckFirstLaunch
import com.example.domain.firstLaunch.CheckFirstLaunchImpl
import com.example.domain.firstLaunch.UpdateFirstLaunch
import com.example.domain.firstLaunch.UpdateFirstLaunchImpl
import com.example.domain.photo.GetPhoto
import com.example.domain.photo.GetPhotoImpl
import com.example.domain.photo.LoadPhoto
import com.example.domain.photo.LoadPhotoImpl
import com.example.domain.queryValue.GetQueryValue
import com.example.domain.queryValue.GetQueryValueImpl
import com.example.domain.queryValue.UpdateQueryValue
import com.example.domain.queryValue.UpdateQueryValueImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@[Module InstallIn(ViewModelComponent::class)]
abstract class MainModule {
    @Binds
    abstract fun bindGetPhoto(
        getPhotoImpl: GetPhotoImpl
    ): GetPhoto

    @Binds
    abstract fun bindGetCachedData(
        getCachedDataImpl: GetCachedDataImpl
    ): GetCachedData

    @Binds
    abstract fun bindGetPhotoDetails(
        getPhotoDetailsImpl: GetPhotoDetailsImpl
    ): GetPhotoDetails

    @Binds
    abstract fun bindLoadImages(
        loadImagesImpl: LoadPhotoImpl
    ): LoadPhoto

    @Binds
    abstract fun bindUpdateFirstLaunch(
        updateFirstLaunchImpl: UpdateFirstLaunchImpl
    ): UpdateFirstLaunch

    @Binds
    abstract fun bindCheckFirstLaunch(
        checkFirstLaunchImpl: CheckFirstLaunchImpl
    ): CheckFirstLaunch

    @Binds
    abstract fun bindGetQueryValue(
        getQueryValue: GetQueryValueImpl
    ): GetQueryValue

    @Binds
    abstract fun bindUpdateQueryValue(
        updateQueryValue: UpdateQueryValueImpl
    ): UpdateQueryValue
}
