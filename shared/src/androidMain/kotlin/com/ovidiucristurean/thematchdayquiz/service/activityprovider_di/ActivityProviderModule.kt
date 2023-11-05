package com.ovidiucristurean.thematchdayquiz.service.activityprovider_di

import com.ovidiucristurean.thematchdayquiz.service.activityprovider_api.ActivityProvider
import com.ovidiucristurean.thematchdayquiz.service.activityprovider_api.ActivitySetter
import com.ovidiucristurean.thematchdayquiz.service.activityprovider_implementation.ActivityProviderImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val activityProviderModule: Module = module {
    single<ActivityProvider> { get<ActivityProviderImpl>() }
    single<ActivitySetter> { get<ActivityProviderImpl>() }
    single { ActivityProviderImpl() }
}
