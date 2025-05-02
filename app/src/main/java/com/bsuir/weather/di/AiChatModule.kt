package com.bsuir.weather.di

import android.content.Context
import com.bsuir.weather.data.repository.AiChatRepositoryImpl
import com.bsuir.weather.data.source.network.chat.AiChatNetwork
import com.bsuir.weather.domain.repository.AiChatRepository
import com.bsuir.weather.domain.usecase.AskAiChatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AiChatModule {
    @Provides
    @Singleton
    fun provideAiChatNetwork(http: HttpClient, @ApplicationContext context: Context): AiChatNetwork {
        return AiChatNetwork(http, context)
    }

    @Provides
    @Singleton
    fun provideAiChatRepository(aiChatNetwork: AiChatNetwork): AiChatRepository {
        return AiChatRepositoryImpl(aiChatNetwork)
    }

    @Provides
    @Singleton
    fun provideAskAiChatUseCase(repository: AiChatRepository): AskAiChatUseCase {
        return AskAiChatUseCase(repository)
    }
}