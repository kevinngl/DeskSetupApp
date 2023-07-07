package com.kevin.desksetupapp.di

import com.kevin.desksetupapp.data.DeskSetupRepository

object Injection {
    fun provideRepository(): DeskSetupRepository {
        return DeskSetupRepository.getInstance()
    }
}
