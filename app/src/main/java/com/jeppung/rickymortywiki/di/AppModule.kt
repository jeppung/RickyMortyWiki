package com.jeppung.rickymortywiki.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jeppung.rickymortywiki.feature_character.data.repository.CharacterRepositoryImpl
import com.jeppung.rickymortywiki.feature_character.domain.repository.CharacterRepository
import com.jeppung.rickymortywiki.feature_character.domain.use_case.CharacterUseCases
import com.jeppung.rickymortywiki.feature_character.domain.use_case.GetCharacter
import com.jeppung.rickymortywiki.feature_character.domain.use_case.GetSingleCharacter
import com.jeppung.rickymortywiki.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(
        @ApplicationContext context: Context
    ): ApiService {
        val chucker = ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

        val client = OkHttpClient.Builder().addInterceptor(
            chucker
        ).build()

        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(apiService: ApiService): CharacterRepository {
        return CharacterRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCharacterUseCases(repository: CharacterRepository): CharacterUseCases {
        return CharacterUseCases(
            getCharacters = GetCharacter(repository),
            getSingleCharacter = GetSingleCharacter(repository)
        )
    }
}