package com.rampu.erasmapp.channels

import com.rampu.erasmapp.channels.data.FirebaseChannelRepository
import com.rampu.erasmapp.channels.domian.IChannelRepository
import com.rampu.erasmapp.channels.ui.channels.ChannelsViewModel
import com.rampu.erasmapp.channels.ui.questions.QuestionsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val channelsModule = module {
    single<IChannelRepository> { FirebaseChannelRepository(get(), get()) }
    viewModel { ChannelsViewModel(get()) }
    viewModel { (channelId: String, channelTitle: String) -> QuestionsViewModel(channelId, channelTitle, get()) }
}