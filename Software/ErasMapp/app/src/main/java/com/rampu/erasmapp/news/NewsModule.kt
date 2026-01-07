package com.rampu.erasmapp.news

import com.rampu.erasmapp.news.data.FirebaseNewsRepository
import com.rampu.erasmapp.news.domain.INewsRepository
import org.koin.dsl.module

val newsModule = module {
    single<INewsRepository> { FirebaseNewsRepository(get(), get()) }

}